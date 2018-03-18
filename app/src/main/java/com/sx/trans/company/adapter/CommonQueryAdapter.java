package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyVehicleBean;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 */

public class CommonQueryAdapter extends BaseAdapter {

    private List<CommpanyVehicleBean> Vehicle_data;
    private Context context;

    public CommonQueryAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    /**
     * 车牌号 vehicleNo、车牌颜色 plateColor、车辆类别 brand
     *
     * @param holder   视图管理者
     * @param data     数据源
     * @param position
     */
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        CommpanyVehicleBean bean = (CommpanyVehicleBean) data;
        LogUtils.i(bean.toString());
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getVehicleNo());
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText(bean.getVehicleNoColor());
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setText(bean.getVehicleType());
    }


}
