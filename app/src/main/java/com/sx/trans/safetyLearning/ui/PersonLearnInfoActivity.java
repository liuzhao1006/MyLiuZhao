package com.sx.trans.safetyLearning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.widget.font.LzTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xxxxxx on 2018/2/5.
 */

public class PersonLearnInfoActivity extends BaseTransActivity implements BaseNetApi, View.OnClickListener {
    @InjectView(R.id.layout_top_leftimg)
    ImageView layoutTopLeftimg;
    @InjectView(R.id.layout_top_leftinfo)
    TextView layoutTopLeftinfo;
    @InjectView(R.id.layout_top_with_buttom_tmp)
    LinearLayout layoutTopWithButtomTmp;
    @InjectView(R.id.layout_top_modleinfo)
    TextView layoutTopModleinfo;
    @InjectView(R.id.layout_top_rightinfo)
    ImageView layoutTopRightinfo;
    @InjectView(R.id.layout_top_righttextinfo)
    TextView layoutTopRighttextinfo;
    @InjectView(R.id.im)
    ImageView im;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.im_gender)
    ImageView imGender;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.ltv_birthiday)
    LzTextView ltvBirthiday;
    @InjectView(R.id.ltv_idnumber)
    LzTextView ltvIdnumber;
    @InjectView(R.id.ltv_address)
    LzTextView ltvAddress;
    @InjectView(R.id.ltv_qualificationCode)
    LzTextView ltvQualificationCode;
    @InjectView(R.id.ltv_drivetype)
    LzTextView ltvDrivetype;
    @InjectView(R.id.ltv_company)
    LzTextView ltvCompany;
    @InjectView(R.id.ltv_certificate_number)
    LzTextView ltvCertificateNumber;
    @InjectView(R.id.ltv_firm_name)
    LzTextView ltvFirmName;
    @InjectView(R.id.ltv_getdate)
    LzTextView ltvGetdate;
    @InjectView(R.id.ltv_license_number)
    LzTextView ltvLicenseNumber;
    @InjectView(R.id.ll_next)
    LinearLayout llNext;

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_next://学习明细详情
                intent = new Intent(PersonLearnInfoActivity.this, PersonLearnInfoDetailActivity.class);

                startActivity(intent);
                break;
            default:
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

    @Override
    protected int getContentViewLayoutID() {

        return R.layout.activity_learn_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        leftGoBack(this);
        layoutTopModleinfo.setText("本月完成进度");
        llNext.setOnClickListener(this);
    }
}
