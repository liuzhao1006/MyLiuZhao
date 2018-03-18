package com.sx.trans.transport.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.widget.font.LzTextView;

/**
 * 提醒详情
 */
public class StudentCarfulStopActivity extends BaseTransActivity {


    private RemberBean bean;

    private LzTextView ltv_name;
    private LzTextView ltv_type;
    private LzTextView ltv_xingtai;
    private LzTextView ltv_time;
    private LzTextView ltv_address;
    private LzTextView ltv_sky;
    private LzTextView ltv_grade;
    private LzTextView ltv_situation;
    private LzTextView ltv_lmzk;
    private LzTextView ltv_zjyy;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_carful_stop;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("提醒信息");
            Bundle data = intent.getBundleExtra("StudentCarfulStopActivity");
            if (data != null) {
                bean = (RemberBean) data.getSerializable("StudentCarfulStopActivity.class");
                LogUtils.i(bean);
                initData(bean);
            }
        }
    }


    /**
     * 事故数据设置
     */
    private void initData(RemberBean bean) {
        ltv_name.setText(bean.getTargetName());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getRemindCount() + "次");
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getAlarmDateTime().split(" ")[0]);
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getState());
        ltv_time.ShowAll();
        ltv_address.setText(bean.getHandleMessage());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getUserName());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getHandleDatetime().split(" ")[0]);
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getHandleOpinion());
        ltv_situation.ShowAll();
        ltv_lmzk.setText(bean.getPersonLiable());
        ltv_lmzk.ShowAll();
        ltv_zjyy.setText(bean.getAlarmDescription());
        ltv_zjyy.ShowAll();
        ltv_address.setOnClickShowContent(new LzTextView.OnClickShowContent() {
            @Override
            public void onClickShowContent(View view) {

            }
        });
    }

    /**
     * 初始化控件
     * 提醒名称（指标项名称）：targetName
     * 提醒次数：remindCount
     * 提醒时间：alarmDateTime
     * 状态：state
     * 详情：（包含列表显示内容）
     * 处置建议：handleMessage
     * 处理人：userName
     * 处理时间：handleDatetime
     * 处理意见：handleOpinion
     * 责任人：personLiable
     * 提醒内容：alarmDescription
     */
    private void initFindView() {
        //提醒名称
        ltv_name = findViewById(R.id.ltv_name);
        //提醒次数
        ltv_type = findViewById(R.id.ltv_type);
        //提醒时间
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //状态
        ltv_time = findViewById(R.id.ltv_time);
        //处置建议
        ltv_address = findViewById(R.id.ltv_address);
        //处理人
        ltv_sky = findViewById(R.id.ltv_sky);
        //处理时间
        ltv_grade = findViewById(R.id.ltv_grade);
        //处理意见
        ltv_situation = findViewById(R.id.ltv_situation);
        //责任人
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //提醒内容
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
    }

    private void showContentDialog(View view){


    }

}
