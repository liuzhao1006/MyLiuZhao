package com.sx.trans.transport.home.adapter;

import android.support.v7.widget.RecyclerView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.transport.home.bean.YearCarfulBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.UiUtils;
import com.sx.trans.widget.utils.Utils;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 */

public class YearTrialAdapter extends BaseAdapter {

    public YearTrialAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        YearCarfulBean beans = (YearCarfulBean) data;
        LzTextView ll_accident_name = holder.itemView.findViewById(R.id.ll_accident_name);//事故名称
        LzTextView ll_accident_time = holder.itemView.findViewById(R.id.ll_accident_time);//事故时间
        LzTextView ll_accident_normal = holder.itemView.findViewById(R.id.ll_accident_normal);//事故级别
        LzTextView ll_accident_handling = holder.itemView.findViewById(R.id.ll_accident_handling);//处理状态
        ll_accident_name.setText(beans.getUserName());
        ll_accident_time.setText(Utils.getCurrentTimes(beans.getLicenseYearDate()));
        ll_accident_normal.setText("证书编号:" + beans.getCertificateNumber());
        ll_accident_handling.setText("年检日期:" + Utils.getCurrentTimes(beans.getLicenseYearLimited()).split(" ")[0]);
    }
}
