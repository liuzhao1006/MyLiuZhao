package com.sx.trans.transport.home.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccidentsAdapter extends BaseAdapter {

    public AccidentsAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        AccidentBeans beans = (AccidentBeans) data;
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);//事故名称
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);//事故时间
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);//事故级别
        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);//处理状态
        ImageView iv_addident_icon = holder.itemView.findViewById(R.id.iv_addident_icon);

        ll_accident_name.setText(beans.getAccidentname());


        ll_accident_time.setText(beans.getVehicle_no());
        if (beans.getAccidenttype().equals("1")){
            ll_accident_normal.setTextColor(Color.YELLOW);
            ll_accident_normal.setText("一般事故");
            iv_addident_icon.setImageResource(R.drawable.accidentphotos);

        }else{
            ll_accident_normal.setTextColor(0xFFE87459);
            ll_accident_normal.setText("重大事故");
            iv_addident_icon.setImageResource(R.drawable.accident2);
        }
        if (!beans.isIshandle()){
            ll_accident_handling.setTextColor(Color.RED);
            ll_accident_handling.setText("未处理");
        }else{
            ll_accident_handling.setTextColor(Color.GREEN);
            ll_accident_handling.setText("已处理");
        }


    }
}
