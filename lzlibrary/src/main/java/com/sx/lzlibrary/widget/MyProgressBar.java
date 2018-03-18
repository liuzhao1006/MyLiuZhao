package com.sx.lzlibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class MyProgressBar extends ProgressBar {

    private String text_progress;
    private Paint mPaint;//画笔

    public MyProgressBar(Context context) {
        super(context);
        initPaint();
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        setTextProgress(progress);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
//        Rect rect = new Rect();
//        this.mPaint.getTextBounds(this.text_progress, 0, this.text_progress.length(), rect);
//        int x = (getWidth() / 2) - rect.centerX();
//        int y = (getHeight() / 2) - rect.centerY();


        float progressFloat = Progress / 100.0f;

        //当前文字移动的长度
        float currentMovedLentgh = totalMovedLength * progressFloat;

        //画左侧已经完成的进度条，长度为从Veiw左端到文字的左侧


//        canvas.drawText(this.text_progress, x - 20, y, this.mPaint);

        canvas.drawText(Progress + "%", currentMovedLentgh, textBottomY, mPaint);
    }

    /**
     * description: 初始化画笔
     * Create by lll on 2013-8-13 下午1:41:49
     */
    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(Color.RED);
        mPaint.setTextSize(25f);
    }

    private int Progress;

    private void setTextProgress(int progress) {
        Progress = progress;
        int i = (int) ((progress * 1.0f / this.getMax()) * 100);
        this.text_progress = String.valueOf(i) + "%";
    }

    float textWidth;
    Rect rect = new Rect();
    private float textBottomY;
    private int viewCenterY, viewWidth;
    private int totalMovedLength;

    private void getWidthAndHeight() {

        //得到包围文字的矩形的宽高
        mPaint.getTextBounds("000%", 0, "000%".length(), rect);
        textWidth = rect.width();
        textBottomY = viewCenterY + rect.height() / 2;

        //得到自定义视图的高度
        int viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        viewCenterY = viewHeight / 2;
        totalMovedLength = (int) (viewWidth - textWidth);

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getWidthAndHeight();
    }
}
