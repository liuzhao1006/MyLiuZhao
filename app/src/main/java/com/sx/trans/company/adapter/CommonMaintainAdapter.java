package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * Created by liuchao on 2017/9/25.
 * 维护列表适配器
 */


public class CommonMaintainAdapter extends BaseAdapter {

    private Context context;

    public CommonMaintainAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        MaintenanceBean bean = (MaintenanceBean) data;

        TextView name = holder.itemView.findViewById(R.id.tv_name);
        TextView state = holder.itemView.findViewById(R.id.tv_state);

        if (bean == null) {
            return;
        }
        if (TextUtils.isEmpty(bean.getRegistration_no())) {
            name.setText("-");
        } else {
            name.setText(bean.getRegistration_no());

        }

        if (TextUtils.isEmpty(bean.getNext_time())) {
            state.setText("-");
        } else {
            state.setText(bean.getNext_time().split(" ")[0]);
        }
    }
}
