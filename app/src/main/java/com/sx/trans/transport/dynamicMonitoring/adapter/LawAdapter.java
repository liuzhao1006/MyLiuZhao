package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.dynamicMonitoring.bean.LocalLawBean;
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

public class LawAdapter extends BaseAdapter {

    public LawAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        LocalLawBean bean = (LocalLawBean) data;
        LogUtils.i(bean.toString());
        LzTextView tv_vehicle_plate = holder.itemView.findViewById(R.id.tv_vehicle_plate);
        LzTextView tv_vehicle_time = holder.itemView.findViewById(R.id.tv_vehicle_time);
        LzTextView tv_vehicle_des = holder.itemView.findViewById(R.id.tv_vehicle_des);
        tv_vehicle_plate.setText(bean.getLaws_name());
        tv_vehicle_time.setText("(" + bean.getAdd_time().split(" ")[0] + ")");
        tv_vehicle_des.setText(bean.getGroup_id());

    }

}