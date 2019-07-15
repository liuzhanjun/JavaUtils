package com.yun.hai.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ConversionUtils {


    /**
     * 十进制转换成二进制
     *
     * @param ten
     * @return
     */
    public static String TenToTwo(int ten) {

        int n = ten / 2;
        int y = ten % 2;

        ArrayList<Integer> count = new ArrayList<Integer>();
        count.add(y);
        while (n > 1) {
            y = n % 2;
            n = n / 2;
            count.add(y);
        }
        if (n == 1) {
            count.add(n);
        }

        Collections.reverse(count);
        StringBuilder builder = new StringBuilder();
        for (Integer integer : count) {
            builder.append(integer);
        }
        return builder.toString();
    }


    /**
     * 二进制转换位十进制
     *
     * @param two 二进制符号
     * @return
     */
    public static int TwoToTen(int two) {
        ArrayList<Integer> count = new ArrayList<Integer>();
        int n = two / 10;
        int y = two % 10;
        count.add(y);
        while (n > 1) {
            y = n % 10;
            n /= 10;
            if (y > 1) {
                throw new RuntimeException("参数 two 应该是一个二进制数");

            }
            count.add(y);
        }

        if (y == 1) {
            count.add(y);
        }

        double nub = 0;
        for (int i = 0; i < count.size(); i++) {
            nub += count.get(i) * (Math.pow(2, i));
        }
        return (int) nub;
    }
}
