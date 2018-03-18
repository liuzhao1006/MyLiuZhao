package com.sx.trans.supervision.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.sx.trans.R;


/**
 * Created by mr_wang on 2017/8/25.
 * 仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度
 *
 * @author 有点凉了
 */
public class RoundProgressBar extends View {
    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;

    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;

    /**
     * 圆环的宽度
     */
    private float roundWidth;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;

    /**
     * 进度的风格，实心或者空心
     */
    private int style;

    public static final int STROKE = 0;
    public static final int FILL = 1;
    private int mProgress = 0;
    private boolean drawCanver = true;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint();


        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);

        //获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, context.getResources().getColor(R.color.home_blueprogress1));
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, context.getResources().getColor(R.color.home_blueprogress11));
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, context.getResources().getColor(R.color.gray_3));
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 25);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 15);


        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        mTypedArray.recycle();
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        int centre = getWidth() / 2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); //圆环的半径
        paint.setColor(roundColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); //画出圆环


        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
//        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体粗体
        int percent = (int) (((float) progress / (float) max) * 100);  //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        float textWidth = paint.measureText(percent + "%");   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间

        if (textIsDisplayable && percent != 0 && style == STROKE) {
            canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize / 2, paint); //画出进度百分比
        } else {
            canvas.drawText("0%", centre - textWidth / 2, centre + textSize / 2, paint);

        }


        /**
         * 画圆弧 ，画圆环的进度
         */

        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
        final RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限

        switch (style) {
            case STROKE: {
//                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(oval, -90, 360 * progress / max, false, paint);  //根据进度画圆弧
                break;
            }
            case FILL: {
                paint.setStrokeCap(Paint.Cap.ROUND);
                if (progress != 0)
                    canvas.drawArc(oval, -90, 360 * progress / max, true, paint);  //根据进度画圆弧
                break;
            }
        }
    }


    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max <= 0) {
            this.max = 100;
        } else {
            this.max = max;
        }
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
//        if (progress == 0)
//            return;
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
//            drawthread.start();
        }
    }

    //开始动画绘制
    Thread drawthread = new Thread() {
        @Override
        public void run() {
            while (drawCanver) {
                beginDraw();
            }
        }
    };

    private void beginDraw() {
        if ((progress - mProgress) > 10000) {
            mProgress = mProgress + 1000;
        } else if ((progress - mProgress) > 7500) {
            mProgress = mProgress + 750;
        } else if ((progress - mProgress) > 5000) {
            mProgress = mProgress + 500;
        } else if ((progress - mProgress) > 2500) {
            mProgress = mProgress + 250;
        } else if ((progress - mProgress) > 1000) {
            mProgress = mProgress + 100;
        } else if ((progress - mProgress) > 750) {
            mProgress = mProgress + 75;
        } else if ((progress - mProgress) > 500) {
            mProgress = mProgress + 50;
        } else if ((progress - mProgress) > 250) {
            mProgress = mProgress + 25;
        } else if ((progress - mProgress) > 150) {
            mProgress = mProgress + 15;
        } else if ((progress - mProgress) > 100) {
            mProgress = mProgress + 10;
        } else if ((progress - mProgress) > 50) {
            mProgress = mProgress + 5;
        } else {
            mProgress++;
        }
        if (mProgress > progress) {
            drawCanver = false;
            drawthread.interrupt();
        } else {
            drawCanver = true;
            postInvalidate();
            try {
                Thread.sleep(50); //通过传递过来的速度参数来决定线程休眠的时间从而达到绘制速度的快慢
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }


}