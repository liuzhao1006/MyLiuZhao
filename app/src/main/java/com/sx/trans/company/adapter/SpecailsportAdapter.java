package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.company.bean.SafeRulesDetialBean;
import com.sx.trans.company.bean.SafeSpiecialCampaignBean;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 */

public class SpecailsportAdapter extends BaseAdapter {

    private List<SafeMeetingBean> data;
    private Context context;

    public SpecailsportAdapter(int layoutResId, List data, Context context) {
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
        SafeSpiecialCampaignBean bean = (SafeSpiecialCampaignBean) data;
//        会议名称、会议日期
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getTitle());
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText("" + bean.getCreate_date().split(" ")[0]);
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setVisibility(View.GONE);
    }
}
