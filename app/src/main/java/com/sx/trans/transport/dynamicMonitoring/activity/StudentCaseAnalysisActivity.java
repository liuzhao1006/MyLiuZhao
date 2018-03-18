package com.sx.trans.transport.dynamicMonitoring.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;

public class StudentCaseAnalysisActivity extends BaseTransActivity implements BaseNetApi {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_case_analysis;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("案例分析");

        /**
         * 点击item 跳转到展示PDF页面,PDF阅读器
         * 目前假数据
         */
        findViewById(R.id.bt_myoutbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StudentPdfActivity.class);
            }
        });

        leftGoBack(this);

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
