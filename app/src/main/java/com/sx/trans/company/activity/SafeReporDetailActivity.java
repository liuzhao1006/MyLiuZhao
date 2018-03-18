package com.sx.trans.company.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.bean.SafeRepotBean;
import com.sx.trans.widget.font.LzTextView;

public class SafeReporDetailActivity extends BaseTransActivity implements BaseNetApi {
private LzTextView tv_handlefeedback_title;
    private  LzTextView  tv_handlefeedback_date ;

    private  LzTextView  tv_tousuren ;
    private  LzTextView tv_contactfind  ;
    private  LzTextView  tv_contentfind ;
    private  LzTextView  tv_contentfind1 ;
    private  LzTextView  tv_contentfind2;
    private  LzTextView  tv_contentfind3 ;
    private  LzTextView  tv_contentfind4 ;
    private  LzTextView  tv_contentfind5 ;
    private  LzTextView  tv_contentfind6 ;
    private  LzTextView  tv_contentfind7 ;
    private  LzTextView  tv_contentfind8 ;
    private  LzTextView  tv_contentfind9 ;
    private  LzTextView  tv_contentfind10 ;
    private  LzTextView  tv_contentfind11 ;




private SafeRepotBean.DataBean bean;

    protected int getContentViewLayoutID() {
        return R.layout.activity_company_safereportdetails;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);

        l.setText("安全报告详情");

        tv_handlefeedback_title=  findViewById(R.id.tv_handlefeedback_title);
        tv_handlefeedback_title.ShowAll();
        tv_handlefeedback_date= findViewById(R.id.tv_handlefeedback_date);
        tv_handlefeedback_date.ShowAll();
        tv_tousuren=  findViewById(R.id.tv_tousuren);
        tv_tousuren.ShowAll();
        tv_contactfind= findViewById(R.id.tv_contactfind);
        tv_contactfind.ShowAll();
        tv_contentfind1=  findViewById(R.id.tv_contentfind1);
        tv_contentfind1.ShowAll();
        tv_contentfind2=  findViewById(R.id.tv_contentfind2);
        tv_contentfind2.ShowAll();
        tv_contentfind3=  findViewById(R.id.tv_contentfind3);
        tv_contentfind3.ShowAll();
        tv_contentfind4=  findViewById(R.id.tv_contentfind4);
        tv_contentfind4.ShowAll();
        tv_contentfind5=  findViewById(R.id.tv_contentfind5);
        tv_contentfind5.ShowAll();
        tv_contentfind6=  findViewById(R.id.tv_contentfind6);
        tv_contentfind6.ShowAll();
        tv_contentfind7=  findViewById(R.id.tv_contentfind7);
        tv_contentfind7.ShowAll();
        tv_contentfind8=  findViewById(R.id.tv_contentfind8);
        tv_contentfind8.ShowAll();
        tv_contentfind9=  findViewById(R.id.tv_contentfind9);
        tv_contentfind9.ShowAll();
        tv_contentfind10=  findViewById(R.id.tv_contentfind10);
        tv_contentfind10.ShowAll();
        tv_contentfind11=  findViewById(R.id.tv_contentfind11);
        tv_contentfind11.ShowAll();

        Intent intent = getIntent();
        if (intent != null) {

            Bundle data = intent.getBundleExtra("SafeRulesDetialActivity");
            if (data != null) {
                bean = (SafeRepotBean.DataBean) data.getSerializable("SafeRulesDetialActivity.class");
                LogUtils.i(bean);

                initData(bean);
            }
        }
        leftGoBack(this);
    }
public void initData(SafeRepotBean.DataBean bean){
    tv_handlefeedback_title.setText(bean.getCompanyName());

    tv_handlefeedback_date.setText(bean.getAnalysisDate()+"");
    tv_tousuren.setText(bean.getVehicleAccessCount()+"个");
    tv_contactfind.setText(bean.getVehicleOnlineRate()+"");//在线率
    tv_contentfind1.setText(bean.getAlarmVehicleCount()+"个");//报警车辆数
    tv_contentfind2.setText(bean.getAlarmCount()+"个");//报警总数
    tv_contentfind3.setText(bean.getAlarmVehicleRate()+"");//报警率
    tv_contentfind4.setText(bean.getOverSpeedCount()+"个");//报警数
    tv_contentfind5.setText(bean.getSpeed160Count()+"个");//超过160KM/H:
    tv_contentfind6.setText(bean.getOverSpeedHandler()+"个");//已处理
    tv_contentfind7.setText(bean.getTiredCount()+"个");//报警数
    tv_contentfind8.setText(bean.getTiredDuration()+"小时");//时长
    tv_contentfind9.setText(bean.getTiredHandler()+"个");//已处理
    tv_contentfind10.setText(bean.getProhibitDrivingCount()+"个");//报警数
    tv_contentfind11.setText(bean.getProhibitDrivingHandler()+"个");//已处理





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
