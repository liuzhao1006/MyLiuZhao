package com.sx.trans.supervision.Presenter;

import android.content.Context;
import android.util.Log;

import com.sx.trans.R;

import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.app.UrlConstants;
import com.sx.trans.supervision.constants.Request.CheckDetailInfo;
import com.sx.trans.supervision.constants.Request.SearchCmdRequest;
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
 * Created by mr_wang on 2017/9/4.
 * 查岗接口
 */

public class PostSearchPresenter {

    private PublicView mView;
    private Context mContext;
    private static final String TAG = "PostSearchPresenter";
    private Result result;

    public PostSearchPresenter(PublicView mView, Context _context) {
        this.mView = mView;
        this.mContext = _context;
    }


    /**
     * 查岗问题
     */
    public void getQuestion() {
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.GETQUESTION + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
                .build()//
                .execute(new MyStringCallback());
    }

    //下发指令
    public void sendCmd(int tradeId,String name,String question,String answer,String checker) {
        OkHttpUtils
                .postString()//
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.SENDCMD + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
                .content(new Gson().toJson(new SearchCmdRequest(tradeId+"", name,question,answer,checker)))
                .build()//
                .execute(new MyStringCallback());
    }

    //查岗记录
    public void checkStatistic() {
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.CHECKSTATISTIC + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE,""))
                .build()//
                .execute(new MyStringCallback());
    }

    //查岗记录详情
    public void checkDetail(String question,String time,int limit,int page) {
        Log.d("asd","question:"+question+",time:"+time+",limit:"+limit+",page:"+page);
        OkHttpUtils
                .postString()//
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.CHECKDETAIL + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1)+"/"+ LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""))
                .content(new Gson().toJson(new CheckDetailInfo(question,time,limit,page)))
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
            try {
                result = JSONUtils.fromJson(response, Result.class);
                if (result.getCode() == 0) {
                    mView.Success(result, 0);
                } else {
                    mView.Success(result, result.getCode());
                }
            } catch (Exception e) {
                Log.d(TAG,"异常信息:"+e.getMessage());
                mView.showError(mContext.getString(R.string.socket_time_out));
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

}
