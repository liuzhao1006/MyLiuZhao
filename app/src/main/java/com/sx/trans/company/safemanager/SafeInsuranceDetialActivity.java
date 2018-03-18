package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;

/**
 * 保险详情页
 */
public class SafeInsuranceDetialActivity extends BaseTransActivity {
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
    private LzTextView ltv_cyzglb;
    private LinearLayout ll_xzxx_list;
    private LzTextView ltv_down;
    private SafeInsuranceBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_insurance_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("保险详情");
            Bundle data = intent.getBundleExtra("SafeInsuranceDetialActivity");
            if (data != null) {
                bean = (SafeInsuranceBean) data.getSerializable("SafeInsuranceDetialActivity.class");
                LogUtils.i(bean);
                initData(bean);
            }
        }
    }

    /**
     * 赋值
     */
    private void initData(SafeInsuranceBean bean) {
        ltv_name.setText(bean.getVehicle_no());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getCar_body_color());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getOperate_type());
        ltv_xingtai.ShowAll();
        ltv_address.setText(bean.getCarframe_code());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getForm_code());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getSafe_company());
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getForm_money()+"元");
        ltv_situation.ShowAll();
        ltv_lmzk.setText(bean.getSafe_money());
        ltv_lmzk.ShowAll();
        ltv_zjyy.setText(Utils.getCurrentTimes(bean.getBegin_time()).split("\\s+")[0]);
        ltv_zjyy.ShowAll();
        ltv_jsy.setText(Utils.getCurrentTimes(bean.getEnd_time()).split("\\s+")[0]);
        ltv_jsy.ShowAll();
        ltv_cyzglb.setText(bean.getSafe_company());
        ltv_cyzglb.ShowAll();
    }

    /**
     * 初始化控件
     * <p>
     * <p>
     * 详情页：展示以下内容：
     * 车辆信息：车牌号、车牌颜色、经营类型、经营性质、发动机号；
     * 保单信息：
     * 保单号、承保公司、保额、保险费、开始日期、终止日期、承保公司；险种信息（列表）：险种、保额、保费；
     * 附件：显示附件名称，可点击下载；
     * 注：一个车辆有多条保单、一个保单有多个险种；
     */

    private void initFindView() {
        //车牌号
        ltv_name = findViewById(R.id.ltv_name);
        //车牌颜色
        ltv_type = findViewById(R.id.ltv_type);
        //经营类型
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //经营性质
        ltv_time = findViewById(R.id.ltv_time);
        //发动机号
        ltv_address = findViewById(R.id.ltv_address);
        //保单号
        ltv_sky = findViewById(R.id.ltv_sky);
        //承保公司
        ltv_grade = findViewById(R.id.ltv_grade);
        //保额
        ltv_situation = findViewById(R.id.ltv_situation);
        //保险费
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //开始日期
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //终止日期
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //承保公司
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);


        //险种信息（列表）：险种、保额、保费；
        ll_xzxx_list = findViewById(R.id.ll_xzxx_list);
        //附件：显示附件名称，可点击下载；
        ltv_down = findViewById(R.id.ltv_down);

    }


}
