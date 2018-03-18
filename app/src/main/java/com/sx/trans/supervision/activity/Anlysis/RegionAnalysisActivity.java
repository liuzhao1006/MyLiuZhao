package com.sx.trans.supervision.activity.Anlysis;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.RegionAnalysisPresenter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.RegionAnalsisInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.TradeList;
import com.sx.trans.supervision.utils.DateUtils;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.views.RoundProgressBar;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.sx.trans.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/8/25.
 * 各市区区域分析
 */

public class RegionAnalysisActivity extends BaseActivity implements PublicView{
  private PieChart piechart;
  private BarChart mBarChart;
  private RoundProgressBar idRoundprogress;
  private RoundProgressBar idRoundprogress2;
  private RoundProgressBar idRoundprogress3;
  private RoundProgressBar idRoundprogress4;
  private TextView tvVehicle;

    private void initControls(){

        piechart = findViewById(R.id.piechart);
        mBarChart = findViewById(R.id.barchart);
        idRoundprogress = findViewById(R.id.id_roundprogress);
        idRoundprogress2 = findViewById(R.id.id_roundprogress2);
        idRoundprogress3 = findViewById(R.id.id_roundprogress3);
        idRoundprogress4 = findViewById(R.id.id_roundprogress4);
        tvVehicle = findViewById(R.id.tv_vehicle);
        tvNet = findViewById(R.id.tv_net);
        tvNetbai = findViewById(R.id.tv_netbai);
        tvOnline = findViewById(R.id.tv_online);
        tvWarm = findViewById(R.id.tv_warm);
        iv_nodate = findViewById(R.id.iv_nodate);
        ll_pirchat_title = findViewById(R.id.ll_pirchat_title);
        tv_speed_warm = findViewById(R.id.tv_speed_warm);
        tv_fatigue_warm = findViewById(R.id.tv_fatigue_warm);
        tv_other_warm = findViewById(R.id.tv_other_warm);
        tv_progress1 = findViewById(R.id.tv_progress1);
        tv_progress2 = findViewById(R.id.tv_progress2);
        tv_progress3 = findViewById(R.id.tv_progress3);
        tv_progress4 = findViewById(R.id.tv_progress4);
        ll_progress1 = findViewById(R.id.ll_progress1);
        ll_progress2 = findViewById(R.id.ll_progress2);
        ll_progress3 = findViewById(R.id.ll_progress3);
        ll_progress4 = findViewById(R.id.ll_progress4);

    }
  private TextView tvNet;
  private TextView tvNetbai;
  private TextView tvOnline;
  private TextView tvWarm;
  private RelativeLayout iv_nodate;
  private LinearLayout ll_pirchat_title;
  private TextView tv_speed_warm;
  private TextView tv_fatigue_warm;
  private TextView tv_other_warm;
  private TextView tv_progress1;
  private TextView tv_progress2;
  private TextView tv_progress3;
  private TextView tv_progress4;
  private LinearLayout ll_progress1;
  private LinearLayout ll_progress2;
  private LinearLayout ll_progress3;
  private LinearLayout ll_progress4;

