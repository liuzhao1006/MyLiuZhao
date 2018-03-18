package com.sx.trans.widget.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.widget.WheelView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.widget.view.DatePicker;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class DataUtils {

    private Context mContext;
    private WheelView year_wheel, mouth_wheel;
    DataLinstener linstener;

    public DataUtils(Context mContext, DataLinstener linstener) {
        this.mContext = mContext;
        this.linstener = linstener;
    }

    public void show() {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.show_wheel);
        window.setWindowAnimations(R.style.AnimBottom);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final DatePicker datePicker = window.findViewById(R.id.date_picker_start);
        final DatePicker end = window.findViewById(R.id.date_picker_end);
        window.findViewById(R.id.Btn_queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linstener.setDataTime(datePicker.getYear() + "-" + datePicker.getMonth(), "" + end.getYear() + end.getMonth());
                dialog.dismiss();
            }
        });
        window.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public interface DataLinstener {
        void setDataTime(String startTime, String endTime);
    }
}
