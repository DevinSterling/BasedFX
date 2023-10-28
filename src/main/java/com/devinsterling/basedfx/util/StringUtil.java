package com.devinsterling.basedfx.util;

public class StringUtil {
    public static boolean isNumeric(String s) {
        return s != null && !s.isEmpty() && s.chars()
                .skip(s.charAt(0) == '-' && s.length() > 1 ? 1 : 0)
                .allMatch(Character::isDigit);
    }
}
