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

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.RulesAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.LawBean;
import com.sx.trans.transport.dynamicMonitoring.manager.StudentManager;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 地方法律法规
 */
public class CaseAnalysisActivity extends BaseTransActivity implements BaseNetApi {
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private List<LawBean> mList = new ArrayList<>();
    private StudentManager manager;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("案例分析");
        leftGoBack(this);
        setRefreshViewData();
        httpClick();

    }

    /**
     * 网络请求
     */
    private void httpClick() {
        manager = new StudentManager(this, this);
        manager.getLawRules(pageNum, pageSize);
        manager.setOnRulesData(new StudentManager.RulesData() {
            @Override
            public void onRulesData(int what, List<LawBean> rulesList) {
                if (rulesList.size() <= 0) {
                    return;
                }
                if (ref_rlv.isRefreshing()) {
                    ref_rlv.setRefreshing(false);
                }
                mList.addAll(rulesList);
                RulesAdapter mAdapter = new RulesAdapter(R.layout.item_ledger_query, mList);
                rlv.setAdapter(mAdapter);
                mAdapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
//                        Intent intent = new Intent(CaseAnalysisActivity.this, StudentEnterPriseDetialActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("SafeRulesDetialActivity.class", mList.get(position));
//                        intent.putExtra("SafeRulesDetialActivity", bundle);
//                        startActivity(intent);

                    }
                });
            }
        });

    }

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

    }

    @Override
    public void netFailed(int what, String message) {
        showToast(message);
    }

    /**
     * 设置RecycleView样式,,通用
     */
    private void setRefreshViewData() {
        ref_rlv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        ref_rlv.setOnRefreshListener(mOnRefreshListener);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (mList.size() > 0) {
                mList.clear();
            }
            if (pageNum != 1) {
                pageNum = 1;
            }

            manager.getLawRules(pageNum, pageSize);
            refreshData();
        }
    };
    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了

                if (mList.size() % pageSize != 0) {
                    showToast("数据加载完毕");
                    return;
                }

                manager.getLawRules(pageNum++, pageSize);
                refreshData();
            }
        }
    };

    /**
     * 防止刷新图表不隐藏
     */
    private void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ref_rlv.isRefreshing()) {
                    ref_rlv.setRefreshing(false);
                }
            }
        }, 10000);
    }



}
