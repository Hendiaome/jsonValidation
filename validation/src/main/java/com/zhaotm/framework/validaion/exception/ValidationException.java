package com.zhaotm.framework.validaion.exception;

/**
 * @author zhaotianming1
 * @date 2018/3/30
 */
public class ValidationException extends Exception {
    private static final long serialVersionUID = -8817043486453546021L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
