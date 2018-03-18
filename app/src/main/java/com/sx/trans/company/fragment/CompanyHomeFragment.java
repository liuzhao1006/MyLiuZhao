package com.sx.trans.company.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.blankj.utilcode.util.ToastUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sx.trans.R;
import com.sx.trans.app.Config;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.activity.CompanyLedgerActivity;
import com.sx.trans.company.activity.PersonnelQueryActivity;
import com.sx.trans.company.activity.VehicleQueryActivity;
import com.sx.trans.company.adapter.HomePagerAdapter;
import com.sx.trans.company.bean.CommpanyRiskBean;
import com.sx.trans.company.bean.CompanyLegerBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/9/7.
 * 企业端首页
 */

public class CompanyHomeFragment extends Fragment implements OnChartValueSelectedListener, View.OnClickListener, BaseNetApi {

    private ImageView layoutTopLeftimg;
    private TextView layoutTopLeftinfo;
    private LinearLayout layoutTopWithButtomTmp;
    private TextView layoutTopModleinfo;
    private ImageView layoutTopRightinfo;
    private TextView layoutTopRighttextinfo;
    private ViewPager mViewPager;
    private TextView tvTitle;
    private LinearLayout llContainer;
    private LzTextView tvCompany;
    private LzTextView tvCar;
    private LzTextView tvPerson;
    private LzTextView tvCompare;
    private PieChart mPieChart;
    private View v;
    private LinearLayout llCar;
    private LinearLayout llPerson;
    private CompanyLegerBean bean;
    private int total;
    private int averageRiskproportion;
    private int moderateRiskproportion;
    private int significantRiskproportion;
    private boolean isAdd = true;//第一次添加5个小圆点,以后不添加
    private List<String> pieList = new ArrayList<>();
    //图片id集合
    private int[] mImageIds = new int[]{R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d, R.drawable.e};

    // 图片标题集合
    private final String[] mImageDes = Config.mImageDes;
    private int averageRisk = 0;
    private int moderateRisk = 0;
    private int significantRisk = 0;

