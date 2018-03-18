package com.sx.trans.supervision.Presenter;

import android.content.Context;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
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
 * 业户主页列表Presenter
 */

public class OwnerListPresenter {

    private PublicView mView;
    private Context mContext;
    private static final String TAG = "OwnerListPresenter";
    private Result result;


    public OwnerListPresenter(PublicView mView, Context _context) {
        this.mView = mView;
        this.mContext = _context;
    }

    public void getOwnerList(String unitName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("unitName",unitName);
        LogUtils.i(UrlConstants.GET_TRADE_LIST + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
;
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.GET_TRADE_LIST + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
                .params(params)
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
