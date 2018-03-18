package com.sx.trans.transport.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

import java.text.SimpleDateFormat;

public class StudentRemberActivity extends BaseTransActivity implements BaseNetApi {


    private RemberBean accidentBean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_rember;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("事故提醒");
        leftGoBack(this);

        Intent intent = getIntent();
        if (intent != null) {
            l.setText("提醒");
            Bundle data = intent.getBundleExtra("StudentRemberActivity");
            accidentBean = (RemberBean) data.getSerializable("StudentRemberActivity.class");
            LogUtils.i(accidentBean);
        }

//        Utils.getCurrentTimes();
        LzTextView tv_time = findViewById(R.id.tv_time);
        LzTextView tv_name = findViewById(R.id.tv_name);
//        tv_time.setText(accidentBean.getReferenceTime());
        tv_name.setText(PrefUtils.getString(this, LoginConfig.EMPLOYEE_USERNAME, null));

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
