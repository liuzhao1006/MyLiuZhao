package com.sx.trans.supervision.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sx.trans.R;

import com.sx.trans.supervision.constants.IndustryAnalysisInfo;
import com.sx.trans.supervision.utils.DateUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/7/8.
 * 车队分析柱形图适配器
 */

public class IndutryAnaylsisAdapter extends BaseAdapter {

    private Context mContext;
    protected String[] mMonths = new String[]{"二月", "三月", "四月", "五月", "六月", "七月"};//X轴标签
    //    private ArrayList<IndustryAnalysisInfo.> abc;
    ArrayList<ArrayList<IndustryAnalysisInfo.RegTrend>> industryAInfoArrayList;

    public IndutryAnaylsisAdapter(Context context, ArrayList<ArrayList<IndustryAnalysisInfo.RegTrend>> _industryAInfoArrayList) {
        mContext = context;
        industryAInfoArrayList = _industryAInfoArrayList;
        for (int i = 0; i <= 5; i++) {
            mMonths[5 - i] = DateUtils.getMonth(i);
        }
    }

    @Override
    public int getCount() {
        return industryAInfoArrayList.size();
    }

    @Override
    public ArrayList<IndustryAnalysisInfo.RegTrend> getItem(int position) {
        return industryAInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.iteam_indutryanaylsis, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            setBarChart(holder.chart);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setDate(holder, position);
        return convertView;
    }


    private void setDate(ViewHolder holder, int position) {

        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        int max = 0;
        for (int i = 0; i < industryAInfoArrayList.get(position).size(); i++) {
            if (max < industryAInfoArrayList.get(position).get(i).getCnt()) {
                max = industryAInfoArrayList.get(position).get(i).getCnt();
            }
            barEntries.add(new BarEntry(i, industryAInfoArrayList.get(position).get(i).getCnt()));
        }

        if (max != 0) {
            holder.chart.getAxisLeft().setAxisMaximum((max / 4 + 1) * 4);//设置左边Y轴的最大数值
        } else {
            holder.chart.getAxisLeft().setAxisMaximum(200);//设置左边Y轴的最大数值

        }

        BarDataSet set1;
        if (holder.chart.getData() != null &&
                holder.chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) holder.chart.getData().getDataSetByIndex(0);
            set1.setValues(barEntries);
            holder.chart.getData().notifyDataChanged();
            holder.chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(barEntries, "T");

            set1.setDrawIcons(false);

//            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            set1.setValueTextColor(mContext.getResources().getColor(R.color.gray_9));
            set1.setValueTextSize(10);
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.4f);
            data.setValueFormatter(new DefaultValueFormatter(0) {
            });//保留整数，不带小数点

            holder.chart.setData(data);
        }
        for (IDataSet<?> set : holder.chart.getData().getDataSets())
            set.setDrawValues(true); //!set.isDrawValuesEnabled()

        setIteam(holder, position, set1);

        holder.chart.invalidate();
    }


    //初始化柱形图
    private void setBarChart(BarChart barChart) {

        barChart.getDescription().setEnabled(false);

//        mBarChart.setVisibleXRangeMaximum(20);
        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);
        barChart.setHighlightFullBarEnabled(false);

        barChart.setTouchEnabled(false); // 设置是否可以触摸
//        mBarChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放
        barChart.setScaleXEnabled(false); //启用X轴上的缩放
        barChart.setScaleYEnabled(false);//关闭y轴上的缩放

        XAxis xAxis = barChart.getXAxis();
//        xAxis.setLabelCount(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10);
        xAxis.setTextColor(mContext.getResources().getColor(R.color.gray_c));
        //设置底部线条
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        xAxis.setAxisLineWidth(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) (value) % mMonths.length];

            }

        });


        //设置左边Y轴标签
//        Log.d("asd","max:"+max);
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setLabelCount(5);
        leftAxis.setTextColor(mContext.getResources().getColor(R.color.gray_9));
        leftAxis.setTextSize(12f);
        leftAxis.setDrawAxisLine(false);


        leftAxis.enableGridDashedLine(8f, 8f, 0f);
        leftAxis.setGridColor(mContext.getResources().getColor(R.color.gray_e)); //X轴上的刻度竖线的颜色
//        yaxisleft.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        leftAxis.setAxisLineWidth(0.1f);
        leftAxis.setDrawGridLines(true);


//
        Legend mLegend = barChart.getLegend(); // 设置比例图标示

        //是否显示注释
        mLegend.setEnabled(false);

        //不显示两侧y轴
        barChart.getAxisRight().setEnabled(false);

        barChart.animateY(3000);
    }

    //设置不同的区域图颜色和标题
    private void setIteam(ViewHolder viewHolder, int position, BarDataSet set1) {
        if (position == 0) {
            viewHolder.tvIndutryTitle.setText(mContext.getResources().getString(R.string.industry_iteam1));
            set1.setColors(mContext.getResources().getColor(R.color.industry_2));
        }
        if (position == 1) {
            viewHolder.tvIndutryTitle.setText(mContext.getResources().getString(R.string.industry_iteam2));
            set1.setColors(mContext.getResources().getColor(R.color.industry_3));
        }
        if (position == 2) {
            viewHolder.tvIndutryTitle.setText(mContext.getResources().getString(R.string.industry_iteam3));
            set1.setColors(mContext.getResources().getColor(R.color.industry_4));
        }
        if (position == 3) {
            viewHolder.tvIndutryTitle.setText(mContext.getResources().getString(R.string.industry_iteam4));
            set1.setColors(mContext.getResources().getColor(R.color.industry_5));
        }

    }

    class ViewHolder {
        public TextView tvIndutryTitle;
        public BarChart chart;

        ViewHolder(View view) {
            tvIndutryTitle = view.findViewById(R.id.tv_indutry_title);
            chart = view.findViewById(R.id.chart);
        }
    }

}
