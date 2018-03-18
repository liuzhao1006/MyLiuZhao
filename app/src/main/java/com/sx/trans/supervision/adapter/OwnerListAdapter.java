package com.sx.trans.supervision.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.activity.company.CompanyListActivity;
import com.sx.trans.supervision.constants.OwnerInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 业户分析主页列表Adapter
 */

public class OwnerListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<OwnerInfo> ownerInfoList;

    public OwnerListAdapter(Context mContext, ArrayList<OwnerInfo> ownerInfoList) {
        this.mContext = mContext;
        this.ownerInfoList = ownerInfoList;
    }


    @Override
    public int getCount() {
        return ownerInfoList.size();
    }

    @Override
    public OwnerInfo getItem(int i) {
        return ownerInfoList == null ? null : ownerInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_owner_list_item, null);
            vh = new ViewHolder(view);
            vh.position = i;
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        setOwnerItemData(vh, ownerInfoList.get(i));
        return view;
    }


    class ViewHolder {
        public int position;

        public TextView tvOwnerName;
        public RelativeLayout rlDetail;
        public TextView tvOwnerNum;
        public TextView tvInNum;
        public TextView tvAverage;

        ViewHolder(View view) {
            tvOwnerName = view.findViewById(R.id.tv_owner_name);
            rlDetail = view.findViewById(R.id.rl_detail);
            tvOwnerNum = view.findViewById(R.id.tv_owner_num);
            tvInNum = view.findViewById(R.id.tv_in_num);
            tvAverage = view.findViewById(R.id.tv_average);
            rlDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CompanyListActivity.class);
                    intent.putExtra("id", ownerInfoList.get(position).getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private void setOwnerItemData(ViewHolder v, OwnerInfo ownerInfo) {
        v.tvOwnerName.setText(TextUtils.isEmpty(ownerInfo.getName()) ? "-" : ownerInfo.getName());
        v.tvOwnerNum.setText(ownerInfo.getUnitCnt() + "");
        v.tvInNum.setText(ownerInfo.getRegVehCnt() + "");
        DecimalFormat df = new DecimalFormat("#0.0");
        if (0 < ownerInfo.getUnitCnt()) {
            v.tvAverage.setText(df.format((float) ownerInfo.getRegVehCnt() / ownerInfo.getUnitCnt()));
        } else {
            v.tvAverage.setText("-");
        }

    }


}
