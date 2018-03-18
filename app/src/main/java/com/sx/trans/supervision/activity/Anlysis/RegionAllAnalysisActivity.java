package com.sx.trans.supervision.activity.Anlysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.RegionAnalysisPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.MapList;
import com.sx.trans.supervision.constants.RegionAllAnalsisInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.views.MenuViewItem;
import com.sx.trans.supervision.views.PublicView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by mr_wang on 2017/8/25.
 * 总市区区域分析
 */

public class RegionAllAnalysisActivity extends BaseActivity implements PublicView{

    protected String[] mMonths = new String[]{"遵义", "铜仁", "黔南", "贵阳", "黔东南", "安顺", "黔西南", "六盘水", "毕节"};//X轴标签
  private  MenuViewItem imzunyin;
  private  MenuViewItem imTongten;
  private  MenuViewItem ivBijie;
  private  MenuViewItem imGuiyang;
  private  MenuViewItem imDujun;
  private  MenuViewItem imKaili;
  private  MenuViewItem imLiupanshui;
  private  MenuViewItem imMapPnganshun;
  private  MenuViewItem imXingyi;
  private  BarChart mBarChart;
    private void initControls() {

        mBarChart = findViewById(R.id.barchart);
        imzunyin = findViewById(R.id.im_zunyi);
        imTongten = findViewById(R.id.im_tongten);
        ivBijie = findViewById(R.id.iv_bijie);
        imGuiyang = findViewById(R.id.im_guiyang);
        imDujun = findViewById(R.id.im_dujun);
        imKaili = findViewById(R.id.im_kaili);
        imLiupanshui = findViewById(R.id.im_liupanshui);
        imMapPnganshun = findViewById(R.id.im_map_anshun);
        imXingyi = findViewById(R.id.im_xingyi);
        tvZunyionline = findViewById(R.id.tv_zunyionline);
        tvZunyinet = findViewById(R.id.tv_zunyinet);
        tvTongrenonline = findViewById(R.id.tv_tongrenonline);
        tvTongrennet = findViewById(R.id.tv_tongrennet);
        tvBijieonline = findViewById(R.id.tv_bijieonline);
        tvBijienet = findViewById(R.id.tv_bijienet);
        tvGuiyangonline = findViewById(R.id.tv_guiyangonline);
        tvGuiyangnet = findViewById(R.id.tv_guiyangnet);
        tvDuyunonline = findViewById(R.id.tv_duyunonline);
        tvDuyunnet = findViewById(R.id.tv_duyunnet);
        tvKailionline = findViewById(R.id.tv_kailionline);
        tvKailinet = findViewById(R.id.tv_kailinet);
        tvLiupanonline = findViewById(R.id.tv_liupanonline);
        tvLiupannet = findViewById(R.id.tv_liupannet);
        tvAnshunonlien = findViewById(R.id.tv_anshunonlien);
        tvAnshunnet = findViewById(R.id.tv_anshunnet);
        tvXingyionline = findViewById(R.id.tv_xingyionline);
        tvXingyinet = findViewById(R.id.tv_xingyinet);

    }
  private  TextView tvZunyionline;
  private  TextView tvZunyinet;
  private  TextView tvTongrenonline;
  private  TextView tvTongrennet;
  private  TextView tvBijieonline;
  private  TextView tvBijienet;
  private  TextView tvGuiyangonline;
  private  TextView tvGuiyangnet;
  private  TextView tvDuyunonline;
  private  TextView tvDuyunnet;
  private  TextView tvKailionline;
  private  TextView tvKailinet;
  private  TextView tvLiupanonline;
  private  TextView tvLiupannet;
  private  TextView tvAnshunonlien;
  private  TextView tvAnshunnet;
  private  TextView tvXingyionline;
  private  TextView tvXingyinet;

    private RegionAllAnalsisInfo regionAllAnalsisInfo;
    private RegionAnalysisPresenter regionAnalysisPresenter;
    private Map<String,MapList> maplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regionanalysisall);
        initControls();
        init();
        regionAnalysisPresenter.getMapData();
//        TextDate();
    }

    private void init() {
        setTitle(true, "区域分析", false, null);
        setBarChart();
        regionAllAnalsisInfo=new RegionAllAnalsisInfo();
        regionAnalysisPresenter=new RegionAnalysisPresenter(this,this);
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
        xAxis.setLabelCount(9);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10);
        xAxis.setTextColor(getResources().getColor(R.color.gray_9));

