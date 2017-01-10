package bwie.todayhistory.AdapterUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bwie.todayhistory.BeanUtils.Bean_content;
import bwie.todayhistory.R;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/8.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyHolder> {

    private final Context context;
    private final ArrayList<Bean_content.ResultBean> list;

    public ContentAdapter(Context context, ArrayList<Bean_content.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder=new MyHolder(LayoutInflater.from(context)
                .inflate(R.layout.content_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.content.setText(list.get(position).content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView content;

        public MyHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
