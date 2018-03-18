package com.sx.trans.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.trans.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 */

public abstract class BaseTransActivity extends BaseActivity {

    @Override
    protected void beforeInit() {
        super.beforeInit();
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal),0);
    }


    public void leftGoBack(final Activity activity) {
        LinearLayout layout_top_with_buttom_tmp = findViewById(R.id.layout_top_with_buttom_tmp);
        layout_top_with_buttom_tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
