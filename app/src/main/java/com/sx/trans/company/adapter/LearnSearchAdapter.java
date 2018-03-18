package com.sx.trans.company.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;

import java.util.List;


public class LearnSearchAdapter extends BaseAdapter {

    public LearnSearchAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {


        TextView name = holder.itemView.findViewById(R.id.name);
        TextView telephone = holder.itemView.findViewById(R.id.telephone);


        name.setText("雄霸天下心太乱");
        telephone.setText("11");


    }
}
