package com.sx.trans.supervision.adapter;

/**
 * Created by mr_wang on 2017/6/30.
 * 查岗记录
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.constants.PostCheckMoreInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchMorRecordAdapter extends BaseAdapter {

    private Context mContext;
    public static int mPosition = 0;
    private ArrayList<PostCheckMoreInfo> dates;

    public SearchMorRecordAdapter(Context con, ArrayList<PostCheckMoreInfo> date) {
        mContext = con;
        dates = date;
        mPosition = dates.size() - 1;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_search_morrecord, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        setDate(vh, position);
        return convertView;
    }

    private void setDate(ViewHolder vh, int postion) {
        vh.tvRecordTime.setText(TextUtils.isEmpty(dates.get(postion).getTime()) ? "-" : dates.get(postion).getTime());
        vh.tvRecordTitle.setText(dates.get(postion).getCheckedName());

    }

    static class ViewHolder {
        public TextView tvRecordTime;
        public ImageView ivZt;
        public TextView tvRecordTitle;
        public LinearLayout llSearchrecord;

        ViewHolder(View view) {
            tvRecordTime = view.findViewById(R.id.tv_record_time);
            ivZt = view.findViewById(R.id.iv_zt);
            tvRecordTitle = view.findViewById(R.id.tv_record_title);
            llSearchrecord = view.findViewById(R.id.ll_searchrecord);
        }
    }
}