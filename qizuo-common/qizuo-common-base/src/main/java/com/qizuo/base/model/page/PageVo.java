/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.page;

import lombok.Data;


/**
 * page基本
 */
@Data
public class PageVo {

    public PageVo() {

    }

    public PageVo(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageVo(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * The cur page.当前页
     */
    private int currentPage = 1;

    /**
     * The next page.下一页
     */
    private int nextPage;

    /**
     * The pre page. 上一页
     */
    private int prePage;

    /**
     * The total row. 总条数
     */
    private int totalRow;

    /**
     * The page size.每页条数
     */
    private int pageSize = 10;

    /**
     * The total page.总页数
     */
    private int totalPage;

    /**
     * The start. 开始条数
     */
    private int start;

    /**
     * The buttons.
     */
    private int[] buttons;

    /**
     * 当前页条数
     */
    private int curPageSize;

}
