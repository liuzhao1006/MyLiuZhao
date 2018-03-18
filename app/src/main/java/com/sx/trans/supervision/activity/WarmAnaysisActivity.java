package com.sx.trans.supervision.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.login.bean.Company;
import com.sx.trans.supervision.Presenter.WarmAlarmAnalysePresnter;
import com.sx.trans.R;

import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.constants.Request.ALarmListBean;
import com.sx.trans.supervision.constants.Request.HomeAlarBean;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.WarmAnaysisInfo;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.views.RoundProgressBar;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/8/27.
 * 报警分析界面
 */

public class WarmAnaysisActivity extends BaseActivity implements PublicView, BaseNetApi {
    private static final String[] arrayType = new String[]{};
    private ArrayAdapter<String> adapter;
    private TextView tvVehicle;
    private TextView tvOnline;
    private TextView tvSpeeding;
    private TextView tvFatigue;
    private TextView tvOther;
    private PieChart piechart;
    private RoundProgressBar idRoundprogress;
    private RoundProgressBar idRoundprogress2;
    private RoundProgressBar idRoundprogress3;
    private Spinner spinnerType;//选择类型
    private ALarmListBean bean1;
    private ArrayList<ALarmListBean> mList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();

    private void initControls() {
        companyname=findViewById(R.id.companyname);
        spinnerType = findViewById(R.id.spinnerType);
        tvVehicle = findViewById(R.id.tv_vehicle);
        tvOnline = findViewById(R.id.tv_online);
        tvSpeeding = findViewById(R.id.tv_Speeding);
        tvFatigue = findViewById(R.id.tv_fatigue);
        tvOther = findViewById(R.id.tv_other);
        piechart = findViewById(R.id.piechart);
        idRoundprogress = findViewById(R.id.id_roundprogress);
        idRoundprogress2 = findViewById(R.id.id_roundprogress2);
        idRoundprogress3 = findViewById(R.id.id_roundprogress3);
        tv_progress3 = findViewById(R.id.tv_progress3);
        tv_progress2 = findViewById(R.id.tv_progress2);
        tv_progress1 = findViewById(R.id.tv_progress1);
        iv_nodate = findViewById(R.id.iv_nodate);
        ll_pirchat_title = findViewById(R.id.ll_pirchat_title);
        tv_speed_warm = findViewById(R.id.tv_speed_warm);
        tv_fatigue_warm = findViewById(R.id.tv_fatigue_warm);
        tv_other_warm = findViewById(R.id.tv_other_warm);


    }
    private TextView companyname;
    private TextView tv_progress3;
    private TextView tv_progress2;
    private TextView tv_progress1;
    private RelativeLayout iv_nodate;
    private LinearLayout ll_pirchat_title;
    private TextView tv_speed_warm;
    private TextView tv_fatigue_warm;
    private TextView tv_other_warm;
    private HomeAlarBean bean;
    private CompanyManager manager;
    private WarmAlarmAnalysePresnter warmAlarmAnalysePresnter;
    private WarmAnaysisInfo warmAnaysisInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmays);
        manager = new CompanyManager(mContext, this);
        nameList.add("全部");
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("CompanyMonitorFragment");

        initControls();
        init();
        manager.getAlarmlist();
        manager.setQueryAlarmList(new CompanyManager.QueryAlarmList() {


            @Override
            public void onQueryAlarmDataList(int what, List<ALarmListBean> vehicleLedgetList) {

                mList.clear();
                nameList.clear();
                nameList.add("全部");
                for (int i = 0; i < vehicleLedgetList.size(); i++) {
                    mList.add(vehicleLedgetList.get(i));
                    nameList.add(vehicleLedgetList.get(i).getCompanyName());
                }
                setCoordinate(spinnerType, nameList);
            }
        });

        if (data != null) {
            bean = (HomeAlarBean) data.getSerializable("CompanyMonitorFragment.class");
            ;


        }


