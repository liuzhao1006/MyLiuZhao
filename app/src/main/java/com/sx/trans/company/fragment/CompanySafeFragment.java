package com.sx.trans.company.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.app.Config;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.activity.SpecialLearningActivity;
import com.sx.trans.company.adapter.CommonDistributionAdapter;
import com.sx.trans.company.adapter.HomePagerAdapter;
import com.sx.trans.company.bean.RadarBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.company.safemanager.AnnualVerificationActivity;
import com.sx.trans.company.safemanager.SafeAccidentActivity;
import com.sx.trans.company.safemanager.SafeExerciseActivity;
import com.sx.trans.company.safemanager.SafeHidderDrangActivity;
import com.sx.trans.company.safemanager.SafeInputActivity;
import com.sx.trans.company.safemanager.SafeInsuranceActivity;
import com.sx.trans.company.safemanager.SafeMaintainActivity;
import com.sx.trans.company.safemanager.SafeMeetingActivity;

import com.sx.trans.company.safemanager.SafeReportActivity;
import com.sx.trans.company.safemanager.SafeRulesActivity;
import com.sx.trans.company.safemanager.SafeSpecialCampaignActivity;
import com.sx.trans.company.safemanager.SafeSpecialCampaignDetailActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.safetyLearning.accounted.bean.AccountTotleBean;
import com.sx.trans.safetyLearning.ui.SafeLearnActivity;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.sx.trans.widget.view.RadarMarkerView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/9/7.
 * 安全管理
 */

public class CompanySafeFragment extends Fragment implements View.OnClickListener, BaseNetApi {

    private ImageView layoutTopLeftimg;

    private TextView layoutTopLeftinfo;

    private LinearLayout layoutTopWithButtomTmp;

    private TextView layoutTopModleinfo;

    private ImageView layoutTopRightinfo;

    private TextView layoutTopRighttextinfo;
    private ViewPager mViewPager;

    private TextView tvTitle;

    private LinearLayout llContainer;
    private ProgressBar bar;
    private LinearLayout tvSafeStudy;
    private TextView jindu;
    private LinearLayout tvAnnualVerification;

    private LinearLayout tvAccident;

    private LinearLayout tvInsurance;

    private LinearLayout tvMaintenance;

    private LinearLayout tvHiddenTrouble;

    private LinearLayout tvMeetting;

    private LinearLayout tvSystemSpecification;

    private LinearLayout tvSafetyMessage;

    private LinearLayout tvSafeShow;

    private LinearLayout tvSafetyGoals;

    private LinearLayout tvSafetyInput;

    private LinearLayout tvSpecialCampaign;

    private RadarChart mChart;
    private int type = 1;
    private View v;
    private boolean isAdd = true;//第一次添加5个小圆点,以后不添加

    //图片id集合
    private int[] mImageIds = new int[]{R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d, R.drawable.e};

