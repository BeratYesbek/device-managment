package com.beratyesbek.oneglobal.exception;

public class OneGlobalException extends RuntimeException {
    public OneGlobalException(String message) {
        super(message);
    }

    public OneGlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public OneGlobalException(Throwable cause) {
        super(cause);
    }

    public OneGlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
