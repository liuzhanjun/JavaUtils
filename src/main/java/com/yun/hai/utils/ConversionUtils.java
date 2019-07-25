package com.yun.hai.utils;

import java.util.ArrayList;
import java.util.Collections;

public class ConversionUtils {


    /**
     * 十进制转换成二进制字符串
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


    /**
     * 判断整数是否是偶数
     *
     * @param a
     * @return
     */
    public static boolean isEven(int a) {
        if ((a & 1) == 1) {
            return false;
        }
        return true;
    }


    /**
     * 10进制转二进制字节数组
     * 将data 转为对应字节长度的数
     * 与getInt互转
     * 如果data的值超过了字节长度所能表示的长度就会重新从0开始
     * 比如1个字节能表示的最大数为255，则256则会表示为0
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
     * 将对应二进制字节长度的数转换为10进制
     * 字节长度是bytes数组的长度
     * 与getBytes互转
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


    /**
     * 10进制转16进制
     *
     * @param n
     * @return
     */
    private static String intToHex(int n) {
        //StringBuffer s = new StringBuffer();
        StringBuilder sb = new StringBuilder(8);
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            sb = sb.append(b[n % 16]);
            n = n / 16;
        }
        a = sb.reverse().toString();
        return a;
    }


    /**
     * 获得imei ，其功能也是将16进制字符串转为二进制字节数组
     *
     * @param ox
     * @return
     */
    public static byte[] getoXBinary(String ox) {
        int len = ox.length();
        int[] IMEIS = new int[(len / 2) + len % 2];
        int len2 = IMEIS.length;
        System.out.println(IMEIS.length);
        //将IMEIS中的元素看作16进制转位10进制
        for (int i = len2 - 1; i >= 0; i--) {
            IMEIS[i] = Integer.parseInt(ox.substring(len - 2 <= 0 ? 0 : len - 2, len), 16);
            len -= 2;
        }


        byte[] result = new byte[len2];

        for (int i = 0; i < IMEIS.length; i++) {
            result[i] = getBytes(IMEIS[i], 1)[0];
        }


        return result;
    }


    /**
     * @param bytes 获得一个字节的表示的整数
     * @return
     */
    public static int getIntByByte(byte bytes) {
        return bytes & 0xff;
    }


}
