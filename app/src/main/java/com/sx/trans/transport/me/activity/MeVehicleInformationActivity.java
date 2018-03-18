package com.sx.trans.transport.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.bean.CommpanyVehicleBean;
import com.sx.trans.transport.me.bean.MeVehicleInfoBean;
import com.sx.trans.widget.font.LzTextView;

/**
 * 从业端车辆信息
 */
public class MeVehicleInformationActivity extends BaseTransActivity {

    private MeVehicleInfoBean bean;
    private LzTextView ltv_cyzglb;
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


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_me_vehicle_information;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("车辆信息");
            Bundle data = intent.getBundleExtra("MeVehicleInformationActivity");
            if (data != null) {
                bean = (MeVehicleInfoBean) data.getSerializable("MeVehicleInformationActivity.class");
                LogUtils.i(bean);
                initData(bean);
            }
        }
    }


    private void initData(MeVehicleInfoBean bean) {
        ltv_cyzglb.setText(bean.getVehicleNo());
        ltv_cyzglb.ShowAll();
        ltv_name.setText(bean.getVheicleUseYear());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getVehicleNoColor());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getVehicleType());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getDrivingLicenseExpiryDate().split(" ")[0]);
        ltv_time.ShowAll();
        ltv_address.setText(bean.getShippingAnnualDate().split(" ")[0]);
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getAnnualValidity());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getDefendValTime().split(" ")[0]);
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getCompanyName());
        ltv_situation.ShowAll();
        ltv_lmzk.setText(bean.getCheckPerson());
        ltv_lmzk.ShowAll();
        ltv_zjyy.setText(bean.getAllQuality());
        ltv_zjyy.ShowAll();
        ltv_jsy.setText(bean.getClassLineName());
        ltv_jsy.ShowAll();
    }


    private void initFindView() {
        //vehicleNo车牌号
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);

        //vheicleUseYear车辆使用年限
        ltv_name = findViewById(R.id.ltv_name);
        //vehicleNoColor车牌颜色
        ltv_type = findViewById(R.id.ltv_type);
        //vehicleType车辆类型
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //drivingLicenseExpiryDate行驶证下次到期时间
        ltv_time = findViewById(R.id.ltv_time);
        //shippingAnnualDate道路运输证下次到期时间
        ltv_address = findViewById(R.id.ltv_address);
        //annualValidity线路牌年审有效期
        ltv_sky = findViewById(R.id.ltv_sky);
        //defendValTime车辆下次维护日期
        ltv_grade = findViewById(R.id.ltv_grade);
        //companyName所属单位
        ltv_situation = findViewById(R.id.ltv_situation);
        //checkPerson核载人数
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //allQuality核载吨数
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //ClassLineName班线名称
        ltv_jsy = findViewById(R.id.ltv_jsy);
    }


}
