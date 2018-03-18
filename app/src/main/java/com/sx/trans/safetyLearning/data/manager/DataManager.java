package com.sx.trans.safetyLearning.data.manager;

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
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class DataManager extends BaseManager implements NetJsonApi {
    private Context context;
    private BaseNetApi api;

    private ResponseData data;
    private static final int WHAT = 0x001;
    private StringBuffer sb;

    public DataManager(Context context, BaseNetApi api) {
        this.api = api;
        this.context = context;
        data = new ResponseData(context, this);
        sb = new StringBuffer();
    }

    /**
     * http://47.92.29.57:14806/GetDataService.asmx/GetCompanyProgress?CompanyId=5156&strYearMonth=2017-09
     * @param date
     * @param companyId
     * @param userType
     * @return
     */
    public String getData(String date, String companyId, String userType) {
        sb.delete(0, sb.length());
        sb.append(getProperties(context, "IP"))
                .append(Config.DataUrl)
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
        api.netSuccessed(what, data);
    }

    @Override
    public void netFailed(int what, String message) {

    }
}
