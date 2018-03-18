package com.sx.trans.transport.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseFragment;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.company.safemanager.SafeInsuranceDetialActivity;
import com.sx.trans.login.SplashActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.dynamicMonitoring.activity.MailBoxActivity;
import com.sx.trans.transport.dynamicMonitoring.activity.NoticeActivity;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.transport.me.activity.MeLeadershipMailboxActivity;
import com.sx.trans.transport.me.activity.MeVehicleInformationActivity;
import com.sx.trans.transport.me.bean.MeVehicleInfoBean;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 * 学员端
 */

public class MeFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        LzTextView ltv_name = mRootView.findViewById(R.id.ltv_name);//姓名
        if (PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_USERNAME, null) != null) {
            ltv_name.setText(PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_USERNAME, null));
        }
        LzTextView ltv_phone = mRootView.findViewById(R.id.ltv_phone);//电话
        if (PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_PHONE, null) != null) {
            ltv_phone.setText("电话: " + PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_PHONE, null));
        }

        final LinearLayout ll_car_info = mRootView.findViewById(R.id.ll_car_info);//车辆信息
        final LinearLayout ll_policy_info = mRootView.findViewById(R.id.ll_policy_info);

        EmployeesManager manager = new EmployeesManager(getActivity(), this);
        manager.getEmployeeIdInsuranceList();
        manager.getEmployeeIdVehicleList();
        manager.setOnCarData(new EmployeesManager.CarData() {
            @Override
            public void onCarData(int what, final List<MeVehicleInfoBean> carList) {
                ll_car_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), MeVehicleInformationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("MeVehicleInformationActivity.class", carList.get(0));
                        intent.putExtra("MeVehicleInformationActivity", bundle);
                        startActivity(intent);
                    }
                });
            }
        });
        manager.setOnInsuranceData(new EmployeesManager.InsuranceData() {
            @Override
            public void onInsuranceData(int what, final List<SafeInsuranceBean> learnList) {
                ll_policy_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), SafeInsuranceDetialActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("SafeInsuranceDetialActivity.class", learnList.get(0));
                        intent.putExtra("SafeInsuranceDetialActivity", bundle);
                        startActivity(intent);
                    }
                });
            }
        });

        mRootView.findViewById(R.id.ll_leadership_mailbox_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//领导信箱
                startActivity(new Intent(getActivity(), MailBoxActivity.class));
            }
        });
        mRootView.findViewById(R.id.ll_student_wodebaomingyixiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//通知消息
                startActivity(new Intent(getActivity(), NoticeActivity.class));
            }
        });
        Button bt_myoutbutton = mRootView.findViewById(R.id.bt_myoutbutton);
        bt_myoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setTitle("提示");

                builder.setMessage("请选择执行操作？");

                builder.setPositiveButton("退出程序", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LzApp.exit();
                        getActivity().finish();

                    }
                });

                builder.setNegativeButton("注销用户", new DialogInterface.OnClickListener() {
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

            }
        });
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