//        warmAlarmAnalysePresnter.getAlarmAnalyse();
    }

    private void init() {
        setTitle(true, getString(R.string.warm_title), false, null);
//        warmAlarmAnalysePresnter = new WarmAlarmAnalysePresnter(this, this);
//        warmAnaysisInfo = new WarmAnaysisInfo();
        setPieChart();
        idRoundprogress.setCricleColor(getResources().getColor(R.color.warm_speedingwarm));
        idRoundprogress.setCricleProgressColor(getResources().getColor(R.color.warm_speedingwarm2));
        idRoundprogress.setRoundWidth(10);
        idRoundprogress2.setCricleColor(getResources().getColor(R.color.warm_fatiguewarm));
        idRoundprogress2.setCricleProgressColor(getResources().getColor(R.color.warm_fatiguewarm2));
        idRoundprogress2.setRoundWidth(10);
        idRoundprogress3.setCricleColor(getResources().getColor(R.color.warm_otherwarm));
        idRoundprogress3.setCricleProgressColor(getResources().getColor(R.color.warm_otherwarm2));
        idRoundprogress3.setRoundWidth(10);
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
//        if (code == 0) {
//            warmAnaysisInfo = result.getData(WarmAnaysisInfo.class);
//            getPieChartData(warmAnaysisInfo.getOverSpCnt(), warmAnaysisInfo.getTiredCnt(), warmAnaysisInfo.getOtherCnt());
//            setProgressNetDate(warmAnaysisInfo.getHanderStatistic());
//            setHeadDate(warmAnaysisInfo.getVehCnt(), warmAnaysisInfo.getOnLineVehCnt(), warmAnaysisInfo.getOverSpCnt(), warmAnaysisInfo.getTiredCnt(), warmAnaysisInfo.getOtherCnt());
//        }
    }

    @Override
    public void showError(String error) {
        HideDiaLogLoading();
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }


