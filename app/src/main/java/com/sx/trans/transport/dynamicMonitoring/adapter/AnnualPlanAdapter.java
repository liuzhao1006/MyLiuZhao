package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.transport.home.bean.AnnualPlanBean;
import com.sx.trans.transport.home.bean.YearPlanBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 */

public class AnnualPlanAdapter extends BaseAdapter {

    public AnnualPlanAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        YearPlanBean beans = (YearPlanBean) data;
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);


        ll_accident_name.setText(beans.getProfessionName());
        ll_accident_time.setVisibility(View.GONE);

    }
}
