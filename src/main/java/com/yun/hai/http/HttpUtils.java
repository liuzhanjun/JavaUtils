package com.yun.hai.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yun.hai.In.ComeRequestIn;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum HttpUtils {
    httpUtils {
        @Override
        public HttpUtils getInstance() {
            return this;
        }
    };

    private Observable<String> observable;
    private String form = "form";
    private String json = "json";

    public abstract HttpUtils getInstance();


    String TAG = null;

    private HttpUtils() {

    }


    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public String getTAG() {
        return TAG;
    }

    CompositeDisposable disposables = new CompositeDisposable();
    private static String PROTOCOL_CHARSET = "UTF-8";
    private static String PROTOCOL_CONTENT_TYPE_JSON = String.format(
            "application/json; charset=%s", new Object[]{PROTOCOL_CHARSET});

    private static String PROTOCOL_CONTENT_TYPE_FORM = String.format(
            "application/x-www-form-urlencoded; charset=%s",
            new Object[]{PROTOCOL_CHARSET});
    private Gson mGson = new Gson();


    private RequetstPostSub rps = new RequetstPostSub();

    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    OkHttpClient client = builder.build();

    DisposableObserver v;
//    private Functions functions = new Functions();

    /**
     * @param url
     * @param model
     * @param <T>
     */
    public <T> void requestPost(String url, ComeRequestIn.RequestModel model, CallBack<T> disCallBack) {

        final Type type = disCallBack.getmType();
        rps.setRequestModel(model);
        rps.setUrl(url);
        observable = Observable.create(rps);
        Functions functions = new Functions<T>();
        functions.setType(type);
        functions.setGson(mGson);
        v = (DisposableObserver) observable.map(functions)
                .subscribeWith(disCallBack);
        disposables.add(v);



    }


    private class Functions<S> implements io.reactivex.functions.Function<String, S> {
        private Type type;
        private Gson mGson;

        public void setType(Type type) {
            this.type = type;
        }

        public void setGson(Gson gson) {
            this.mGson = gson;
        }

        @Override
        public S apply(String s) throws Throwable {
            if (null == s) {
                return null;
            }
            if (String.class.toString().equals(
                    type.toString())) {
                return (S) s;
            }
            S result = mGson.fromJson(s, type);
            return result;
        }
    }

    public class RequetstPostSub implements ObservableOnSubscribe<String> {
        //        url = "http://localhost:8080/Myapp1//MyApp";
        private ComeRequestIn.RequestModel requestModel;
        private String url;

        public void setRequestModel(ComeRequestIn.RequestModel requestModel) {
            this.requestModel = requestModel;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
            RequestBody body = RequestBody.create(getMediaType("form"),
                    getBody(requestModel));

//            builder.addInterceptor()添加拦截器
//            builder.connectTimeout()设置连接超时
            Request request = new Request.Builder()
//                    .addHeader()//添加请求头
                    .url(url)
                    .post(body)
                    .build();
            try {

                Response response = client.newCall(request).execute();
                observableEmitter.onNext(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                observableEmitter.onError(e);
            } finally {
                observableEmitter.onComplete();
            }
        }
    }


    /**
     * 清空注册
     */
    public void clear() {
        disposables.clear();
        observable = null;
        v = null;
    }

    /**
     * @param var 取值 form json
     * @return
     */
    private MediaType getMediaType(String var) {
        if (form.equals(var)) {
            return MediaType.parse(PROTOCOL_CONTENT_TYPE_FORM);
        } else {
            if (json.equals(var)) {
                return MediaType.parse(PROTOCOL_CONTENT_TYPE_JSON);
            } else {
                return MediaType.parse(PROTOCOL_CONTENT_TYPE_FORM);
            }
        }

    }


    private byte[] getBody(Object obj) {
        if (obj != null) {
            Map<String, String> params = jsonToMap(obj);
            if (params != null && params.size() > 0) {
                return encodeParameters(params, PROTOCOL_CHARSET);
            }
            params.clear();
            params = null;
        }
        return null;
    }


    private byte[] encodeParameters(Map<String, String> params,
                                    String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(),
                        paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(),
                        paramsEncoding));
                encodedParams.append('&');
            }
            encodedParams.delete(encodedParams.length() - 1, encodedParams.length());

            byte[] btype = encodedParams.toString().getBytes(paramsEncoding);
            encodedParams = null;
            return btype;
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: "
                    + paramsEncoding, uee);
        }
    }

    /**
     * 将json to map
     *
     * @param obj
     * @return
     */
    private HashMap<String, String> jsonToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        HashMap<String, String> map = null;
        JsonElement jsonTree = mGson.toJsonTree(obj);
        JsonObject asJsonObject = jsonTree.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = asJsonObject.entrySet();
        if (entrySet.size() > 0) {
            map = new HashMap<String, String>();
        }

        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue().getAsString();
            if (null != map) {
                map.put(key, value);
            }
        }
        return map;
    }

    public class NetThrowable extends Throwable {
        private int status;
        private String RequestString;

        public int getStatus() {
            return status;
        }

        public NetThrowable(String message) {
            super(message);
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRequestString() {
            return RequestString;
        }

        public void setRequestString(String requestString) {
            RequestString = requestString;
        }
    }
}
