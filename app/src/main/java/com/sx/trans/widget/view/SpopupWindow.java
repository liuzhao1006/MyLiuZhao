package com.sx.trans.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toolbar;

import com.sx.trans.R;

/**
 * Created by xxxxxx on 2017/11/17.
 */

public class SpopupWindow extends PopupWindow {
    private View mPopView;
    private int popupWidth;
    private int popupHeight;

    public SpopupWindow(Context context,String tx) {
        super(context);
       init(context,tx);
       setPopupWindow();
    }

    private void init(Context context,String tx) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.s_popup_window, null);
      TextView  pop_tv=mPopView.findViewById(R.id.pop_tv);
        pop_tv.setText(tx);
        pop_tv.setTextColor(Color.BLACK);
        //获取自身的长宽高
        mPopView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = mPopView.getMeasuredHeight();
        popupWidth = mPopView.getMeasuredWidth();
    }

    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.mypopwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0xecececec));

    }
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }
}