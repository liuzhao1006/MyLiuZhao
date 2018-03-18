package com.sx.trans.company.adapter;

import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Administrator on 2017/9/7.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> Fragments;


    public MainPagerAdapter(FragmentManager fm,List<Fragment>  Fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.Fragments =  Fragments;
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        return Fragments.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Fragments.size();
    }


    // 初始化每个页卡选项
    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        // TODO Auto-generated method stub
        return super.instantiateItem(arg0, arg1);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }






}
