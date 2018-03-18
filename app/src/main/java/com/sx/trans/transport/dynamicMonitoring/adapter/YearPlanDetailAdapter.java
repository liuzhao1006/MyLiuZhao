package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.home.bean.YearPlanBean;
import com.sx.trans.transport.home.bean.YearPlanDetailBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 */

public class YearPlanDetailAdapter extends BaseAdapter {

    public YearPlanDetailAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        YearPlanDetailBean bean = (YearPlanDetailBean) data;
        TextView montn_plan_name = holder.itemView.findViewById(R.id.montn_plan_name);//xue
        TextView studytime = holder.itemView.findViewById(R.id.studytime);//事故时间

        TextView addtime = holder.itemView.findViewById(R.id.addtime);//事故时间
        montn_plan_name.setText(bean.getStudyMonth().substring(0,4)+"月计划详情");
        studytime.setText("基础学时:"+bean.getBasicsHours()+"学时");
        addtime.setText("添加时间:"+bean.getAddTime().split(" ")[0]);

    }
}
