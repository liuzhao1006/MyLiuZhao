package com.sx.trans.safetyLearning.accounted;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.sx.trans.R;
import com.sx.trans.safetyLearning.accounted.manager.AccountManager;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseFragment;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.DataUtils;
import com.sx.trans.widget.utils.Utils;

import java.util.ArrayList;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccountTwoFragment extends BaseFragment implements DataUtils.DataLinstener {
    private ImageView back;
    private LzTextView lvMiddle;
    private LzTextView right;
    private LzTextView enterprise_type;
    private LzTextView schedule_name;
    private PieChart mChart;
    //    private List<ListBean> list = new ArrayList<>();
//    private List<AccountListBean.StuDataBean> stuList = new ArrayList<>();
    private AccountManager manager;

    private String date, Company;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        setTitle();
        enterprise_type = mRootView.findViewById(R.id.enterprise_type);
        schedule_name = mRootView.findViewById(R.id.schedule_name);
        enterprise_type.setBackground(getResources().getDrawable(R.drawable.jituan));
        mChart = mRootView.findViewById(R.id.pieChart1);
    }

    private void setTitle() {
        String currentTime = Utils.getCurrentTime();
        String time = currentTime.split("-")[0] + "-" + currentTime.split("-")[1];
        manager = new AccountManager(getActivity(), this);
        manager.getAccount(time, PrefUtils.getString(getActivity(), LoginConfig.COMPANY, null));

        back = mRootView.findViewById(R.id.back);
        lvMiddle = mRootView.findViewById(R.id.center_tv);
        lvMiddle.setText(time);
        lvMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DataUtils(getActivity(), AccountTwoFragment.this).show();
            }
        });
        right = mRootView.findViewById(R.id.right_iv);
    }

    @Override
    public void setDataTime(String startTime, String endTime) {
        lvMiddle.setText(startTime);
        manager.getAccount(startTime, PrefUtils.getString(getActivity(), LoginConfig.COMPANY, null));

    }

    @Override
    public void netStart() {

    }

    @Override
    public void netStop() {

    }

    @Override
    public void netSuccessed(int what, String data) {
//        if (list.size() > 0) {
//            list.clear();
//        }
//        if (stuList.size() > 0) {
//            stuList.clear();
//        }
//        list = JSON.parseObject(data, AccountListBean.class).getList();
//        stuList = JSON.parseObject(data, AccountListBean.class).getStuData();
//        if(list.size()<0){
//            return;
//        }
//        schedule_name.setText(list.get(0).getStuName());
//        setData(list);
    }

    @Override
    public void netFailed(int what, String message) {

    }

    private void setPieChart() {

        mChart.setUsePercentValues(true);//设置使用百分比
        mChart.getDescription().setEnabled(false);//设置描述
        mChart.setExtraOffsets(20, 15, 30, 15);
        mChart.setCenterTextSize(18f);//设置环中文字的大小
        mChart.setCenterText("人员完成率");
        mChart.setDrawCenterText(true);//设置绘制环中文字
        mChart.setRotationAngle(120f);//设置旋转角度
        mChart.setDrawEntryLabels(false);
        //图例设置
        Legend legend = mChart.getLegend();
        legend.setEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setFormSize(12);
        legend.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);

        mChart.animateXY(1000, 1000);
        mChart.invalidate();
    }

    /**
     * generates less data (1 DataSet, 4 values)
     *
     * @return
     */
    protected void generatePieData() {
        ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();
        PieEntry pieEntry1 = new PieEntry(SanShi, "0-30%" + "(" + SanShi + "人)");
        PieEntry pieEntry2 = new PieEntry(San_Liu, "31%-60%" + "(" + San_Liu + "人)");
        PieEntry pieEntry3 = new PieEntry(Liu_Jiu, "61%-90%" + "(" + Liu_Jiu + "人)");
        PieEntry pieEntry4 = new PieEntry(JiuShi, "90%以上" + "(" + JiuShi + "人)");
        entries1.add(pieEntry1);
        entries1.add(pieEntry2);
        entries1.add(pieEntry3);
        entries1.add(pieEntry4);

        setPieChart();
        PieDataSet dataSet = new PieDataSet(entries1, "");
        dataSet.setSliceSpace(3f);//设置饼块之间的间隔
        dataSet.setSelectionShift(5f);//设置饼块选中时偏离饼图中心的距离

        dataSet.setColors(VORDIPLOM_COLORS);//设置饼块的颜色
        dataSet.setValueLinePart1OffsetPercentage(80f);//数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setValueLineColor(Color.BLUE);//设置连接线的颜色
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.DKGRAY);

        mChart.setData(pieData);
        mChart.highlightValues(null);
        mChart.invalidate();


    }

    public static final int[] VORDIPLOM_COLORS = {
            Color.rgb(255, 169, 20), Color.rgb(64, 219, 105), Color.rgb(66, 147, 255),
            Color.rgb(254, 119, 118)
    };

    private int SanShi = 0, San_Liu = 0, Liu_Jiu = 0, JiuShi = 0;

//    private void setData(List<ListBean> list) {
//
//        try {
//            SanShi = 0;
//            San_Liu = 0;
//            Liu_Jiu = 0;
//            JiuShi = 0;
//            for (int i = 0; i < list.size(); i++) {
//                double tyope = Double.valueOf(list.get(i).getHoursRatio());
//                if (tyope >= 0 && tyope <= 0.3) {
//                    SanShi++;
//                }
//                if (tyope > 0.3 && tyope <= 0.6) {
//                    San_Liu++;
//                }
//                if (tyope > 0.6 && tyope <= 0.9) {
//                    Liu_Jiu++;
//                }
//                if (tyope > 0.6 && tyope <= 0.9) {
//                    JiuShi++;
//                }
//            }
//            generatePieData();
//        } catch (Exception e) {
//            LogUtils.e(e.toString());
//        }
//
//
//    }

    private View view;
    private PopupWindow menuWindow;
    private ListView pop_lv;
    private String StudyMouth;

    private void setPopLayout() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.getmouth_pop, null);
        pop_lv = (ListView) view.findViewById(R.id.pop_lv);


//        pop_lv.setAdapter(new GetComPanyAdapter(getActivity(), ls));

        pop_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                StudyMouth = ls.get(position).CompanyName;
                schedule_name.setText(StudyMouth);
//                Company = ls.get(position).CompanyId;
//                tOHttp(getYeanList.get(position).StudyYear);
                menuWindow.dismiss();
            }
        });
//
        showPopwindow(view);
    }

    private void showPopwindow(View view) {

        WindowManager manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        menuWindow = new PopupWindow(view, manager.getDefaultDisplay().getWidth() / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        menuWindow.setFocusable(true);


        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            menuWindow.showAsDropDown(schedule_name, 0, 0, Gravity.CENTER_HORIZONTAL);
        }
        menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                menuWindow = null;
            }
        });
    }


}
