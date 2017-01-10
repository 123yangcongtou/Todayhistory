package bwie.todayhistory.MainUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import bwie.todayhistory.BaseUtils.BaseActivity;
import bwie.todayhistory.FragmentUtils.Fragment1;
import bwie.todayhistory.FragmentUtils.Fragment2;
import bwie.todayhistory.FragmentUtils.Fragment3;
import bwie.todayhistory.FragmentUtils.Fragment4;
import bwie.todayhistory.R;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navagation;
    //创建一个Drawerlayout和Toolbar联动的开关
    private ActionBarDrawerToggle toggle;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //实例化控件
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        navagation = (NavigationView) findViewById(R.id.main_navigation);
    }

    @Override
    public void initData() {
        toolbar.setTitle("历史上的今天");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.weather1005);
        replaceFragment(R.id.framelayout,new Fragment1());
        //设置MenuItem默认选中项
        navagation.getMenu().getItem(0).setChecked(true);
        //设置Drawerlayout开关
        setDrawerToggle();
         //设置监听器
        setListener();
    }
    //设置Drawerlayout的开关,并且和Home图标联动
    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        //同步drawerlayout的状态
        toggle.syncState();
    }
    //设置监听器
    private void setListener() {
        navagation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        replaceFragment(R.id.framelayout,new Fragment1());
                        toolbar.setTitle("历史上的今天");
                        break;
                    case R.id.two:
                        replaceFragment(R.id.framelayout,new Fragment2());
                            toolbar.setTitle("妹纸");
                        break;
                    case R.id.three:
                        replaceFragment(R.id.framelayout,new Fragment3());
                        toolbar.setTitle("我的收藏");
                        break;
                    case R.id.four:
                        replaceFragment(R.id.framelayout,new Fragment4());
                        toolbar.setTitle("设置");
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            final AlertDialog dialog =  builder.create();
            View v = View.inflate(this, R.layout.dialog, null);
            dialog.setView(v);
            dialog.show();
            Button sure=(Button) v.findViewById(R.id.sure);
            Button diss=(Button) v.findViewById(R.id.diss);
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                }
            });
            diss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
