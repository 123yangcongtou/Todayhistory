package bwie.todayhistory.FragmentUtils;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import bwie.todayhistory.AdapterUtils.CardAdapter;
import bwie.todayhistory.BaseUtils.BaseFragment;
import bwie.todayhistory.BeanUtils.Bean_card;
import bwie.todayhistory.MainUtils.PicActivity;
import bwie.todayhistory.OkHttpUtils.OkHttp;
import bwie.todayhistory.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 * http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1
 */
public class Fragment2 extends BaseFragment {

    private int i=1;
    private CardAdapter adapter;
    private FloatingActionButton up;
    private PullLoadMoreRecyclerView rv;
    private ArrayList<Bean_card.ResultsBean> list=new ArrayList();
    private String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";
    private Callback mCallBack=new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String result = response.body().string();
            Gson gson=new Gson();
            Bean_card bean_card = gson.fromJson(result, Bean_card.class);
            List<Bean_card.ResultsBean> results = bean_card.results;
            list.addAll(results);
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
        return View.inflate(getActivity(), R.layout.fragment2,null);
    }

    @Override
    protected void initFindViewbyId(View view) {
        rv = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        up = (FloatingActionButton) view.findViewById(R.id.up);
        rv.setGridLayout(2);
        rv.setOnPullLoadMoreListener(new MyPullListener());
        up.setOnClickListener(new Myup());
        adapter = new CardAdapter(getActivity(),list);
        rv.setAdapter(adapter);
        adapter.setOnCardClickListener(new MyCardListener());
    }

    @Override
    protected void initData() {
        i++;
        OkHttp.getAnsy(url+(i++),mCallBack);
    }

    class MyCardListener implements CardAdapter.MyCardClickListener{
        @Override
        public void onCardClick(View view, int postion) {
            Intent in=new Intent(getActivity(),PicActivity.class);
            String path=list.get(postion).url;
            in.putExtra("path",path);
            startActivity(in);
        }
    }

    class Myup implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            rv.scrollToTop();
        }
    }

    class MyPullListener implements PullLoadMoreRecyclerView.PullLoadMoreListener{

        @Override
        public void onRefresh() {
            initData();
            rv.setPullLoadMoreCompleted();
        }

        @Override
        public void onLoadMore() {
            initData();
            rv.setPullLoadMoreCompleted();
        }
    }

}
