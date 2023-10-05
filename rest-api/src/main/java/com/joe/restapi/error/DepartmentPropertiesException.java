package com.joe.restapi.error;

public class DepartmentPropertiesException extends Exception{
    public DepartmentPropertiesException() {
        super();
    }

    public DepartmentPropertiesException(String message) {
        super(message);
    }

    public DepartmentPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentPropertiesException(Throwable cause) {
        super(cause);
    }

    protected DepartmentPropertiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
