package com.devinsterling.basedfx.util;

public class StringUtil {
    public static boolean isNumeric(String s) {
        return s != null && !s.isEmpty() && s.chars().allMatch(Character::isDigit);
    }
}