//        xAxis.setSpaceBetweenLabels(2);//设置名字names之间的间距

        //设置底部线条
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        xAxis.setAxisLineWidth(1);
//        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) (value) % mMonths.length];
            }

        });

        YAxis yaxisleft = mBarChart.getAxisLeft();
        yaxisleft.setTextColor(getResources().getColor(R.color.gray_9));
        yaxisleft.setTextSize(12f);
        yaxisleft.setDrawAxisLine(false);

        yaxisleft.enableGridDashedLine(8f, 8f, 0f);
        yaxisleft.setGridColor(mContext.getResources().getColor(R.color.gray_e)); //X轴上的刻度竖线的颜色
//        yaxisleft.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        yaxisleft.setAxisLineWidth(0.1f);
        yaxisleft.setDrawGridLines(true);

        yaxisleft.setLabelCount(4);
        yaxisleft.setAxisMinimum(0);

        Legend mLegend = mBarChart.getLegend(); // 设置比例图标示

        //是否显示注释
        mLegend.setEnabled(false);
        //不显示两侧y轴
        mBarChart.getAxisRight().setEnabled(false);

        // add a nice and smooth animation
        mBarChart.animateY(3000);

        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        ArrayList<BarEntry> barEntries2 = new ArrayList<BarEntry>();
        for (int i = 0; i < 9; i++) {
            // turn your data into Entry objects
            barEntries.add(new BarEntry(i, 0));
            barEntries2.add(new BarEntry(i, 0));
        }
        getBarChartData(barEntries, barEntries2, 200);
    }

    //设置柱形图数据
    private void setBarEntry(){
        ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2=new ArrayList<BarEntry>();
        int max = 0;
        if (maplist==null) {
            for (int i = 0; i < 9; i++) {
                entries.add(new BarEntry(i, 0));
                entries2.add(new BarEntry(i, 0));
            }
        }
        if (maplist.get(IConstants.mSpre_mapName.ZunYi)!=null) {//遵义
            entries.add(new BarEntry(0, maplist.get(IConstants.mSpre_mapName.ZunYi).getVehCnt()));
            entries2.add(new BarEntry(0, maplist.get(IConstants.mSpre_mapName.ZunYi).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.ZunYi).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.ZunYi).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(0, 0));
            entries2.add(new BarEntry(0, 0));
        }
        if (maplist.get(IConstants.mSpre_mapName.TongRen)!=null) {//铜仁
            entries.add(new BarEntry(1, maplist.get(IConstants.mSpre_mapName.TongRen).getVehCnt()));
            entries2.add(new BarEntry(1, maplist.get(IConstants.mSpre_mapName.TongRen).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.TongRen).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.TongRen).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(1, 0));
            entries2.add(new BarEntry(1, 0));
        }

        if (maplist.get(IConstants.mSpre_mapName.DuYun)!=null) {//都匀
            entries.add(new BarEntry(2, maplist.get(IConstants.mSpre_mapName.DuYun).getVehCnt()));
            entries2.add(new BarEntry(2, maplist.get(IConstants.mSpre_mapName.DuYun).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.DuYun).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.DuYun).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(2, 0));
            entries2.add(new BarEntry(2, 0));
        }

        if (maplist.get(IConstants.mSpre_mapName.GuiYang)!=null) {//贵阳
            entries.add(new BarEntry(3, maplist.get(IConstants.mSpre_mapName.GuiYang).getVehCnt()));
            entries2.add(new BarEntry(3, maplist.get(IConstants.mSpre_mapName.GuiYang).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.GuiYang).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.GuiYang).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(3, 0));
            entries2.add(new BarEntry(3, 0));
        }

        if (maplist.get(IConstants.mSpre_mapName.KaiLi)!=null) {//凯里
            entries.add(new BarEntry(4, maplist.get(IConstants.mSpre_mapName.KaiLi).getVehCnt()));
            entries2.add(new BarEntry(4, maplist.get(IConstants.mSpre_mapName.KaiLi).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.KaiLi).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.KaiLi).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(4, 0));
            entries2.add(new BarEntry(4, 0));
        }


        if (maplist.get(IConstants.mSpre_mapName.AnShun)!=null) {//安顺市
            entries.add(new BarEntry(5, maplist.get(IConstants.mSpre_mapName.AnShun).getVehCnt()));
            entries2.add(new BarEntry(5, maplist.get(IConstants.mSpre_mapName.AnShun).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.AnShun).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.AnShun).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(5, 0));
            entries2.add(new BarEntry(5, 0));
        }

        if (maplist.get(IConstants.mSpre_mapName.XingYi)!=null) {//兴义
            entries.add(new BarEntry(6, maplist.get(IConstants.mSpre_mapName.XingYi).getVehCnt()));
            entries2.add(new BarEntry(6, maplist.get(IConstants.mSpre_mapName.XingYi).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.XingYi).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.XingYi).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(6, 0));
            entries2.add(new BarEntry(6, 0));
        }

        if (maplist.get(IConstants.mSpre_mapName.LiuPanShui)!=null) {//六盘水
            entries.add(new BarEntry(7, maplist.get(IConstants.mSpre_mapName.LiuPanShui).getVehCnt()));
            entries2.add(new BarEntry(7, maplist.get(IConstants.mSpre_mapName.LiuPanShui).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.LiuPanShui).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.LiuPanShui).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(7, 0));
            entries2.add(new BarEntry(7, 0));
        }

        if (maplist.get(IConstants.mSpre_mapName.BiJie)!=null) {//毕节市
            entries.add(new BarEntry(8, maplist.get(IConstants.mSpre_mapName.BiJie).getVehCnt()));
            entries2.add(new BarEntry(8, maplist.get(IConstants.mSpre_mapName.BiJie).getOnLineVehCnt()));
            if (max<maplist.get(IConstants.mSpre_mapName.BiJie).getVehCnt()){
                max=maplist.get(IConstants.mSpre_mapName.BiJie).getVehCnt();
            }
        }else{
            entries.add(new BarEntry(8, 0));
            entries2.add(new BarEntry(8, 0));
        }
        if (max!=0) {
            max = (max / 4 + 1) * 4;
        }else{
            max=200;
        }
        getBarChartData(entries,entries2,max);
    }
    public void getBarChartData(ArrayList<BarEntry> entries, ArrayList<BarEntry> entries2, int max) {

        if (max!=0) {
            mBarChart.getAxisLeft().setAxisMaximum(max);
        }
        BarDataSet set1, set2;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mBarChart.getData().getDataSetByIndex(1);
            set1.setValues(entries);
            set2.setValues(entries2);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, "Company A");
            set1.setColor(mContext.getResources().getColor(R.color.net));
            set2 = new BarDataSet(entries2, "Company B");
            set2.setColor(mContext.getResources().getColor(R.color.online));

