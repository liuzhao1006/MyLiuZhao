package com.sx.trans.company.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.SupportMapFragment;
import com.apkfuns.logutils.LogUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.adapter.WorkBenchBusinessAdapter;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.company.supervision.VehicleActivity;
import com.sx.trans.supervision.Presenter.HomePresnter;
import com.sx.trans.supervision.activity.Anlysis.IndustryAnalysisActivity;
import com.sx.trans.supervision.activity.company.CompanyDetailActivity;
import com.sx.trans.supervision.activity.SearchPublicCarActivity;
import com.sx.trans.supervision.activity.WarmAnaysisActivity;
import com.sx.trans.supervision.constants.Request.HomeAlarBean;
import com.sx.trans.supervision.constants.Request.HomeDAteBean;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.TradeList;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.views.RoundProgressBar;
import com.sx.trans.supervision.widget.ScrollGridView.PageControlView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/7.
 * 监管
 */

public class CompanyMonitorFragment extends SupportMapFragment implements PublicView, View.OnClickListener, BaseNetApi {


    private TextView headTitleTv;
    //    private TextView tvPlatform;
    private TextView tvVehilce;
    private TextView tvNet;
    private TextView tvOnline;
    private TextView tvWarm;
    private RoundProgressBar id_roundprogress;
    private RoundProgressBar id_roundprogress3;
    private RoundProgressBar id_roundprogress2;
    private RoundProgressBar id_roundprogress4;
    private PieChart piechart;
    //    private ImageView ivAllanalysis;
    private TextView tvZunyionline;
    private TextView tvZunyinet;
    private TextView tvTongrenonline;
    private TextView tvTongrennet;
    private TextView tvBijieonline;
    private TextView tvBijienet;
    private TextView tvGuiyangonline;
    private TextView tvGuiyangnet;
    private TextView tvDuyunonline;
    private TextView tvDuyunnet;
    private TextView tvKailionline;
    private TextView tvKailinet;
    private TextView tvLiupanonline;
    private TextView tvLiupannet;
    private TextView tvAnshunonlien;
    private TextView tvAnshunnet;
    private TextView tvXingyionline;
    private TextView tvXingyinet;
    private TextView tv_progress1;
    private TextView tv_progress2;
    private TextView tv_progress3;
    private TextView tv_progress4;
    private LinearLayout ll_progress1;
    private LinearLayout ll_progress2;
    private LinearLayout ll_progress3;
    private LinearLayout ll_progress4;
    private RelativeLayout iv_nodate;
    private LinearLayout ll_pirchat_title;
    private LinearLayout ll_progresstitle;
    private TextView tv_speed_warm;
    private TextView tv_fatigue_warm;
    private TextView tv_other_warm;
    private LinearLayout ll_latform;
    private LinearLayout ll_vehilce;
    private LinearLayout ll_net;
    private LinearLayout ll_online;
    private LinearLayout ll_warmBar;
    private LinearLayout ll_warm;

    //服务相关
//    private com.sx.trans.supervision.widget.ScrollGridView.ScrollLayout ScrollLayout;
    private PageControlView pageControl;
    private List<String> mList;

