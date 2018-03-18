package com.sx.trans.network.request;

import android.content.Context;
import android.graphics.Bitmap;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.cache.MemoryCacheUtils;
import com.sx.trans.network.api.NetBitmapApi;
import com.sx.trans.network.cache.LocalCacheUtils;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class ResponseBitmap implements OnResponseListener<Bitmap> {
    private Context context;
    private NetBitmapApi api;

    private LocalCacheUtils cacheUtils;
    private MemoryCacheUtils memoryCacheUtils;
    private String imageUrl;

    public ResponseBitmap(Context context, String url, NetBitmapApi api) {
        this.context = context;
        this.api = api;
        String[] split = url.split("/");
        imageUrl = split[split.length - 1];

        cacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    @Override
    public void onStart(int what) {
        api.netStart();
    }

    @Override
    public void onSucceed(int what, Response<Bitmap> response) {
        if (response.responseCode() == 200) {
            api.netSuccessed(what, response.get());
            cacheUtils.setLocalCache(imageUrl, response.get());//写缓存
            memoryCacheUtils.setMemoryCache(imageUrl, response.get());//写缓存
        } else if (response.responseCode() == 404) {
            LogUtils.i("404了");
            api.netFailed(what, "服务器出错, 请稍后进入,或者联系管理员......");

        } else if (response.responseCode() == 304) {
            //访问网络路径发生改变,比如response.responseCode() == 400
            Bitmap bitmap = memoryCacheUtils.getMemroyCache(imageUrl);
            if (bitmap != null) {
                api.netSuccessed(what, response.get());
                LogUtils.i("我是内存缓存");
                return;
            }
            bitmap = cacheUtils.getLocalCache(imageUrl);
            if (bitmap != null) {
                api.netSuccessed(what, response.get());
                LogUtils.i("我是本地缓存");
                return;
            }
            api.netFailed(what, "无缓存数据");

        } else {
            api.netFailed(what, "我也不知道怎么了,反正就是找不到您要的资源......");
        }
    }

    @Override
    public void onFailed(int what, Response<Bitmap> response) {
        api.netFailed(what, "网络错误");
    }

    @Override
    public void onFinish(int what) {
        api.netStop();
    }
}
