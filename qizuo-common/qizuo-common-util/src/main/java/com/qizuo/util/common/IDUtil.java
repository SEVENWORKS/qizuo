/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.util.common;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: fangl
 * @description: 主键生成器
 * @date: 16:37 2019/1/10
 */
public class IDUtil {
    private static final long ONE_STEP = 10;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private static int count = 0;

    /**
     * @author: fangl
     * @description: 生成唯一主键
     * @date: 16:37 2019/1/10
     */
    public static String nextId() {
        LOCK.lock();
        try {
            if (lastCount == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (java.lang.InterruptedException e) {
                        }
                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }
            count = lastCount++;
        } finally {
            LOCK.unlock();
            return lastTime + "" + String.format("%03d", count);
        }
    }
}
