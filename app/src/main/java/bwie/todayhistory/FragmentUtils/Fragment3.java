package bwie.todayhistory.FragmentUtils;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;
import bwie.todayhistory.BaseUtils.BaseFragment;
import bwie.todayhistory.MainUtils.ContentActivity;
import bwie.todayhistory.R;
import bwie.todayhistory.db.History;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 */
public class Fragment3 extends BaseFragment {

    private DefaultItemTouchHelpCallback mCallback;
    private RecyclerView mRecyclerView;
    private CollectAdapter adapter;
    private List<History> all;
    SwipeRefreshLayout mSrl;
    TextView tvEmpty;

    @Override
    public View initView(LayoutInflater inflater) {
        return View.inflate(getActivity(), R.layout.fragment3,null);
    }

    @Override
    protected void initFindViewbyId(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        tvEmpty = (TextView) view.findViewById(R.id.tv_empty);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initData() {
        all = DataSupport.findAll(History.class);
        adapter = new CollectAdapter(getActivity(), all);
        mRecyclerView.setAdapter(adapter);
        if (adapter.getItemCount()<=0){
            tvEmpty.setVisibility(View.VISIBLE);
        }
        mSrl.setRefreshing(false);
        setItemTouch();
        addListener();
    }

    //滑动删除
    private void setItemTouch() {
        // 滑动删除的时候，从数据库、数据源移除，并刷新UI
        mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int position) {
                // 滑动删除的时候，从数据库、数据源移除，并刷新UI
                History history = all.get(position);
                history.delete();
                all.remove(position);
                adapter.notifyItemRemoved(position);
                if (adapter.getItemCount() <= 0) {
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                adapter.notifyItemMoved(srcPosition, targetPosition);
                return true;
            }
        });
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void addListener() {
        adapter.setOnItemClickLitener(new CollectAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                String db_id = all.get(position).getDb_id();
                Intent in=new Intent(getActivity(),ContentActivity.class);
                in.putExtra("db_mId",db_id);
                startActivity(in);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                all.clear();
                List<History> all1 =  DataSupport.findAll(History.class);
                all.addAll(all1);
                adapter.notifyDataSetChanged();
                mSrl.setRefreshing(false);
                if (adapter.getItemCount() <= 0) {
                    tvEmpty.setVisibility(View.VISIBLE);
                } else {
                    tvEmpty.setVisibility(View.GONE);
                }
            }
        });
    }
}
