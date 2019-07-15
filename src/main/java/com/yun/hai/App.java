package com.yun.hai;


import beans.Address;
import beans.Human;
import com.yun.hai.In.ComeRequestIn;
import com.yun.hai.effctivetest.TestBuild;
import com.yun.hai.http.CallBack;
import com.yun.hai.http.HttpUtils;

/**
 * Hello world!
 */
public class App {

    private static String url;

    public static void main(String[] args) {

        TestBuild.test();////Builder 构造器实现案例
        url = "http://localhost:8080/Myapp1//MyApp";

        request();
    }

    public static void request() {
        Address address = new Address();
        address.setLongitude(4545.44);
        address.setLatitude(45.55);


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            requet(address);
//            System.out.println(url);

        }
    }


    private static void requet(ComeRequestIn.RequestModel model) {
        HttpUtils.httpUtils.getInstance();
        HttpUtils.httpUtils.requestPost(url, model, new CallBack<Human>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Human result) {
                System.out.println(result.toString());

            }

            @Override
            public void failure(HttpUtils.NetThrowable error) {

            }

            @Override
            public void finish() {
                HttpUtils.httpUtils.clear();
            }
        });
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
