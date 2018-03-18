package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanySafeExerciseBean;
import com.sx.trans.company.bean.SafeMeetingBean;

import java.util.List;


/**
 * Created by Administrator on 2017/9/8.
 * 安全管理 -- 风险分布分析 -- 图表数据展示适配器
 */

public class CommonSafePractiseAdapter extends BaseAdapter {

    private List<SafeMeetingBean.MeetManageRelInfosBean> data;
    private Context context;

    public CommonSafePractiseAdapter(int layoutResId, List data, Context context) {
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
        CommpanySafeExerciseBean.PersonListBean bean = (CommpanySafeExerciseBean.PersonListBean) data;
        // 姓名、岗位、是否参会、所属机构

        TextView name = holder.itemView.findViewById(R.id.tv_name);
        TextView date = holder.itemView.findViewById(R.id.tv_Date);
        TextView state = holder.itemView.findViewById(R.id.tv_state);

        name.setText(bean.getTeamname());
        date.setText(bean.getChargepersontel());
        state.setText(bean.getChargeperson());
        ;

    }


}
