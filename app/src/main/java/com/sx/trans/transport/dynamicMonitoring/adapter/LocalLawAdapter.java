package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.trans.R;
import com.sx.trans.base.ItemApi;
import com.sx.trans.transport.dynamicMonitoring.bean.PdfBean;
import com.sx.trans.widget.font.LzTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class LocalLawAdapter extends SwipeMenuAdapter<LocalLawAdapter.AccidentHolder> {

    private Context context;
    private List<PdfBean> beanList;
    private ItemApi api;

    public LocalLawAdapter(Context context, List<PdfBean> beanList, ItemApi api) {
        this.beanList = beanList;
        this.context = context;
        this.api = api;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.item_local_law, parent, false);
    }

    @Override
    public AccidentHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new AccidentHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final AccidentHolder holder, final int position) {
        PdfBean bean = beanList.get(position);
        holder.ltv_local_name.setText(bean.name);
        holder.ltv_local_des.setText(bean.time);
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
        public LzTextView ltv_local_name;
        public LzTextView ltv_local_des;

        public AccidentHolder(View itemView) {
            super(itemView);
            ltv_local_name = itemView.findViewById(R.id.ltv_local_name);
            ltv_local_des = itemView.findViewById(R.id.ltv_local_des);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private LocalLawAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(LocalLawAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}