package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SafeAccidentBean;
import com.sx.trans.company.bean.SafeInputBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * Created by liuchao on 2017/9/25.
 */


public class CommonInputAdapter extends BaseAdapter {

    private Context context;

    public CommonInputAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        SafeInputBean bean = (SafeInputBean) data;
        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);
//年份、月份、金额、所属机构；

        ll_accident_name.setText(bean.getCompany_name());
        ll_accident_time.setText("投资" + bean.getAmount() + "万元");
        ll_accident_normal.setText(bean.getYear() + "年" + bean.getMonth() + "月");

        ll_accident_handling.setText(Html.fromHtml(bean.getPurpose()));

    }
}
