package bwie.todayhistory.MainUtils;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import bwie.todayhistory.BaseUtils.BaseActivity;
import bwie.todayhistory.R;

public class WelcomeActivity extends BaseActivity{
    private int i=3;
    private Timer time;

    @Override
    public int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    hand.sendEmptyMessage(0);
                }
            },1000,1000);
    }

    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i--;
            if(i==2){
                startActivitys(MainActivity.class);
                finish();
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        time.cancel();;
        hand.removeCallbacksAndMessages(null);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void loadData() {

    }
}
