package com.sx.trans.transport.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sx.lzlibrary.widget.NoScrollViewPager;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseFragment;
import com.sx.trans.base.BaseTransActivity;

import static com.sx.trans.base.FragmentFactory.getFragment;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 */

public class MainActivity extends BaseTransActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private NoScrollViewPager mViewPager;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb5;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.rg_group);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        rb2 = (RadioButton) findViewById(R.id.rb_2);
        rb5 = (RadioButton) findViewById(R.id.rb_5);
        LzApp.addActivity(this);
        MyMainAdapter adapter = new MyMainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        radioGroup.check(R.id.rb_1);
        radioGroup.setOnCheckedChangeListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        rb1.setChecked(true);
                        radioGroup.check(R.id.rb_1);
                        break;
                    case 1:
                        rb2.setChecked(true);
                        radioGroup.check(R.id.rb_2);
                        break;
                    case 2:
                        rb5.setChecked(true);
                        radioGroup.check(R.id.rb_5);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_1:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_2:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.rb_5:
                mViewPager.setCurrentItem(3, false);
                break;
            default:
                break;
        }

    }

    class MyMainAdapter extends FragmentPagerAdapter {
        private String[] mTabNames;

        public MyMainAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = new String[]{"首页", "学习", "我的"};
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = getFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

    }


}
