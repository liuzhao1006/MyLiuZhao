package com.sx.trans.company.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SafeAccidentBean;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

import java.util.List;

/**
 * Created by liuchao on 2017/9/25.
 */


public class CommonAccidentAdapter extends BaseAdapter {

    private Context context;

    public CommonAccidentAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        AccidentBeans bean = (AccidentBeans) data;
        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);
//事故名称、事故日期、车牌号、驾驶员
//        ll_accident_name.setText(bean.getAccidentname());
//        ll_accident_normal.setText(bean.getAccidentdate().split(" ")[0]);
//        ll_accident_time.setText(bean.getVehicle_no());
//        ll_accident_handling.setText(bean.getUser_name());
        ImageView iv_addident_icon = holder.itemView.findViewById(R.id.iv_addident_icon);

        ll_accident_name.setText(bean.getAccidentname());


        ll_accident_time.setText(bean.getVehicle_no());
        if (bean.getAccidenttype().equals("1")){
            ll_accident_normal.setTextColor(Color.YELLOW);
            ll_accident_normal.setText("一般事故");
            iv_addident_icon.setImageResource(R.drawable.accidentphotos);

        }else{
            ll_accident_normal.setTextColor(0xFFE87459);
            ll_accident_normal.setText("重大事故");
            iv_addident_icon.setImageResource(R.drawable.accident2);
        }
        if (!bean.isIshandle()){
            ll_accident_handling.setTextColor(Color.RED);
            ll_accident_handling.setText("未处理");
        }else{
            ll_accident_handling.setTextColor(Color.GREEN);
            ll_accident_handling.setText("已处理");
        }


    }

}
