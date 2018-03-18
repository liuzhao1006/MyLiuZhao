package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.AnnualPlanAdapter;
import com.sx.trans.transport.dynamicMonitoring.adapter.YearPlanDetailAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.LawBean;
import com.sx.trans.transport.home.bean.AnnualPlanBean;
import com.sx.trans.transport.home.bean.YearPlanBean;
import com.sx.trans.transport.home.bean.YearPlanDetailBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxxxxx on 2017/11/27.
 */

public class YearPlanDetailActivity extends BaseTransActivity implements BaseNetApi {
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;

    private List<YearPlanDetailBean> mList = new ArrayList<>();
    private static final int ACCIDENT = 0x0001;//事故
    private YearPlanDetailAdapter adapter;
    private EmployeesManager manager;
    private int pageNum = 1;//分页
    private int pageSize = 10;//分页中每页显示多少数据
    private  String strYear= Utils.getCurrentTime();
    private  YearPlanDetailBean bean;
    private TextView l;
    private String professionId;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_accident;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {



        leftGoBack(this);
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        setRecycleViewData();
        Intent intent = getIntent();


         l = findViewById(R.id.layout_top_modleinfo);
        l.setText("年度计划详情");
        if (intent != null) {
            professionId=intent.getStringExtra("IndustryId");


                httpRequest();



        }
    }


    private void setRecycleViewData() {
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        srv.setOnRefreshListener(mOnRefreshListener);
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 网络请求
     */
    private void httpRequest() {
        manager = new EmployeesManager(YearPlanDetailActivity.this, this);
        if (pageNum != 1) {
            pageNum = 1;
        }
        manager.getYearPlanDetail(strYear, professionId);
        manager.setOnYearPlanData(new EmployeesManager.YearPlanData() {
            @Override
            public void onYearPlanData(int what, List<YearPlanDetailBean> annualPlanList) {


                //获取到数据
                if (annualPlanList == null || annualPlanList.size() <= 0) {
                    showToast("查无此信息");
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                mList.clear();
                for (int i = 0; i < annualPlanList.size(); i++) {
                    mList.add(annualPlanList.get(i));
                }

                l.setText( mList.get(0).getStudyYear()+"年计划详情");
                setCommonContract(mList);
            }
        });
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (pageNum != 1) {
                pageNum = 1;
            }
            if (mList.size() > 0) {

            }
            manager.getYearPlanDetail(strYear,professionId);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (srv.isRefreshing()) {
                        srv.setRefreshing(false);
                        showToast("请求失败");
                    }
                }
            }, 10000);
        }
    };
    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                if (mList.size() >= 20) {
                    manager.getAnnualPlan(strYear);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //10秒之后,请求没有返回来,停止刷新
                        if (srv.isRefreshing()) {
                            srv.setRefreshing(false);
                        }
                    }
                }, 10000);
            }
        }
    };


    @Override
    public void netStart() {
        srv.setRefreshing(true);
    }

    @Override
    public void netStop() {
        if (srv.isRefreshing()) {
            srv.setRefreshing(false);
        }
    }

    @Override
    public void netSuccessed(int what, String data) {
//        showToast("netSuccessed:::" + data);
    }

    @Override
    public void netFailed(int what, String message) {
        showToast(message);
    }


    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(final List<YearPlanDetailBean> mList) {

        if (adapter == null) {
            adapter = new YearPlanDetailAdapter(R.layout.item_year_plandetail, mList);
            rlv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