//    private void setProgressNetDateCompany(ALarmListBean aLarmListBean) {
//        if (aLarmListBean == null) {
//            return;
//        }
//
//        idRoundprogress.setMax(aLarmListBean.getOverSpeedCount());
//        idRoundprogress.setProgress(aLarmListBean.getHandleCountOverspeed());
////                if (aLarmListBean.get(i).getName() != null) {
////                    tv_progress1.setText(aLarmListBean.get(i).getName());
////                }
//
//
//        idRoundprogress2.setMax(aLarmListBean.getTiredCount());
//        idRoundprogress2.setProgress(aLarmListBean.getHandleCountTired());
////                if (aLarmListBean.get(i).getName() != null) {
////                    tv_progress2.setText(aLarmListBean.get(i).getName());
////                }
//
//
//        idRoundprogress3.setMax(aLarmListBean.getOtherAlarmCount());
//        idRoundprogress3.setProgress(aLarmListBean.getHandleCountOther());
////        if (aLarmListBean.get(i).getName() != null) {
////            tv_progress3.setText(aLarmListBean.get(i).getName());
////
////
////        }
//
//    }
//
//    ;
//
//    private void setProgressNetDateALL(HomeAlarBean aLarmListBean) {
//        if (aLarmListBean == null) {
//            return;
//        }
//
//
//        idRoundprogress.setMax(aLarmListBean.getOverSpeedCount());
//        idRoundprogress.setProgress(aLarmListBean.getHandleCountOverspeed());
////                if (aLarmListBean.get(i).getName() != null) {
////                    tv_progress1.setText(aLarmListBean.get(i).getName());
////                }
//
//
//        idRoundprogress2.setMax(aLarmListBean.getTiredCount());
//        idRoundprogress2.setProgress(aLarmListBean.getHandleCountTired());
////                if (aLarmListBean.get(i).getName() != null) {
////                    tv_progress2.setText(aLarmListBean.get(i).getName());
////                }
//
//
//        idRoundprogress3.setMax(aLarmListBean.getOtherAlarmCount());
//        idRoundprogress3.setProgress(aLarmListBean.getHandleCountOther());
////        if (aLarmListBean.get(i).getName() != null) {
////            tv_progress3.setText(aLarmListBean.get(i).getName());
////
////
////        }
//
//    }

    //设置报警处理率占比
    private void setProgressNetDate(Object obj, int type) {
        HomeAlarBean b;
        ALarmListBean b1;
        if (type == 1) {
            //说明是总的报警数据
            b = (HomeAlarBean) obj;


            if (b == null) {
                return;
            }
            if (b.getOverSpeedCount() > 0) {
                idRoundprogress.setMax(b.getOverSpeedCount());
                idRoundprogress.setProgress(b.getHandleCountOverspeed());
            } else {
                idRoundprogress.setMax(100);
                idRoundprogress.setProgress(100);
            }

            if (b.getTiredCount() > 0) {
                idRoundprogress2.setMax(b.getTiredCount());
                idRoundprogress2.setProgress(b.getHandleCountTired());
            } else {
                idRoundprogress2.setMax(100);
                idRoundprogress2.setProgress(100);
            }
            if (b.getOtherAlarmCount() > 0) {
                idRoundprogress3.setMax(b.getOtherAlarmCount());
                idRoundprogress3.setProgress(b.getHandleCountOther());
            } else {
                idRoundprogress3.setMax(100);
                idRoundprogress3.setProgress(100);
            }
//



//



        } else if (type == 2) {
            //说明是各个公司的报警数据
            b1 = (ALarmListBean) obj;
            if (b1 == null) {
                return;
            }

            if (b1.getOverSpeedCount() > 0) {
                idRoundprogress.setMax(b1.getOverSpeedCount());
                idRoundprogress.setProgress(b1.getHandleCountOverspeed());
            } else {
                idRoundprogress.setMax(100);
                idRoundprogress.setProgress(100);
            }

            if (b1.getTiredCount() > 0) {
                idRoundprogress2.setMax(b1.getTiredCount());
                idRoundprogress2.setProgress(b1.getHandleCountTired());
            } else {
                idRoundprogress2.setMax(100);
                idRoundprogress2.setProgress(100);
            }
            if (b1.getOtherAlarmCount() > 0) {
                idRoundprogress3.setMax(b1.getOtherAlarmCount());
                idRoundprogress3.setProgress(b1.getHandleCountOther());
            } else {
                idRoundprogress3.setMax(100);
                idRoundprogress3.setProgress(100);
            }

        } else if (type == 3) {
            idRoundprogress.setMax(100);
            idRoundprogress.setProgress(0);
//


            idRoundprogress2.setMax(100);
            idRoundprogress2.setProgress(0);
//


            idRoundprogress3.setMax(100);
            idRoundprogress3.setProgress(0);
        }


    }

