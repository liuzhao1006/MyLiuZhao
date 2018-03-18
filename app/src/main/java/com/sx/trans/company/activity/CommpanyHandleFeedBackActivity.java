package com.sx.trans.company.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;



/**
 * 信箱处理
 */
public class CommpanyHandleFeedBackActivity extends BaseActivity {



    ImageView layoutTopLeftimg;

    TextView layoutTopLeftinfo;

    LinearLayout layoutTopWithButtomTmp;

    TextView layoutTopModleinfo;

    ImageView layoutTopRightinfo;

    TextView layoutTopRighttextinfo;

    RelativeLayout include1;

    TextView tvHandlefeedbackTitle;

    TextView tvHandlefeedbackDate;

    TextView tvTousuren;

    TextView tvContactfind;

    TextView tvContentfind;

    EditText etPlace;

    Button btAdminPutIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_company_handlefeedback);
        LzApp.addActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setTopModleText("信箱处理");
        layoutTopLeftimg = findViewById(R.id.layout_top_leftimg);
        layoutTopLeftinfo = findViewById(R.id.layout_top_leftinfo);
        layoutTopWithButtomTmp = findViewById(R.id.layout_top_with_buttom_tmp);
        layoutTopModleinfo = findViewById(R.id.layout_top_modleinfo);
        layoutTopRightinfo = findViewById(R.id.layout_top_rightinfo);
        layoutTopRighttextinfo = findViewById(R.id.layout_top_righttextinfo);
        include1 = findViewById(R.id.include1);
        tvHandlefeedbackTitle = findViewById(R.id.tv_handlefeedback_title);
        tvHandlefeedbackDate = findViewById(R.id.tv_handlefeedback_date);
        tvTousuren = findViewById(R.id.tv_tousuren);
        tvContactfind = findViewById(R.id.tv_contactfind);
        etPlace = findViewById(R.id.et_place);
        btAdminPutIn = findViewById(R.id.bt_adminPutIn);
        btAdminPutIn.setOnClickListener(this);

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_adminPutIn:
                break;

        }
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
