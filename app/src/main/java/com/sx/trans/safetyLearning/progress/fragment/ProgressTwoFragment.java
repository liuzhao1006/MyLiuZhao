package com.sx.trans.safetyLearning.progress.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseFragment;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.safetyLearning.bean.ProgressListBean;
import com.sx.trans.safetyLearning.progress.activity.ProgressDetailsActivity;
import com.sx.trans.safetyLearning.progress.adapter.ProgressAdapter;
import com.sx.trans.safetyLearning.progress.manager.ProgressManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.DataUtils;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressTwoFragment extends BaseFragment implements DataUtils.DataLinstener {

    private ImageView back;
    private LzTextView lvMiddle;
    private LzTextView right;
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private LzTextView sum_people;
    private ProgressAdapter adapter;
    private LzTextView finish_rate;
    private ProgressManager manager;
    private List<ProgressListBean.ListBean> list = new ArrayList<>();
    private List<ProgressListBean.StuDataBean> stuList = new ArrayList<>();
    private String companyId_e;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_progress_two;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        setTitle();

        sum_people = mRootView.findViewById(R.id.Sum_people);
        finish_rate = mRootView.findViewById(R.id.finish_rate);
        ref_rlv = mRootView.findViewById(R.id.ref_rlv);
        rlv = mRootView.findViewById(R.id.rlv);
        setRefreshViewData();
    }

    /**
     * 设置头布局
     */
    private void setTitle() {
        String currentTime = Utils.getCurrentTime();
        String time = currentTime.split("-")[0] + "-" + currentTime.split("-")[1];


        manager = new ProgressManager(getActivity(), this);

        companyId_e = PrefUtils.getString(getActivity(), LoginConfig.COMPANY_COMPANY_LEARN, null);
        manager.getProgress(time, companyId_e, PrefUtils.getString(getActivity(), LoginConfig.USERTYPE, null));
        back = mRootView.findViewById(R.id.back);
        lvMiddle = mRootView.findViewById(R.id.center_tv);
        lvMiddle.setText(time);
        lvMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DataUtils(getActivity(), ProgressTwoFragment.this).show();
            }
        });
        right = mRootView.findViewById(R.id.right_iv);
    }

    @Override
    public void setDataTime(String startTime, String endTime) {

        lvMiddle.setText(startTime);
        manager.getProgress(startTime, companyId_e, PrefUtils.getString(getActivity(), LoginConfig.USERTYPE, null));

    }


    /**
     * 设置RecycleView样式,,通用
     */
    private void setRefreshViewData() {
        ref_rlv.setOnRefreshListener(mOnRefreshListener);
        rlv.setLayoutManager(new LinearLayoutManager(mActivity));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 设置显示RecycleView 合适的时机调用
     */
    private void setRecycleView() {
        String name = stuList.get(0).getStunum() + "人";
        String progress = stuList.get(0).getHoursRatio() + "%";
        finish_rate.setText(progress);
        sum_people.setText(name);
        adapter = new ProgressAdapter(getActivity(), list);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ProgressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ProgressDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AgencyDataJson.class", list.get(position));
                intent.putExtra("AgencyDataJson", bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            manager.getProgress(lvMiddle.getText().toString().trim(), companyId_e, PrefUtils.getString(getActivity(), LoginConfig.USERTYPE, null));
        }
    };
    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
            }
        }
    };


    @Override
    public void netStart() {
        ref_rlv.setRefreshing(true);
    }

    @Override
    public void netStop() {
        ref_rlv.setRefreshing(false);
    }

    @Override
    public void netSuccessed(int what, String data) {
        if (list.size() > 0) {
            list.clear();
        }
        if (stuList.size() > 0) {
            stuList.clear();
        }
        list = JSON.parseObject(data, ProgressListBean.class).getList();
        stuList = JSON.parseObject(data, ProgressListBean.class).getStuData();
        LogUtils.i(list.toString());
        setRecycleView();
    }

    @Override
    public void netFailed(int what, String message) {

    }


}