    protected String[] mMonths = new String[]{"二月", "三月", "四月", "五月", "六月", "七月"};//X轴标签
    private RegionAnalsisInfo regionAnalsisInfo;
    private RegionAnalysisPresenter regionAnalysisPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regionanalysis);
        initControls();
        init();
    }

    private void init() {
        regionAnalsisInfo=new RegionAnalsisInfo();
        regionAnalysisPresenter=new RegionAnalysisPresenter(this,this);
        setTitle(true, getIntent().getStringExtra("title"), false, null);
        setPieChart();
        for (int i = 0; i <=5; i++) {
            mMonths[5 - i] = DateUtils.getMonth(i);
        }
        setBarChart();
        iniPorgress();

        if (getIntent().getStringExtra(IConstants.mSpre_Constants.AREACODE)!=null) {
            regionAnalysisPresenter.getMapMoreData(getIntent().getStringExtra(IConstants.mSpre_Constants.AREACODE));
        }else{
            iv_nodate.setVisibility(View.VISIBLE);
            piechart.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        showDiaLogLoading(false);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    @Override
    public void Success(Result result, int code) {
        if (code==0){
            regionAnalsisInfo=result.getData(RegionAnalsisInfo.class);
            String NetBai="";
            if (regionAnalsisInfo.getVehCnt()!=0){
                DecimalFormat df   =new DecimalFormat("#0.0");
                NetBai=df.format(((double)regionAnalsisInfo.getRegVehCnt()/regionAnalsisInfo.getVehCnt())*100)+"%";
            }else{
                NetBai="0%";
            }
            setHeadDate(regionAnalsisInfo.getVehCnt(),regionAnalsisInfo.getRegVehCnt(),NetBai,regionAnalsisInfo.getOnLineVehCnt(),regionAnalsisInfo.getAlarmCnt());
            getPieChartData(regionAnalsisInfo.getOverSpCnt(),regionAnalsisInfo.getTiredCnt(),regionAnalsisInfo.getOtherAlarmCnt());
            setProgressNetDate(regionAnalsisInfo.getTrade());

            ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
            ArrayList<RegionAnalsisInfo.RegTrent>  regTrents=getRegTrend(regionAnalsisInfo.getRegTrent());
            int max=0;
            for (int i=0;i<6;i++){
                int cnt=regTrents.get(i).getCnt();
                if (max<cnt){
                    max=cnt;
                }
                barEntries.add(new BarEntry(i,cnt));
            }
            getBarChartData(barEntries,max);
        }
    }

    @Override
    public void showError(String error) {
        hideLoading();
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    //设置标题栏数据
    private void setHeadDate(int Vehicle,int Net,String NetBai,int Online,int Warm){
        tvVehicle.setText(Vehicle+"");
        tvNet.setText(Net+"");
        tvNetbai.setText(NetBai+"");
        tvOnline.setText(Online+"");
        tvWarm.setText(Warm+"");
    }

    //设置行业入网车辆占比
    private void setProgressNetDate(ArrayList<TradeList> tradelist) {
        ll_progress1.setVisibility(View.GONE);
        ll_progress2.setVisibility(View.GONE);
        ll_progress3.setVisibility(View.GONE);
        ll_progress4.setVisibility(View.GONE);
        for (int i = 0; i < tradelist.size(); i++) {
            if (i==0) {
                idRoundprogress.setMax(tradelist.get(i).getTotalRegVehCnt());
                idRoundprogress.setProgress(tradelist.get(i).getRegVehCnt());
                tv_progress1.setText(tradelist.get(i).getName());
                ll_progress1.setVisibility(View.VISIBLE);
            }
            if (i==1) {
                idRoundprogress2.setMax(tradelist.get(i).getTotalRegVehCnt());
                idRoundprogress2.setProgress(tradelist.get(i).getRegVehCnt());
                tv_progress2.setText(tradelist.get(i).getName());
                ll_progress2.setVisibility(View.VISIBLE);
            }
            if (i==2) {
                idRoundprogress3.setMax(tradelist.get(i).getTotalRegVehCnt());
                idRoundprogress3.setProgress(tradelist.get(i).getRegVehCnt());
                tv_progress3.setText(tradelist.get(i).getName());
                ll_progress3.setVisibility(View.VISIBLE);
            }
            if (i==3) {
                idRoundprogress4.setMax(tradelist.get(i).getTotalRegVehCnt());
                idRoundprogress4.setProgress(tradelist.get(i).getRegVehCnt());
                tv_progress4.setText(tradelist.get(i).getName());
                ll_progress4.setVisibility(View.VISIBLE);
            }
        }
    }

    //设置圆环数据
    public void getPieChartData(int getOverSpCnt, int getTiredCnt, int getOtherCnt) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        DecimalFormat df   =new   DecimalFormat("#0.00");
        int num=getOverSpCnt+getTiredCnt+getOtherCnt;
        if (getOverSpCnt != 0) {
            colors.add(Color.rgb(28, 152, 248));
            entries.add(new PieEntry(getOverSpCnt));
            tv_speed_warm.setText(df.format((double) getOverSpCnt / num*100) + "%");
        }else{
            tv_speed_warm.setText("0.00%");
        }
        if (getTiredCnt != 0) {
            colors.add(Color.rgb(245, 209, 101));
            entries.add(new PieEntry(getTiredCnt));
            tv_fatigue_warm.setText(df.format((double) getTiredCnt / num*100) + "%");
        }else{
            tv_fatigue_warm.setText("0.00%");
        }
        if (getOtherCnt != 0) {
            colors.add(Color.rgb(184, 233, 134));
            entries.add(new PieEntry(getOtherCnt));
            tv_other_warm.setText(df.format((double) getOtherCnt / num*100) + "%");
        }else{
            tv_other_warm.setText("0.00%");
        }

        if (entries==null||entries.size()==0) {
            iv_nodate.setVisibility(View.VISIBLE);
            ll_pirchat_title.setVisibility(View.GONE);
            return;
        }else{
            iv_nodate.setVisibility(View.GONE);
            ll_pirchat_title.setVisibility(View.VISIBLE);
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawValues(true);//是否在块上面显示值以及百分百
        dataSet.setSliceSpace(2f);//块间距

        dataSet.setDrawIcons(false);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//        for (int c : MPChartLibColor.RegionAnaylsisColor)
//            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        piechart.setData(data);

        // undo all highlights
        piechart.highlightValues(null);
        piechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        for (IDataSet<?> set : piechart.getData().getDataSets())
            set.setDrawValues(false); //!set.isDrawValuesEnabled()
        piechart.invalidate();
    }

    //对趋势数据进行第二次处理
    private ArrayList<RegionAnalsisInfo.RegTrent> getRegTrend(ArrayList<RegionAnalsisInfo.RegTrent> regTrendArrayList){
        ArrayList<RegionAnalsisInfo.RegTrent> regTrends=new   ArrayList<RegionAnalsisInfo.RegTrent>();

        for (int i = 0; i <=5; i++){
            RegionAnalsisInfo.RegTrent regTrendd=new RegionAnalsisInfo.RegTrent();
            regTrendd.setMonth(DateUtils.getMonthYear(5-i));
            regTrendd.setCnt(0);
            regTrends.add(regTrendd);
        }

        if (regTrendArrayList!=null&&regTrendArrayList.size()>0) {
            for (RegionAnalsisInfo.RegTrent regTrend : regTrends) {
                b:
                for (RegionAnalsisInfo.RegTrent regTrendd : regTrendArrayList) {
                    if (regTrend.getMonth().equals(regTrendd.getMonth().substring(regTrendd.getMonth().length() - 2, regTrendd.getMonth().length()))) {
                        regTrend.setCnt(regTrendd.getCnt());
                        break b;
                    }
                }
            }
        }
        return regTrends;
    }
    //设置柱形图数据
    public void getBarChartData(ArrayList<BarEntry> entries, int max) {
        if (max!=0){
            mBarChart.getAxisLeft().setAxisMaximum(max+5);
        }
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(entries);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, "T");

            set1.setDrawIcons(false);

//            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            set1.setColors(mContext.getResources().getColor(R.color.home_blueprogress22));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            set1.setValueTextColor(getResources().getColor(R.color.gray_9));
            set1.setValueTextSize(10);
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.4f);

            mBarChart.setData(data);
        }
        for (IDataSet<?> set : mBarChart.getData().getDataSets())
            set.setDrawValues(true); //!set.isDrawValuesEnabled()
        mBarChart.invalidate();
    }

    //初始化圆环属性
    private void setPieChart() {
        piechart.setUsePercentValues(true);//是否使用百分比
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(0, 0, 0, 0);// 圆环距离屏幕上下左右的距离

        piechart.setDragDecelerationFrictionCoef(0.95f);

        //无数据显示
        piechart.setNoDataText("");

        //圆环中间部分
        piechart.setCenterTextColor(getResources().getColor(R.color.gray_9));
        piechart.setDrawCenterText(true);
        piechart.setDrawHoleEnabled(true);//是否显示中间的洞
        piechart.setHoleColor(Color.WHITE);//设置中间的颜色
//        piechart.setHoleRadius(60f);//洞的半径

        /**
         * 设置圆环透明度及半径
         */
        piechart.setTransparentCircleColor(Color.WHITE);
        piechart.setTransparentCircleAlpha(110);
        piechart.setHoleRadius(58f);//半径
        piechart.setTransparentCircleRadius(61f);//设置大圆里面透明小圆半径，和洞不是一个圆


        /**
         * 是否可以旋转，且设置旋转后的度数
         */
        piechart.setRotationAngle(0);
        // enable rotation of the chart by touch
        piechart.setRotationEnabled(true);
        piechart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener

//        piechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        //设置是否显示比例块
        Legend l = piechart.getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
//        l.setWordWrapEnabled(true);
        // entry label styling
        piechart.setEntryLabelColor(Color.WHITE);
//        piechart.setEntryLabelTypeface(mTfRegular);
        piechart.setEntryLabelTextSize(12f);

    }

    //初始化柱形图
    private void setBarChart() {

        mBarChart.getDescription().setEnabled(false);

//        mBarChart.setVisibleXRangeMaximum(20);
        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setHighlightFullBarEnabled(false);

        mBarChart.setTouchEnabled(false); // 设置是否可以触摸
//        mBarChart.setDragEnabled(true);// 是否可以拖拽
        mBarChart.setScaleEnabled(false);// 是否可以缩放
        mBarChart.setScaleXEnabled(false); //启用X轴上的缩放
        mBarChart.setScaleYEnabled(false);//关闭y轴上的缩放

        XAxis xAxis = mBarChart.getXAxis();
//        xAxis.setLabelCount(15);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10);
        xAxis.setTextColor(getResources().getColor(R.color.gray_9));

//        xAxis.setSpaceBetweenLabels(2);//设置名字names之间的间距

        //设置底部线条
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(getResources().getColor(R.color.gray_e));
        xAxis.setAxisLineWidth(1f);
//        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) (value) % mMonths.length];
            }

        });

        YAxis yaxisleft = mBarChart.getAxisLeft();
        yaxisleft.setTextColor(getResources().getColor(R.color.gray_9));
        yaxisleft.setLabelCount(4);
        yaxisleft.setTextSize(12f);
        yaxisleft.setDrawAxisLine(false);

        yaxisleft.enableGridDashedLine(8f, 8f, 0f);
        yaxisleft.setGridColor(mContext.getResources().getColor(R.color.gray_e)); //X轴上的刻度竖线的颜色
