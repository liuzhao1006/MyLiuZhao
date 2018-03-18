package com.sx.trans.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lz.cloud.widget.LzBar;
import com.sx.trans.R;

public abstract class BaseFragmentActivity extends FragmentActivity {

    public TextView lefttextTitle;
    public ImageView lefttextImg;
    public ImageView righttext;
    public TextView modletext;

    public TextView layout_top_rightinfo;

    protected void onCreate(Bundle arg0) {
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.title_ground_normal), 1);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal));
        super.onCreate(arg0);
        initTopView();
        initView();
        addListener();
        initDate();
        initParams();
    }

    protected abstract void initParams();

    protected abstract void initView();

    protected abstract void addListener();

    protected abstract void initDate();

    private void initTopView() {
        try {
            lefttextImg = (ImageView) findViewById(R.id.layout_top_leftimg);
            lefttextTitle = (TextView) findViewById(R.id.layout_top_leftinfo);
            modletext = (TextView) findViewById(R.id.layout_top_modleinfo);
            righttext = (ImageView) findViewById(R.id.layout_top_rightinfo);
            layout_top_rightinfo = (TextView) findViewById(R.id.layout_top_righttextinfo);

            lefttextImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTopleftImg(int leftImg) {
        lefttextImg.setBackgroundResource(leftImg);
        lefttextImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void cancelTopleftImg() {
        lefttextImg.setVisibility(View.GONE);
    }

    public void setTopleftText(String leftText) {
        lefttextTitle.setText(leftText);
    }

    public void setTopRightText(String rightText) {
        layout_top_rightinfo.setText(rightText);

    }

    public void cancelToprightImg() {
        layout_top_rightinfo.setVisibility(View.GONE);
    }

    public void setTopModleText(String modleText) {
        modletext.setText(modleText);
    }

    public void setTopRightIMG(int leftImg) {
        righttext.setVisibility(View.VISIBLE);
        righttext.setImageResource(leftImg);
    }

    public void setTopModleIMG(int leftImg) {
        modletext.setVisibility(View.VISIBLE);
        modletext.setBackgroundResource(leftImg);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void finish() {

    }


}