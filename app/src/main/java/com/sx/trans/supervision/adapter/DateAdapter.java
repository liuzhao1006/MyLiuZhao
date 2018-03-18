package com.sx.trans.supervision.adapter;

/**
 * Created by mr_wang on 2017/6/30.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DateAdapter extends BaseAdapter {

    private Context mContext;
    public static int mPosition = 0;
    private ArrayList<Date> dates;

    public DateAdapter(Context con, ArrayList<Date> date) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_date, null);
            vh = new ViewHolder(convertView);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vh.rlImg.getLayoutParams();
            layoutParams.width = getScreenWidth(mContext) / 5;
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvTime.setText(DateUtils.format_11(dates.get(position)));
        if (mPosition == position) {
            vh.tvTime.setTextColor(mContext.getResources().getColor(R.color.search_post));
            vh.dateLine.setVisibility(View.VISIBLE);
        } else {
            vh.tvTime.setTextColor(mContext.getResources().getColor(R.color.gray_9));
            vh.dateLine.setVisibility(View.GONE);
        }

        return convertView;
    }

    //获取屏宽
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels - 100;
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