//        yaxisleft.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        yaxisleft.setAxisLineWidth(0.1f);
        yaxisleft.setDrawGridLines(true);

        yaxisleft.setAxisMaximum(200);//设置左边Y轴的最大数值
        yaxisleft.setAxisMinimum(0);

//
        Legend mLegend = mBarChart.getLegend(); // 设置比例图标示

        //是否显示注释
        mLegend.setEnabled(false);
        //不显示两侧y轴
        mBarChart.getAxisRight().setEnabled(false);

        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        barEntries.add(new BarEntry(0, (float) 0));
        barEntries.add(new BarEntry(1, (float) 0));
        barEntries.add(new BarEntry(2, (float) 0));
        barEntries.add(new BarEntry(3, (float) 0));
        barEntries.add(new BarEntry(4, (float) 0));
        barEntries.add(new BarEntry(5, (float) 0));
        getBarChartData(barEntries, 200);

        // add a nice and smooth animation
        mBarChart.animateY(3000);
    }
    //初始化进度条
    private void  iniPorgress() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);  //size.x就是宽度，size.y就是高度

        idRoundprogress2.setCricleColor(getResources().getColor(R.color.home_blueprogress2));
        idRoundprogress2.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress22));
        idRoundprogress3.setCricleColor(getResources().getColor(R.color.home_blueprogress3));
        idRoundprogress3.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress33));
        idRoundprogress4.setCricleColor(getResources().getColor(R.color.home_blueprogress4));
        idRoundprogress4.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress44));

        Log.d("asd","size.x:"+size.x+",size.y:"+size.y);
        if (size.x>=720&&size.x<=800&&size.y>=1100){
            idRoundprogress.setTextSize(20);
            idRoundprogress.setRoundWidth(10);
            idRoundprogress2.setTextSize(20);
            idRoundprogress2.setRoundWidth(10);
            idRoundprogress3.setTextSize(20);
            idRoundprogress3.setRoundWidth(10);
            idRoundprogress4.setTextSize(20);
            idRoundprogress4.setRoundWidth(10);
        }
    }

}
