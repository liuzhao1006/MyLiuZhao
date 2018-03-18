package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyRiskDistributionBean;
import com.sx.trans.company.bean.RadarBean;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.company.bean.SafeRulesBean;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 * 安全管理 -- 风险分布分析 -- 图表数据展示适配器
 */

public class CommonDistributionAdapter extends BaseAdapter {


    private Context context;

    public CommonDistributionAdapter(int layoutResId, List data, Context context) {
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
        RadarBean bean = (RadarBean) data;
        // 会议名称、会议日期
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getIndicatorData().get(position).getName());
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText(bean.getIndicatorData().get(position).getMax()+"");
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setText(bean.getSeriesData().get实际分数().get(position)+"");

    }


}
