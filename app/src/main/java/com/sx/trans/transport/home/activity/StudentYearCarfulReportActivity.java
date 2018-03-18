package com.sx.trans.transport.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.transport.home.bean.YearCarfulBean;

public class StudentYearCarfulReportActivity extends BaseTransActivity {


    private YearCarfulBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_year_careful;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initData();
        l.setText("年审详情");
        leftGoBack(this);
        Intent intent = getIntent();
        if (intent != null) {
            l.setText("年审详情");
            Bundle data = intent.getBundleExtra("StudentYearCarfulReportActivity.class");
            bean = (YearCarfulBean) data.getSerializable("StudentYearCarfulReportActivity");
            LogUtils.i(bean);
            if (bean != null) {

            }
        }
    }

    /**
     * 初始化控件
     */
    private void initData() {
    }
}