    public void initControls(View view) {
        headTitleTv = view.findViewById(R.id.headTitleTv);
//        tvPlatform = view.findViewById(R.id.tv_platform);
        tvVehilce = view.findViewById(R.id.tv_vehilce);
        tvNet = view.findViewById(R.id.tv_net);
        tvOnline = view.findViewById(R.id.tv_online);
        tvWarm = view.findViewById(R.id.tv_warm);
        id_roundprogress = view.findViewById(R.id.id_roundprogress);
        id_roundprogress3 = view.findViewById(R.id.id_roundprogress3);
        id_roundprogress2 = view.findViewById(R.id.id_roundprogress2);
        id_roundprogress4 = view.findViewById(R.id.id_roundprogress4);
        piechart = view.findViewById(R.id.piechart);
//        ivAllanalysis = view.findViewById(R.id.iv_allanalysis);
        tvZunyionline = view.findViewById(R.id.tv_zunyionline);
        tvZunyinet = view.findViewById(R.id.tv_zunyinet);
        tvTongrenonline = view.findViewById(R.id.tv_tongrenonline);
        tvTongrennet = view.findViewById(R.id.tv_tongrennet);
        tvBijieonline = view.findViewById(R.id.tv_bijieonline);
        tvBijienet = view.findViewById(R.id.tv_bijienet);
        tvGuiyangonline = view.findViewById(R.id.tv_guiyangonline);
        tvGuiyangnet = view.findViewById(R.id.tv_guiyangnet);
        tvDuyunonline = view.findViewById(R.id.tv_duyunonline);
        tvDuyunnet = view.findViewById(R.id.tv_duyunnet);
        tvKailionline = view.findViewById(R.id.tv_kailionline);
        tvKailinet = view.findViewById(R.id.tv_kailinet);
        tvLiupanonline = view.findViewById(R.id.tv_liupanonline);
        tvLiupannet = view.findViewById(R.id.tv_liupannet);
        tvAnshunonlien = view.findViewById(R.id.tv_anshunonlien);
        tvAnshunnet = view.findViewById(R.id.tv_anshunnet);
        tvXingyionline = view.findViewById(R.id.tv_xingyionline);
        tvXingyinet = view.findViewById(R.id.tv_xingyinet);
        tv_progress1 = view.findViewById(R.id.tv_progress1);
        tv_progress2 = view.findViewById(R.id.tv_progress2);
        tv_progress3 = view.findViewById(R.id.tv_progress3);
        tv_progress4 = view.findViewById(R.id.tv_progress4);
        ll_progress1 = view.findViewById(R.id.ll_progress1);
        ll_progress2 = view.findViewById(R.id.ll_progress2);
        ll_progress3 = view.findViewById(R.id.ll_progress3);
        ll_progress4 = view.findViewById(R.id.ll_progress4);
        iv_nodate = view.findViewById(R.id.iv_nodate);
        ll_pirchat_title = view.findViewById(R.id.ll_pirchat_title);
        ll_progresstitle = view.findViewById(R.id.ll_progresstitle);
        ll_progresstitle.setVisibility(View.GONE);
        tv_speed_warm = view.findViewById(R.id.tv_speed_warm);
        tv_fatigue_warm = view.findViewById(R.id.tv_fatigue_warm);
        tv_other_warm = view.findViewById(R.id.tv_other_warm);
//        ll_latform = view.findViewById(R.id.ll_latform);
        ll_vehilce = view.findViewById(R.id.ll_vehilce);
        ll_net = view.findViewById(R.id.ll_net);
        ll_online = view.findViewById(R.id.ll_online);
        ll_warmBar = view.findViewById(R.id.ll_warmBar);
        ll_warm = view.findViewById(R.id.ll_warm);

        ll_warmBar.setOnClickListener(this);
        ll_vehilce.setOnClickListener(this);
//        ll_latform.setOnClickListener(this);
        ll_net.setOnClickListener(this);
        ll_progresstitle.setOnClickListener(this);
        ll_online.setOnClickListener(this);
        ll_warm.setOnClickListener(this);
//        ivAllanalysis.setOnClickListener(this);
//        ScrollLayout = view.findViewById(R.id.ScrollLayout);
        pageControl = view.findViewById(R.id.pageControl);
    }

    private CompanyManager manager;
    private Context mContext;
    private HomePresnter homePresnter;
    private HomeDAteBean homedateinfo;
    private HomeAlarBean bean;
    private Dialog updataDig;
    private ImageView iv_progress, iv_progress2;
    private String[] nameStr = {"车辆定位", "实时视频", "轨迹回放"};
//    private String[] nameStr = {"车辆定位", "实时视频", "轨迹回放", "行业分析", "报警分析",
//            "平台分析"};

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        initControls(view);
        init();
//        homePresnter.getHomeDate();
        manager.getAlarmAll();
        manager.setqueryallAlarm(new CompanyManager.QueryAllAlarm() {
                                     @Override
                                     public void onQueryAlarmData(int what, List<HomeAlarBean> vehicleLedgetList) {
                                         LogUtils.i(vehicleLedgetList.size());
                                         if (vehicleLedgetList.size() != 0) {
                                             bean = vehicleLedgetList.get(0);
                                             tvWarm.setText(bean.getAlarmCount() + "");
                                             setPieChartData(bean.getOverSpeedCount(), bean.getTiredCount(), bean.getOtherAlarmCount());
//
                                         }
//                                         bean = vehicleLedgetList.get(0);
//                                         tvWarm.setText(bean.getAlarmCount() + "");
//                                         setPieChartData(bean.getOverSpeedCount(), bean.getTiredCount(), Integer.parseInt(bean.getOtherAlarmCount()));
//            setProgressNetDate(homedateinfo.getTrade());
                                     }
                                 }


        );
        manager.getSuperviseData();
        manager.setSuperviseData(new CompanyManager.QuerySupervise() {
            @Override
            public void onQuerySupervise(int what, List<HomeDAteBean> vehicleLedgetList) {
                if (vehicleLedgetList.size() != 0) {
                    homedateinfo = vehicleLedgetList.get(0);
                    setHeadDate(homedateinfo.getVehicleCount(), homedateinfo.getVehicleAccessCount(), homedateinfo.getVehicleOnlineCount(), homedateinfo.getAlarmVehicleCount());
                }
            }
        });
        mList = new ArrayList<>();
        for (int i = 0; i < nameStr.length; i++) {
            mList.add(nameStr[i]);
        }


