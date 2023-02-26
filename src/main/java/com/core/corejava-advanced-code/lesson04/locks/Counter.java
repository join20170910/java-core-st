package com.core.corejava;

public class Counter {
    private int value;
    public synchronized int increment() {
        value++;
        return value;
    }
}