package com.sx.trans.transport.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.home.bean.AccidentBean;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.widget.font.LzTextView;

/**
 * 维护详情
 */
public class StudentMaintenanceReportActivity extends BaseTransActivity {

    private MaintenanceBean accidentBean;
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


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_maintenance_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initData();
        l.setText("维护详情");
        leftGoBack(this);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("StudentMaintenanceReportActivity.class");
            accidentBean = (MaintenanceBean) data.getSerializable("StudentMaintenanceReportActivity");
            LogUtils.i(accidentBean);
            if (accidentBean != null) {
                ltv_name.setText(accidentBean.getRegistration_no());
                ltv_name.ShowAll();
                ltv_type.setText(accidentBean.getCarframe_code());
                ltv_type.ShowAll();
                ltv_xingtai.setText(accidentBean.getCompany_name());
                ltv_xingtai.ShowAll();
                ltv_time.setText(accidentBean.getDefend_time().split(" ")[0]);
                ltv_time.ShowAll();
                ltv_address.setText(accidentBean.getNext_time().split(" ")[0]);
                ltv_address.ShowAll();
                ltv_sky.setText(accidentBean.getOperating_mileage());
                ltv_sky.ShowAll();
                ltv_grade.setText(accidentBean.getService_company());
                ltv_grade.ShowAll();
                ltv_situation.setText(accidentBean.getRepair_code());
                ltv_situation.ShowAll();
                ltv_zjyy.setText(accidentBean.getCheck_code());
                ltv_zjyy.ShowAll();
                ltv_lmzk.setText(accidentBean.getCertifivate_code());
                ltv_lmzk.ShowAll();
                if (accidentBean.isCheck_out()) {
                    ltv_jsy.setText("是");
                } else {
                    ltv_jsy.setText("否");
                }
                ltv_cyzglb.setText(accidentBean.getLine_service_record_time());
                ltv_cyzglb.ShowAll();
                ltv_cyzgzh.setText(accidentBean.getAdd_word());
                ltv_cyzgzh.ShowAll();
                ltv_cph.setText(accidentBean.getMain_parts_change());
                ltv_cph.ShowAll();
                ltv_yszh.setText(accidentBean.getDefend_remark());
                ltv_yszh.ShowAll();

            }
        }
    }

    /**
     * 维护信息初始化控件
     */
    private void initData() {
        //车牌号
        ltv_name = findViewById(R.id.ltv_name);
        //车架号
        ltv_type = findViewById(R.id.ltv_type);
        //所属机构
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //维护日期
        ltv_time = findViewById(R.id.ltv_time);
        //下次维护日期
        ltv_address = findViewById(R.id.ltv_address);
        //营运公里数
        ltv_sky = findViewById(R.id.ltv_sky);
        //维护单位
        ltv_grade = findViewById(R.id.ltv_grade);
        //维修合同编号
        ltv_situation = findViewById(R.id.ltv_situation);
        //合格证号码
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //检测编号
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //是否合格
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //运管备案日期
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //主要附加作业项目
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //总成及主要零部件更换
        ltv_cph = findViewById(R.id.ltv_cph);
        //备注
        ltv_yszh = findViewById(R.id.ltv_yszh);


    }

}
