package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.dynamicMonitoring.bean.LawBean;
import com.sx.trans.transport.dynamicMonitoring.bean.LocalLawBean;
import com.sx.trans.transport.dynamicMonitoring.bean.ReadPdfBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 * <p>
 * 展示地方法规政策列表
 */

public class RulesAdapter extends BaseAdapter {

    public RulesAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        LawBean bean = (LawBean) data;
        //        会议名称、会议日期
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getLaws_name());
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText("" + bean.getUse_date().split(" ")[0]);
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setVisibility(View.GONE);
    }

}