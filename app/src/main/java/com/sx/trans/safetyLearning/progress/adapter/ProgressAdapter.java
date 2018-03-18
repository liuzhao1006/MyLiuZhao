package com.sx.trans.safetyLearning.progress.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.lzlibrary.utils.DrableTextUtils;
import com.sx.lzlibrary.widget.MyProgressBar;
import com.sx.trans.R;
import com.sx.trans.safetyLearning.bean.ProgressListBean.ListBean;
import com.sx.trans.widget.font.LzTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

public class ProgressAdapter extends SwipeMenuAdapter<ProgressAdapter.SimpleAdapterViewHolder> {

    private Context context;
    private List<ListBean> mList;

    public ProgressAdapter(Context context, List<ListBean> mList) {
        this.context = context;
        this.mList = mList;

    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_progress, parent, false);
        return view;
    }

    @Override
    public SimpleAdapterViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new SimpleAdapterViewHolder(realContentView, true);
    }

    @Override
    public void onBindViewHolder(final SimpleAdapterViewHolder holder, int position) {
        if (mList == null) {
            return;
        }
        ListBean ListBean = mList.get(position);
        holder.lName.setText(ListBean.getStuName());
        holder.lRank.setText(position + 1 + "");
        holder.lCard.setText(ListBean.getCertiDepartment());
        DrableTextUtils.setTextDrable(context, holder.lName, context.getResources().getDrawable(R.drawable.persion_no), "left");
        DrableTextUtils.setTextDrable(context, holder.lCard, context.getResources().getDrawable(R.drawable.card), "left");

        holder.myProgressBar.setProgress((int) (Double.valueOf(ListBean.getHoursRatio()) * 100));
        //item点击事件
        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private ProgressAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(ProgressAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public MyProgressBar myProgressBar;
        public LzTextView lName;
        public LzTextView lRank;
        public LzTextView lCard;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                myProgressBar = (MyProgressBar) itemView.findViewById(R.id.base_bar);
                lName = (LzTextView) itemView.findViewById(R.id.base_name);
                lRank = (LzTextView) itemView.findViewById(R.id.base_ranking);
                lCard = (LzTextView) itemView.findViewById(R.id.base_card);
            }

        }
    }
}

