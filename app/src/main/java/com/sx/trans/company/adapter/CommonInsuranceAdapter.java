package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 */

public class CommonInsuranceAdapter extends BaseAdapter {

    private List<SafeInsuranceBean> data;
    private Context context;

    public CommonInsuranceAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    /**
     * @param holder   视图管理者
     * @param data     数据源
     * @param position
     */
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        SafeInsuranceBean bean = (SafeInsuranceBean) data;

        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);
        //车牌号、保单号、到期日期、承保公司
        ll_accident_handling.setText(bean.getVehicle_no());
        ll_accident_normal.setText(bean.getCarframe_code());
        ll_accident_time.setText(Utils.getCurrentTimes(bean.getEnd_time()).split("\\s+")[0]);
        ll_accident_name.setText(bean.getSafe_company());

    }


}
