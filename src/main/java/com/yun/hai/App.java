package com.yun.hai;


import beans.Address;
import beans.B;
import beans.C;
import beans.Human;
import com.yun.hai.In.ComeRequestIn;
import com.yun.hai.effctivetest.TestBuild;
import com.yun.hai.http.CallBack;
import com.yun.hai.http.HttpUtils;
import com.yun.hai.threadtest.A;
import com.yun.hai.utils.ConversionUtils;

import java.lang.ref.WeakReference;

/**
 * Hello world!
 */
public class App {

    public static String url;

    public static void main(String[] args) {

        TestBuild.test();////Builder 构造器实现案例
        url = "http://localhost:8080/Myapp1//MyApp";


        TestBuild build=new TestBuild();
        build.request();


//        request();
        while (true) {

        }


//        System.out.println(ConversionUtils.isEven(10));

//        A a=new A();
//        a.Test();
    }


    public static void request() {
        Address address = new Address();


        System.out.println(Thread.currentThread().getName());

//        for (int i = 0; i < 10; i++) {
//            address.setName(String.format("%d", i));
        requet(address);
//            System.out.println(url);

//        }
        sleep(1000);
        address.setName("1");
        HttpUtils.httpUtils.requestPost(url, address, new CallBack<B>() {
            @Override
            public void before() {

            }

            @Override
            public void success(B result) {
                System.out.println(result.getName());

            }

            @Override
            public void failure(HttpUtils.NetThrowable error) {

            }

            @Override
            public void finish() {

            }
        });


        Address address1 = new Address();
        address1.setName("2");

        HttpUtils.httpUtils.requestPost(url, address1, new CallBack<C>() {
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

            }
        });
    }


    private static void requet(ComeRequestIn.RequestModel model) {
        HttpUtils.httpUtils.getInstance();
        CallBack<Human> callBack = new CallBack<Human>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Human result) {
                System.out.println(result.toString() + "===" + Thread.currentThread().getName());

            }

            @Override
            public void failure(HttpUtils.NetThrowable error) {

            }

            @Override
            public void finish() {
                HttpUtils.httpUtils.clear();

            }
        };
        HttpUtils.httpUtils.requestPost(url, model, callBack);
    }

    private static void sleep(int millis) {
        Thread mainThread = Thread.currentThread();
        try {
            mainThread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
