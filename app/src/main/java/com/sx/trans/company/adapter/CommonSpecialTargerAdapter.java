package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SpecialTargerBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者: 刘朝
 * 日期: 2017/10/10
 * 描述: 安全目标适配器
 */

public class CommonSpecialTargerAdapter extends BaseAdapter {
    private Context context;

    public CommonSpecialTargerAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        SpecialTargerBean bean = (SpecialTargerBean) data;

        TextView name = holder.itemView.findViewById(R.id.tv_name);
        TextView ltv_type = holder.itemView.findViewById(R.id.tv_state);

        if (bean == null) {
            return;
        }
        name.setText(bean.getPointName());
        ltv_type.setEllipsize(TextUtils.TruncateAt.END);// 展开
        ltv_type.setSingleLine(true);
        ltv_type.setText( bean.getFormulateDate().split(" ")[0]);


    }
}
