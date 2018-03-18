package com.sx.trans.transport.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;

public class MePolicyInformationActivity extends BaseTransActivity implements BaseNetApi {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_me_policy_information;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("保险详情");
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
