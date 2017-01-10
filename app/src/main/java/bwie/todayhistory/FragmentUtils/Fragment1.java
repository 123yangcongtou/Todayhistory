package bwie.todayhistory.FragmentUtils;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import bwie.todayhistory.AdapterUtils.ItemAdapter;
import bwie.todayhistory.BaseUtils.BaseFragment;
import bwie.todayhistory.BeanUtils.Bean_list;
import bwie.todayhistory.BeanUtils.MessageEvent;
import bwie.todayhistory.MainUtils.ContentActivity;
import bwie.todayhistory.MainUtils.DayActivity;
import bwie.todayhistory.OkHttpUtils.OkHttp;
import bwie.todayhistory.R;
import de.greenrobot.event.EventBus;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 * http://api.juheapi.com/japi/toh?key=ae0fed899afa35cd405f970383f5984e&v=1.0&month=11&day=1
 */

public class Fragment1 extends BaseFragment implements View.OnClickListener{

    private TextView day;
    private ImageView left;
    private ImageView right;
    private RecyclerView rv;
    private String url2;
    private String path;
    private int date;
    private int year;
    private int month;
    private Calendar c;
    private ItemAdapter adapter;
    private FloatingActionButton floating;
    private ArrayList<Bean_list.ResultBean> list=new ArrayList();
    private String url = "http://api.juheapi.com/japi/toh?v=1.0&key=ae0fed899afa35cd405f970383f5984e";
    private Callback mCallBack=new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String result = response.body().string();
            Gson gson=new Gson();
            Bean_list bean = gson.fromJson(result, Bean_list.class);
            List<Bean_list.ResultBean> result1 = bean.result;
            list.clear();
            list.addAll(result1);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    };

    @Override
    public View initView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        return View.inflate(getActivity(),R.layout.fragment1,null);
    }

    @Override
    protected void initFindViewbyId(View view) {
        day = (TextView) view.findViewById(R.id.day);
        left = (ImageView) view.findViewById(R.id.left);
        right = (ImageView) view.findViewById(R.id.right);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        floating = (FloatingActionButton) view.findViewById(R.id.floating);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        floating.setOnClickListener(new Listener());

        //获得当天日期
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        date = c.get(Calendar.DATE);

        //点击事件
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        adapter = new ItemAdapter(getActivity(),list);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContenListener());
    }

    @Override
    protected void initData() {
        day.setText(year +"年"+ month +"月"+ date +"号");
        path = url+"&month="+ month +"&day="+ date;
        OkHttp.getAnsy(path, mCallBack);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(MessageEvent message){
        month= Integer.parseInt(message.m_month);
        date=Integer.parseInt(message.m_date);
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left:
                //把日期往前减少一天.整数往后推,负数往前移动
                c.add(c.DATE,-1);
                month=c.get(Calendar.MONTH)+1;
                date=c.get(Calendar.DAY_OF_MONTH);
                day.setText(year +"年"+ month +"月"+ date +"号");
                initData();
                break;
            case R.id.right:
                //把日期往后增加一天.整数往后推,负数往前移动
                c.add(c.DATE,1);
                month=c.get(Calendar.MONTH)+1;
                date=c.get(Calendar.DAY_OF_MONTH);
                day.setText(year +"年"+ month +"月"+ date +"号");
                initData();
                break;
        }
    }

    class  Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            startActivitys(DayActivity.class);
        }
    }

    private class ContenListener implements ItemAdapter.MyItemClickListener {
        @Override
        public void onItemClick(View view, int postion) {
            Intent in=new Intent(getActivity(),ContentActivity.class);
            in.putExtra("id",list.get(postion)._id);
            in.putExtra("titles",list.get(postion).title);
            startActivity(in);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
