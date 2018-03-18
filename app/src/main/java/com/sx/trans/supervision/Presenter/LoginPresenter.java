package com.sx.trans.supervision.Presenter;

import android.content.Context;
import android.util.Log;
import com.sx.trans.R;

import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.app.UrlConstants;
import com.sx.trans.supervision.constants.Request.LoginBeanRequest;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.utils.JSONUtils;
import com.sx.trans.supervision.views.PublicView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 登录账号
 */

public class LoginPresenter {

    private PublicView mJokeView;
    private Context mcontext;
    private static final String TAG = "LoginPresenter";
    private Result result;

    public LoginPresenter(PublicView jokeView, Context _context) {
        mJokeView = jokeView;
        mcontext = _context;
    }

    public void PostLogin(String loginname,String loginpwd,String address) {
        OkHttpUtils
                .postString()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.LOGIN)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(new LoginBeanRequest(loginname, loginpwd, "0",address)))
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
