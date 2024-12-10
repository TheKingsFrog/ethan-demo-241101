package com.ethan.businesscore.myoptional.myException;

public class ValueAbsentException extends Throwable {

    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "My Exception";
    }
}
