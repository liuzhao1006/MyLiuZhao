package com.sx.trans.safetyLearning.accounted.manager;

import android.content.Context;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.request.ResponseData;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;


/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccountManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;
    private ResponseData data;
    private static final int WHAT = 0x001;
    private static final int COMPANYSTATISTICS = 0x020;//安全学习企业总人数接口
    private static final int COMPANYPIESTATISTICS = 0x021;//安全学习企业总人数饼状图接口
    private StringBuffer sb;

    public AccountManager(Context context, BaseNetApi api) {
        this.api = api;
        this.context = context;

        data = new ResponseData(context, this);
        sb = new StringBuffer();

    }

    /**
     * 统计请求
     *
     * @param date
     * @param companyId
     * @return http://47.92.29.57:14806/GetDataService.asmx/GetCompanyProgress?CompanyId=1&strYearMonth=201709
     */
    public String getAccount(String date, String companyId) {
        sb.delete(0, sb.length());
        sb.append(getProperties(context, "companyStatistics"))
                .append("CompanyId=")
                .append(companyId)
                .append("&strYearMonth=")
                .append(date);
        LogUtils.i(sb.toString());
        if (TextUtils.isEmpty(companyId)) {
            return "可以请求数据";
        }
        Request<String> request = NoHttp.createStringRequest(sb.toString());
        request(COMPANYSTATISTICS, request, data);
        return "请求数据已发送";
    }

    /**
     * 饼状图请求
     * <p>
     * http://47.92.29.57:14806/GetDataService.asmx/GetStuLearHoursRatio?Company=1&strYearMonth=201709
     */
    public String getPieAccount(String date, String companyId) {
        sb.delete(0, sb.length());
        sb.append(getProperties(context, "companyPieStatsics"))
                .append("Company=")
                .append(companyId)
                .append("&strYearMonth=")
                .append(date);
        LogUtils.i(sb.toString());
        if (TextUtils.isEmpty(companyId)) {
            return "可以请求数据";
        }
        Request<String> request = NoHttp.createStringRequest(sb.toString());
        request(COMPANYPIESTATISTICS, request, data);
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
        api.netSuccessed(what, data);
    }

    @Override
    public void netFailed(int what, String message) {

    }


}
