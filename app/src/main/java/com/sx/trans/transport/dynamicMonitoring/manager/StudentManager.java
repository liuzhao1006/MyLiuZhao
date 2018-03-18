package com.sx.trans.transport.dynamicMonitoring.manager;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.bean.CommpanyEmployeeInfoBean;
import com.sx.trans.company.bean.CommpanyEmployeeListBean;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.company.bean.CommpanyRiskBean;
import com.sx.trans.company.bean.CommpanySafeMaintainBean;
import com.sx.trans.company.bean.CommpanyVehicItemBean;
import com.sx.trans.company.bean.CommpanyVehicleBean;
import com.sx.trans.company.bean.CompanyLegerBean;
import com.sx.trans.company.bean.SafeAccidentBean;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.company.bean.SafeInputBean;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.company.bean.SafeRulesBean;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.network.request.ResponseData;
import com.sx.trans.transport.dynamicMonitoring.bean.LawBean;
import com.sx.trans.transport.dynamicMonitoring.bean.LocalLawBean;
import com.sx.trans.transport.dynamicMonitoring.bean.ReadPdfBean;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 * 从业端接口管理类
 */

public class StudentManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;
    private ResponseData responseData;

    private static final int STUDENT_LAW = 0x1111;//从业端法律法规数据
    private static final int STUDENT_COMPANY_RULES = 0x1112;//从业端规章制度数据


    private int groupId;//企业编号

    public StudentManager(Context context, BaseNetApi api) {
        this.context = context;
        this.api = api;
        responseData = new ResponseData(context, this);
        groupId = Integer.parseInt(PrefUtils.getString(context, LoginConfig.EMPLOYEE_GROUPID, null));

    }

    /**
     * 企业端 从业端法律法规数据请求
     * <p>
     */
    public void getLaw() {
        Request<String> request = doPost(context, "mployeesLaw");
        request.add("groupId", groupId);
        request(STUDENT_LAW, request, responseData);
    }

    /**
     * 企业端 从业端地方法规政策请求
     * <p>
     */
    public void getLawRules(int PageNum, int PageSize) {
        Request<String> request = doPost(context, "studentLocalLaw");
        request.add("groupId", groupId)
                .add("PageNum", PageNum)
                .add("PageSize", PageSize);
        request(STUDENT_COMPANY_RULES, request, responseData);
    }


    @Override
    public void netStart() {
        api.netStart();
    }

    @Override
    public void netStop() {
        api.netStop();
    }

    @Override
    public void netSuccessed(int what, String data) {
        api.netSuccessed(what, data);//可以进行进一步拆分
        switch (what) {
            case STUDENT_LAW:
                lawdata.onLawData(what, JSON.parseArray(data, LocalLawBean.class));
                break;
            case STUDENT_COMPANY_RULES:
                rulesdata.onRulesData(what, JSON.parseArray(data, LawBean.class));
                break;
        }
    }

    @Override
    public void netFailed(int what, String message) {
        api.netFailed(what, message);
    }

    /*===========================================接口配置===================================================*/

    /*===从业端地方法规列表接口数据回调===*/
    public interface LawData {
        void onLawData(int what, List<LocalLawBean> lawList);
    }

    private LawData lawdata;

    public void setOnLawData(LawData lawdata) {
        this.lawdata = lawdata;
    }
    /*===从业端地方法规列表接口数据回调===*/

    /*===从业端地方法规列表接口数据回调===*/
    public interface RulesData {
        void onRulesData(int what, List<LawBean> rulesList);
    }

    private RulesData rulesdata;

    public void setOnRulesData(RulesData rulesdata) {
        this.rulesdata = rulesdata;
    }
    /*===从业端地方法规列表接口数据回调===*/

}
