package com.github.rateldesign.exception;

/**
 * bean异常
 * @author stephen
 * @date 2020/10/21 4:23 下午
 */
public class BeanException extends RuntimeException {

    public BeanException() {
    }

    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanException(Throwable cause) {
        super(cause);
    }

    public BeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
