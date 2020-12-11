package com.newlag.poster.utils;

public class DateUtil {

    public static String getDate(Long time) {
        Long difference = time / (100 * 60 * 24);
        return String.valueOf(difference);
    }
}
