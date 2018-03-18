package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyEmployeeInfoBean;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 */

public class CommonHidderDrangAdapter extends BaseAdapter {

    private List<CommpanyHidderDrangBean> data;
    private Context context;

    public CommonHidderDrangAdapter(int layoutResId, List data, Context context) {
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
     * 13001	公交驾驶员
     * 14001 轨道驾驶员
     *
     * @param holder   视图管理者
     * @param data     数据源
     * @param position
     */
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        CommpanyHidderDrangBean bean = (CommpanyHidderDrangBean) data;

        LzTextView ltv_drang_year = holder.itemView.findViewById(R.id.ltv_drang_year);
        LzTextView ltv_drang_month = holder.itemView.findViewById(R.id.ltv_drang_month);
        LzTextView ltv_drang_company = holder.itemView.findViewById(R.id.ltv_drang_company);
        LzTextView ltv_drang_matter = holder.itemView.findViewById(R.id.ltv_drang_matter);
        LzTextView ltv_drang_type = holder.itemView.findViewById(R.id.ltv_drang_type);
        ImageView iv_drang_go = holder.itemView.findViewById(R.id.iv_drang_go);

        if (TextUtils.isEmpty(bean.getYear_plan())) {
            ltv_drang_company.setText("(-) 年");
        } else {
            ltv_drang_company.setText(bean.getYear_plan()+"-"+bean.getMonth_plan());
        }
        ltv_drang_month.setVisibility(View.GONE);
//        if (TextUtils.isEmpty(bean.getMonth_plan())) {
//            ltv_drang_month.setText("(-) 月");
//        } else {
//            ltv_drang_month.setText(bean.getMonth_plan() + "月");
//        }
        if (TextUtils.isEmpty(bean.getCompany_name_plan())) {
            ltv_drang_year.setText("-");
        } else {
            ltv_drang_year.setEllipsize(TextUtils.TruncateAt.END);
            ltv_drang_year.setSingleLine(true);
            ltv_drang_year.setText(bean.getCompany_name_plan());
        }
        if (TextUtils.isEmpty(bean.getObject_plan())) {
            ltv_drang_matter.setText("-");
        } else {
            ltv_drang_matter.setText(bean.getObject_plan());
        }

        ltv_drang_type.setText(bean.getRemark_plan() + "");

    }


}
