package com.beratyesbek.global.exception;

public class GlobalCustomException extends RuntimeException {
    public GlobalCustomException(String message) {
        super(message);
    }

    public GlobalCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalCustomException(Throwable cause) {
        super(cause);
    }

    public GlobalCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
