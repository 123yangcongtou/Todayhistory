package bwie.todayhistory.MainUtils;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bwie.todayhistory.AdapterUtils.ContentAdapter;
import bwie.todayhistory.BaseUtils.BaseActivity;
import bwie.todayhistory.BeanUtils.Bean_content;
import bwie.todayhistory.OkHttpUtils.OkHttp;
import bwie.todayhistory.R;
import bwie.todayhistory.db.History;
import bwie.todayhistory.db.MySQLiteHelper;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 请求地址：http://api.juheapi.com/japi/tohdet
 请求参数：http://api.juheapi.com/japi/tohdet?v=1.0&id=17321207&key=ae0fed899afa35cd405f970383f5984e
 请求方式：GET
 */
public class ContentActivity extends BaseActivity {

    private boolean ischeck;
    private Toolbar toolbar;
    private RecyclerView recycleview;
    private String titles;
    private ImageView pic;
    private SQLiteDatabase db;
    private ContentAdapter adapter;
    private FloatingActionButton coll;
    private CollapsingToolbarLayout tool;


    private ArrayList<Bean_content.ResultBean> list=new ArrayList();
    private String url = "http://api.juheapi.com/japi/tohdet?v=1.0&key=ae0fed899afa35cd405f970383f5984e&id=";
    private Callback mCallBack=new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String result = response.body().string();
            Gson gson=new Gson();
            Bean_content bean_content = gson.fromJson(result, Bean_content.class);
            List<Bean_content.ResultBean> result1 = bean_content.result;
            list.addAll(result1);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tool.setTitle(list.get(0).title);
                    tool.setExpandedTitleColor(Color.WHITE);
                    tool.setCollapsedTitleTextColor(Color.WHITE);
                    Glide.with(ContentActivity.this)
                            .load(list.get(0).pic)
                            .into(pic);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    };
    private List<History> all;

    @Override
    public int bindLayout() {
        return R.layout.activity_content;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        pic = (ImageView) findViewById(R.id.pic);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coll = (FloatingActionButton) findViewById(R.id.collection);
        recycleview = (RecyclerView) findViewById(R.id.recyclerView);
        tool = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new MyNavigationListener());
        coll.setOnClickListener(new MyColl());
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContentAdapter(ContentActivity.this,list);
        recycleview.setAdapter(adapter);
    }

    @Override
    public void initData() {
        Intent in=getIntent();
        titles = in.getStringExtra("titles");
        if(in.getStringExtra("id")!=null){
            OkHttp.getAnsy(url+ in.getStringExtra("id"),mCallBack);
        }else if(in.getStringExtra("db_mId")!=null){
            ischeck = ischeck == true ? false : true;
            coll.setSelected(true);
            OkHttp.getAnsy(url+ in.getStringExtra("db_mId"),mCallBack);
        }
    }

    @Override
    public void loadData() {
        SQLiteOpenHelper helper=new MySQLiteHelper(this,"demo.db",null,1);
        db = helper.getWritableDatabase();
        all = DataSupport.findAll(History.class);
        for (int i = 0; i< all.size(); i++){
            if(all.get(i).getDb_title().equals(titles)){
                ischeck = ischeck == true ? false : true;
                coll.setSelected(true);
            }
        }
    }

    private class MyColl implements OnClickListener {
        @Override
        public void onClick(View v) {
            ischeck = ischeck == true ? false : true;
            v.setSelected(ischeck);
            if(ischeck){
                History hh=new History();
                hh.setDb_mId(list.get(0)._id);
                hh.setDb_id(list.get(0)._id);
                hh.setDb_day(list.get(0).year+"年"+list.get(0).month+"月"+list.get(0).day+"日");
                hh.setDb_title(list.get(0).title);
                hh.save();
                Toast.makeText(ContentActivity.this,"已收藏" , Toast.LENGTH_SHORT).show();
            }else{
                DataSupport.deleteAll(History.class,"db_title=?",list.get(0).title);
                Toast.makeText(ContentActivity.this, "已取消", Toast.LENGTH_SHORT).show();
            }

        }
    }

    class MyNavigationListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
