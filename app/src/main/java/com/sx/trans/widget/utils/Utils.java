package com.sx.trans.widget.utils;

import android.content.Context;
import android.util.Log;

import com.sx.trans.app.LzApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.util.Properties;

import Decoder.BASE64Encoder;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class Utils {
    public static Context getContext() {
        return LzApp.getContext();
    }

    /**
     * 返回当前时间的格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }

    public static String getCurrentTimes(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(l);
    }

    private static BASE64Encoder base64en = new BASE64Encoder();


    public static String getTimes(long l) {
        //毫秒值转换成时
        long h = l / 100000;
        long min = l / 10000;
        long s = l / 1000;
        if (h > 0) {
            if (min > 0) {
                if (s > 0) {
                    return h + "时" + min + "分" + s + "秒";
                } else {
                    return h + "时" + min + "分00秒";
                }
            } else {
                if (s > 0) {

                    return h + "时00分" + s + "秒";
                } else {
                    return h + "时00分00秒";
                }
            }
        } else {
            if (min > 0) {
                if (s > 0) {
                    return "00时" + min + "分" + s + "秒";
                } else {
                    return "00时" + min + "分00秒";
                }
            } else {
                if (s > 0) {

                    return "00时00分" + s + "秒";
                } else {
                    return "00时00分00秒";
                }
            }
        }
    }

    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) {
        // 确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        // 加密后的字符串
        String newstr;
        try {
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return newstr;
    }


    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }

        return "";
    }

    /**
     * 获取网络配置
     *
     * @param keyName
     * @param context
     * @return
     */
    public static String getProperties(Context context, String keyName) {
        Properties props = new Properties();
        try {
            props.load(context.getAssets().open("config.properties"));
//            props.load(context.openFileInput("config.properties"));
            return props.getProperty(keyName);
        } catch (FileNotFoundException e) {
            Log.e("BaseManager::", "config.properties Not Found Exception", e);
            return "配置文件不存在";
        } catch (IOException e) {
            Log.e("BaseManager::", "config.properties IO Exception", e);
            return "读取失败";
        }

    }

}
