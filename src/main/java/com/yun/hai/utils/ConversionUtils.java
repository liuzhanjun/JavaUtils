package com.yun.hai.utils;

import java.text.NumberFormat;
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


    public static boolean isEven(int a) {
        if ((a & 1) == 1) {
            return false;
        }
        return true;
    }


    /**
     * 将data 转为对应字节长度的数
     *
     * @param data
     * @param len
     * @return
     */
    public static byte[] getBytes(int data, int len) {
        // 0   len-1
        //1   len-2
        // 2   len-3
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[len - i - 1] = (byte) ((data & (0xff << (i * 8))) >> (i * 8));
        }
        return bytes;
    }

    /**
     * 将对应字节长度的数转换为int
     * 字节长度是bytes数组的长度
     *
     * @param bytes
     * @return
     */
    public static int getInt(byte[] bytes) {
        int count = 0;
        int len = bytes.length;

        for (int i = bytes.length - 1; i >= 0; i--) {
            count |= ((0xff << ((len - i - 1) * 8)) & bytes[i] << ((len - i - 1) * 8));
        }
        return count;
    }


}
