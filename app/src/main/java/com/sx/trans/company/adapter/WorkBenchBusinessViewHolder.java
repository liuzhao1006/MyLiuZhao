package com.sx.trans.company.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sx.trans.R;
import com.sx.trans.widget.font.LzTextView;

/**
 * Created by liuchao on 2017/6/21.
 */

public class WorkBenchBusinessViewHolder extends RecyclerView.ViewHolder {


    public LzTextView tvWorkBenchBusiness;
    public ImageView ivShop;
    public LinearLayout ll;

    public WorkBenchBusinessViewHolder(View itemView) {
        super(itemView);

        ivShop = (ImageView) itemView.findViewById(R.id.iv_work_shop);
        tvWorkBenchBusiness = (LzTextView) itemView.findViewById(R.id.tv_work_bench_business);
        ll = itemView.findViewById(R.id.ll);

    }
}
