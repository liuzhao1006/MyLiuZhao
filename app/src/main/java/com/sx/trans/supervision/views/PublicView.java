package com.sx.trans.supervision.views;

import com.sx.trans.supervision.constants.Result;

/**
 * Created by mr_wang on 2017/6/11 0011.
 * 公共view
 */

public interface PublicView {
    //加载
    void showLoading();

    //加载完毕
    void hideLoading();

    //数据获取成功
    void Success(Result result, int code);

    //数获取失败
    void showError(String error);

}
