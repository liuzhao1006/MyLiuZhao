package com.sx.trans.company.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryLeaderMailbox;

import java.util.List;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class MailAdapter extends BaseAdapter {
    public MailAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        QueryLeaderMailbox.DataBean bean= (QueryLeaderMailbox.DataBean) data;
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getTitle());name. setEllipsize(TextUtils.TruncateAt.END);
        name.setSingleLine(true);

        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setVisibility(View.GONE);
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        if(bean.isHandleState()){
            state.setText("已处理");
        }else{
            state.setText("未处理");
        }

        if (bean.isHandleState()==false){
            state.setTextColor(Color.RED);
        }else{
            state.setTextColor(Color.GREEN);
        }
    }
}
