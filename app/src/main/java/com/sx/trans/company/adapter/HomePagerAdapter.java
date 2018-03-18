package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 作者 : 刘朝,
 * on 2017/9/9,
 * GitHub : https://github.com/liuzhao1006
 */

public class HomePagerAdapter extends PagerAdapter {
    private Context context;
    private int[] mImageIds;

    public HomePagerAdapter(Context context, int[] mImageIds) {
        this.context = context;
        this.mImageIds = mImageIds;
    }

    //返回item的个数
    @Override
    public int getCount() {
        //return mImageIds.length;
        return Integer.MAX_VALUE;//无限循环
    }

    //确定当前要显示的View对象是不是来源于instantiateItem返回的Object
    //如果不是,fasle, 不予显示; 是的话才显示
    //确保 PagerAdapter 正常运行
    //固定写法
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化item布局,类似getView方法
    //ViewPager避免内存溢出原理:
    //永远默认只加载最多三个页面,当前页,上一页,下一页, 其他页面全部销毁, 避免内存溢出
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //0->0, 1->1, 4->4,  5->0, 6->1, 7->2...10->0
        position = position % mImageIds.length;//position可能很大,需要取余,避免角标越界

        //1. 初始化布局对象
        ImageView view = new ImageView(context);
        view.setImageResource(mImageIds[position]);//为了保证宽高比例不失真, 有可能会有留白, src
        view.setScaleType(ImageView.ScaleType.FIT_XY);//设置缩放类型, FIT_XY表示填充父窗体
        //view.setBackgroundResource(mImageIds[position]);//设置背景, 会填充ImageView

        //2. 将当前布局对象添加给ViewPager的容器
        container.addView(view);

        System.out.println("初始化布局了:" + position);

        return view;//返回布局对象
    }

    //销毁布局对象
    //固定写法
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        //从ViewPager容器中移除要销毁的对象
        container.removeView((View) object);
        System.out.println("销毁布局了:" + position);
    }


}
