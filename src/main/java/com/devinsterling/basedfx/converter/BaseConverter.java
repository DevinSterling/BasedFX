package com.devinsterling.basedfx.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BaseConverter {
    public int[] convert(BigInteger value, int targetBase) {
        BigInteger base = BigInteger.valueOf(targetBase);
        List<Integer> list = new ArrayList<>();

        if (value.compareTo(BigInteger.ZERO) == 0) list.add(0);
        else while (value.compareTo(BigInteger.ZERO) > 0) {
            list.addFirst(value.mod(base).intValue());
            value = value.divide(base);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
