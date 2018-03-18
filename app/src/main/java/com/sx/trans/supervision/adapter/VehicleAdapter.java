package com.sx.trans.supervision.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.constants.VehicleInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/8/22.
 * 首页车辆信息列表
 */

public class VehicleAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<VehicleInfo> vehicleInfolist;

    public VehicleAdapter(Context con, ArrayList<VehicleInfo> vehicleInfoArrayList) {
        mContext = con;
        vehicleInfolist = vehicleInfoArrayList;
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
    public int getItemViewType(int position) {
        String paramValue = vehicleInfolist.get(position).getParamValue();
        if (paramValue != null && paramValue.length() > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_vehicle, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        setDate(vh, position);
        return convertView;
    }

    private void setDate(ViewHolder vh, int mPosition) {
        vh.tvRegistrationNo.setText(TextUtils.isEmpty(vehicleInfolist.get(mPosition).getRegistrationNo()) ? "-" : vehicleInfolist.get(mPosition).getRegistrationNo() + "（" + vehicleInfolist.get(mPosition).getColorName() + "）");
        vh.tvAccessPlatform.setText(TextUtils.isEmpty(vehicleInfolist.get(mPosition).getPlatName()) ? "-" : vehicleInfolist.get(mPosition).getPlatName());
        vh.tvCompany.setText(TextUtils.isEmpty(vehicleInfolist.get(mPosition).getEntName()) ? "-" : vehicleInfolist.get(mPosition).getEntName());
        vh.tvNetTime.setText(TextUtils.isEmpty(vehicleInfolist.get(mPosition).getNetTime()) ? "-" : vehicleInfolist.get(mPosition).getNetTime());
        vh.tv_location_time.setText(TextUtils.isEmpty(vehicleInfolist.get(mPosition).getGpsTime()) ? "-" : vehicleInfolist.get(mPosition).getGpsTime());
        vh.rlCarinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class ViewHolder {
        public TextView tvRegistrationNo;
        public RelativeLayout rlCarinto;
        public TextView tvAccessPlatform;
        public TextView tvCompany;
        public TextView tvNetTime;
        public TextView tv_location_time;

        ViewHolder(View view) {
            tvRegistrationNo = view.findViewById(R.id.tv_registrationNo);
            rlCarinto = view.findViewById(R.id.rl_carinto);
            tvAccessPlatform = view.findViewById(R.id.tv_AccessPlatform);
            tvCompany = view.findViewById(R.id.tv_company);
            tvNetTime = view.findViewById(R.id.tv_net_time);
            tv_location_time = view.findViewById(R.id.tv_location_time);
        }
    }
}
