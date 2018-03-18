package com.sx.trans.supervision.adapter;

/**
 * Created by mr_wang on 2017/6/30.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;


import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TroubleAdapter extends BaseAdapter {

    private Context mContext;
    public static int mPosition = 0;
    private ArrayList<String> dates;
    private String name;

    public TroubleAdapter(Context con, ArrayList<String> date, String _name) {
        mContext = con;
        dates = date;
        name = _name;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_trouble, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvTitle.setText(dates.get(position));
        if (name.equals(dates.get(position).toString())) {
            vh.ivRight.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView tvTitle;
        public ImageView ivRight;
        public RelativeLayout rlSeleted;

        ViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
            ivRight = view.findViewById(R.id.iv_right);
            rlSeleted = view.findViewById(R.id.rl_seleted);
        }
    }
}