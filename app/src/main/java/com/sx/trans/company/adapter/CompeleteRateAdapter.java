package com.sx.trans.company.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanySafeExerciseBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;


public class CompeleteRateAdapter extends BaseAdapter {

    public CompeleteRateAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {


        TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
        TextView tv_state = holder.itemView.findViewById(R.id.tv_state);


        tv_name.setText("11");
        tv_state.setText("22");
    }
}
