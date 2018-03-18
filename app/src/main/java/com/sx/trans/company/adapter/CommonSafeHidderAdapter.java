package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;

import java.util.List;

/**
 * 作者: 刘朝
 * 日期: 2017/10/10
 * 描述: 隐患详情 检查项数据适配器
 */

public class CommonSafeHidderAdapter extends BaseAdapter {
    private Context context;

    public CommonSafeHidderAdapter(int layoutResId, List<CommpanyHidderDrangBean.CheckItemsBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        CommpanyHidderDrangBean.CheckItemsBean bean = (CommpanyHidderDrangBean.CheckItemsBean) data;
        TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
        TextView tv_Date = holder.itemView.findViewById(R.id.tv_Date);
        tv_Date.setText(bean.getCheckitemcontent());
        tv_name.setText(bean.getCheckitemname());
    }
}
