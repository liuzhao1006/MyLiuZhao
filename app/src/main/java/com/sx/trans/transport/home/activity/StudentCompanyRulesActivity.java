package com.sx.trans.transport.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;

public class StudentCompanyRulesActivity extends BaseTransActivity implements BaseNetApi{


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_company_rules;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
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
