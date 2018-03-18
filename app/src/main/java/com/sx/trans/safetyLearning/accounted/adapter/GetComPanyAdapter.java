package com.sx.trans.safetyLearning.accounted.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.safetyLearning.accounted.bean.AccountListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public class GetComPanyAdapter extends BaseAdapter {

    private List<AccountListBean.ListBean> list = new ArrayList<>();

    private Context mcontext;


    public GetComPanyAdapter(Context context, List<AccountListBean.ListBean> list) {
        this.list = list;
        this.mcontext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HoldView holdView = null;
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.getcompanyadapter, null);
            holdView.tv = (TextView) convertView.findViewById(R.id.getyan_tv);
            convertView.setTag(holdView);
        }

        holdView = (HoldView) convertView.getTag();

        holdView.tv.setText(list.get(position).getHoursRatio());
        return convertView;
    }

    class HoldView {
        TextView tv;
    }
}
