package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.company.bean.SpecialTargerBean;
import com.sx.trans.widget.utils.Utils;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 * 安全管理 -- 风险分布分析 -- 图表数据展示适配器
 */

public class CommonSafeMeetingAdapter extends BaseAdapter {

    private List<SafeMeetingBean.MeetManageRelInfosBean> data;
    private Context context;

    public CommonSafeMeetingAdapter(int layoutResId, List data, Context context) {
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
        SafeMeetingBean.MeetManageRelInfosBean bean = (SafeMeetingBean.MeetManageRelInfosBean) data;
        // 姓名、岗位、是否参会、所属机构

        TextView name = holder.itemView.findViewById(R.id.tv_name);
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        TextView tv_state_company = holder.itemView.findViewById(R.id.tv_state_company);
        name.setText(bean.getUserName());
        color.setText(bean.getPostName());
        if (bean.isIsCome()) {
            state.setText("是");
        } else {
            state.setText("否");
        }
        tv_state_company.setText(bean.getCompanyName());

    }


}
