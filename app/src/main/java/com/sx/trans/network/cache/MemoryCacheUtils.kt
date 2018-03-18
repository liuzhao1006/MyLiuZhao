package com.lz.cloud.cache

import android.graphics.Bitmap
import android.support.v4.util.LruCache

/**
 * 作者 : 刘朝,
 * on 2017/8/31,
 * GitHub : https://github.com/liuzhao1006
 */

class MemoryCacheUtils {
    private val mLruCache: LruCache<String, Bitmap>

    init {
        val maxMemory = Runtime.getRuntime().maxMemory()//获取虚拟机分配的最大内存,默认16MB
        println("maxMemory:" + maxMemory)
        //maxSize:内存缓存上限
        mLruCache = object : LruCache<String, Bitmap>((maxMemory / 8).toInt()) {
            //返回单个对象占用内存的大小
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                //计算图片占用内存大小
                return value!!.rowBytes * value.height
            }
        }
    }

    //写缓存
    fun setMemoryCache(url: String, bitmap: Bitmap) {
        mLruCache.put(url + ".jpg", bitmap)
    }

    //读缓存
    fun getMemroyCache(url: String): Bitmap {
        return mLruCache.get(url + ".jpg")
    }

}
