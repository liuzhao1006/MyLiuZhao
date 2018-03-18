package com.sx.trans.company.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;

import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseFragmentActivity;
import com.sx.trans.company.adapter.MainPagerAdapter;
import com.sx.trans.company.fragment.CompanyHomeFragment;
import com.sx.trans.company.fragment.CompanyMonitorFragment;
import com.sx.trans.company.fragment.CompanyMyFragment;
import com.sx.trans.company.fragment.CompanySafeFragment;
import com.sx.trans.transport.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/9/7.
 */

public class CompanyMainActivity extends BaseFragmentActivity {

    // 底部导航菜单
    private LinearLayout ll_appoint, ll_home, ll_me, ll_supervision;
    private ImageView img_appoint, img_home, img_me, img_supervision;
    private TextView tv_appoint, tv_home, tv_me, tv_supervision;
    private List<Fragment> Fragments = new ArrayList<Fragment>();
    private long fristTime;
    // 页面加载View
    private ViewPager mViewPager;
    private int currentid = 0;// 标识符
    private int off;
    private Intent intent;


    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub

        setContentView(R.layout.activity_company_main);
        LzApp.addActivity(this);
        super.onCreate(arg0);

    }

    @Override
    protected void initView() {


        // 初始化ViewPager
        mViewPager = (ViewPager) this.findViewById(R.id.viewPager_main);
        mViewPager.setOffscreenPageLimit(1);
        // 初始化菜单
        img_appoint = findViewById(R.id.img_appoint);
        img_home = findViewById(R.id.img_home);
        ll_supervision = findViewById(R.id.ll_supervision);
        img_supervision = findViewById(R.id.img_supervision);
        tv_supervision = findViewById(R.id.tv_supervision);
        img_me = findViewById(R.id.img_me);
        tv_appoint = findViewById(R.id.tv_appoint);
        tv_home = findViewById(R.id.tv_home);
        tv_me = findViewById(R.id.tv_me);
        ll_appoint = findViewById(R.id.ll_appoint);
        ll_home = findViewById(R.id.ll_home);

        ll_me = findViewById(R.id.ll_me);
        ll_appoint.setOnClickListener(clickListener);
        ll_home.setOnClickListener(clickListener);
        ll_me.setOnClickListener(clickListener);
        ll_supervision.setOnClickListener(clickListener);

        startviewpager();
    }

    // 导航菜单
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.ll_home) {
                mViewPager.setCurrentItem(0);

            }
            if (arg0.getId() == R.id.ll_supervision) {
                mViewPager.setCurrentItem(1);
            }
            if (arg0.getId() == R.id.ll_appoint) {
                mViewPager.setCurrentItem(2);

            }
            if (arg0.getId() == R.id.ll_me) {
                mViewPager.setCurrentItem(3);

            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    // 添加ViewPager监听事件
    public void startviewpager() {
        Fragment mHomeFragment = new CompanyHomeFragment();
        Fragment mCompanyMonitorFragment = new CompanyMonitorFragment();
        Fragment mAppraiseBookFragment = new CompanySafeFragment();
        Fragment mMyFragment = new CompanyMyFragment();
        Fragments.add(mHomeFragment);
        Fragments.add(mCompanyMonitorFragment);
        Fragments.add(mAppraiseBookFragment);
        Fragments.add(mMyFragment);
        // viewpager添加滑动页
        intent = getIntent();
        int i = intent.getIntExtra("jiemian", 0);
        if (i == 0) {
            mViewPager.setCurrentItem(0);
            img_home.setBackgroundResource(R.drawable.zhuyes);
        } else if (i == 1) {
            mViewPager.setCurrentItem(1);
        } else if (i == 2) {
            mViewPager.setCurrentItem(2);
        } else if (i == 3) {
            mViewPager.setCurrentItem(3);
        }
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), Fragments));
        mViewPager.setOnPageChangeListener(new myviewpageronpager());
    }


    // viewpager滑动事件，预约，评价，我的界面滑动监听事件
    class myviewpageronpager implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        // 初始化导航背景
        public void clos() {
            img_appoint.setBackgroundResource(R.drawable.securitymanagement_01);
            img_supervision.setBackgroundResource(R.drawable.car_nor);
            img_home.setBackgroundResource(R.drawable.zhuye);
            img_me.setBackgroundResource(R.drawable.wo);
            tv_appoint.setTextColor(getResources().getColor(R.color.maintvno_bg));
            tv_home.setTextColor(getResources().getColor(R.color.maintvno_bg));
            tv_me.setTextColor(getResources().getColor(R.color.maintvno_bg));
            tv_supervision.setTextColor(getResources().getColor(R.color.maintvno_bg));
        }

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            clos();
            switch (arg0) {
                case 0:
                    if (currentid == 1) {
                        animation = new TranslateAnimation(0, 0, 0, 0);
                    } else if (currentid == 3) {
                        animation = new TranslateAnimation(0, 0, -150, 0);
                    }
                    img_home.setBackgroundResource(R.drawable.zhuyes);
                    tv_home.setTextColor(getResources().getColor(R.color.title_ground_normal));

                    break;
                case 1:
                    if (currentid == 0) {
                        animation = new TranslateAnimation(0, 0, 0, 0);
                    } else if (currentid == 2) {
                        animation = new TranslateAnimation(0, 0, -150, 0);
                    }
                    img_supervision.setBackgroundResource(R.drawable.car_pre);
                    tv_supervision.setTextColor(getResources().getColor(R.color.title_ground_normal));
                    break;

                case 2:
                    if (currentid == 1) {
                        animation = new TranslateAnimation(0, 0, 0, 0);
                    } else if (currentid == 3) {
                        animation = new TranslateAnimation(0, 0, -150, 0);
                    }
                    img_appoint.setBackgroundResource(R.drawable.aqglsy);
                    tv_appoint.setTextColor(getResources().getColor(R.color.title_ground_normal));

                    break;
                case 3:
                    if (currentid == 2) {
                        animation = new TranslateAnimation(0, 0, 0, -150);
                    } else if (currentid == 0) {
                        animation = new TranslateAnimation(0, 0, 0, -150);
                    }
                    img_me.setBackgroundResource(R.drawable.sywd);
                    tv_me.setTextColor(getResources().getColor(R.color.title_ground_normal));

                    break;
            }
            currentid = arg0;
        }
    }

    @Override
    protected void initParams() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        // mMessageReceiver.unregisterReceiver();
        super.onDestroy();
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initDate() {


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            long currentTime = System.currentTimeMillis();
            if (currentTime - fristTime < 2000) {
                LzApp.exit();
                finish();
            } else {
                fristTime = currentTime;
                Toast.makeText(CompanyMainActivity.this, "再按一次返回键退出程序", Toast.LENGTH_SHORT);
            }


        }

        return false;
    }
}