//           set1.setColors(ColorTemplate.MATERIAL_COLORS);
            set1.setValueTextColor(getResources().getColor(R.color.gray_9));
            set2.setValueTextColor(getResources().getColor(R.color.gray_9));
            set1.setValueTextSize(10);
            set2.setValueTextSize(10);

            BarData data = new BarData(set1, set2);
            data.setValueTextSize(10f);
            mBarChart.setData(data);
        }

        //fromX表示从X轴开始的坐标
        float groupSpace = 0.4f;// groupSpace表示组之间的距离
        float barSpace = 0f;// barSpace表示组内柱子之间的距离
        float barWidth = 0.3f;
        //柱状图数据集
        //设置柱子宽度
        mBarChart.getBarData().setBarWidth(barWidth);
        mBarChart.groupBars(-0.5f, groupSpace, barSpace);

        for (IDataSet<?> set : mBarChart.getData().getDataSets())
            set.setDrawValues(false); //!set.isDrawValuesEnabled()
        mBarChart.invalidate();
    }

    //设置地图模块数据
    private void setMapDate(){
        if (maplist==null){
            return;
        }
        if (maplist.get(IConstants.mSpre_mapName.ZunYi)!=null) {//遵义
            tvZunyionline.setText(maplist.get(IConstants.mSpre_mapName.ZunYi).getOnLineVehCnt()+"");
            tvZunyinet.setText(maplist.get(IConstants.mSpre_mapName.ZunYi).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.TongRen)!=null) {//铜仁
            tvTongrenonline.setText(maplist.get(IConstants.mSpre_mapName.TongRen).getOnLineVehCnt()+"");
            tvTongrennet.setText(maplist.get(IConstants.mSpre_mapName.TongRen).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.BiJie)!=null) {//毕节市
            tvBijieonline.setText(maplist.get(IConstants.mSpre_mapName.BiJie).getOnLineVehCnt()+"");
            tvBijienet.setText(maplist.get(IConstants.mSpre_mapName.BiJie).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.GuiYang)!=null) {//贵阳
            tvGuiyangonline.setText(maplist.get(IConstants.mSpre_mapName.GuiYang).getOnLineVehCnt()+"");
            tvGuiyangnet.setText(maplist.get(IConstants.mSpre_mapName.GuiYang).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.LiuPanShui)!=null) {//六盘水
            tvLiupanonline.setText(maplist.get(IConstants.mSpre_mapName.LiuPanShui).getOnLineVehCnt()+"");
            tvLiupannet.setText(maplist.get(IConstants.mSpre_mapName.LiuPanShui).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.AnShun)!=null) {//安顺市
            tvAnshunonlien.setText(maplist.get(IConstants.mSpre_mapName.AnShun).getOnLineVehCnt()+"");
            tvAnshunnet.setText(maplist.get(IConstants.mSpre_mapName.AnShun).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.XingYi)!=null) {//黔西南
            tvXingyionline.setText(maplist.get(IConstants.mSpre_mapName.XingYi).getOnLineVehCnt()+"");
            tvXingyinet.setText(maplist.get(IConstants.mSpre_mapName.XingYi).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.DuYun)!=null) {//黔南
            tvDuyunonline.setText(maplist.get(IConstants.mSpre_mapName.DuYun).getOnLineVehCnt()+"");
            tvDuyunnet.setText(maplist.get(IConstants.mSpre_mapName.DuYun).getVehCnt()+"");
        }
        if (maplist.get(IConstants.mSpre_mapName.KaiLi)!=null) {//黔东南
            tvKailionline.setText(maplist.get(IConstants.mSpre_mapName.KaiLi).getOnLineVehCnt()+"");
            tvKailinet.setText(maplist.get(IConstants.mSpre_mapName.KaiLi).getVehCnt()+"");
        }
    }

    @OnClick({R.id.im_zunyi, R.id.iv_bijie, R.id.im_tongten, R.id.im_guiyang, R.id.im_dujun, R.id.im_kaili, R.id.im_liupanshui, R.id.im_map_anshun, R.id.im_xingyi})
    public void onViewClicked(View view) {
        Intent intent = new Intent(mContext, RegionAnalysisActivity.class);
        switch (view.getId()) {
            case R.id.im_zunyi:
                intent.putExtra("title", "遵义市");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.ZunYi)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.ZunYi).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_tongten:
                intent.putExtra("title", "铜仁市");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.TongRen)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.TongRen).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.iv_bijie:
                intent.putExtra("title", "毕节市");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.BiJie)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.BiJie).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_guiyang:
                intent.putExtra("title", "贵阳市");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.GuiYang)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.GuiYang).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_dujun:
                intent.putExtra("title", "黔南");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.DuYun)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.DuYun).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_kaili:
                intent.putExtra("title", "黔东南");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.KaiLi)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.KaiLi).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_liupanshui:
                intent.putExtra("title", "六盘水市");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.LiuPanShui)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.LiuPanShui).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_map_anshun:
                intent.putExtra("title", "安顺市");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.AnShun)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.AnShun).getAreaCode());
                }
                startActivity(intent);
                break;
            case R.id.im_xingyi:
                intent.putExtra("title", "黔西南");
                if (maplist!=null&&maplist.get(IConstants.mSpre_mapName.XingYi)!=null) {
                    intent.putExtra(IConstants.mSpre_Constants.AREACODE, maplist.get(IConstants.mSpre_mapName.XingYi).getAreaCode());
                }
                startActivity(intent);
                break;
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
            regionAllAnalsisInfo=result.getData(RegionAllAnalsisInfo.class);
            maplist=regionAllAnalsisInfo.getMap();
            setMapDate();
            setBarEntry();
        }
    }

    @Override
    public void showError(String error) {
        HideDiaLogLoading();
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}
