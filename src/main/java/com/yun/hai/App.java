package com.yun.hai;


import beans.Address;
import beans.Human;
import com.yun.hai.In.ComeRequestIn;
import com.yun.hai.effctivetest.TestBuild;
import com.yun.hai.http.CallBack;
import com.yun.hai.http.HttpUtils;
import com.yun.hai.threadtest.A;
import com.yun.hai.utils.ConversionUtils;

/**
 * Hello world!
 */
public class App {

    private static String url;

    public static void main(String[] args) {

        TestBuild.test();////Builder 构造器实现案例
        url = "http://localhost:8080/Myapp1//MyApp";

        request();

        System.out.println(ConversionUtils.isEven(10));

//        A a=new A();
//        a.Test();
    }

    public static void request() {
        Address address = new Address();
        address.setLongitude(4545.44);
        address.setLatitude(45.55);

        System.out.println(Thread.currentThread().getName());

//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
        requet(address);
//            System.out.println(url);

//        }
        sleep(1000);
    }


    private static void requet(ComeRequestIn.RequestModel model) {
        HttpUtils.httpUtils.getInstance();
        CallBack<Human> callBack = new CallBack<Human>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Human result) {
                System.out.println(result.toString()+"==="+Thread.currentThread().getName());

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
