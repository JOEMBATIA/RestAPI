package com.joe.restapi.error;

public class DepartmentExistsException extends Exception{
    public DepartmentExistsException() {
        super();
    }

    public DepartmentExistsException(String message) {
        super(message);
    }

    public DepartmentExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentExistsException(Throwable cause) {
        super(cause);
    }

    protected DepartmentExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
