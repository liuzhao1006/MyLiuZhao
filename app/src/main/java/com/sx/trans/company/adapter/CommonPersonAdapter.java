package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyEmployeeInfoBean;
import com.sx.trans.company.bean.CommpanyEmployeeListBean;
import com.sx.trans.company.bean.CommpanyVehicleBean;
import com.sx.trans.transport.home.enums.EmploymentTypeEnum;

import java.util.List;

import static com.sx.trans.transport.home.enums.EmploymentTypeEnum.EMPLOYMENT_ONE;


/**
 * Created by Administrator on 2017/9/8.
 */

public class CommonPersonAdapter extends BaseAdapter {

    private List<CommpanyEmployeeInfoBean> Vehicle_data;
    private Context context;

    public CommonPersonAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    /**
     * 车牌号 vehicleNo、车牌颜色 plateColor、车辆类别 brand
     * <p>
     * employmentType		从业类型
     * 备注：1001  客运驾驶员
     * 2001	货运驾驶员
     * 3001	危运驾驶员
     * 9001	出租驾驶员
     * 13001 公交驾驶员
     * 14001 轨道驾驶员
     *
     * @param holder   视图管理者
     * @param data     数据源
     * @param position
     */
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        CommpanyEmployeeListBean bean = (CommpanyEmployeeListBean) data;
        LogUtils.i(bean.toString());
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getUserName());
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText(bean.getPhone());
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setText(bean.getEmployeeType());
    }


}



