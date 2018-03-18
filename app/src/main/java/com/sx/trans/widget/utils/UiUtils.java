package com.sx.trans.widget.utils;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class UiUtils {


    public static int dip2px(Context ctx, int dip) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);//四舍五入
        //3.1-->3
        //3.6-->3
        //3.1->3.6  3
        //3.6+0.5->4.1  4
    }

    public static int px2dip(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);//四舍五入
    }

    public static void setWidthanHigth(TextView tv, int px) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
        params.height = px;
        params.width = px;
        tv.setLayoutParams(params);
    }

    public static void setWidth(Context mContext,TextView tv, int px) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int higth = manager.getDefaultDisplay().getHeight();
        int width = manager.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
        params.width = width / px-50;
        tv.setLayoutParams(params);
    }
    public static void setHight(Context mContext, View tv, int px) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int higth = manager.getDefaultDisplay().getHeight();
        int width = manager.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
        params.height = higth /px;
        tv.setLayoutParams(params);
    }
    public static void setHight2(Context mContext, View tv, int px) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int higth = manager.getDefaultDisplay().getHeight();
        int width = manager.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
        params.height = higth /px-80;
        tv.setLayoutParams(params);
    }


}
