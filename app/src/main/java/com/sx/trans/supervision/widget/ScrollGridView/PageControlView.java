package com.sx.trans.supervision.widget.ScrollGridView;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sx.trans.R;



/**
 * 分页的点点
 */
public class PageControlView extends LinearLayout {
    private Context context;

    private int count;

    public void bindScrollViewGroup(ScrollLayout scrollViewGroup) {
        this.count = scrollViewGroup.getChildCount();
        generatePageControl(scrollViewGroup.getCurrentScreenIndex());

        scrollViewGroup.setOnScreenChangeListener(new ScrollLayout.OnScreenChangeListener() {

            public void onScreenChange(int currentIndex) {
                // TODO Auto-generated method stub
                generatePageControl(currentIndex);
            }
        });
    }

    public PageControlView(Context context) {
        super(context);
        this.init(context);
    }

    public PageControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    private void generatePageControl(int currentIndex) {
        this.removeAllViews();

        int pageNum = 6; // 显示多少个
        int pageNo = currentIndex + 1; //第几页
        int pageSum = this.count; //总共多少页

        if (pageSum > 1) {
            int currentNum = (pageNo % pageNum == 0 ? (pageNo / pageNum) - 1
                    : (int) (pageNo / pageNum))
                    * pageNum;

            if (currentNum < 0)
                currentNum = 0;

            if (pageNo > pageNum) {

                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 12, 12, 12);
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(lp);

                imageView.setImageResource(R.drawable.home_node_n);
                this.addView(imageView);
            }


            for (int i = 0; i < pageNum; i++) {
                if ((currentNum + i + 1) > pageSum || pageSum < 2)
                    break;

                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 12, 12, 12);
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(lp);

                if (currentNum + i + 1 == pageNo) {
                    imageView.setImageResource(R.drawable.home_node_b);
                } else {
                    imageView.setImageResource(R.drawable.home_node_n);
                }
                this.addView(imageView);
            }

            if (pageSum > (currentNum + pageNum)) {
                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 12, 12, 12);
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(lp);

                imageView.setImageResource(R.drawable.home_node_n);
                this.addView(imageView);
            }
        }
    }
}  