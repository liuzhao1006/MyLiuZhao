package com.sx.trans.widget.font;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.sx.trans.widget.view.SpopupWindow;


/**
 * 作者 : 刘朝,
 * on 2017/8/29,
 * GitHub : https://github.com/liuzhao1006
 */

public class LzTextView extends AppCompatTextView {
    private Context context;
    private boolean flag = true;//切换标记

    public LzTextView(Context context) {
        this(context, null);
    }

    private boolean IsShowAll = false;

    public LzTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LzTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/custom_regular.ttf");
        this.setTypeface(typeFace);
    }

    private void setTextViewPoint(Context context, int padding) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
    }

    private Handler handler = new Handler();
    private SpopupWindow mPop;
    private String d;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (content != null) {
                            content.onClickShowContent(LzTextView.this);
                        }
                    }
                }, 3000);

                if (isOverFlowed()) {
                    if (IsShowAll) {

                        String d = (String) this.getText();

                        mPop = new SpopupWindow(this.getContext(), d);
//                        mPop.showAtLocation(this.getRootView(), Gravity.TOP | Gravity.CENTER, 0, 0);
//                        mPop.showAsDropDown(this);
                        mPop.showUp(this);
                    }
                }
                System.out.println("我按下了...");

                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("我移动了...");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("我起来了...");
                if (isOverFlowed()) {
                    if (IsShowAll) {
                        mPop.dismiss();
                    }
                }
                break;
        }

        if (IsShowAll) {

            return true;
        } else {
            return super.onTouchEvent(event);
        }


    }

    public void ShowAll() {


        IsShowAll = true;

    }

    private int getAvailableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    public boolean isOverFlowed() {
        Paint paint = getPaint();
        float width = paint.measureText(getText().toString());
        if (width > getAvailableWidth()) return true;
        return false;
    }

    public interface OnClickShowContent {
        void onClickShowContent(View view);
    }

    private OnClickShowContent content;

    public void setOnClickShowContent(OnClickShowContent content) {
        this.content = content;
    }
}
