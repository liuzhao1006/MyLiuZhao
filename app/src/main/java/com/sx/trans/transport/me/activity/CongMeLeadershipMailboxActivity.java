package com.sx.trans.transport.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;

import java.text.SimpleDateFormat;

public class CongMeLeadershipMailboxActivity extends BaseTransActivity implements BaseNetApi {
private  TextView   tv_handlefeedback_title;
    private  TextView  tv_handlefeedback_date ;

    private  TextView  tv_tousuren ;
    private  TextView tv_contactfind  ;

private CQueryLeaderMailbox.DataBean bean;
    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.activity_me_leadership_mailbox;
//    }
    protected int getContentViewLayoutID() {
        return R.layout.activity_person_handlefeedback;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);

        l.setText("领导信箱");
        tv_handlefeedback_title=  findViewById(R.id.tv_handlefeedback_title);
        tv_handlefeedback_date= findViewById(R.id.tv_handlefeedback_date);
        tv_tousuren=  findViewById(R.id.tv_tousuren);
        tv_contactfind= findViewById(R.id.tv_contactfind);



        Intent intent = getIntent();
        if (intent != null) {

            Bundle data = intent.getBundleExtra("SafeRulesDetialActivity");
            if (data != null) {
                bean = (CQueryLeaderMailbox.DataBean) data.getSerializable("SafeRulesDetialActivity.class");
                LogUtils.i(bean);

                initData(bean);
            }
        }
        leftGoBack(this);
    }
    public void initData(CQueryLeaderMailbox.DataBean bean) {
        String timeDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bean.getCreateDate());
        tv_handlefeedback_title.setText(bean.getTitle());
        tv_handlefeedback_date.setText(timeDetail.split(" ")[0]+"");
        tv_tousuren.setText(bean.getContent());
        tv_contactfind.setText(bean.getHandleIdea());

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
