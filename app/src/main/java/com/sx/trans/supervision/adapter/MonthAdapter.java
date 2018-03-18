package com.sx.trans.supervision.adapter;

/**
 * 月份
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MonthAdapter extends BaseAdapter {

    private Context mContext;
    public static int mPosition = 0;
    private ArrayList<String> months;

    public MonthAdapter(Context con, ArrayList<String> months) {
        this.mContext = con;
        this.months = months;
        this.mPosition = months.size() - 1;
    }

    @Override
    public int getCount() {
        return months.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_date, null);
            vh = new ViewHolder(convertView);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vh.rlImg.getLayoutParams();
            layoutParams.width = getScreenWidth(mContext) / 3;
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvTime.setText(months.get(position));
        if (mPosition == position) {
            vh.tvTime.setTextColor(mContext.getResources().getColor(R.color.home_blue));
            vh.dateLine.setVisibility(View.VISIBLE);
        } else {
            vh.tvTime.setTextColor(mContext.getResources().getColor(R.color.gray_9));
            vh.dateLine.setVisibility(View.GONE);
        }

        return convertView;
    }

    //获取屏宽
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels - 200;
    }

    class ViewHolder {
        public TextView tvTime;
        public View dateLine;
        public RelativeLayout rlImg;

        ViewHolder(View view) {
            tvTime = view.findViewById(R.id.tv_time);
            dateLine = view.findViewById(R.id.date_line);
            rlImg = view.findViewById(R.id.rl_img);
        }
    }
}