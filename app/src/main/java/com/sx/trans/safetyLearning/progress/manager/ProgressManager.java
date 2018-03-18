package com.sx.trans.safetyLearning.progress.manager;

import android.content.Context;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.app.Config;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.request.ResponseData;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;

    private ResponseData data;
    private static final int WHAT = 0x001;
    private StringBuffer sb;
    private static final int PROGRESS_COMPANY = 0x040;//企业人员学习列表
    private static final int PROGRESS_COMPANY_DETIAL = 0x041;//企业人员完成进度详细列表

    public ProgressManager(Context context, BaseNetApi api) {
        this.api = api;
        this.context = context;

        data = new ResponseData(context, this);
        sb = new StringBuffer();
    }


    /**
     * /点击总人数,调用下面URL返回每个人的信息
     * //http://47.92.29.57:14806/GetDataService.asmx/
     * // GetStuLearHoursRatio?Company=1&strYearMonth=201709
     *
     * @param date
     * @param companyId
     * @param userType
     * @return
     */
    public String getProgress(String date, String companyId, String userType) {

        sb.delete(0, sb.length());
        sb.append(getProperties(context, "IP3"))
                .append(Config.EnterpriseUrl)
                .append("Company=")
                .append(companyId)
                .append("&strYearMonth=")
                .append(date);
        LogUtils.i(sb.toString());
        if (TextUtils.isEmpty(companyId)) {
            return "可以请求数据";
        }

        Request<String> request = NoHttp.createStringRequest(sb.toString());
        request(PROGRESS_COMPANY, request, data);
        return "请求数据已发送";
    }

    /**
     * 详细人员学时完成进度
     * <p>
     * http://47.92.29.57:14806/GetDataService.asmx/GetStuVideoLearList?
     * stuId=7&
     * strYearMonth=201709
     */
    public String getProgressDetialInfo(String stuId, String strYearMonth) {
        sb.delete(0, sb.length());
        sb.append(getProperties(context, "IP3"))
                .append(Config.PeopleUrl)
                .append("stuId=")
                .append(stuId)
                .append("&strYearMonth=")
                .append(strYearMonth);
        LogUtils.i(sb.toString());
        if (TextUtils.isEmpty(stuId)) {
            return "可以请求数据";
        }
        Request<String> request = NoHttp.createStringRequest(sb.toString());
        request(PROGRESS_COMPANY_DETIAL, request, data);
        return "请求数据已发送";

    }

    public String getProgressBoss(String date, String companyId, String userType) {
        //http://47.92.29.57:14806/GetDataService.asmx/GetCompanyProgress?CompanyId=0&strYearMonth=2017-09
        sb.delete(0, sb.length());
        sb.append(getProperties(context, "IP"))
                .append(Config.JianGuanList1)
                .append("CompanyId=")
                .append(companyId)
                .append("&strYearMonth=")
                .append(date);
        LogUtils.i(sb.toString());
        if (TextUtils.isEmpty(companyId)) {
            return "可以请求数据";
        }
        Request<String> request = NoHttp.createStringRequest(sb.toString());
        request(WHAT, request, data);
        return "请求数据已发送";
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

        LogUtils.i(data);
        if (what == PROGRESS_COMPANY) {
            api.netSuccessed(what, data);
        } else if (what == PROGRESS_COMPANY_DETIAL) {
            api.netSuccessed(what, data);
        }
    }

    @Override
    public void netFailed(int what, String message) {

    }


}
