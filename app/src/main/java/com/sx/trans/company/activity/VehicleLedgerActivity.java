package com.sx.trans.company.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.bean.CommpanyVehicItemBean;
import com.sx.trans.company.bean.CommpanyVehicleBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;


/**
 * 车辆台账
 */
public class VehicleLedgerActivity extends BaseTransActivity {

    private CommpanyVehicleBean bean;
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
    private LzTextView ltv_jsy;
    private LzTextView ltv_cyzgzh;
    private LzTextView ltv_cyzglb;
    private LzTextView ltv_cph;
    private LzTextView ltv_yszh;
    private LzTextView ltv_cx;
    private LzTextView ltv_hdrs;
    private LzTextView ltv_szrshu;
    private LzTextView ltv_wxhxppm;
    private LzTextView ltv_yxxl;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_vehicle_ledger;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("车辆台账");
            Bundle data = intent.getBundleExtra("VehicleLedgerActivity");
            if (data != null) {
                bean = (CommpanyVehicleBean) data.getSerializable("VehicleLedgerActivity.class");
                LogUtils.i(bean);
                initData(bean);
            }
        }
    }

    /**
     * 基础信息展示：车牌号（车辆年限，在线状态：默认在线）、
     * 行驶证（行驶证下次到期时间）、
     * 道路运输证（道路运输证下次到期时间）、
     * 年审到期（车辆下次年审日期）、
     * 下次维护日期（车辆下次维护日期）、
     * 所属公司（车辆所属公司）、
     * 载客人数（车辆载客人数）、
     * 核载吨位（车辆核载吨位）、
     * 行驶里程（车辆当前行驶里程）、
     * 所属班线（客运专有，线路名称）
     * 监控报警数据（暂定不实现）：轨迹回放（暂不处理）、
     * 视频监控（暂不处理）、
     * 超速（总超速报警次数，未处理超速报警次数）、疲劳驾驶（总疲劳驾驶报警次数，未处理疲劳驾驶报警次数）、
     * 夜间行车（总夜间行车次数，未处理夜间行车次数）
     * 安全管理业务数据：事故（本车辆关联的事故总数，未分析事故数）、保险（本车辆关联的保险数）
     */
    private void initData(CommpanyVehicleBean bean) {
        ltv_name.setText(bean.getVheicleUseYear()+"年");
        ltv_type.setText(bean.getVehicleOnline());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getDrivingLicenseExpiryDate().split(" ")[0]);
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getShippingAnnualDate().split(" ")[0]);
        ltv_time.ShowAll();
        ltv_address.setText(bean.getAnnualValidity().split(" ")[0]);
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getDefendValTime().split(" ")[0]);
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getCompanyName());
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getCheckPerson()+"个");
        ltv_lmzk.setText(bean.getAllQuality()+"吨");
        ltv_zjyy.setText(bean.getMileage()+"公里");
        ltv_jsy.setText(bean.getClassLineName());
        ltv_jsy.ShowAll();
        ltv_cyzglb.setText(bean.getVehicleSpeedCount()+"次");
        ltv_cyzgzh.setText(bean.getVehicleSpeedNoCount()+"次");
        ltv_cph.setText(bean.getVehicleFatigueDrivingCount()+"次");
        ltv_yszh.setText(bean.getVehicleFatigueDrivingNoCount()+"次");
        ltv_cx.setText(bean.getVehicleNightDrivingCount()+"次");
        ltv_hdrs.setText(bean.getVehicleNightDrivingNoCount()+"次");
        ltv_szrshu.setText(bean.getAccidentReportCount()+"个");
        ltv_wxhxppm.setText(bean.getAccidentreportCountNo()+"个");
        ltv_yxxl.setText(bean.getVehicleSafeInfo()+"个");
    }


    private void initFindView() {
        //车辆年限
        ltv_name = findViewById(R.id.ltv_name);
        //在线状态
        ltv_type = findViewById(R.id.ltv_type);
        //行驶证下次到期时间
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //道路运输证下次到期时间
        ltv_time = findViewById(R.id.ltv_time);
        //车辆下次年审日期
        ltv_address = findViewById(R.id.ltv_address);
        //车辆下次维护日期
        ltv_sky = findViewById(R.id.ltv_sky);
        //车辆所属公司
        ltv_grade = findViewById(R.id.ltv_grade);
        //车辆载客人数
        ltv_situation = findViewById(R.id.ltv_situation);
        //车辆核载吨位
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //车辆当前行驶里程
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //所属班线
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //总超速报警次数
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //未处理超速报警次数
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //总疲劳驾驶报警次数
        ltv_cph = findViewById(R.id.ltv_cph);
        //未处理疲劳驾驶报警次数
        ltv_yszh = findViewById(R.id.ltv_yszh);
        //总夜间行车次数
        ltv_cx = findViewById(R.id.ltv_cx);
        //未处理夜间行车次数
        ltv_hdrs = findViewById(R.id.ltv_hdrs);
        //本车辆关联的事故总数
        ltv_szrshu = findViewById(R.id.ltv_szrshu);
        //未分析事故数
        ltv_wxhxppm = findViewById(R.id.ltv_wxhxppm);
        //本车辆关联的保险数
        ltv_yxxl = findViewById(R.id.ltv_yxxl);

    }
}
