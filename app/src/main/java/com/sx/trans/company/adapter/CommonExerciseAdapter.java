package com.sx.trans.company.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanySafeExerciseBean;
import com.sx.trans.transport.home.bean.AnnualPlanBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 * <p>
 * 演练名称、演练日期、应急预案、应急队伍；
 */

public class CommonExerciseAdapter extends BaseAdapter {

    public CommonExerciseAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {

        CommpanySafeExerciseBean beans = (CommpanySafeExerciseBean) data;
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);//事故名称
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);//事故时间
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);//事故级别
        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);//处理状态

        ll_accident_name.setText(beans.getRehearsalname_drill());
        ll_accident_time.setText("("+beans.getRehearsaldate_drill().split("\\s")[0]+")");
        ll_accident_normal.setText(beans.getPlanname());
        ll_accident_handling.setText(beans.getTeamname());
    }
}
