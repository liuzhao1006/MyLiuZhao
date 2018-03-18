package com.sx.trans.network.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.apkfuns.logutils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者 : 刘朝,
 * on 2017/8/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class LocalCacheUtils {

    String PATH = Environment.getExternalStorageDirectory()
            + "/liuzhao_cache/";//缓存文件夹

    //写缓存
    public void setLocalCache(String url, Bitmap bitmap) {
        //将图片保存在本地文件

        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }

        File dir = new File(sdDir, "liuzhao_cache");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();//创建文件夹
        }
        try {
            FileOutputStream fos = null;
            File cacheFile = new File(dir, MD5Encode.encode(url) + ".jpg");//创建本地文件, 以url的md5命名
            LogUtils.i(cacheFile);
            fos = new FileOutputStream(cacheFile);
            if (null != fos) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读缓存

    public Bitmap getLocalCache(String url) {
        try {

            String encode = MD5Encode.encode(url) + ".jpg";
            File cacheFile = new File(PATH, encode);

            if (cacheFile.exists()) {
                //缓存存在
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
                        cacheFile));
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
