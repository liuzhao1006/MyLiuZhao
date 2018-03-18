package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 * <p>
 * 提醒名称、提醒类型、提醒时间、提醒内容
 */

public class StopAdapter extends BaseAdapter {

    public StopAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        RemberBean bean = (RemberBean) data;
        LogUtils.i(bean.toString());
        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);
        ImageView iv_addident_icon = holder.itemView.findViewById(R.id.iv_addident_icon);
        ll_accident_name.setText(bean.getTargetName());
        ll_accident_normal.setText(bean.getAlarmDescription());
        ll_accident_time.setText("(" + bean.getAlarmDateTime().split(" ")[0] + ")");
        ll_accident_handling.setText(bean.getState());
//        ll_accident_handling.setVisibility(View.GONE);
//        if (bean.getTargetName().contains("超速")) {
//            iv_addident_icon.setImageResource(R.drawable.overspeed);
//        } else if (bean.getTargetName().contains("疲劳")) {
//            iv_addident_icon.setImageResource(R.drawable.tired);
//        } else if (bean.getTargetName().contains("证")) {
//            iv_addident_icon.setImageResource(R.drawable.roadtransportcertificate);
//        }
    }

}