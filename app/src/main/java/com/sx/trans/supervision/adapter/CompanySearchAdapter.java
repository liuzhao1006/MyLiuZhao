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

import com.sx.trans.supervision.constants.CompanyInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 搜索公司适配器
 */

public class CompanySearchAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<CompanyInfo> companyInfoList;
    private String SeachNo;

    public CompanySearchAdapter(Context con, ArrayList<CompanyInfo> companyInfoList, String seachNo) {
        this.mContext = con;
        this.companyInfoList = companyInfoList;
        this.SeachNo = seachNo;
    }

    @Override
    public int getCount() {
        return companyInfoList.size();
    }

    @Override
    public CompanyInfo getItem(int position) {
        return companyInfoList == null ? null : companyInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_publicseach_company_item, null);
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
        if (companyInfoList.get(mPosition).getName() == null)
            return;

        //设置搜索字体颜色
        if (SeachNo != null && SeachNo.length() > 0) {
            int fstart = companyInfoList.get(mPosition).getName().indexOf(SeachNo);
            int fend = fstart + SeachNo.length();
            if (fstart > 0 && fend >= fstart) {
                SpannableStringBuilder style = new SpannableStringBuilder(companyInfoList.get(mPosition).getName());
                style.setSpan(new ForegroundColorSpan(Color.BLUE), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }

        vh.tvName.setText(companyInfoList.get(mPosition).getName());

        //设置是否显示搜索历史图标
        if (companyInfoList.get(mPosition).getSearch() == 0) {
            vh.ivHistroy.setVisibility(View.VISIBLE);
        } else {
            vh.ivHistroy.setVisibility(View.GONE);
        }
    }

    class ViewHolder {
        public ImageView ivHistroy;
        public TextView tvName;

        ViewHolder(View view) {
            ivHistroy = view.findViewById(R.id.iv_histroy);
            tvName = view.findViewById(R.id.tv_name);
        }
    }
}
