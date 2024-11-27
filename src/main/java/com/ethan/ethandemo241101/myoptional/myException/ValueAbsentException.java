package com.ethan.ethandemo241101.myoptional.myException;

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
