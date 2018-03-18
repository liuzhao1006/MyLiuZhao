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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.constants.PostCheckInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchRecordAdapter extends BaseAdapter {

    private Context mContext;
    public static int mPosition = 0;
    private ArrayList<PostCheckInfo> dates;

    public SearchRecordAdapter(Context con, ArrayList<PostCheckInfo> date) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.iteam_searchrecord, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        setDate(vh, position);
        return convertView;
    }

    private void setDate(ViewHolder vh, final int postion) {
        vh.tvSerachtrouble.setText("问题:" + (TextUtils.isEmpty(dates.get(postion).getQuestion()) ? "-" : dates.get(postion).getQuestion()));
        vh.tvHouseholdsNumber.setText(dates.get(postion).getEntCnt() + "");
        DecimalFormat df = new DecimalFormat("#0.0");
        vh.tvPostOnline.setText(df.format(dates.get(postion).getOnineRatio()) + "%");
        vh.tvRecordtitle.setText(TextUtils.isEmpty(dates.get(postion).getName()) ? "-" : dates.get(postion).getName());
        String time = dates.get(postion).getTime();
        if (time != null && time.length() > 18) {
            vh.tvSearchTime.setText(time.substring(0, 19));
        } else {
            vh.tvSearchTime.setText(TextUtils.isEmpty(time) ? "-" : time);
        }

//        vh.llSearchrecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent  intent=new Intent(new Intent(mContext,PostRecordListActivity.class));
//                Bundle bundle=new Bundle();
//                bundle.putSerializable(IConstants.mSpre_Constants.SEARCHRECORD,dates.get(postion));
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
//            }
//        });

    }


    static class ViewHolder {
        public TextView tvRecordtitle;
        public TextView tvHouseholdsNumber;
        public TextView tvPostOnline;
        public TextView tvSerachtrouble;
        public TextView tvSearchTime;
        public LinearLayout llSearchrecord;

        ViewHolder(View view) {
            tvRecordtitle = view.findViewById(R.id.tv_recordtitle);
            tvHouseholdsNumber = view.findViewById(R.id.tv_households_number);
            tvPostOnline = view.findViewById(R.id.tv_post_online);
            tvSerachtrouble = view.findViewById(R.id.tv_serachtrouble);
            tvSearchTime = view.findViewById(R.id.tv_search_time);
            llSearchrecord = view.findViewById(R.id.ll_searchrecord);
        }
    }
}