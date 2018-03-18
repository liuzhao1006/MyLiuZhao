package com.sx.trans.transport.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.bean.SendLeaderMailbox;
import com.sx.trans.transport.manager.EmployeesManager;

import java.util.List;

public class MeSendMailboxActivity extends BaseTransActivity implements BaseNetApi {
    private EditText tv_handlefeedback_title;
    private EditText tv_handlefeedback_date;
    private EmployeesManager manager;
    private EditText et_place;
    private Button send;


    @Override

    protected int getContentViewLayoutID() {
        return R.layout.activity_send_mailbox;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("发送邮件");


        tv_handlefeedback_title = findViewById(R.id.tv_handlefeedback_title);
        tv_handlefeedback_date = findViewById(R.id.tv_handlefeedback_date);
        et_place = findViewById(R.id.et_place);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //发送邮件
                httpClick();
            }
        });


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

    /**
     * 网络请求
     */
    private void httpClick() {
        String t1 = tv_handlefeedback_title.getText().toString();
        String t2 = tv_handlefeedback_date.getText().toString();
        String t3 = et_place.getText().toString();
        if (TextUtils.isEmpty(t1) | TextUtils.isEmpty(t3)) {
            Toast.makeText(MeSendMailboxActivity.this, "信箱标题和内容不能为空...", Toast.LENGTH_SHORT).show();

        } else {
            manager = new EmployeesManager(this, this);
            manager.getSendLeadMailbox(t1, t2, t3);
            manager.setSendMData(new EmployeesManager.SendLeadMdata() {
                @Override
                public void onSendData(int what, String message) {

                    Toast.makeText(MeSendMailboxActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

}
