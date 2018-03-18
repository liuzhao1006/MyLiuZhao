package com.sx.trans.company.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SafeRepotBean;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryLeaderMailbox;

import java.util.List;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class SafeReportAdapter extends BaseAdapter {
    public SafeReportAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        SafeRepotBean.DataBean bean= (SafeRepotBean.DataBean) data;
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(""+bean.getCompanyName());
        name.setSingleLine(true);
        name.setEllipsize(TextUtils.TruncateAt.END);
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText(""+bean.getAnalysisDate());
        TextView state = holder.itemView.findViewById(R.id.tv_state);
       state.setVisibility(View.GONE);
//        state.setText("处理状态"+bean.isHandleState());
    }
}
