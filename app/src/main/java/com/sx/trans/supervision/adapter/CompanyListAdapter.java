package com.sx.trans.supervision.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.activity.company.CompanyDetailActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.CompanyInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 业户分析公司列表Adapter
 */

public class CompanyListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<CompanyInfo> companyInfoList;

    public CompanyListAdapter(Context mContext, ArrayList<CompanyInfo> companyInfoList) {
        this.mContext = mContext;
        this.companyInfoList = companyInfoList;
    }


    @Override
    public int getCount() {
        return companyInfoList.size();
    }

    @Override
    public CompanyInfo getItem(int i) {
        return companyInfoList == null ? null : companyInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_company_list_item, null);
            vh = new ViewHolder(view);
            vh.position = i;
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        setItemData(vh, companyInfoList.get(i));
        return view;
    }


    private void setItemData(ViewHolder v, CompanyInfo ownerInfo) {
        v.tvCompanyName.setText(TextUtils.isEmpty(ownerInfo.getName()) ? "-" : ownerInfo.getName());
        v.tvAllNum.setText(ownerInfo.getVehCnt() + "");
        v.tvInNum.setText(ownerInfo.getRegVehCnt() + "");
        DecimalFormat df = new DecimalFormat("#0.0");
        if (0 < ownerInfo.getVehCnt()) {
            v.tvPer.setText(df.format((float) ownerInfo.getRegVehCnt() * 100 / ownerInfo.getVehCnt()) + "%");
        } else {
            v.tvPer.setText("-");
        }
    }

    class ViewHolder {
        public int position;
        public TextView tvCompanyName;
        public RelativeLayout rlDetail;
        public TextView tvAllNum;
        public TextView tvInNum;
        public TextView tvPer;

        ViewHolder(View view) {
            tvCompanyName = view.findViewById(R.id.tv_company_name);
            rlDetail = view.findViewById(R.id.rl_detail);
            tvAllNum = view.findViewById(R.id.tv_all_num);
            tvInNum = view.findViewById(R.id.tv_in_num);
            tvPer = view.findViewById(R.id.tv_per);
            rlDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CompanyDetailActivity.class);
                    Bundle bundle = new Bundle();
                    companyInfoList.get(position).setUid(companyInfoList.get(position).getId());
                    bundle.putSerializable(IConstants.mSpre_Constants.COMPANY_INFO, companyInfoList.get(position));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });

        }


    }
}
