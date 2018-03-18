package com.sx.trans.transport.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.ArrayListApi;
import com.sx.trans.base.ItemApi;
import com.sx.trans.transport.home.bean.AccidentBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccidentAdapter extends SwipeMenuAdapter<AccidentAdapter.AccidentHolder> {

    private Context context;
    private List<AccidentBeans> beanList;
    private ItemApi api;

    public AccidentAdapter(Context context, List<AccidentBeans> beanList, ItemApi api) {
        this.beanList = beanList;
        this.context = context;
        this.api = api;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.item_accident, parent, false);
    }

    @Override
    public AccidentHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new AccidentHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final AccidentHolder holder, final int position) {
        AccidentBeans accidentBean = beanList.get(position);
        holder.ll_accident_name.setText(accidentBean.getAccidentname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.onItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class AccidentHolder extends RecyclerView.ViewHolder {
        public LzTextView ll_accident_handling;
        public LzTextView ll_accident_normal;
        public LzTextView ll_accident_time;
        public LzTextView ll_accident_name;
        public ImageView iv_addident_icon;

        public AccidentHolder(View itemView) {
            super(itemView);

            ll_accident_handling = (LzTextView) itemView.findViewById(R.id.ll_accident_handling);
            ll_accident_normal = (LzTextView) itemView.findViewById(R.id.ll_accident_normal);
            ll_accident_time = (LzTextView) itemView.findViewById(R.id.ll_accident_time);
            ll_accident_name = (LzTextView) itemView.findViewById(R.id.ll_accident_name);
            iv_addident_icon = (ImageView) itemView.findViewById(R.id.iv_addident_icon);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private AccidentAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(AccidentAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}