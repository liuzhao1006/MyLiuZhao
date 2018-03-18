package com.sx.trans.base;

import android.support.v4.util.SparseArrayCompat;

import com.sx.trans.safetyLearning.accounted.AccountFragment;
import com.sx.trans.safetyLearning.accounted.AccountTwoFragment;
import com.sx.trans.safetyLearning.data.DataFragment;
import com.sx.trans.safetyLearning.data.DataTwoFragment;
import com.sx.trans.safetyLearning.progress.fragment.ProgressFragment;
import com.sx.trans.safetyLearning.progress.fragment.ProgressTwoFragment;
import com.sx.trans.transport.dynamicMonitoring.DMFragment;
import com.sx.trans.transport.home.HFragment;
import com.sx.trans.transport.me.MeFragment;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 */

public class FragmentFactory {

    static SparseArrayCompat<BaseFragment> map = new SparseArrayCompat<BaseFragment>();//
    static SparseArrayCompat<BaseFragment> mapSL = new SparseArrayCompat<BaseFragment>();//

    public static BaseFragment getFragment(int position) {

        BaseFragment fragment = map.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HFragment();
                    break;
                case 1:
                    fragment = new DMFragment();
                    break;
                case 2:
                    fragment = new MeFragment();
                    break;
                default:
                    break;
            }
            map.put(position, fragment);
        }

        return fragment;
    }

    /**
     * @param position
     * @param userType 164表示企业端, 166表示监管端
     * @return
     */
    public static BaseFragment getSLFragment(int position, String userType) {
        BaseFragment fragment = mapSL.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    if ("164".equals(userType) || "166".equals(userType)) {
                        fragment = new ProgressFragment();
                    } else {
                        fragment = new ProgressTwoFragment();
                    }
                    break;
                case 1:
                    if ("164".equals(userType) || "166".equals(userType)) {
                        fragment = new AccountFragment();
                    } else {
                        fragment = new AccountTwoFragment();
                    }
                    break;
                case 2:
                    if ("164".equals(userType) || "166".equals(userType)) {
                        fragment = new DataFragment();
                    } else {
                        fragment = new DataTwoFragment();
                    }
                    break;
                default:
                    break;
            }
            mapSL.put(position, fragment);
        }
        return fragment;
    }

    // 移除所有
    public static void removeAllFragment() {
        if (map != null && map.size() > 0) {
            map.clear();
        }
        if (mapSL != null && mapSL.size() > 0) {
            mapSL.clear();
        }
    }

    // 拿到列表
    public static SparseArrayCompat<BaseFragment> getTransFragment() {
        if (map != null && map.size() > 0) {
            return map;
        }

        return null;
    }

    // 拿到列表
    public static SparseArrayCompat<BaseFragment> getSafeLearnFragment() {
        if (mapSL != null && mapSL.size() > 0) {
            return mapSL;
        }

        return null;
    }
}
