package com.sx.trans.supervision.Presenter;

import android.content.Context;
import android.util.Log;
import com.sx.trans.R;

import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.app.UrlConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.utils.JSONUtils;
import com.sx.trans.supervision.views.PublicView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by mr_wang on 2017/8/29.
 * 报警趋势分析
 */

public class WarmAlarmAnalysePresnter {

    private PublicView mView;
    private Context mContext;
    private static final String TAG = "WarmAlarmAnalysePresnter";
    private Result result;

    public WarmAlarmAnalysePresnter(PublicView mView, Context _context) {
        this.mView = mView;
        this.mContext = _context;
    }


    public void getAlarmAnalyse() {
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.ALARMANALYSE + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
                .build()//
                .execute(new MyStringCallback());
    }


    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.e(TAG, "loading");
            mView.showLoading();
        }

        @Override
        public void onAfter(int id) {
            Log.e(TAG, "Sample-okHttp");
            mView.hideLoading();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            if(e.toString().contains("SocketTimeout")){//连接超时提示
                mView.showError(mContext.getString(R.string.socket_time_out));
            }else{
                mView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void onResponse(String response, int id) {
            Log.d("asd","response:"+response);
            response="{\"alarmCnt\":101.0,\"handerStatistic\":[\n" +
                    "        {\"cnt\":195.0,\"handlerCnt\":195.0,\"name\":\"超速报警\",\"type\":1.0},\n" +
                    "        {\"cnt\":3.0,\"handlerCnt\":3.0,\"name\":\"疲劳驾驶报警\",\"type\":2.0},\n" +
                    "        {\"cnt\":5.0,\"handlerCnt\":5.0,\"type\":3.0}],\n" +
                    "        \"onLineVehCnt\":458.0,\"otherAlarmCnt\":3.0,\"otherCnt\":3.0,\"overSpCnt\":100.0,\n" +
                    "            \"tiredCnt\":2.0,\"vehCnt\":6379.0}";
            try {
                result = JSONUtils.fromJson(response, Result.class);
                if (result.getCode() == 0) {
                    mView.Success(result, 0);
                } else {
                    mView.Success(result, result.getCode());
                }
            } catch (Exception e) {
                Log.d(TAG,"异常信息:"+e.getMessage());
                mView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }
}