//    private void setProgressNetDate(ArrayList<WarmAnaysisInfo.HanderStattis> handerStattisArrayList) {
//        if (handerStattisArrayList == null)
//            return;
//        for (int i = 0; i < handerStattisArrayList.size(); i++) {
//            if (i == 0) {
//                idRoundprogress.setMax(handerStattisArrayList.get(i).getCnt());
//                idRoundprogress.setProgress(handerStattisArrayList.get(i).getHandlerCnt());
//                if (handerStattisArrayList.get(i).getName() != null) {
//                    tv_progress1.setText(handerStattisArrayList.get(i).getName());
//                }
//            }
//            if (i == 1) {
//                idRoundprogress2.setMax(handerStattisArrayList.get(i).getCnt());
//                idRoundprogress2.setProgress(handerStattisArrayList.get(i).getHandlerCnt());
//                if (handerStattisArrayList.get(i).getName() != null) {
//                    tv_progress2.setText(handerStattisArrayList.get(i).getName());
//                }
//            }
//            if (i == 2) {
//                idRoundprogress3.setMax(handerStattisArrayList.get(i).getCnt());
//                idRoundprogress3.setProgress(handerStattisArrayList.get(i).getHandlerCnt());
//                if (handerStattisArrayList.get(i).getName() != null) {
//                    tv_progress3.setText(handerStattisArrayList.get(i).getName());
//                }
//            }
//        }
//
//    }

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
    public void getPieChartData(int overSpCnt, int TiredCnt, int OtherCnt) {


        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        DecimalFormat df = new DecimalFormat("#0.00");
        int num = overSpCnt + TiredCnt + OtherCnt;

        if (overSpCnt != 0) {
            colors.add(Color.rgb(254, 137, 76));
            entries.add(new PieEntry(overSpCnt));
            tv_speed_warm.setText(df.format((double) overSpCnt / num * 100) + "%");
        } else {
            tv_speed_warm.setText("0.00%");
        }
        if (TiredCnt != 0) {
            colors.add(Color.rgb(248, 192, 28));
            entries.add(new PieEntry(TiredCnt));
            tv_fatigue_warm.setText(df.format((double) TiredCnt / num * 100) + "%");
        } else {
            tv_fatigue_warm.setText("0.00%");
        }
        if (OtherCnt != 0) {
            colors.add(Color.rgb(223, 224, 225));
            entries.add(new PieEntry(OtherCnt));
            tv_other_warm.setText(df.format((double) OtherCnt / num * 100) + "%");
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

    //设置头部标题数据
    private void setHeadDate(int Vehicle, int Online, int Speeding, int Fatigue, int Ohter) {
        tvVehicle.setText(Vehicle + "");
        tvOnline.setText(Online + "");
        tvSpeeding.setText(Speeding + "");
        tvFatigue.setText(Fatigue + "");
        tvOther.setText(Ohter + "");
    }

    //设置头部标题数据
    private void setHeadDateNull(String Vehicle, String Online, String Speeding, String Fatigue, String Ohter) {
        tvVehicle.setText(Vehicle + "");
        tvOnline.setText(Online + "");
        tvSpeeding.setText(Speeding + "");
        tvFatigue.setText(Fatigue + "");
        tvOther.setText(Ohter + "");
    }

    private void setCoordinate(Spinner spinner, ArrayList list) {
        // 将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(spinner,
                list));
        // 设置默认值
        spinner.setVisibility(View.VISIBLE);
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

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        private Spinner spinner;
        private ArrayList arr;

        public SpinnerSelectedListener(Spinner spinner, ArrayList arr) {
            super();
            this.spinner = spinner;
            this.arr = arr;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            companyname.setText(nameList.get(position));
            if (position == 0) {//表示显示的是全部的数据

                if (bean != null) {
                    getPieChartData(bean.getOverSpeedCount(), bean.getTiredCount(), bean.getOtherAlarmCount());
                    setProgressNetDate(bean, 1);
                    setHeadDate(bean.getAlarmCount(), bean.getVehicleOnlineCount(), bean.getOverSpeedCount(), bean.getTiredCount(), bean.getOtherAlarmCount());
//                    init();
                } else {
                    getPieChartData(0, 0, 0);
                    setHeadDateNull("-", "-", "-", "-", "-");
                    setProgressNetDate(bean, 3);
                }

            } else {//表示显示的是各个分公司的数据

                bean1 = mList.get(position - 1);
                if (bean1 != null) {
                    getPieChartData(bean1.getOverSpeedCount(), bean1.getTiredCount(), bean1.getOtherAlarmCount());
                    setProgressNetDate(bean1, 2);
                    setHeadDate(bean1.getAlarmCount(), bean1.getVehicleOnlineCount(), bean1.getOverSpeedCount(), bean1.getTiredCount(), bean1.getOtherAlarmCount());
                } else {
                    getPieChartData(0, 0, 0);
                    setHeadDateNull("-", "-", "-", "-", "-");
                    setProgressNetDate(bean, 3);
                }
//                init();
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
