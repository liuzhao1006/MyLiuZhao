package com.sx.trans.safetyLearning.progress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.safetyLearning.bean.ProgressListBean;
import com.sx.trans.safetyLearning.progress.adapter.ProgressAdapter;
import com.sx.trans.safetyLearning.progress.manager.ProgressManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者 : 刘朝,
 * on 2017/9/9,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressActivity extends BaseTransActivity implements BaseNetApi {


    private TextView lvMiddle;
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private LzTextView sum_people;
    private ProgressAdapter adapter;
    private LzTextView finish_rate;
    private ProgressManager manager;
    private List<ProgressListBean.ListBean> list = new ArrayList<>();
    private List<ProgressListBean.StuDataBean> stuList = new ArrayList<>();
    private static final int PROGRESS_COMPANY = 0x040;//企业人员学习列表
    private String companyId_e;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_progress_two;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle();
        leftGoBack(this);
        sum_people = findViewById(R.id.Sum_people);
        finish_rate = findViewById(R.id.finish_rate);
        ref_rlv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        setRefreshViewData();

    }


    /**
     * 设置头布局
     */
    private void setTitle() {
        String currentTime = Utils.getCurrentTime();
        manager = new ProgressManager(this, this);
        companyId_e = PrefUtils.getString(this, LoginConfig.COMPANY, null);
        manager.getProgress(currentTime.split("-")[0] + currentTime.split("-")[1], companyId_e, PrefUtils.getString(this, LoginConfig.USERTYPE, null));
        lvMiddle = findViewById(R.id.layout_top_modleinfo);
        lvMiddle.setText(currentTime.split("-")[0] + "-" + currentTime.split("-")[1]);
    }


    /**
     * 设置RecycleView样式,,通用
     */
    private void setRefreshViewData() {
        ref_rlv.setOnRefreshListener(mOnRefreshListener);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
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
        adapter = new ProgressAdapter(this, list);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ProgressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ProgressActivity.this, ProgressDetailsActivity.class);
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
            String currentTime = Utils.getCurrentTime();
            manager.getProgress(currentTime.split("-")[0] + currentTime.split("-")[1], companyId_e, PrefUtils.getString(ProgressActivity.this, LoginConfig.USERTYPE, null));
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
        if (what == PROGRESS_COMPANY) {
            //所有人员列表
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

    }

    @Override
    public void netFailed(int what, String message) {

    }

}
