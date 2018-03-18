package com.sx.trans.company.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;


/**
 * Created by Administrator on 2017/9/8.
 */

public class CommpanyAssuranceActivity extends BaseActivity {


    ImageView layoutTopLeftimg;

    TextView layoutTopLeftinfo;

    LinearLayout layoutTopWithButtomTmp;

    TextView layoutTopModleinfo;

    ImageView layoutTopRightinfo;

    TextView layoutTopRighttextinfo;

    ImageView imgCompanysafe;

    TextView tvLifeInsuranceDate;

    TextView tvCompulsoryInsuranceDate;

    TextView tvCommercialInsuranceDate;

    TextView tvCargoInsuranceDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_commpany_assurance);

        LzApp.addActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setTopModleText("保险详情页");
        layoutTopLeftimg = findViewById(R.id.layout_top_leftimg);
        layoutTopLeftinfo = findViewById(R.id.layout_top_leftinfo);
        layoutTopWithButtomTmp = findViewById(R.id.layout_top_with_buttom_tmp);
        layoutTopModleinfo = findViewById(R.id.layout_top_modleinfo);

        layoutTopRightinfo = findViewById(R.id.layout_top_rightinfo);
        layoutTopRighttextinfo = findViewById(R.id.layout_top_righttextinfo);
        imgCompanysafe = findViewById(R.id.img_companysafe);
        tvLifeInsuranceDate = findViewById(R.id.tv_lifeInsuranceDate);

        tvCompulsoryInsuranceDate = findViewById(R.id.tv_CompulsoryInsuranceDate);
        tvCommercialInsuranceDate = findViewById(R.id.tv_commercialInsuranceDate);
        tvCargoInsuranceDate = findViewById(R.id.tv_cargoInsuranceDate);


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
}