    //    private int handleRisk = 0;
    private CommpanyRiskBean riskBean;
    private FrameLayout fl_pie;
    private ImageView iv_pie;
    private CompanyManager manager;
    private Spinner spinnerType;//选择类型
    private TextView spinnerDate;//选择时间
    private static final String[] arrayType = new String[]{"静态风险", "联网监控", "实时报警"};
    private ArrayAdapter<String> adapter;
    private int pieType = 1;//饼状图请求类型riskType
    private String beginTime = "0000";//开始时间
    private String endTime = Utils.toURLEncoded(Utils.getCurrentTime().replace("-", "/"));//结束时间
    private TimePickerView pvCustomLunar;
    private LinearLayout llCompany;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_company_home, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        findView();
        intView();
        httpData();
        return v;
    }

    /**
     * 访问数据
     */
    private void httpData() {

        manager = new CompanyManager(getActivity(), this);
        manager.getCompanyDetial();
//        manager. getDefaltCompanyRisk(pieType);

        manager.setOnSafeCompanyLegerListData(new CompanyManager.SafeCompanyLegerListData() {
            @Override
            public void onSafeCompanyLegerListData(int what, List<CompanyLegerBean> safeCompanyLegerList) {
                bean = safeCompanyLegerList.get(0);
                tvCar.setText(bean.getVehicleCount());
                tvPerson.setText(bean.getPersonCount());
            }
        });
        manager.setOnSafeCompanyRiskData(new CompanyManager.SafeCompanyRiskData() {
            @Override
            public void onSafeCompanyRiskData(int what, List<CommpanyRiskBean> safeCompanyRiskList) {
                if (safeCompanyRiskList == null || safeCompanyRiskList.size() <= 0) {
                    return;
                }
                riskBean = safeCompanyRiskList.get(0);
                if (riskBean.getRiskValueP() != 0) {
                    if (riskBean.getRiskValueP() > 0) {
                        tvCompare.setText("环比上升" + (riskBean.getRiskValueP() * 100) + "%↑");
                    } else {
                        tvCompare.setText("环比下降" + (riskBean.getRiskValueP() * 100) + "%↓");
                    }
                } else {
//                    tvCompare.setText("较昨日下降" + "1%↓");
                    tvCompare.setText(" 最近无数据");
                }
                //一般风险
                averageRisk = riskBean.getAverageRisk();
//                averageRisk = 900;
                //中等风险
                moderateRisk = riskBean.getModerateRisk();
//                moderateRisk =800;
                //重大风险
                significantRisk = riskBean.getSignificantRisk();
//                significantRisk = 600;
                //已处理风险
//                handleRisk = riskBean.getHandleRisk();
//                handleRisk = 40;
                iv_pie.setVisibility(View.GONE);
                if (averageRisk == 0 && moderateRisk == 0 && significantRisk == 0) {
                    iv_pie.setVisibility(View.VISIBLE);
                    mPieChart.setVisibility(View.GONE);
                } else
                    setPieData();
            }
        });
    }


    private void findView() {
        llCompany = v.findViewById(R.id.llCompany);
        layoutTopLeftimg = v.findViewById(R.id.layout_top_leftimg);
        layoutTopLeftinfo = v.findViewById(R.id.layout_top_leftinfo);
        layoutTopWithButtomTmp = v.findViewById(R.id.layout_top_with_buttom_tmp);
        layoutTopModleinfo = v.findViewById(R.id.layout_top_modleinfo);
        layoutTopRightinfo = v.findViewById(R.id.layout_top_rightinfo);
        layoutTopRighttextinfo = v.findViewById(R.id.layout_top_righttextinfo);
        mViewPager = v.findViewById(R.id.viewpager);
        tvTitle = v.findViewById(R.id.tv_title);
        llContainer = v.findViewById(R.id.ll_container);
        tvCompany = v.findViewById(R.id.tv_company);
        tvCar = v.findViewById(R.id.tv_car);
        tvPerson = v.findViewById(R.id.tv_person);
        tvCompare = v.findViewById(R.id.tv_compare);
        mPieChart = v.findViewById(R.id.mPieChart);
        llCar = v.findViewById(R.id.ll_car);
        llPerson = v.findViewById(R.id.ll_person);
        llCompany.setOnClickListener(this);
        llCar.setOnClickListener(this);
        llPerson.setOnClickListener(this);
        tvCompare.setOnClickListener(this);
        fl_pie = v.findViewById(R.id.fl_pie);
        iv_pie = v.findViewById(R.id.iv_pie);
        spinnerType = v.findViewById(R.id.spinnerType);
        spinnerDate = v.findViewById(R.id.spinnerDate);
        spinnerDate.setText(Utils.getCurrentTime().split(" ")[0]);
        setCoordinate(spinnerType, arrayType);
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
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
                System.out.print(getTime(date));


                beginTime = Utils.toURLEncoded(getTime(date).replace("-", "/"));
                spinnerDate.setText(getTime(date).split(" ")[0]);
                manager.getCompanyRisk(pieType, beginTime, endTime);

//                Toast.makeText(CompanyHomeFragment.this, getTime(date), Toast.LENGTH_SHORT).show();
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
//                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                            @Override
//                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                pvCustomLunar.setLunarCalendar(!pvCustomLunar.isLunarCalendar());
//                                //自适应宽
//                                setTimePickerChildWeight(v, 0.8f, isChecked ? 1f : 1.1f);
//                            }
//                        });

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


    public void intView() {

        layoutTopLeftimg.setVisibility(View.INVISIBLE);
        layoutTopModleinfo.setText("首页");

        initViewPager();
        if (riskBean == null) {
            iv_pie.setVisibility(View.VISIBLE);
            mPieChart.setVisibility(View.GONE);
        }

    }

    //可根据需要自行截取数据显示
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    // 设置坐标系Spinner
    private void setCoordinate(Spinner spinner, String[] arr) {
        // 将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arr);

        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(spinner,
                arr));
        // 设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        private Spinner spinner;
        private String[] arr;

        public SpinnerSelectedListener(Spinner spinner, String[] arr) {
            super();
            this.spinner = spinner;
            this.arr = arr;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {

            if (position == 0) {
                pieType = 1;
                if (beginTime.equals("0000")) {
                    manager.getDefaltCompanyRisk(pieType);
                } else {

                    manager.getCompanyRisk(pieType, beginTime, endTime);
                }

            } else if (position == 1) {
                pieType = 2;
                if (beginTime.equals("0000")) {
                    manager.getDefaltCompanyRisk(pieType);
                } else {
                    manager.getCompanyRisk(pieType, beginTime, endTime);
                }

            } else if (position == 2) {
                if (beginTime.equals("0000")) {
                    manager.getDefaltCompanyRisk(pieType);
                } else {
                    manager.getCompanyRisk(pieType, beginTime, endTime);
                }

                pieType = 3;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    /**
     * 饼状图数据
     */
    private void setPieData() {
        mPieChart.setVisibility(View.VISIBLE);
        mPieChart.setUsePercentValues(false);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(30, 30, 30, 30);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
        mPieChart.setCenterText(generateCenterSpannableText());
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(0);

        mPieChart.setHoleRadius(0);//半径
        mPieChart.setTransparentCircleRadius(0);

        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);

        //模拟数据 private int averageRisk = 0;
//        private int moderateRisk = 0;
//        private int significantRisk = 0;
//        private int handleRisk = 0;


        total = averageRisk + significantRisk + moderateRisk;
        averageRiskproportion = (int) (averageRisk * 100 / total);
        moderateRiskproportion = (int) (moderateRisk * 100 / total);
        significantRiskproportion = (int) (significantRisk * 100 / total);

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(averageRiskproportion, "一般风险"));
        entries.add(new PieEntry(moderateRiskproportion, "中度风险"));
        entries.add(new PieEntry(significantRiskproportion, "重度风险"));
//        entries.add(new PieEntry(handleRisk, "已处理"));

        //设置数据
        setData(entries);
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);
        l.setEnabled(true);
        l.setTextSize(6f);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.TRANSPARENT);
        mPieChart.setEntryLabelTextSize(30f);
        mPieChart.invalidate();
    }

    /**
     * 轮播图
     */
    private void initViewPager() {

        mViewPager.setAdapter(new HomePagerAdapter(getActivity(), mImageIds));
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                position = position % mImageIds.length;
                tvTitle.setText(mImageDes[position]);
                for (int i = 0; i < llContainer.getChildCount(); i++) {
                    ImageView point = (ImageView) llContainer.getChildAt(i);
                    if (point != null) {
                        if (i == position) {
                            point.setImageResource(R.drawable.shape_point_selected);
                        } else {
                            point.setImageResource(R.drawable.shape_point_normal);
                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tvTitle.setText(mImageDes[0]);
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

    //设置中间文字
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("风险处理分析");
        return s;
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "昨天");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(Color.rgb(249, 237, 96));//--黄色
        colors.add(Color.rgb(241, 165, 64));//--橘色
        colors.add(Color.rgb(248, 77, 80));//--红色


//        colors.add(Color.rgb(113, 200, 104));//--绿色


        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(1f);
        dataSet.setValueLinePart2Length(1f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.llCompany:
                intent = new Intent(getActivity(), CompanyLedgerActivity.class);
                if (bean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CompanyLedgerActivity.class", bean);
                    intent.putExtra("CompanyLedgerActivity", bundle);
                }
                startActivity(intent);

                break;
            case R.id.ll_car:
                intent = new Intent(getActivity(), VehicleQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_person:
                intent = new Intent(getActivity(), PersonnelQueryActivity.class);
                startActivity(intent);
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
}
