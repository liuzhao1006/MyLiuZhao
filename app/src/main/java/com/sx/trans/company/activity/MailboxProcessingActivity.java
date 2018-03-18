package com.sx.trans.company.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lz.cloud.widget.LzBar;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.company.adapter.CommonQueryAdapter;


public class MailboxProcessingActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    EditText etPersonnel;

    TextView tvQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personnel_query);
        LzApp.addActivity(this);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal),0);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {

        setTopModleText("信箱处理");
        etPersonnel = findViewById(R.id.et_personnel);
        tvQuery = findViewById(R.id.tv_query);


        etPersonnel.setVisibility(View.GONE);
        tvQuery.setVisibility(View.GONE);


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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(MailboxProcessingActivity.this, CommpanyHandleFeedBackActivity.class);

        startActivity(intent);
    }
}
