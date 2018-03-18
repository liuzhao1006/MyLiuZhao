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
 * 区域分析
 */

public class RegionAnalysisPresenter {

    private PublicView mJokeView;
    private Context mcontext;
    private static final String TAG = "RegionAnalysisPresenter";
    private Result result;


    public RegionAnalysisPresenter(PublicView jokeView, Context _context) {
        mJokeView = jokeView;
        mcontext = _context;
    }

    //获得地图展示区域
    public void getMapData() {
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.GETMAPDATA+ LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
                .build()//
                .execute(new MyStringCallback());
    }

    //获得详细区域数据
    public void getMapMoreData(String areaCode) {
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.GETMAPMOREDATE+ LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+areaCode)
                .build()//
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            mJokeView.showLoading();
            Log.e(TAG, "loading");

        }

        @Override
        public void onAfter(int id) {
            mJokeView.hideLoading();
            Log.e(TAG, "Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            if(e.toString().contains("SocketTimeout")){//连接超时提示
                mJokeView.showError(mcontext.getString(R.string.socket_time_out));
            }else{
                mJokeView.showError(mcontext.getString(R.string.no_network));
            }
        }


            @Override
        public void onResponse(String response, int id) {
            try {
                result = JSONUtils.fromJson(response, Result.class);
                if (result.getCode() == 0) {
                    mJokeView.Success(result, 0);
                } else {
                    mJokeView.Success(result, result.getCode());
                }
            } catch (Exception e) {
                Log.d(TAG,"修改密码异常:"+e.getMessage());
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

}
