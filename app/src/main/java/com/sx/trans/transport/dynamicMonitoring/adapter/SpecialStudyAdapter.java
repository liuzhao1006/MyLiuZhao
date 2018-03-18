package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.transport.dynamicMonitoring.bean.SpecialLearnBean;

import java.util.List;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class SpecialStudyAdapter extends BaseAdapter {
    public SpecialStudyAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        SpecialLearnBean bean= (SpecialLearnBean) data;
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(bean.getSpecialStudyName());
        TextView color = holder.itemView.findViewById(R.id.tv_Date);
        color.setText("" + bean.getStudyDuration()+"小时");
        TextView state = holder.itemView.findViewById(R.id.tv_state);
        state.setVisibility(View.GONE);
      ImageView im= holder.itemView.findViewById(R.id.img_phone);
      if(bean.getUrl().equals("")||bean.getUrl()==null){
          im.setVisibility(View.INVISIBLE);
      }
    }
}
