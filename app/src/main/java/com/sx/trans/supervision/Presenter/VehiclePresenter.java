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
 * Created by wbh on 2017/6/11 0011.
 * 我的车辆
 */

public class VehiclePresenter {

    private PublicView mJokeView;
    private Context mContext;
    private static final String TAG = "VehiclePresenter";
    private Result result;


    public VehiclePresenter(PublicView jokeView, Context _context) {
        mJokeView = jokeView;
        mContext = _context;
    }

    public void getVehiclePosition(int color,String regisNo) {
        if (regisNo==null||regisNo.length()==0)
            return;
        Map<String, String> params = new HashMap<String, String>();
        params.put("regisNo", regisNo);
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.VEHICLEPOSITION+color)//
                .params(params)
                .build()//
                .execute(new MyStringCallback());

    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.e(TAG, "loading");
            mJokeView.showLoading();
      }

        @Override
        public void onAfter(int id) {
            Log.e(TAG, "Sample-okHttp");
            mJokeView.hideLoading();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            if(e.toString().contains("SocketTimeout")){//连接超时提示
                mJokeView.showError(mContext.getString(R.string.socket_time_out));
            }else{
                mJokeView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void onResponse(String response, int id) {
            try {
                result = JSONUtils.fromJson(response, Result.class);
                Log.d("asd","response:"+response);
                if (result.getCode() == 0) {
                    mJokeView.Success(result, 0);
                } else {
                    mJokeView.Success(result, result.getCode());
                }
            } catch (Exception e) {
                Log.d(TAG,"异常信息:"+e.getMessage());
                mJokeView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

}
