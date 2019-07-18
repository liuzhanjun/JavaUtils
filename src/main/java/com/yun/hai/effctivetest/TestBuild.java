package com.yun.hai.effctivetest;

import com.yun.hai.App;
import com.yun.hai.effctivetest.staticbuilder.House;

public class TestBuild {


    public static void test() {


        House house = new House.Builder()
                .buildBasis("1")
                .buildLine("2")
                .buildTop("3")
                .buildFence("4")
                .build();


        System.out.println(house.toString());

    }


    public static void request(){


        for (int i = 0; i < 25; i++) {
            App.request();

        }


    }


}
