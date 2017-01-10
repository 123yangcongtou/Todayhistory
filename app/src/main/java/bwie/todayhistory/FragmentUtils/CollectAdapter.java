package bwie.todayhistory.FragmentUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import bwie.todayhistory.AdapterUtils.CardAdapter;
import bwie.todayhistory.R;
import bwie.todayhistory.db.History;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/10.
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyCoHolder> {
    private final Context context;
    private final List<History> all;
    private OnItemClickLitener mOnItemClickLitener;


    public CollectAdapter(Context context, List<History> all) {
        this.context = context;
        this.all = all;
    }

    @Override
    public MyCoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyCoHolder holde =new MyCoHolder(LayoutInflater.from(context)
                .inflate(R.layout.collection_item,parent,false));
        return holde;
    }

    @Override
    public void onBindViewHolder(final MyCoHolder holder, int position) {
        holder.day.setText(all.get(position).getDb_day());
        holder.title.setText(all.get(position).getDb_title());
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    class MyCoHolder extends RecyclerView.ViewHolder{
        TextView day;
        TextView title;
        public MyCoHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.collection_day);
            title = (TextView) itemView.findViewById(R.id.collection_title);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
