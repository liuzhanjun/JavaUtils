package com.yun.hai;


import beans.Address;
import beans.Human;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yun.hai.http.CallBack;
import com.yun.hai.http.HttpUtils;
import io.reactivex.*;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jdk.nashorn.internal.parser.TokenType;
import okhttp3.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args )
    {

        HttpUtils.httpUtils.getInstance();

        Address address=new Address();
        address.setLongitude(4545.44);
        address.setLatitude(45.55);
        HttpUtils.httpUtils.requestPost("http://localhost:8080/Myapp1//MyApp", address, new CallBack<Human>() {
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

            }
        });

//        sleep(10000);
    }

    private static void sleep(int millis) {
        Thread mainThread = Thread.currentThread();
        try {
            mainThread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    public static class MyOnSubscribe implements ObservableOnSubscribe<String>{

        @Override
        public void subscribe(ObservableEmitter<String> obse) throws Exception {
            obse.onNext("你好");
            System.out.println("===============开始");
            
            System.out.println(Thread.currentThread().getId());
            
        }
    }
}