    // 图片标题集合
    private final String[] mImageDes = Config.mImageDes;
    private LinearLayout ltv_distribution;
    private List<RadarBean.IndicatorDataBean> mList = new ArrayList<>();
    private List<RadarBean> mLists = new ArrayList<>();
    private RadarBean b;
    private Typeface mTfLight;
    private ImageView iv_pie;
    private CompanyManager manager;
    private TextView spinnerDate;//选择时间
    private TimePickerView pvCustomLunar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_company_safemanager, null);
    }

    private void initview() {
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/custom_regular.ttf");

        iv_pie = v.findViewById(R.id.iv_pie);
        layoutTopLeftimg = v.findViewById(R.id.layout_top_leftimg);
        layoutTopLeftinfo = v.findViewById(R.id.layout_top_leftinfo);
        layoutTopWithButtomTmp = v.findViewById(R.id.layout_top_with_buttom_tmp);

        layoutTopModleinfo = v.findViewById(R.id.layout_top_modleinfo);
        layoutTopRightinfo = v.findViewById(R.id.layout_top_rightinfo);
        layoutTopRighttextinfo = v.findViewById(R.id.layout_top_righttextinfo);

        mViewPager = v.findViewById(R.id.viewpager);
        tvTitle = v.findViewById(R.id.tv_title);
        llContainer = v.findViewById(R.id.ll_container);

        bar = v.findViewById(R.id.progressBar);
        jindu = v.findViewById(R.id.jindu);
        tvSafeStudy = v.findViewById(R.id.tv_safeStudy);
        tvAnnualVerification = v.findViewById(R.id.tv_annualVerification);
        tvAccident = v.findViewById(R.id.tv_accident);


        tvInsurance = v.findViewById(R.id.tv_insurance);
        tvMaintenance = v.findViewById(R.id.tv_maintenance);
        tvHiddenTrouble = v.findViewById(R.id.tv_hiddenTrouble);

        tvMeetting = v.findViewById(R.id.tv_Meetting);
        tvSystemSpecification = v.findViewById(R.id.tv_SystemSpecification);
        tvSafetyMessage = v.findViewById(R.id.tv_safetyMessage);


        tvSafeShow = v.findViewById(R.id.tv_safeShow);
        tvSafetyGoals = v.findViewById(R.id.tv_safetyGoals);
        tvSafetyInput = v.findViewById(R.id.tv_safetyInput);

        tvSpecialCampaign = v.findViewById(R.id.tv_specialCampaign);
        mChart = v.findViewById(R.id.mPieChart);
        iv_pie.setVisibility(View.VISIBLE);
        mChart.setVisibility(View.GONE);
        layoutTopLeftimg.setVisibility(View.INVISIBLE);
        layoutTopModleinfo.setText("安全管理");

        ltv_distribution = v.findViewById(R.id.ltv_distribution);


        tvSafeStudy.setOnClickListener(this);
        tvAnnualVerification.setOnClickListener(this);
        tvAccident.setOnClickListener(this);


        tvInsurance.setOnClickListener(this);
        tvMaintenance.setOnClickListener(this);
        tvHiddenTrouble.setOnClickListener(this);

        tvMeetting.setOnClickListener(this);
        tvSystemSpecification.setOnClickListener(this);
        tvSafetyMessage.setOnClickListener(this);


        tvSafeShow.setOnClickListener(this);
        tvSafetyGoals.setOnClickListener(this);
        tvSafetyInput.setOnClickListener(this);

        tvSpecialCampaign.setOnClickListener(this);
        ltv_distribution.setOnClickListener(this);

        spinnerDate = v.findViewById(R.id.spinnerDate);
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        spinnerDate.setText(com.sx.trans.widget.utils.Utils.getCurrentTime().split(" ")[0]);
        spinnerDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomLunar.show();
            }
        });
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调


                String selecttime = getTime(date).split(" ")[0];
                spinnerDate.setText(selecttime);

                manager.getRiskDistribution(type, com.sx.trans.widget.utils.Utils.toURLEncoded(selecttime));

            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                        //公农历切换
                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
                        cb_lunar.setVisibility(View.GONE);


                    }

                    /**
                     * 公农历切换后调整宽
                     * @param v
                     * @param yearWeight
                     * @param weight
                     */
                    private void setTimePickerChildWeight(View v, float yearWeight, float weight) {
                        ViewGroup timepicker = (ViewGroup) v.findViewById(R.id.timepicker);
                        View year = timepicker.getChildAt(0);
                        LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams) year.getLayoutParams());
                        lp.weight = yearWeight;
                        year.setLayoutParams(lp);
                        for (int i = 1; i < timepicker.getChildCount(); i++) {
                            View childAt = timepicker.getChildAt(i);
                            LinearLayout.LayoutParams childLp = ((LinearLayout.LayoutParams) childAt.getLayoutParams());
                            childLp.weight = weight;
                            childAt.setLayoutParams(childLp);
                        }
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        manager = new CompanyManager(getActivity(), this);

        String datas = com.sx.trans.widget.utils.Utils.getCurrentTime().split(" ")[0];

        manager.getRiskDistribution(type, com.sx.trans.widget.utils.Utils.toURLEncoded(datas));
        //统计 获取当前年和月数据
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        String CompanyId_E = PrefUtils.getString(getActivity(), LoginConfig.COMPANY, null);
        manager.getAccount(year + month, CompanyId_E);
        manager.setOnSafeCompanyRiskDistributionData(new CompanyManager.SafeCompanyRiskDistributionData() {
            @Override
            public void onSafeCompanyRiskDistributionData(int what, List<RadarBean> safeCompanyRiskDistributionList) {
                //获取到数据
                if (safeCompanyRiskDistributionList == null || safeCompanyRiskDistributionList.size() <= 0) {
                    return;
                }
                b = safeCompanyRiskDistributionList.get(0);
                mList.addAll(safeCompanyRiskDistributionList.get(0).getIndicatorData());
                iv_pie.setVisibility(View.GONE);
                mChart.setVisibility(View.VISIBLE);
                initView(safeCompanyRiskDistributionList);
            }
        });
        manager.setOnSafeLearnData(new CompanyManager.SafeLearnData() {
            @Override
            public void onSafeLearnData(int what, List<AccountTotleBean> safeLearnList) {
                if (safeLearnList == null || safeLearnList.size() <= 0) {
                    return;
                }
                AccountTotleBean bean = safeLearnList.get(0);

                if (!TextUtils.isEmpty(bean.getFinishRatio())) {
                    int presonSuccess = (int) (Double.parseDouble(bean.getFinishRatio()) * 100);
                    jindu.setText(presonSuccess + "%");

                    bar.setProgress(presonSuccess);
                } else {
                    bar.setProgress(0);

                    jindu.setText("无数据");
                }
            }
        });


        initview();

        initViewPager();
        return v;
    }


    public void initView(final List<RadarBean> safeCompanyRiskDistributionList) {
        int size = safeCompanyRiskDistributionList.get(0).getIndicatorData().size();
        final String[] mActivities = new String[size];
        for (int x = 0; x < size; x++) {
            mActivities[x] = safeCompanyRiskDistributionList.get(0).getIndicatorData().get(x).getName();

        }

        mChart.setBackgroundColor(Color.WHITE);

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);
        MarkerView mv = new RadarMarkerView(getActivity(), R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
        setData(safeCompanyRiskDistributionList);
        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            public String getFormattedValue(float value, AxisBase axis) {

                return mActivities[(int) value % mActivities.length];
            }
        });

        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(5, true);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.GRAY);

    }


    public void setData(List<RadarBean> safeCompanyRiskDistributionList) {

        //定义范围
        float mult = 100;
        float min = 0;
        //获取总个数
        int cnt = safeCompanyRiskDistributionList.get(0).getIndicatorData().size();

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();//实际分数
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();//标准分数

        for (int i = 0; i < cnt; i++) {
            float val1 = safeCompanyRiskDistributionList.get(0).getSeriesData().get实际分数().get(i);

            float val2 = safeCompanyRiskDistributionList.get(0).getSeriesData().get标准分数().get(i);
            entries1.add(new RadarEntry((val1 / val2) * 100));
            entries2.add(new RadarEntry(100));

        }

        RadarDataSet set1 = new RadarDataSet(entries1, "实际分数");
        set1.setColor(0xFF3690FF);
        set1.setFillColor(0xFF3690FF);
        set1.setDrawFilled(true);
        set1.setFillAlpha(80);
        set1.setLineWidth(0.8f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "标准分数");
        set2.setColor(0xFFCCCCCC);
        set2.setFillColor(0xFFCCCCCC);
        set2.setDrawFilled(true);
        set2.setFillAlpha(0);
        set2.setLineWidth(1.2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);
        mChart.invalidate();

    }


    /**
     * 轮播图
     */
    private void initViewPager() {

        mViewPager.setAdapter(new HomePagerAdapter(getActivity(), mImageIds));
        //页面滑动的监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //某个页面被选中
            @Override
            public void onPageSelected(int position) {
                position = position % mImageIds.length;
                //更新新闻标题
                tvTitle.setText(mImageDes[position]);

                //更改小红点显示位置
                //				ImageView point = (ImageView) llContainer.getChildAt(position);
                //				if (point != null) {
                //					point.setImageResource(R.drawable.shape_point_selected);
                //				}
                //遍历所有小圆点,更新圆点图片
                for (int i = 0; i < llContainer.getChildCount(); i++) {
                    ImageView point = (ImageView) llContainer.getChildAt(i);
                    if (point != null) {
                        if (i == position) {
                            //选中
                            point.setImageResource(R.drawable.shape_point_selected);
                        } else {
                            //未选中
                            point.setImageResource(R.drawable.shape_point_normal);
                        }
                    }
                }
            }

            //页面滑动监听
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            //页面滑动状态发生变化
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //手动初始化第一个页面的新闻标题
        tvTitle.setText(mImageDes[0]);

        //设置当前item的显示位置
        //mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        mViewPager.setCurrentItem(mImageIds.length * 10000);//保证当前显示在第一个新闻页面

        //判断是否进来过, 如果进来过,则不需要进来, 解决叠加问题
        if (isAdd) {
            for (int i = 0; i < mImageIds.length; i++) {
                ImageView point = new ImageView(getActivity());
                point.setImageResource(R.drawable.shape_point_normal);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);//包裹内容
                if (i > 0) {
                    params.leftMargin = 10;//从第二个圆点开始设置左边距
                } else {
                    point.setImageResource(R.drawable.shape_point_selected);//第一个圆点为红色
                }
                point.setLayoutParams(params);
                llContainer.addView(point);
            }

            mViewPager.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            System.out.println("ACTION_DOWN");

                            break;
                        case MotionEvent.ACTION_UP:
                            System.out.println("ACTION_UP");

                            break;

                        default:
                            break;
                    }

                    return false;//不消费此事件, ViewPager原生触摸响应才能正常运行
                }
            });
            isAdd = false;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_safeStudy://安全学习
                intent = new Intent(getActivity(), SafeLearnActivity.class);
                intent.putExtra("TopName", "safeStudy");
                startActivity(intent);
                break;
            case R.id.tv_safeShow://安全演习
                startActivity(new Intent(getActivity(), SafeExerciseActivity.class));
                break;
            case R.id.tv_annualVerification://年审
                intent = new Intent(getActivity(), AnnualVerificationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_accident://事故
                intent = new Intent(getActivity(), SafeAccidentActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_insurance://保险
                intent = new Intent(getActivity(), SafeInsuranceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_maintenance://维护
                intent = new Intent(getActivity(), SafeMaintainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_hiddenTrouble://隐患
                intent = new Intent(getActivity(), SafeHidderDrangActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_Meetting://会议
                intent = new Intent(getActivity(), SafeMeetingActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_safetyGoals://安全目标
                intent = new Intent(getActivity(), SpecialLearningActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_safetyInput://安全投入
                intent = new Intent(getActivity(), SafeInputActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_specialCampaign://专项活动
                startActivity(new Intent(getActivity(), SafeSpecialCampaignActivity.class));
                break;
            case R.id.tv_SystemSpecification://制度规范
                setIntent(getActivity(), SafeRulesActivity.class, "制度规范");
                break;
            case R.id.tv_safetyMessage://安全报告
                startActivity(new Intent(getActivity(), SafeReportActivity.class));
                break;
            case R.id.ltv_distribution://风险分布分析
                if (b == null | b == null) {
                    Toast.makeText(getActivity(), "没有数据......", Toast.LENGTH_SHORT).show();
                } else {
                    for (int f = 0; f < b.getIndicatorData().size(); f++) {
                        mLists.add(b);
                    }
                    dialog(mLists);

                }
                break;
            default:
                break;


        }
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

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;

    /**
     * 图表弹窗展示
     *
     * @param mLists
     */
    private void dialog(List<RadarBean> mLists) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonDistributionAdapter adapter = new CommonDistributionAdapter(R.layout.item_listview_dialog, mLists, getActivity());
        rlv.setAdapter(adapter);
        srv.setRefreshing(false);
        srv.setOnRefreshListener(mOnRefreshListener);


        WindowManager manager = getActivity().getWindowManager();
        int height = manager.getDefaultDisplay().getHeight();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, height / 2)//屏幕的二分之一
                .addDefaultAnimation()
                .show();

    }

    //可根据需要自行截取数据显示
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();

        }
    };


    /**
     * 防止刷新图表不隐藏
     */
    private void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
            }
        }, 3000);
    }

    private void setIntent(Context context, Class clazz, String interfaceType) {

        Intent intent = new Intent(context, clazz);
        intent.putExtra("interfaceType", interfaceType);
        startActivity(intent);

    }


}
