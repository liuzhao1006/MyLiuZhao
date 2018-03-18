package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryNoticeMessage;

import java.util.List;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class NoticeAdapter extends BaseAdapter {
    public NoticeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        QueryNoticeMessage.DataBean bean= (QueryNoticeMessage.DataBean) data;
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getTitle());
        name. setEllipsize(TextUtils.TruncateAt.END);
        name.setSingleLine(true);
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText(bean.getDate().split(" ")[0]);

        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setVisibility(View.GONE);
    }
}
