package com.sx.trans.transport.dynamicMonitoring.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.widget.utils.Utils;

/**
 * 作者 : 刘朝,
 * on 2017/9/9,
 * GitHub : https://github.com/liuzhao1006
 */

public class StudyPlanActivity extends BaseActivity implements BaseNetApi {

    private TextView layout_top_modleinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_studyplan);
        super.onCreate(savedInstanceState);
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

    @Override
    protected void initView() {

        String currentTime = Utils.getCurrentTime();
        String time = currentTime.split("-")[0] + "-" + currentTime.split("-")[1];

        layout_top_modleinfo = findViewById(R.id.layout_top_modleinfo);
        layout_top_modleinfo.setText(time);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
