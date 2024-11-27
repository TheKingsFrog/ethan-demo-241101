package com.ethan.ethandemo241101.myoptional;

import lombok.Data;

import java.util.NoSuchElementException;
import java.util.Objects;

@Data
public class MyOptional<T> {

    private static final MyOptional<?> EMPTY = new MyOptional<>();

    private final T value;

    public MyOptional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public MyOptional() {
        this.value = null;
    }

    public static <T> MyOptional<T> of(T t) {
        return new MyOptional<>(t);
    }

    public static <T> MyOptional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public static <T> MyOptional<T> empty() {
        return (MyOptional<T>) EMPTY;
    }

    public Boolean isPresent() {
        return this.value != null;
    }

    public T get() {

        if(this.value == null) {
            throw new NoSuchElementException("No value present --HCL");
        }

        return this.value;
    }



}
