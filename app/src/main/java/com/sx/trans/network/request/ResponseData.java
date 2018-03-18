package com.sx.trans.network.request;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.result.LzResultJson;
import com.sx.trans.network.result.LzResultSL;
import com.sx.trans.transport.home.bean.LearnBean;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 */

public class ResponseData implements OnResponseListener<String> {

    private Context context;
    private NetJsonApi api;
    private static final int LEARNINGOVER = 0x0005;//学习完成率
    private static final int D_LEARN = 0x0010;//安全学习
    private static final int VIDEO_LIST = 0x0011;//视频列表
    private static final int COMPANYSTATISTICS = 0x020;//安全学习企业总人数接口
    private static final int COMPANYPIESTATISTICS = 0x021;//安全学习企业总人数饼状图接口
    private static final int PROGRESS_COMPANY = 0x040;//企业人员学习列表
    private static final int PROGRESS_COMPANY_DETIAL = 0x041;//企业人员完成进度详细列表
    private static final int SENDLEADERMAILBOXS = 0x21351;//发送领导信箱
    private static final int HANDLELEADERMAILBOX = 0x02892;//处理领导信箱
    private static final int ANNUALPLAN = 0x1006;//年度计划
    private static final int ANNUALPLANDETAIL = 0x10906;//年度计划详情
    private static final int SAFE_RISK_DISTRIBUTION = 0x221;

    public ResponseData(Context context, NetJsonApi api) {
        this.context = context;
        this.api = api;
    }

    @Override
    public void onStart(int what) {
        api.netStart();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        LogUtils.i(response.get());
        if (response.responseCode() == 200) {
            LogUtils.i(response.get());

            if (what == LEARNINGOVER || what == D_LEARN || what == VIDEO_LIST || what == COMPANYSTATISTICS || what == COMPANYPIESTATISTICS || what == PROGRESS_COMPANY || what == PROGRESS_COMPANY_DETIAL || what == ANNUALPLAN || what == ANNUALPLANDETAIL) {
                LearnBean result = JSON.parseObject(JSONObject.parseObject(response.get()).toJSONString(), LearnBean.class);
                if (result.returnCode.equals("0")) {
                    api.netFailed(what, result.returnMsg);
                    return;
                }
                api.netSuccessed(what, result.returnDate);
                return;
            }

            LzResultSL result = JSON.parseObject(JSONObject.parseObject(response.get()).toJSONString(), LzResultSL.class);

            if (result.code.equals("1")) {
                api.netFailed(what, result.message);
                return;
            }
            if (what != SENDLEADERMAILBOXS && what != HANDLELEADERMAILBOX) {
                if (TextUtils.isEmpty(result.data) || result.data.equals("null")) {
                    api.netFailed(what, "数据丢失");
                    return;
                }
            } else {
                api.netSuccessed(what, result.message);
                return;
            }


            api.netSuccessed(what, result.data);


        } else if (response.responseCode() == 404) {
            LogUtils.i("404了");
            api.netFailed(what, "服务器出错, 请稍后进入,或者联系管理员......");
        } else {
            //访问网络路径发生改变,比如response.responseCode() == 400
//            api.netFailed(what, "服务器给了个:" + response.responseCode() + ",我也不知道怎么了,反正就是找不到您要的资源......");
            api.netFailed(what, "错误的请求......");
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        api.netFailed(what, response.get());
    }

    @Override
    public void onFinish(int what) {
        api.netStop();
    }
}
