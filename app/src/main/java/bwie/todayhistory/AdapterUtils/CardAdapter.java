package bwie.todayhistory.AdapterUtils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bwie.todayhistory.BeanUtils.Bean_card;
import bwie.todayhistory.R;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/8.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyHolder2> {
    private final Context context;
    private final ArrayList<Bean_card.ResultsBean> list;

    public CardAdapter(Context context, ArrayList<Bean_card.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CardAdapter.MyHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder2 holder=new MyHolder2(LayoutInflater.from(context)
                .inflate(R.layout.card_item,parent,false),mCard);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardAdapter.MyHolder2 holder, int position) {
        Glide.with(context)
                .load(list.get(position).url)
                .into(holder.cardpic);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder2 extends RecyclerView.ViewHolder{

        ImageView cardpic;

        public MyHolder2(View itemView, final MyCardClickListener myListener) {
            super(itemView);
            cardpic = (ImageView) itemView.findViewById(R.id.card_pic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(myListener!=null){
                        myListener.onCardClick(v,getPosition());
                    }
                }
            });
        }
    }



    //接口回调
    public interface MyCardClickListener {
        public void onCardClick(View view,int postion);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    private MyCardClickListener mCard;
    public void setOnCardClickListener(MyCardClickListener listener){
        this.mCard = listener;
    }

}
