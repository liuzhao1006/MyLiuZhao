package com.sx.trans.supervision.utils;

import android.content.Context;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * 顺达视屏工具类
 * @author Administrator
 *
 */
public class CommonRequest {
	/**
	 * 成功收到接口返回后的回调接口
	 * 
	 */
	public interface SuccessResponseListener {
		public void onResponse(String result);
	}

	/**
	 * 未成功收到接口返回后的回调接口
	 * 
	 */
	public interface ErrorResponseListener {
		public void onErrorResponse(String reason);
	}

	/**
	 * 请求接口
	 * 
	 * @param context
	 *            上下文对象
	 * @param url
	 *            接口地址
	 * @param map
	 *            参数集合
	 * @param successListener
	 *            成功回调
	 * @param errorListener
	 *            失败回调
	 */
	public static void sendRequest(Context context, String url,
			Map<String, String> map,
			final SuccessResponseListener successListener,
			final ErrorResponseListener errorListener) {
		RequestParams params = new RequestParams(url);
		if (null != map) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				params.addQueryStringParameter(entry.getKey(), entry.getValue());
			}
		}

		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String result) {
				successListener.onResponse("" + result);
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				errorListener.onErrorResponse("网络连接超时，请重试");
				LogUtil.e(ex.toString());
			}

			@Override
			public void onCancelled(CancelledException cex) {
				errorListener.onErrorResponse(cex.toString());
			}

			@Override
			public void onFinished() {
			}
		});

	}

}
