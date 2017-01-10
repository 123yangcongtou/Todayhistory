package bwie.todayhistory.OkHttpUtils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 */
public class OkHttp{
    //单例模式 由于okhttp3不建议创建多个对象，所以采用饿汉式的单例模式
    private static  OkHttpClient client;
    //构造器私有化
    private OkHttp(){};
    //方法名getInstance
    public static OkHttpClient getInstance(){
        if(client==null){
            synchronized (OkHttp.class){
                if(client==null){
                    client=new OkHttpClient();
                }
            }
        }
        return client;
    }
    public static void getAnsy(String url, Callback callback) {
        Request request=new Request.Builder()
                .url(url)
                .build();
        getInstance().newCall(request)
                .enqueue(callback);
    }
}
