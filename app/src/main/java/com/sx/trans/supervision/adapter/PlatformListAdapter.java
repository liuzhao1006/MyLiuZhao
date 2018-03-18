package com.sx.trans.supervision.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.constants.PlatformInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 平台分析列表Adapter
 */

public class PlatformListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<PlatformInfo> infoList;

    public PlatformListAdapter(Context mContext, ArrayList<PlatformInfo> infoList) {
        this.mContext = mContext;
        this.infoList = infoList;
    }


    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public PlatformInfo getItem(int i) {
        return infoList == null ? null : infoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_platform_list_item, null);
            vh = new ViewHolder(view);
            vh.position = i;
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        setItemData(vh, infoList.get(i));
        return view;
    }


    private void setItemData(ViewHolder v, PlatformInfo info) {
        v.tvPlatformName.setText(TextUtils.isEmpty(info.getName()) ? "-" : info.getName());
        v.tvAll.setText(info.getVehCnt() + "");
        v.tvIn.setText(info.getRegVehCnt() + "");
        v.tvInPer.setText(info.getRegVehRadio() + "%");
        v.tvLinkPer.setText(info.getLink() + "%");
    }


    class ViewHolder {
        public int position;
        public TextView tvPlatformName;
        //     public   @InjectView(R.id.rl_detail)
//     public   RelativeLayout rlDetail;
        public TextView tvAll;
        public TextView tvIn;
        public TextView tvInPer;
        public TextView tvLinkPer;

        ViewHolder(View view) {
            tvPlatformName = view.findViewById(R.id.tv_platform_name);
            tvAll = view.findViewById(R.id.tv_all);
            tvIn = view.findViewById(R.id.tv_in);
            tvInPer = view.findViewById(R.id.tv_in_per);
            tvLinkPer = view.findViewById(R.id.tv_link_per);
        }

//        @OnClick(R.id.rl_detail)
//        void goCompanyDetail() {
//            Intent intent = new Intent(mContext, CompanyDetailActivity.class);
//            PlatformInfo info = infoList.get(position);
//            intent.putExtra("id", info.getId());
//            intent.putExtra("name", info.getName() + "");
//            intent.putExtra("vehCnt", info.getVehCnt() + "");
//            intent.putExtra("regVehCnt",info.getRegVehCnt() + "");
//            intent.putExtra("inPer", info.getRegVehRadio());
//            intent.putExtra("linkPer", info.getQuarters());
//            mContext.startActivity(intent);
//        }
    }
}
