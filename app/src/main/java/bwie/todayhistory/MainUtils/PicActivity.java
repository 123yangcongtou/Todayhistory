package bwie.todayhistory.MainUtils;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bwie.todayhistory.BaseUtils.BaseActivity;
import bwie.todayhistory.R;
import uk.co.senab.photoview.PhotoView;

public class PicActivity extends BaseActivity {

    private PhotoView pic;
    private String mUrl;

    @Override
    public int bindLayout() {
        return R.layout.activity_pic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        pic = (PhotoView) findViewById(R.id.photoview);
        Intent in=getIntent();
        mUrl = in.getStringExtra("path");
    }

    @Override
    public void initData() {
        Glide.with(PicActivity.this)
                .load(mUrl)
                .into(pic);
        //支持缩放
        pic.setEnabled(true);
    }

    @Override
    public void loadData() {

    }
}
