package com.sx.trans.supervision.Presenter;

import android.content.Context;

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
 * 搜索业户公司列表Presenter
 */

public class SearchCompanyListPresenter {

    private PublicView mView;
    private Context mContext;
    private static final String TAG = "SearchCompanyListPresenter";
    private Result result;


    public SearchCompanyListPresenter(PublicView mView, Context _context) {
        this.mView = mView;
        this.mContext = _context;
    }

    public void getCompanyList(String unitName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("unitName",unitName);
        params.put("areaCode", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE,""));
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH,""))
                .url(UrlConstants.GET_SEARCH_COMPANY_LIST + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1))
                .params(params)
                .build()//
                .execute(new MyStringCallback());


    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            mView.showLoading();
        }

        @Override
        public void onAfter(int id) {
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
                mView.showError(mContext.getString(R.string.no_network));
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }

}
