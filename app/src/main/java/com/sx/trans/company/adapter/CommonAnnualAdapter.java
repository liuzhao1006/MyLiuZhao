package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

import java.util.List;

/**
 * Created by liuchao on 2017/9/25.
 */


public class CommonAnnualAdapter extends BaseAdapter {

    private Context context;
    /**
     * 0:运输证
     * 1:行驶证
     * 2:线路牌
     */
    private int type;

    public CommonAnnualAdapter(int layoutResId, List data, Context context, int type) {
        super(layoutResId, data);
        this.context = context;
        this.type = type;

    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        SafeAnnualBean bean = (SafeAnnualBean) data;
        LzTextView name = holder.itemView.findViewById(R.id.tv_name);
        LzTextView ltv_type = holder.itemView.findViewById(R.id.ltv_type);

        LzTextView state = holder.itemView.findViewById(R.id.tv_state);
        if (bean == null) {
            return;
        }
        if (TextUtils.isEmpty(bean.getVehicle_no())) {
            name.setText("-");
        } else {
            name.setText(bean.getVehicle_no());
        }
        switch (type) {

            case 0:
                ltv_type.setText("运输证");
                if (TextUtils.isEmpty(bean.getShipping_annual_date())) {
                    state.setText("-");
                } else {
                    state.setText(bean.getShipping_annual_date().split(" ")[0]);
                }
                break;
            case 1:
                ltv_type.setText("行驶证");
                if (TextUtils.isEmpty(bean.getDriving_license_expiry_date())) {
                    state.setText("-");
                } else {
                    state.setText(bean.getDriving_license_expiry_date().split(" ")[0]);
                }
                break;
            case 2:
                ltv_type.setText("线路牌");
                if (TextUtils.isEmpty(bean.getAnnual_date())) {
                    state.setText("-");
                } else {
                    state.setText(bean.getAnnual_date().split(" ")[0]);
                }
                break;

        }


    }
}
