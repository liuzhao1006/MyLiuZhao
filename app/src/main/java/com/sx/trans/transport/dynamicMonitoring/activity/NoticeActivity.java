package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.MailAdapter;
import com.sx.trans.transport.dynamicMonitoring.adapter.NoticeAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryNoticeMessage;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.transport.me.activity.CongMeLeadershipMailboxActivity;
import com.sx.trans.transport.me.activity.MeSendMailboxActivity;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 通知消息
 */
public class NoticeActivity extends BaseTransActivity implements BaseNetApi {
    private NoticeAdapter mAdapter;
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private List<QueryNoticeMessage.DataBean> mList = new ArrayList<>();
    private EmployeesManager manager;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private ImageView iv_pie;
    private LinearLayout mLinearLayout;//对应于主布局中用来添加子布局的View
    private View mGridView;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        TextView l = findViewById(R.id.layout_top_modleinfo);
        iv_pie = findViewById(R.id.iv_pie);

        l.setText("通知消息");
        mGridView = View.inflate(this, R.layout.recycle_title, null);
        mLinearLayout = (LinearLayout) findViewById(R.id.insert);
        TextView t1 = mGridView.findViewById(R.id.t1);
        t1.setText("名称");

        TextView t2 = mGridView.findViewById(R.id.t2);
        t2.setText("时间");
        mLinearLayout.addView(mGridView);
        leftGoBack(this);
        setRefreshViewData();
        httpClick();

    }

    /**
     * 网络请求
     */
    private void httpClick() {
        manager = new EmployeesManager(this, this);
        manager.getQueryNotice(pageNum, pageSize);
        manager.setqueyNotice(new EmployeesManager.QueryNoticedata() {
            @Override
            public void onqueyNotice(int what, List<QueryNoticeMessage.DataBean> rulesList) {

                LogUtils.i(rulesList.size());

                if (rulesList.size() <= 0) {

                    return;
                }
                if (ref_rlv.isRefreshing()) {
                    ref_rlv.setRefreshing(false);
                }

                if (mAdapter == null) {
                    mList.clear();
                }

                mList.addAll(rulesList);
                setCommonContract(mList);
            }
        });
    }

    private void setCommonContract(final List<QueryNoticeMessage.DataBean> mList) {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new NoticeAdapter(R.layout.item_ledger_query, mList);
            rlv.setAdapter(mAdapter);
        }
        mAdapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //跳转到详情
                Intent intent = new Intent(NoticeActivity.this, NoticeMessActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SafeRulesDetialActivity.class", mList.get(position));
                intent.putExtra("SafeRulesDetialActivity", bundle);
                startActivity(intent);

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
        iv_pie.setVisibility(View.VISIBLE);
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
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mAdapter = null;
            if (pageNum != 1) {
                pageNum = 1;
            }

            manager.getQueryNotice(pageNum, pageSize);
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


                manager.getQueryNotice(pageNum++, pageSize);
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

