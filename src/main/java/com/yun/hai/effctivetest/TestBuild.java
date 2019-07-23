package com.yun.hai.effctivetest;

import beans.Address;
import beans.C;
import com.yun.hai.App;
import com.yun.hai.effctivetest.staticbuilder.House;
import com.yun.hai.http.CallBack;
import com.yun.hai.http.HttpUtils;

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

    public void requets3() {


    }

    public void request() {


        Address address = new Address();
        address.setName("2");
        for (int i = 0; i < 300000; i++) {
            System.out.println(i);

            request2(address);
        }

    }

    public void request2(Address address) {
        HttpUtils.httpUtils.getInstance();
        CallBack<C> cCallBack = new CallBack<C>() {
            @Override
            public void before() {

            }

            @Override
            public void success(C result) {
                System.out.println(result.getTip());

            }

            @Override
            public void failure(HttpUtils.NetThrowable error) {

            }

            @Override
            public void finish() {
//            HttpUtils.httpUtils.cleanFunctions();
                HttpUtils.httpUtils.clear();//一定要执行这个，否则Callback不会被回收

            }
        };

        HttpUtils.httpUtils.requestPost(App.url, address, cCallBack);

        cCallBack = null;
    }


}
