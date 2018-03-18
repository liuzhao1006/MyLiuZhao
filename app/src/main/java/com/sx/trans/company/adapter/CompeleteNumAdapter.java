package com.sx.trans.company.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;

import java.util.List;


public class CompeleteNumAdapter extends BaseAdapter {

    public CompeleteNumAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {

        TextView tv_company = holder.itemView.findViewById(R.id.tv_company);
        TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
        TextView tv_state = holder.itemView.findViewById(R.id.tv_state);

        TextView tv_phone = holder.itemView.findViewById(R.id.tv_phone);
        tv_company.setText("11");
        tv_name.setText("11");
        tv_state.setText("22");
       ;
        tv_phone.setText("22");

    }
}
