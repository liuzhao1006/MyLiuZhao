package com.sx.trans.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sx.trans.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */
public class DatePicker extends LinearLayout implements WheelView.OnSelectListener {

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 获取选择的年
     *
     * @return
     */
    public String getYear() {
        return mWheelYear.getSelectedText();
    }

    /**
     * 获取选择的月
     *
     * @return
     */
    public String getMonth() {
        return mWheelMonth.getSelectedText();
    }

    /**
     * 获取选择的日
     *
     * @return
     */
    public String getDay() {
        return mWheelDay.getSelectedText();
    }

    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater.from(getContext()).inflate(R.layout.date_picker, this);

        mWheelYear = (WheelView) findViewById(R.id.year);
        mWheelMonth = (WheelView) findViewById(R.id.month);

        // 格式化当前时间，并转换为年月日整型数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        String[] split = sdf.format(new Date()).split("-");
        int currentYear = Integer.parseInt(split[0]);
        int currentMonth = Integer.parseInt(split[1]);
//        int currentDay = Integer.parseInt(split[2]);

        // 设置默认年月日为当前日期
        mWheelYear.setData(getYearData(currentYear));
        mWheelYear.setDefault(0);
        mWheelMonth.setData(getMonthData());
        mWheelMonth.setDefault(currentMonth - 1);

        mWheelYear.setOnSelectListener(this);
        mWheelMonth.setOnSelectListener(this);

    }

    /**
     * 年范围在：1950~今年
     *
     * @param currentYear
     * @return
     */
    private ArrayList<String> getYearData(int currentYear) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = currentYear; i >= 1900; i--) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                list.add("0" + String.valueOf(i));
            } else {
                list.add(String.valueOf(i));
            }

        }
        return list;
    }

    /**
     * /**
     * 判断是否闰年
     *
     * @param year
     * @return
     */
    private boolean isLeapYear(int year) {
        return (year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    @Override
    public void endSelect(View view, int id, String text) {

    }


    @Override
    public void selecting(View view, int id, String text) {

    }
}

