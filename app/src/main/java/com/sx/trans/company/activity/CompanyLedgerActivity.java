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
import com.sx.trans.company.bean.CompanyLegerBean;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;

import java.io.Serializable;

/**
 * 台账详情
 */
public class CompanyLedgerActivity extends BaseTransActivity {
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
    private LzTextView ltv_sgzr;
    private LzTextView ltv_bar;
    private LzTextView ltv_badh;
    private LzTextView ltv_ssjg;
    private LzTextView ltv_swrs;
    private LzTextView ltv_bcsw;
    private LzTextView ltv_szsw;
    private LzTextView ltv_wjsw;

    private CompanyLegerBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_company_ledger;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        l.setText("企业台账");
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            Bundle data = intent.getBundleExtra("CompanyLedgerActivity");
            if (data != null) {
                bean = (CompanyLegerBean) data.getSerializable("CompanyLedgerActivity.class");
                initData(bean);
            }
        }
    }

    /**
     * 事故数据设置
     * 基础信息展示：企业名称、
     * 经营许可证号、
     * 车辆数（总车辆数、当前在线车辆数）、人员数（总人员数、当前在岗人员数）、线路数（针对客运企业）
     * <p>
     * 监控报警数据：日报警（当天报警数汇总、未处理报警数；特值监控平台报警，暂定默认值）、
     * 月报警（当月报警数汇总、未处理报警数；特值监控平台报警，暂定默认值）、
     * 总报警（企业当前所有报警数汇总、未处理报警数；特值监控平台报警，暂定默认值）
     * 安全管理业务数据：月学习（当月基础学时，企业未完成人数）、
     * 本月保险到期（本月底保险到期已处理件数，未处理件数；包括企业相关人员和车辆保险）、
     * 本月年审到期（本月底年审到期已处理件数，未处理件数；包括车辆年审）、制度规范（本企业相关制度文件类型个数）、
     * 合同到期（企业当月合同到期数量、未续签合同数量）、
     * 年度安全会议（本年度企业安全会议数）、
     * 年度会议（本年度安全会议总数）、
     * 年度事故（本年度事故总数，未处理分析事故总数）、
     * 年度安全演习（本年度安全演习次数）
     */
    private void initData(CompanyLegerBean bean) {
        ltv_name.setText(bean.getCompanyName());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getLicenceCode());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getVehicleCount() + "辆");
        ltv_time.setText(bean.getVehicleOnlineCount() + "辆");
        ltv_address.setText(bean.getPersonCount() + "个");
        ltv_sky.setText(bean.getPersonCountOnline() + "个");
        ltv_grade.setText(bean.getRouteLineCount() + "条");
        if (bean.getDayALLRisk() != null) {
            ltv_situation.setText(bean.getDayALLRisk() + "个");
        }
        if (bean.getDayNoRisk() != null) {
            ltv_lmzk.setText(bean.getDayNoRisk() + "个");
        }
//        ltv_lmzk.setText(bean.getDayNoRisk() + "个");
        ltv_zjyy.setText(bean.getDayRiskPlate() + "个");
        ltv_jsy.setText(bean.getMonthALLRisk() + "个");
        ltv_cyzglb.setText(bean.getMonthNoRisk() + "个");
        ltv_cyzgzh.setText(bean.getMonthRiskPlate() + "个");
        ltv_cph.setText(bean.getYearALLRisk() + "个");
        ltv_yszh.setText(bean.getYearNoRisk() + "个");
        ltv_cx.setText(bean.getYearRiskPlate() + "个");
        ltv_hdrs.setText(bean.getHours() + "学时");
        ltv_szrshu.setText(bean.getNoCompleteHoursCount() + "个");
        ltv_wxhxppm.setText(bean.getInsuranceYesCount() + "件");
        ltv_yxxl.setText(bean.getInsuranceNoCount() + "件");
        ltv_sgzr.setText(bean.getSecurityAdministration() + "条");
        ltv_bar.setText(bean.getLaborContract() + "个");
        ltv_badh.setText(bean.getLaborContractNo() + "个");
        ltv_ssjg.setText(bean.getMeetManageCount() + "场");
        ltv_swrs.setText(bean.getMeetManageCountYear() + "场");
        ltv_bcsw.setText(bean.getAccidentreportCount() + "个");
        ltv_szsw.setText(bean.getAccidentreportCountNo() + "场");
        ltv_wjsw.setText(bean.getEmergencydrillCount() + "次");
    }


    /**
     * 初始化控件
     */
    private void initFindView() {
        //企业名称
        ltv_name = findViewById(R.id.ltv_name);
        //经营许可证号
        ltv_type = findViewById(R.id.ltv_type);
        //总车辆数
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //当前在线车辆数
        ltv_time = findViewById(R.id.ltv_time);
        //总人员数
        ltv_address = findViewById(R.id.ltv_address);
        //当前在岗人员数
        ltv_sky = findViewById(R.id.ltv_sky);
        //线路数
        ltv_grade = findViewById(R.id.ltv_grade);
        //当天报警数汇总
        ltv_situation = findViewById(R.id.ltv_situation);
        //当天未处理报警数
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //当天特值监控平台报警
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //当月报警数汇总
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //当月未处理报警数
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //当月特值监控平台报警
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //企业当前所有报警数汇总
        ltv_cph = findViewById(R.id.ltv_cph);
        //企业当前未处理报警数
        ltv_yszh = findViewById(R.id.ltv_yszh);
        //企业当前特值监控平台报警
        ltv_cx = findViewById(R.id.ltv_cx);
        //当月基础学时
        ltv_hdrs = findViewById(R.id.ltv_hdrs);
        //企业未完成人数
        ltv_szrshu = findViewById(R.id.ltv_szrshu);
        //本月底保险到期已处理件数
        ltv_wxhxppm = findViewById(R.id.ltv_wxhxppm);
        //未处理件数
        ltv_yxxl = findViewById(R.id.ltv_yxxl);
        //本月底年审到期已处理件数
        ltv_xllb = findViewById(R.id.ltv_xllb);
        //未处理件数
        ltv_sfd = findViewById(R.id.ltv_sfd);
        //制度规范
        ltv_sgzr = findViewById(R.id.ltv_sgzr);

        //企业当月合同到期数量
        ltv_bar = findViewById(R.id.ltv_bar);
        //未续签合同数量
        ltv_badh = findViewById(R.id.ltv_badh);
        //年度安全会议
        ltv_ssjg = findViewById(R.id.ltv_ssjg);
        //年度会议
        ltv_swrs = findViewById(R.id.ltv_swrs);
        //本年度事故总数
        ltv_bcsw = findViewById(R.id.ltv_bcsw);
        //未处理分析事故总数
        ltv_szsw = findViewById(R.id.ltv_szsw);
        //年度安全演习
        ltv_wjsw = findViewById(R.id.ltv_wjsw);


    }
}
