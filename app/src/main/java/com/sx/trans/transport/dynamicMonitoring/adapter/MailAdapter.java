package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.SpecialLearnBean;

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
        CQueryLeaderMailbox.DataBean bean= (CQueryLeaderMailbox.DataBean) data;
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getTitle());
        name. setEllipsize(TextUtils.TruncateAt.END);
        name.setSingleLine(true);
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        if(bean.isHandleState()==true){
            color.setText("已处理");
            color.setTextColor(Color.GREEN);
        }else{
            color.setText("未处理");
            color.setTextColor(Color.RED);
        }

        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setVisibility(View.GONE);
    }
}
