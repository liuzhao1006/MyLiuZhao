package com.sx.trans.supervision.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.constants.VehicleInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/8/22.
 * 搜索车辆适配器
 */

public class VehicleSearchAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<VehicleInfo> vehicleInfolist;
    private String SeachNo;

    public VehicleSearchAdapter(Context con, ArrayList<VehicleInfo> vehicleInfoArrayList, String seachNo) {
        mContext = con;
        vehicleInfolist = vehicleInfoArrayList;
        SeachNo = seachNo;
    }

    @Override
    public int getCount() {
        return vehicleInfolist.size();
    }

    @Override
    public VehicleInfo getItem(int position) {
        return vehicleInfolist == null ? null : vehicleInfolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_vehiclesearch, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        setDate(vh, position);
        return convertView;
    }

    //设置数据
    private void setDate(ViewHolder vh, int mPosition) {
        if (vehicleInfolist.get(mPosition).getRegistrationNo() == null)
            return;

        //设置搜索字体颜色
        if (SeachNo != null && SeachNo.length() > 0) {
            int fstart = vehicleInfolist.get(mPosition).getRegistrationNo().indexOf(SeachNo);
            int fend = fstart + SeachNo.length();
            if (fstart > 0 && fend >= fstart) {
                SpannableStringBuilder style = new SpannableStringBuilder(vehicleInfolist.get(mPosition).getRegistrationNo());
                style.setSpan(new ForegroundColorSpan(Color.BLUE), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }

        if (vehicleInfolist.get(mPosition).getColorName() == null || vehicleInfolist.get(mPosition).getColorName().equals("")) {
            vh.tvRegistrationNo.setText(vehicleInfolist.get(mPosition).getRegistrationNo());
        } else {
            vh.tvRegistrationNo.setText(vehicleInfolist.get(mPosition).getRegistrationNo() + "（" + vehicleInfolist.get(mPosition).getColorName() + "）");
        }

        //设置是否显示搜索历史图标
        if (vehicleInfolist.get(mPosition).getSearch() == 0) {
            vh.ivHistroy.setVisibility(View.VISIBLE);
        } else {
            vh.ivHistroy.setVisibility(View.GONE);
        }
    }

    class ViewHolder {
        public ImageView ivHistroy;
        public TextView tvRegistrationNo;

        ViewHolder(View view) {
            ivHistroy = view.findViewById(R.id.iv_histroy);
            tvRegistrationNo = view.findViewById(R.id.tv_registrationNo);
        }
    }
}
