package com.sx.trans.safetyLearning.progress.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.lzlibrary.utils.DrableTextUtils;
import com.sx.lzlibrary.widget.MyProgressBar;
import com.sx.trans.R;
import com.sx.trans.safetyLearning.progress.bean.ProgressDetialBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.UiUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

public class ProgressDatialAdapter extends SwipeMenuAdapter<ProgressDatialAdapter.SimpleAdapterViewHolder> {

    private Context context;
    private List<ProgressDetialBean.VideoListBean> mList;

    public ProgressDatialAdapter(Context context, List<ProgressDetialBean.VideoListBean> mList) {
        this.context = context;
        this.mList = mList;

    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_progress_detial, parent, false);
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
        ProgressDetialBean.VideoListBean ListBean = mList.get(position);
        holder.lRank.setText(position + 1 + "");
        holder.lRank.setTextColor(context.getResources().getColor(R.color.rankcolor));
        holder.lRank.setBackgroundResource(R.drawable.rank_rund_bakageount);
        DrableTextUtils.setTextDrable(context, holder.lName, context.getResources().getDrawable(R.drawable.persion_no), "left");
        DrableTextUtils.setTextDrable(context, holder.lCard, context.getResources().getDrawable(R.drawable.card), "left");
        holder.lName.setText(mList.get(position).getTitle());
        UiUtils.setWidth(context, holder.lName, 1);//  设置宽度
        holder.lCard.setVisibility(View.GONE);

        holder.myProgressBar.setProgress((int) (Double.valueOf(ListBean.getVideoRatio()) * 100));

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

    private ProgressDatialAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(ProgressDatialAdapter.OnItemClickListener clickListener) {
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
                myProgressBar = itemView.findViewById(R.id.base_bar);
                lName = itemView.findViewById(R.id.base_name);
                lRank = itemView.findViewById(R.id.base_ranking);
                lCard = itemView.findViewById(R.id.base_card);
            }

        }
    }
}