        RecyclerView rlvWorkWork = (RecyclerView) view.findViewById(R.id.rlv_work_bench_work);
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        rlvWorkWork.setLayoutManager(manager2);
        WorkBenchBusinessAdapter adapterw = new WorkBenchBusinessAdapter(getActivity(), mList);
        rlvWorkWork.setAdapter(adapterw);
        adapterw.setOnItemClickListener(new WorkBenchBusinessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        //定位
                        startActivity(new Intent(mContext, VehicleActivity.class));
                        break;
                    case 1:
                        Intent intent = new Intent(mContext, SearchPublicCarActivity.class);
                        intent.putExtra("IntenType", 1);
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        Intent intent2 = new Intent(mContext, SearchPublicCarActivity.class);
                        intent2.putExtra("IntenType", 2);
                        mContext.startActivity(intent2);
                        break;
//                    case 3:
//                        mContext.startActivity(new Intent(mContext, RegionAllAnalysisActivity.class));
//                        break;
                    case 3:
//                        mContext.startActivity(new Intent(mContext, IndustryAnalysisActivity.class));
                        break;
                    case 4:
//                        mContext.startActivity(new Intent(mContext, WarmAnaysisActivity.class));
                        break;
////                    case 6:
////                        mContext.startActivity(new Intent(mContext, OwnerListActivity.class));
////                        break;
                    case 5:
//
                        break;
//                    case 8:
//                        mContext.startActivity(new Intent(mContext, PostSearchActivity.class));
//                        break;
                }

            }
        });
        return view;
    }

    private void init() {
        mContext = getActivity();
//        mContext = getActivity();
        manager = new CompanyManager(mContext, this);
        homePresnter = new HomePresnter(this, mContext);
        homedateinfo = new HomeDAteBean();

        headTitleTv.setText("监管");
        setPieChart();
        iniPorgress();


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
        if (code == 0) {

//            homedateinfo = result.getData(HomeDAteBean.class);
//            setHeadDate(homedateinfo.getVehicleCount(), homedateinfo.getVehicleAccessCount(), homedateinfo.getVehicleOnlineCount(), homedateinfo.getAlarmVehicleCount());


//
//                tvWarm.setText(walm + "");
//                setPieChartData(homedateinfo.getOverSpeedCount(), homedateinfo.getTiredCount(), homedateinfo.getOtherCnt());
//            setProgressNetDate(homedateinfo.getTrade());
//
//            homedateinfo = result.getData(HomeDAteBean.class);
//            setHeadDate(homedateinfo.getVehicleCount(), homedateinfo.getVehicleAccessCount(), homedateinfo.getVehicleOnlineCount(), homedateinfo.getAlarmVehicleCount());
//            setPieChartData(homedateinfo.getOverSpeedCount(), homedateinfo.getTiredCount(), homedateinfo.getOtherCnt());
//            setProgressNetDate(homedateinfo.getTrade());
//            setMapDate(homedateinfo.getMap());
        }
    }

    @Override
    public void showError(String error) {
        hideLoading();
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

    //设置圆环数据
    private void setPieChartData(int getOverSpCnt, int getTiredCnt, int getOtherCnt) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        int num = getOverSpCnt + getTiredCnt + getOtherCnt;
        DecimalFormat df = new DecimalFormat("#0.00");

        if (getOverSpCnt != 0) {
            colors.add(Color.rgb(28, 152, 248));
            entries.add(new PieEntry(getOverSpCnt));
            tv_speed_warm.setText(df.format((double) getOverSpCnt / num * 100) + "%");
        } else {
            tv_speed_warm.setText("0.00%");
        }
        if (getTiredCnt != 0) {
            colors.add(Color.rgb(245, 209, 101));
            entries.add(new PieEntry(getTiredCnt));
            tv_fatigue_warm.setText(df.format((double) getTiredCnt / num * 100) + "%");
        } else {
            tv_fatigue_warm.setText("0.00%");
        }
        if (getOtherCnt != 0) {
            colors.add(Color.rgb(223, 224, 225));
            entries.add(new PieEntry(getOtherCnt));
            tv_other_warm.setText(df.format((double) getOtherCnt / num * 100) + "%");
        } else {
            tv_other_warm.setText("0.00%");
        }

        if (entries == null || entries.size() == 0) {
            iv_nodate.setVisibility(View.VISIBLE);
            ll_pirchat_title.setVisibility(View.GONE);
            return;
        } else {
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

    //设置行业入网车辆占比
    private void setProgressNetDate(ArrayList<TradeList> tradelist) {
        ll_progress1.setVisibility(View.GONE);
        ll_progress2.setVisibility(View.GONE);
        ll_progress3.setVisibility(View.GONE);
        ll_progress4.setVisibility(View.GONE);
        for (int i = 0; i < tradelist.size(); i++) {
            if (i == 0) {
                id_roundprogress.setMax(tradelist.get(i).getVehCnt());
                id_roundprogress.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress1.setText(tradelist.get(i).getName());
                ll_progress1.setVisibility(View.VISIBLE);
            }
            if (i == 1) {
                id_roundprogress2.setMax(tradelist.get(i).getVehCnt());
                id_roundprogress2.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress2.setText(tradelist.get(i).getName());
                ll_progress2.setVisibility(View.VISIBLE);
            }
            if (i == 2) {
                id_roundprogress3.setMax(tradelist.get(i).getVehCnt());
                id_roundprogress3.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress3.setText(tradelist.get(i).getName());
                ll_progress3.setVisibility(View.VISIBLE);
            }
            if (i == 3) {
                id_roundprogress4.setMax(tradelist.get(i).getVehCnt());
                id_roundprogress4.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress4.setText(tradelist.get(i).getName());
                ll_progress4.setVisibility(View.VISIBLE);
            }
        }
    }

    //设置头标题数据
    private void setHeadDate(int Vehilce, int Net, int Online, int walm) {
//        tvPlatform.setText(Platform + "");
        tvVehilce.setText(Vehilce + "");
        tvNet.setText(Net + "");
        tvOnline.setText(Online + "");
//        tvWarm.setText(walm + "");
    }

    //设置地图模块数据
//    private void setMapDate(Map<String, MapList> maplist) {
//        if (maplist.get(IConstants.mSpre_mapName.ZunYi) != null) {//遵义
//            tvZunyionline.setText(maplist.get(IConstants.mSpre_mapName.ZunYi).getOnLineVehCnt() + "");
//            tvZunyinet.setText(maplist.get(IConstants.mSpre_mapName.ZunYi).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.TongRen) != null) {//铜仁
//            tvTongrenonline.setText(maplist.get(IConstants.mSpre_mapName.TongRen).getOnLineVehCnt() + "");
//            tvTongrennet.setText(maplist.get(IConstants.mSpre_mapName.TongRen).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.BiJie) != null) {//毕节市
//            tvBijieonline.setText(maplist.get(IConstants.mSpre_mapName.BiJie).getOnLineVehCnt() + "");
//            tvBijienet.setText(maplist.get(IConstants.mSpre_mapName.BiJie).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.GuiYang) != null) {//贵阳
//            tvGuiyangonline.setText(maplist.get(IConstants.mSpre_mapName.GuiYang).getOnLineVehCnt() + "");
//            tvGuiyangnet.setText(maplist.get(IConstants.mSpre_mapName.GuiYang).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.LiuPanShui) != null) {//六盘水
//            tvLiupanonline.setText(maplist.get(IConstants.mSpre_mapName.LiuPanShui).getOnLineVehCnt() + "");
//            tvLiupannet.setText(maplist.get(IConstants.mSpre_mapName.LiuPanShui).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.AnShun) != null) {//安顺市
//            tvAnshunonlien.setText(maplist.get(IConstants.mSpre_mapName.AnShun).getOnLineVehCnt() + "");
//            tvAnshunnet.setText(maplist.get(IConstants.mSpre_mapName.AnShun).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.XingYi) != null) {//黔西南
//            tvXingyionline.setText(maplist.get(IConstants.mSpre_mapName.XingYi).getOnLineVehCnt() + "");
//            tvXingyinet.setText(maplist.get(IConstants.mSpre_mapName.XingYi).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.DuYun) != null) {//黔南
//            tvDuyunonline.setText(maplist.get(IConstants.mSpre_mapName.DuYun).getOnLineVehCnt() + "");
//            tvDuyunnet.setText(maplist.get(IConstants.mSpre_mapName.DuYun).getVehCnt() + "");
//        }
//        if (maplist.get(IConstants.mSpre_mapName.KaiLi) != null) {//黔东南
//            tvKailionline.setText(maplist.get(IConstants.mSpre_mapName.KaiLi).getOnLineVehCnt() + "");
//            tvKailinet.setText(maplist.get(IConstants.mSpre_mapName.KaiLi).getVehCnt() + "");
//        }
//    }

    //显示加载动画
    public void showDiaLogLoading(boolean cancelable) {
        updataDig = new Dialog(mContext, R.style.loading_dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.loading_dialog, null);
        updataDig.setContentView(view);
        updataDig.setCancelable(cancelable);//设置背景是否可以点击
        iv_progress = (ImageView) view.findViewById(R.id.iv_progress);
        iv_progress2 = (ImageView) view.findViewById(R.id.iv_progress2);
        Animation();
        updataDig.show();
    }

    //隐藏加载动画
    public void HideDiaLogLoading() {
        if (iv_progress != null) {
            iv_progress.clearAnimation();
        }
        if (iv_progress2 != null) {
            iv_progress2.clearAnimation();
        }
        if (updataDig != null)
            updataDig.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.ll_vehilce://车辆
                startActivity(new Intent(getActivity(), CompanyDetailActivity.class));
//                Intent intent = new Intent(getActivity(), CompanyDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("CompanyMonitorFragment.class", bean);
//                intent.putExtra("CompanyMonitorFragment", bundle);
//                startActivity(intent);
                break;
            case R.id.ll_net://入网
                startActivity(new Intent(getActivity(), CompanyDetailActivity.class));
//                Intent intent1 = new Intent(getActivity(), CompanyDetailActivity.class);
//                Bundle bundle1 = new Bundle();
//                bundle1.putSerializable("CompanyMonitorFragment.class", bean);
//                intent1.putExtra("CompanyMonitorFragment", bundle1);
//                startActivity(intent1);
                break;
            case R.id.ll_online://在线
                startActivity(new Intent(getActivity(), CompanyDetailActivity.class));
//                Intent intent2 = new Intent(getActivity(), CompanyDetailActivity.class);
//                Bundle bundle2 = new Bundle();
//                bundle2.putSerializable("CompanyMonitorFragment.class", bean);
//                intent2.putExtra("CompanyMonitorFragment", bundle2);
//                startActivity(intent2);
                break;
            case R.id.ll_warm://报警
//                startActivity(new Intent(getActivity(), WarmAnaysisActivity.class));
                Intent intent3 = new Intent(getActivity(), WarmAnaysisActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable("CompanyMonitorFragment.class", bean);
                intent3.putExtra("CompanyMonitorFragment", bundle3);
                startActivity(intent3);
                break;
            case R.id.ll_progresstitle://行业
                startActivity(new Intent(getActivity(), IndustryAnalysisActivity.class));
                break;
            case R.id.ll_warmBar://报警分析
//                startActivity(new Intent(getActivity(), WarmAnaysisActivity.class));
                Intent intent1 = new Intent(getActivity(), WarmAnaysisActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("CompanyMonitorFragment.class", bean);
                intent1.putExtra("CompanyMonitorFragment", bundle1);
                startActivity(intent1);
                break;
        }
    }

    //初始化进度条
    private void iniPorgress() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);  //size.x就是宽度，size.y就是高度

        id_roundprogress2.setCricleColor(getResources().getColor(R.color.home_blueprogress2));
        id_roundprogress2.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress22));
        id_roundprogress3.setCricleColor(getResources().getColor(R.color.home_blueprogress3));
        id_roundprogress3.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress33));
        id_roundprogress4.setCricleColor(getResources().getColor(R.color.home_blueprogress4));
        id_roundprogress4.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress44));

        if (size.x >= 720 && size.x <= 800 && size.y >= 1100) {
            id_roundprogress.setTextSize(20);
            id_roundprogress.setRoundWidth(10);
            id_roundprogress2.setTextSize(20);
            id_roundprogress2.setRoundWidth(10);
            id_roundprogress3.setTextSize(20);
            id_roundprogress3.setRoundWidth(10);
            id_roundprogress4.setTextSize(20);
            id_roundprogress4.setRoundWidth(10);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //控制动画
    public void Animation() {
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotate2 = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //默认为0，为-1时一直循环动画
        rotate.setRepeatCount(-1);
        rotate2.setRepeatCount(-1);
        rotate.setDuration(1000);
        rotate2.setDuration(1000);
        rotate.setFillAfter(true);
        rotate2.setFillAfter(true);
        LinearInterpolator lir = new LinearInterpolator();
        rotate.setInterpolator(lir);
        rotate2.setInterpolator(lir);
        iv_progress.startAnimation(rotate);
        iv_progress2.startAnimation(rotate2);
    }


    @Override
    public void netStart() {

    }

    @Override
    public void netStop() {

    }

    @Override
    public void netSuccessed(int what, String data) {

    }

    @Override
    public void netFailed(int what, String message) {

    }
}
