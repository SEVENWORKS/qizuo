/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.exception;

/**
 * 项目基本exception.
 */
public class QizuoException extends RuntimeException {
	private static final long serialVersionUID = -318154770875589045L;

	public QizuoException(String message) {
		super(message);
	}
}
