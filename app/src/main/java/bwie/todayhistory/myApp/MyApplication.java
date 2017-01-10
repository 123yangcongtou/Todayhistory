package bwie.todayhistory.myApp;

import android.app.Application;

import org.litepal.LitePal;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/9.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
