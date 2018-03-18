package com.sx.trans.company.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.app.LzApp;
import com.sx.trans.company.activity.MailBoxActivity;
import com.sx.trans.company.activity.MailboxProcessingActivity;
import com.sx.trans.company.activity.NoticeActivity;
import com.sx.trans.company.safemanager.SafeAgreementActivity;
import com.sx.trans.login.SplashActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentSpecialStudyActivity;
import com.sx.trans.transport.me.activity.MeLeadershipMailboxActivity;
import com.sx.trans.widget.font.LzTextView;


/**
 * Created by Administrator on 2017/9/7.
 */

public class CompanyMyFragment extends Fragment implements View.OnClickListener {

    private ImageView imgMyIcon;
    private LzTextView ltvUserName;
    private LzTextView ltvPlace;
    private LzTextView ltvTel;
    private LinearLayout LinearLayout1;
    private LinearLayout llSpecialExercise;
    private LinearLayout llSafetyResponsibilityAgreement;
    private LinearLayout llMailboxProcessing;
    private LinearLayout llAdvertiseMessage;
    private LinearLayout llUsingHelp;
    private Button btOutButton;
    private LinearLayout layoutTopWithButtomTmp;
    private TextView layoutTopModleinfo;
    private ImageView layoutTopRightinfo;
    private TextView layoutTopRighttextinfo;
    private View v;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_company_me, null);
        inteView();
        return v;
    }

    private void inteView() {
        imgMyIcon = v.findViewById(R.id.img_myIcon);
        String imgPath = PrefUtils.getString(getActivity(), LoginConfig.COMPANY_IMGURL, null);
        Glide.with(getActivity())
                .load(imgPath)
                .placeholder(R.drawable.vehicle) //设置占位图
                .error(R.drawable.vehicle) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                //.dontAnimate() //不显示动画效果
                .into(imgMyIcon);
        ltvUserName = v.findViewById(R.id.ltv_userName);
        ltvPlace = v.findViewById(R.id.ltv_Place);
        ltvTel = v.findViewById(R.id.ltv_Tel);
        LinearLayout1 = v.findViewById(R.id.LinearLayout1);
        llSpecialExercise = v.findViewById(R.id.ll_SpecialExercise);
        llSafetyResponsibilityAgreement = v.findViewById(R.id.ll_SafetyResponsibilityAgreement);
        llMailboxProcessing = v.findViewById(R.id.ll_MailboxProcessing);
        llAdvertiseMessage = v.findViewById(R.id.ll_AdvertiseMessage);
        llUsingHelp = v.findViewById(R.id.ll_usingHelp);
        btOutButton = v.findViewById(R.id.bt_outButton);
        layoutTopWithButtomTmp = v.findViewById(R.id.layout_top_with_buttom_tmp);
        layoutTopModleinfo = v.findViewById(R.id.layout_top_modleinfo);
        layoutTopRightinfo = v.findViewById(R.id.layout_top_rightinfo);
        layoutTopRighttextinfo = v.findViewById(R.id.layout_top_righttextinfo);
        llSpecialExercise.setOnClickListener(this);
        llSafetyResponsibilityAgreement.setOnClickListener(this);
        llMailboxProcessing.setOnClickListener(this);
        llAdvertiseMessage.setOnClickListener(this);
        llUsingHelp.setOnClickListener(this);
        btOutButton.setOnClickListener(this);
        ltvUserName.setText(PrefUtils.getString(getActivity(), LoginConfig.COMPANY_USERNAME, null));
        ltvTel.setText("电话:" + PrefUtils.getString(getActivity(), LoginConfig.COMPANY_TEL, null));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_SpecialExercise://专项学习

                startActivity(new Intent(getActivity(), StudentSpecialStudyActivity.class));
                break;
            case R.id.ll_SafetyResponsibilityAgreement://安全责任书
                startActivity(new Intent(getActivity(), SafeAgreementActivity.class));
                break;
            case R.id.ll_MailboxProcessing://信箱处理
                intent = new Intent(getActivity(), MailBoxActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_AdvertiseMessage://公告消息
                intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_usingHelp://使用帮助

                break;
            case R.id.bt_outButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("提示").setMessage("请选择执行操作？")
                        .setPositiveButton("退出程序", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LzApp.exit();
//                                getActivity().finish();
                                System.out.println("退出应用程序。。。。。。");
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("注销用户", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent in = new Intent(getActivity(), SplashActivity.class);
                                LzApp.exit();
                                getActivity().startActivity(in);
                                getActivity().finish();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }
}
