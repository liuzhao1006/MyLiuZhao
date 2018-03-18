package com.sx.trans.transport.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.ItemApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.home.bean.AccidentBean;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.widget.font.LzTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import static com.sx.trans.app.LzApp.context;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class MaintenanceAdapter extends BaseAdapter {


    private Context context;

    public MaintenanceAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        MaintenanceBean bean = (MaintenanceBean) data;
        TextView tv_vehicle_plate = holder.itemView.findViewById(R.id.tv_name);
        TextView tv_vehicle_time = holder.itemView.findViewById(R.id.tv_state);
        tv_vehicle_plate.setText(bean.getRegistration_no());
        tv_vehicle_time.setText( bean.getNext_time().split(" ")[0] + "");
    }

}