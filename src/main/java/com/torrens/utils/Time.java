package com.torrens.utils;

public class Time {

    public final static long SECOND = 1000000000l;

    public static long get(){
        return System.nanoTime();
    }
}
