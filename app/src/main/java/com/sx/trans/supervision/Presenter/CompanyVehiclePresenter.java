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

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 公司-车辆分布
 */

public class CompanyVehiclePresenter {

    private PublicView publicView;
    private Context mContext;
    private static final String TAG = "CompanyVehiclePresenter";
    private Result result;


    public CompanyVehiclePresenter(PublicView publicView, Context _context) {
        this.publicView = publicView;
        this.mContext = _context;
    }

    public void getCompanyVehiclePosition(int unitid,String regisNo ) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("regisNo", regisNo);
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.COMPANY_VEHICLE_POSITION + unitid)//
                .params(params)
                .build()//
                .execute(new MyStringCallback());

    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.e(TAG, "loading");
            publicView.showLoading();
      }

        @Override
        public void onAfter(int id) {
            Log.e(TAG, "Sample-okHttp");
            publicView.hideLoading();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            if(e.toString().contains("SocketTimeout")){//连接超时提示
                publicView.showError(mContext.getString(R.string.socket_time_out));
            }else if(e.toString().contains("404")){
                publicView.showError("链接错误");
            }else{
                publicView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void onResponse(String response, int id) {
            try {
                result = JSONUtils.fromJson(response, Result.class);
                if (result.getCode() == 0) {
                    publicView.Success(result, 0);
                } else {
                    publicView.Success(result, result.getCode());
                }
            } catch (Exception e) {
                Log.d(TAG,"异常信息:"+e.getMessage());
                publicView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

}
