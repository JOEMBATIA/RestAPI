package com.joe.restapi.error;

public class DepartmentNonExistentException extends Exception{
    public DepartmentNonExistentException() {
        super();
    }

    public DepartmentNonExistentException(String message) {
        super(message);
    }

    public DepartmentNonExistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNonExistentException(Throwable cause) {
        super(cause);
    }

    protected DepartmentNonExistentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
