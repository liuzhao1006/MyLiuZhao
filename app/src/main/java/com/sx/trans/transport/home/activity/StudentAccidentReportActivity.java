package com.sx.trans.transport.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.home.bean.AccidentBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;

import java.io.Serializable;

public class StudentAccidentReportActivity extends BaseTransActivity {

    private AccidentBeans accidentBean;
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
    private LzTextView ltv_szpz;
    private LzTextView ltv_bar;
    private LzTextView ltv_badh;
    private LzTextView ltv_ssjg;
    private LzTextView ltv_swrs;
    private LzTextView ltv_bcsw;
    private LzTextView ltv_szsw;
    private LzTextView ltv_wjsw;
    private LzTextView ltv_szrs;
    private LzTextView ltv_bcsz;
    private LzTextView ltv_szsz;
    private LzTextView ltv_wjsz;
    private LzTextView ltv_ssrs;
    private LzTextView ltv_bcsshang;
    private LzTextView ltv_szss;
    private LzTextView ltv_qsrs;
    private LzTextView ltv_zsrs;
    private LzTextView ltv_wjss;
    private LzTextView ltv_jjss;
    private LzTextView ltv_bcss;
    private LzTextView ltv_szsshi;
    private LzTextView ltv_pcbl;
    private LzTextView ltv_sggk;
    private LzTextView ltv_sfcl;
    private LzTextView ltv_clrq;
    private LzTextView ltv_sfja;
    private LzTextView ltv_jarq;
    private LzTextView ltv_jafs;
    private LzTextView ltv_cyf;
    private LzTextView ltv_sgyy;
    private LzTextView ltv_dcclqkuang;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_accident_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("事故信息");
            Bundle data = intent.getBundleExtra("StudentAccidentReportActivity");
            if (data != null) {
                accidentBean = (AccidentBeans) data.getSerializable("StudentAccidentReportActivity.class");
                LogUtils.i(accidentBean);
                initData(accidentBean);
            }
        }
    }

    /**
     * 事故数据设置
     */
    private void initData(AccidentBeans accidentBean) {
        ltv_zjyy.setText(accidentBean.getAccidentname());
        ltv_zjyy.ShowAll();
        //事故分类
        /**
         * 事故分类：accidenttype
         1	一般事故	SGFL	事故分类
         2	造成重大污染的危险化学品运输事故	SGFL	事故分类
         3	涉及外籍人员死亡的行车事故	SGFL	事故分类
         4	死亡3人及以上的行车事故	SGFL	事故分类

         */
        if (!TextUtils.isEmpty(accidentBean.getAccidenttype())) {
            switch (Integer.parseInt(accidentBean.getAccidenttype())) {
                case 1:
                    ltv_type.setText("一般事故");
                    break;
                case 2:
                    ltv_type.setText("造成重大污染的危险化学品运输事故");
                    ltv_type.ShowAll();
                    break;
                case 3:
                    ltv_type.setText("涉及外籍人员死亡的行车事故");
                    ltv_type.ShowAll();
                    break;
                case 4:
                    ltv_type.setText("死亡3人及以上的行车事故");
                    ltv_type.ShowAll();
                    break;
            }
        }

        ltv_xingtai.setText(accidentBean.getAccidentform());
        ltv_xingtai.ShowAll();
        //事故形态

        /**
         * 事故形态：accidentform

         * 1	碰撞	SGXT	事故形态
         2	刮擦	SGXT	事故形态
         3	碾压	SGXT	事故形态
         4	翻车	SGXT	事故形态
         5	坠车	SGXT	事故形态
         6	失火	SGXT	事故形态
         7	撞固定物	SGXT	事故形态
         8	撞静止车辆	SGXT	事故形态
         9	其他	SGXT	事故形态

         */
        if (!TextUtils.isEmpty(accidentBean.getAccidentform())) {
            switch (Integer.parseInt(accidentBean.getAccidentform())) {
                case 1:
                    ltv_xingtai.setText("碰撞");
                    break;
                case 2:
                    ltv_xingtai.setText("刮擦");
                    break;
                case 3:
                    ltv_xingtai.setText("碾压");
                    break;
                case 4:
                    ltv_xingtai.setText("翻车");
                    break;
                case 5:
                    ltv_xingtai.setText("坠车");
                    break;
                case 6:
                    ltv_xingtai.setText("失火");
                    break;
                case 7:
                    ltv_xingtai.setText("撞固定物");
                    ltv_xingtai.ShowAll();
                    break;
                case 8:
                    ltv_xingtai.setText("撞静止车辆");
                    ltv_xingtai.ShowAll();
                    break;
                case 9:
                    ltv_xingtai.setText("其他");
                    break;
            }
        }

        ltv_time.setText(accidentBean.getAccidentdate());
        ltv_time.ShowAll();
        /**
         * 天气情况：weathercondition
         * 1	雾	TQQK	天气情况
         2	雨	TQQK	天气情况
         3	冰雪	TQQK	天气情况
         9	其他	TQQK	天气情况

         */
        if (!TextUtils.isEmpty(accidentBean.getWeathercondition())) {
            switch (Integer.parseInt(accidentBean.getWeathercondition())) {
                case 1:
                    ltv_sky.setText("雾");
                    break;
                case 2:
                    ltv_sky.setText("雨");
                    break;
                case 3:
                    ltv_sky.setText("冰雪");
                    break;
                case 9:
                    ltv_sky.setText("其他");
                    break;
                default:
                    break;
            }
        }
        ltv_xingtai.setText(accidentBean.getAccidentform());
        ltv_xingtai.ShowAll();
        ltv_address.setText(accidentBean.getAccidentaddress());
        ltv_address.ShowAll();
        ltv_sky.setText(accidentBean.getWeathercondition());
        ltv_sky.ShowAll();
        /**
         * 事发路段公路技术等级：highway_technical_grade

         * 1	高速
         2	一级
         3	二级
         4	三级
         5	四级
         6	等外
         */

        if (!TextUtils.isEmpty(accidentBean.getHighway_technical_grade())) {
            switch (Integer.parseInt(accidentBean.getHighway_technical_grade())) {
                case 1:
                    ltv_grade.setText("高速");
                    break;
                case 2:
                    ltv_grade.setText("一级");
                    break;
                case 3:
                    ltv_grade.setText("二级");
                    break;
                case 4:
                    ltv_grade.setText("三级");
                    break;
                case 5:
                    ltv_grade.setText("四级");
                    break;
                case 6:
                    ltv_grade.setText("等外");
                    break;
            }
        }

        /**
         * 事发路段线性状况：section_linear_condition
         *
         * 1	直线	SFLDXXZK	事发路段线性状况
         2	弯道	SFLDXXZK	事发路段线性状况
         3	坡道	SFLDXXZK	事发路段线性状况
         4	山区路	SFLDXXZK	事发路段线性状况
         5	临崖路	SFLDXXZK	事发路段线性状况
         6	临河路	SFLDXXZK	事发路段线性状况
         7	高架桥	SFLDXXZK	事发路段线性状况
         9	其他	SFLDXXZK	事发路段线性状况

         */

        if (!TextUtils.isEmpty(accidentBean.getSection_linear_condition())) {
            switch (Integer.parseInt(accidentBean.getSection_linear_condition())) {
                case 1:
                    ltv_situation.setText("直线");
                    break;
                case 2:
                    ltv_situation.setText("弯道");
                    break;
                case 3:
                    ltv_situation.setText("坡道");
                    break;
                case 4:
                    ltv_situation.setText("山区路");
                    break;
                case 5:
                    ltv_situation.setText("临崖路");
                    break;
                case 6:
                    ltv_situation.setText("临河路");
                    break;
                case 7:
                    ltv_situation.setText("高架桥");
                    break;
                case 9:
                    ltv_situation.setText("其他");
                    break;
            }
        }
        /**
         * 事发路段路面状况：section_pavement_condition
         1	积水	SFLDLMZK	事发路段路面状况
         2	积雪	SFLDLMZK	事发路段路面状况
         3	覆冰	SFLDLMZK	事发路段路面状况
         4	占道施工	SFLDLMZK	事发路段路面状况
         9	其他	SFLDLMZK	事发路段路面状况
         */
        if (!TextUtils.isEmpty(accidentBean.getSection_pavement_condition())) {
            switch (Integer.parseInt(accidentBean.getSection_pavement_condition())) {
                case 1:
                    ltv_lmzk.setText("积水");
                    break;
                case 2:
                    ltv_lmzk.setText("积雪");
                    break;
                case 3:
                    ltv_lmzk.setText("覆冰");
                    break;
                case 4:
                    ltv_lmzk.setText("占道施工");
                    break;
                case 9:
                    ltv_lmzk.setText("其他");
                    break;
            }
        }
        /**
         * 事故直接原因：accidentreason
         1	超载	SGZJYY	事故直接原因
         2	超速	SGZJYY	事故直接原因
         3	驾驶员操作不当	SGZJYY	事故直接原因
         4	疲劳驾驶	SGZJYY	事故直接原因
         5	机械故障	SGZJYY	事故直接原因
         6	爆胎	SGZJYY	事故直接原因
         7	公路及设施原因 	SGZJYY	事故直接原因
         9	其他	SGZJYY	事故直接原因

         */

        if (!TextUtils.isEmpty(accidentBean.getAccidentreason())) {
            switch (Integer.parseInt(accidentBean.getAccidentreason())) {
                case 1:
                    ltv_zjyy.setText("超载");
                    break;
                case 2:
                    ltv_zjyy.setText("超速");
                    break;
                case 3:
                    ltv_zjyy.setText("驾驶员操作不当");
                    ltv_zjyy.ShowAll();
                    break;
                case 4:
                    ltv_zjyy.setText("疲劳驾驶");
                    break;
                case 5:
                    ltv_zjyy.setText("机械故障");
                    break;
                case 6:
                    ltv_zjyy.setText("爆胎");
                    break;
                case 7:
                    ltv_zjyy.setText("公路及设施原因");
                    ltv_zjyy.ShowAll();
                    break;
                case 9:
                    ltv_zjyy.setText("其他");
                    break;
            }
        }
        ltv_jsy.setText(accidentBean.getUser_name());
        /**
         * 从业资格类别：employee_type
         ltv_cyzglb
         13001	公交驾驶员	CYLB	从业类别
         14001	轨道驾驶员	CYLB	从业类别

         */
        if (!TextUtils.isEmpty(accidentBean.getEmployee_type())) {
            switch (Integer.parseInt(accidentBean.getEmployee_type())) {
                case 13001:
                    ltv_cyzglb.setText("公交驾驶员");
                    ltv_cyzglb.ShowAll();
                    break;
                case 14001:
                    ltv_cyzglb.setText("公交驾驶员");
                    ltv_cyzglb.ShowAll();
                    break;

            }
        }

        ltv_cyzgzh.setText(accidentBean.getCertificate_number());
        ltv_cyzgzh.ShowAll();
        ltv_cph.setText(accidentBean.getVehicle_no());
        ltv_cph.ShowAll();
        ltv_yszh.setText(accidentBean.getRoad_certificate_no());
        ltv_yszh.ShowAll();
        /**
         * ltv_cx
         */
        ltv_cx.setText(accidentBean.getVehicle_type());
        ltv_cx.ShowAll();
        ltv_hdrs.setText("核定人(" + accidentBean.getCheck_person() + ")数");
        ltv_hdrs.ShowAll();
        ltv_szrshu.setText("核载人(" + accidentBean.getAll_quality() + ")数");
        ltv_szrshu.ShowAll();
        ltv_wxhxppm.setText(accidentBean.getHazard_name());
        ltv_wxhxppm.ShowAll();
        ltv_yxxl.setText(accidentBean.getLine_name());
        ltv_yxxl.ShowAll();
        /**
         * 线路类别：line_mold

         1	城乡公共	XLLB	线路类别
         2	普客	XLLB	线路类别
         3	高速直达	XLLB	线路类别
         4	普快	XLLB	线路类别
         5	直达	XLLB	线路类别


         */

        if (!TextUtils.isEmpty(accidentBean.getLine_mold())) {
            switch (Integer.parseInt(accidentBean.getLine_mold())) {
                case 1:
                    ltv_xllb.setText("城乡公共");
                    break;
                case 2:
                    ltv_xllb.setText("普客");
                    break;
                case 3:
                    ltv_xllb.setText("高速直达");
                    break;
                case 4:
                    ltv_xllb.setText("普快");
                case 5:
                    ltv_xllb.setText("直达");
                    break;
            }
        }else {
            ltv_xllb.setText("-");
        }
        ltv_sfd.setText(accidentBean.getSite_name());
        ltv_sfd.ShowAll();
        /**
         * 事故责任：accidentliability
         * 1	全责	SGZR	事故责任
         2	主责	SGZR	事故责任
         3	同责	SGZR	事故责任
         4	次责	SGZR	事故责任
         5	无责	SGZR	事故责任
         ltv_sgzr
         */
        if (!TextUtils.isEmpty(accidentBean.getAccidentliability())) {
            switch (Integer.parseInt(accidentBean.getAccidentliability())) {
                case 1:
                    ltv_sgzr.setText("全责");
                    break;
                case 2:
                    ltv_sgzr.setText("主责");
                    break;
                case 3:
                    ltv_sgzr.setText("同责");
                    break;
                case 4:
                    ltv_sgzr.setText("次责");
                    break;
                case 5:
                    ltv_sgzr.setText("无责");
                    break;
            }
        }
        ltv_szpz.setText(accidentBean.getThirdcarno());
        ltv_szpz.ShowAll();
        ltv_bar.setText(accidentBean.getInformant());
        ltv_bar.ShowAll();
        ltv_badh.setText(accidentBean.getInformanttel());
        ltv_badh.ShowAll();
        ltv_ssjg.setText(accidentBean.getCompany_name());
        ltv_ssjg.ShowAll();
        ltv_swrs.setText(accidentBean.getDeath_num()+"个");
        ltv_bcsw.setText(accidentBean.getThe_death()+"个");
        ltv_szsw.setText(accidentBean.getThird_death()+"个");
        ltv_wjsw.setText(accidentBean.getForeign_death()+"个");
        ltv_szrs.setText(accidentBean.getMiss_num()+"个");
        ltv_bcsz.setText(accidentBean.getThe_miss()+"个");
        ltv_szsz.setText(accidentBean.getThird_miss()+"个");
        ltv_wjsz.setText(accidentBean.getForeign_miss()+"个");
        ltv_ssrs.setText(accidentBean.getInjured_num()+"个");
        ltv_bcsshang.setText(accidentBean.getThe_injured()+"个");
        ltv_szss.setText(accidentBean.getThird_injured()+"个");
        ltv_qsrs.setText(accidentBean.getMild_injured()+"个");
        ltv_zsrs.setText(accidentBean.getSerious_injured()+"个");
        ltv_wjss.setText(accidentBean.getForeign_injured()+"个");
        ltv_jjss.setText(accidentBean.getFinancial_loss()+"元");
        ltv_bcss.setText(accidentBean.getThe_miss()+"元");
        ltv_szsshi.setText(accidentBean.getThird_loss()+"元");
        ltv_pcbl.setText(accidentBean.getRata());
        ltv_pcbl.ShowAll();
        if (accidentBean.isIshandle()) {
            ltv_sfcl.setText("是");
        } else {
            ltv_sfcl.setText("否");

        }
        ltv_clrq.setText(accidentBean.getHandledate());
        ltv_clrq.ShowAll();
        if (accidentBean.isIsclosed()) {
            ltv_sfja.setText("是");
        } else {
            ltv_sfja.setText("否");

        }
        ltv_jarq.setText(accidentBean.getCloseddate());
        ltv_jarq.ShowAll();
        ltv_jafs.setText(accidentBean.getClosedtype());
        ltv_jafs.ShowAll();
        ltv_cyf.setText(accidentBean.getParticipate());
        ltv_cyf.ShowAll();
        ltv_sgyy.setText(Html.fromHtml(""+accidentBean.getAccidentreson()));
        ltv_sgyy.ShowAll();
        ltv_dcclqkuang.setText(Html.fromHtml(""+accidentBean.getInvestigationandhandling()));
        ltv_dcclqkuang.ShowAll();
    }

    /**
     * 初始化控件
     */
    private void initFindView() {
        //事故名称
        ltv_name = findViewById(R.id.ltv_name);
        //事故分类
        ltv_type = findViewById(R.id.ltv_type);
        //事故形态
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //事故时间
        ltv_time = findViewById(R.id.ltv_time);
        //事故地点
        ltv_address = findViewById(R.id.ltv_address);
        //天气情况
        ltv_sky = findViewById(R.id.ltv_sky);
        //事发路段公路技术等级
        ltv_grade = findViewById(R.id.ltv_grade);
        //事发路段线性状况
        ltv_situation = findViewById(R.id.ltv_situation);
        //事发路段路面状况
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //事故直接原因
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //驾驶员
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //从业资格类别
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //从业资格证号
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //车牌号
        ltv_cph = findViewById(R.id.ltv_cph);
        //运输证号
        ltv_yszh = findViewById(R.id.ltv_yszh);
        //车型
        ltv_cx = findViewById(R.id.ltv_cx);
        //核定人（吨）数
        ltv_hdrs = findViewById(R.id.ltv_hdrs);
        //实载人（吨）数
        ltv_szrshu = findViewById(R.id.ltv_szrshu);
        //危险化学品品名
        ltv_wxhxppm = findViewById(R.id.ltv_wxhxppm);
        //运行线路
        ltv_yxxl = findViewById(R.id.ltv_yxxl);
        //线路类别
        ltv_xllb = findViewById(R.id.ltv_xllb);
        //始发站（地）
        ltv_sfd = findViewById(R.id.ltv_sfd);
        //事故责任
        ltv_sgzr = findViewById(R.id.ltv_sgzr);
        //三者车牌
        ltv_szpz = findViewById(R.id.ltv_szpz);
        //报案人
        ltv_bar = findViewById(R.id.ltv_bar);
        //报案电话
        ltv_badh = findViewById(R.id.ltv_badh);
        //所属机构
        ltv_ssjg = findViewById(R.id.ltv_ssjg);
        //伤亡损失情况
        //死亡人数
        ltv_swrs = findViewById(R.id.ltv_swrs);
        //本车死亡
        ltv_bcsw = findViewById(R.id.ltv_bcsw);
        //三者死亡
        ltv_szsw = findViewById(R.id.ltv_szsw);
        //外籍死亡
        ltv_wjsw = findViewById(R.id.ltv_wjsw);
        //失踪人数
        ltv_szrs = findViewById(R.id.ltv_szrs);
        //本车失踪
        ltv_bcsz = findViewById(R.id.ltv_bcsz);
        //三者失踪
        ltv_szsz = findViewById(R.id.ltv_szsz);
        //外籍失踪
        ltv_wjsz = findViewById(R.id.ltv_wjsz);
        //受伤人数
        ltv_ssrs = findViewById(R.id.ltv_ssrs);
        //本车受伤
        ltv_bcsshang = findViewById(R.id.ltv_bcsshang);
        //三者伤数
        ltv_szss = findViewById(R.id.ltv_szss);
        //轻伤人数
        ltv_qsrs = findViewById(R.id.ltv_qsrs);
        //重伤人数
        ltv_zsrs = findViewById(R.id.ltv_zsrs);
        //外籍受伤
        ltv_wjss = findViewById(R.id.ltv_wjss);
        //经济损失
        ltv_jjss = findViewById(R.id.ltv_jjss);
        //本车损失
        ltv_bcss = findViewById(R.id.ltv_bcss);
        //三者损失
        ltv_szsshi = findViewById(R.id.ltv_szsshi);
        //赔偿比例
        ltv_pcbl = findViewById(R.id.ltv_pcbl);
        //事故概况
        //事故概况详情
        ltv_sggk = findViewById(R.id.ltv_sggk);
        //调查处理信息
        //是否处理
        ltv_sfcl = findViewById(R.id.ltv_sfcl);
        //处理日期
        ltv_clrq = findViewById(R.id.ltv_clrq);
        //是否结案
        ltv_sfja = findViewById(R.id.ltv_sfja);
        //结案日期
        ltv_jarq = findViewById(R.id.ltv_jarq);
        //结案方式
        ltv_jafs = findViewById(R.id.ltv_jafs);
        //参与方
        ltv_cyf = findViewById(R.id.ltv_cyf);
        //事故原因
        ltv_sgyy = findViewById(R.id.ltv_sgyy);
        //调查处理情况
        ltv_dcclqkuang = findViewById(R.id.ltv_dcclqkuang);

    }

}
