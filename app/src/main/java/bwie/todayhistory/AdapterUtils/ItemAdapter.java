package bwie.todayhistory.AdapterUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bwie.todayhistory.BeanUtils.Bean_list;
import bwie.todayhistory.R;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private final Context context;
    private final ArrayList<Bean_list.ResultBean> list;

    public ItemAdapter(Context context, ArrayList<Bean_list.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item,parent,false),mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemdata.setText(list.get(position).year+"年"+list.get(position).month+"月"+list.get(position).day);
        holder.itemtitle.setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemdata;
        TextView itemtitle;
        TextView line;

        public MyViewHolder(View itemView, final ItemAdapter.MyItemClickListener mylistener) {
            super(itemView);
            itemdata = (TextView) itemView.findViewById(R.id.item_data);
            itemtitle = (TextView) itemView.findViewById(R.id.item_title);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(mylistener!=null){
                        mylistener.onItemClick(v,getPosition());
                    }
                }
            });
        }
    }

    //接口回调
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    private MyItemClickListener mListener;
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }


}
