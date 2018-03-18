package com.sx.trans.company.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.bean.CommpanyEmployeeListBean;
import com.sx.trans.widget.font.LzTextView;


public class PersonnelLedgerActivity extends BaseTransActivity {

    private CommpanyEmployeeListBean bean;

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
    private LzTextView ltv_xllb;
    private LzTextView ltv_sfd;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_personnel_ledger;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("人员台账");
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            Bundle data = intent.getBundleExtra("PersonnelLedgerActivity");
            if (data != null) {
                bean = (CommpanyEmployeeListBean) data.getSerializable("PersonnelLedgerActivity.class");
                initData(bean);
            }
        }
    }


    /**
     * 设置数据
     */
    private void initData(CommpanyEmployeeListBean bean) {
        ltv_name.setText(bean.getCompanyName());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getAge() + "岁");
        ltv_xingtai.setText(bean.getPhone());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getCarType());
        ltv_time.ShowAll();

        ltv_address.setText(bean.getDriverYear());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getDriverNumberLimited());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getEmploymentType());
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getLicenseLimited().split(" ")[0]);
        ltv_situation.ShowAll();
        ltv_lmzk.setText(bean.getTravelLine());//客运专属
        ltv_lmzk.ShowAll();
        ltv_jsy.setText(bean.getVehicleSpeedCount()+"次");
        ltv_cyzglb.setText(bean.getVehicleSpeedNoCount()+"次");
        ltv_cyzgzh.setText(bean.getVehicleFatigueDrivingCount()+"次");
        ltv_cph.setText(bean.getVehicleFatigueDrivingNoCount()+"次");
        ltv_yszh.setText(bean.getVehicleNightDrivingCount()+"次");
        ltv_cx.setText(bean.getVehicleNightDrivingNoCount()+"次");
        ltv_hdrs.setText(bean.getAccidentAllCount()+"个");
        ltv_szrshu.setText(bean.getAccidentCountNo()+"个");
        ltv_yxxl.setText(bean.getYearSafeLearn()+"个");
        ltv_xllb.setText(bean.getYearSpecialLearn()+"个");
        ltv_sfd.setText(bean.getMonthSafeLearn()+"个");

    }


    /**
     * 初始化控件
     */
    private void initFindView() {
        //所属企业
        ltv_name = findViewById(R.id.ltv_name);
        //年龄
        ltv_type = findViewById(R.id.ltv_type);
        //手机号
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //驾照类型
        ltv_time = findViewById(R.id.ltv_time);
        //驾龄
        ltv_address = findViewById(R.id.ltv_address);
        //到期时间
        ltv_sky = findViewById(R.id.ltv_sky);
        //从业类别名
        ltv_grade = findViewById(R.id.ltv_grade);
        //到期时间
        ltv_situation = findViewById(R.id.ltv_situation);
        //客运专属
        ltv_lmzk = findViewById(R.id.ltv_lmzk);

        //总超速报警次数
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //未处理超速报警次数
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //总疲劳驾驶报警次数
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //未处理疲劳驾驶报警次数
        ltv_cph = findViewById(R.id.ltv_cph);
        //总夜间行车次数
        ltv_yszh = findViewById(R.id.ltv_yszh);
        //未处理夜间行车次数
        ltv_cx = findViewById(R.id.ltv_cx);
        //本人关联的事故总数
        ltv_hdrs = findViewById(R.id.ltv_hdrs);
        //未分析事故数
        ltv_szrshu = findViewById(R.id.ltv_szrshu);

        //年度安全学习
        ltv_yxxl = findViewById(R.id.ltv_yxxl);
        //年度专项学习
        ltv_xllb = findViewById(R.id.ltv_xllb);
        //本月安全学习
        ltv_sfd = findViewById(R.id.ltv_sfd);

    }

}
