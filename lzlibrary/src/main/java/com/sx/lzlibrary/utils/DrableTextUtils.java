package com.sx.lzlibrary.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class DrableTextUtils {
    public static void setTextDrable(Context context, TextView textView, Drawable drawable , String top) {//  textView  设置图标  top  表示设置方位
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (top.equals("top")) {//  textView  上面
            textView.setCompoundDrawables(null, drawable, null, null);
        }
        if (top.equals("bottom")) {//textView  下面
            textView.setCompoundDrawables(null, drawable, null, drawable);
        }
        if (top.equals("left")) {
            textView.setCompoundDrawables(drawable, null, null, null);
        }
        if (top.equals("rigth")) {
            textView.setCompoundDrawables(null, null, drawable, null);
        }

    }
}