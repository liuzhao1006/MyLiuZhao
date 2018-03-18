package com.sx.trans.transport.home.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.StopAdapter;
import com.sx.trans.transport.home.adapter.AccidentsAdapter;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentHomeAlarmActivity extends BaseTransActivity implements BaseNetApi {

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<RemberBean> mList = new ArrayList<>();
    private StopAdapter adapter;
    private int pageNum = 1;
    private int pageSize = 20;
    private EmployeesManager manager;
    private int interfaceType;
    private static final int REMINDING = 0x0004;//提醒
    private static final int ALAM = 0x1005;//报警
    private ImageView iv_error;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_home_alarm;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        leftGoBack(this);
        iv_error = findViewById(R.id.iv_error);
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        setRecycleViewData();
        TextView layout_top_modleinfo = findViewById(R.id.layout_top_modleinfo);
        Intent intent = getIntent();
        if (intent != null) {
            interfaceType = Integer.parseInt(intent.getStringExtra("interfaceType"));
            if (interfaceType == 1) {
                layout_top_modleinfo.setText("当前报警");
            } else if (interfaceType == 2) {
                layout_top_modleinfo.setText("未处理报警");
            } else {
                layout_top_modleinfo.setText("总报警");

            }
        }

        httpRequest();

    }

    private void setRecycleViewData() {
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        srv.setOnRefreshListener(mOnRefreshListener);
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 网络请求
     */
    private void httpRequest() {
        manager = new EmployeesManager(StudentHomeAlarmActivity.this, this);
        if (pageNum != 1) {
            pageNum = 1;
        }
        manager.getRember(pageNum, pageSize, interfaceType);
        manager.setOnRemberData(new EmployeesManager.RemberData() {
            @Override
            public void onRemberData(int what, List<RemberBean> stopBeanList) {
                if (stopBeanList == null || stopBeanList.size() <= 0) {
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter==null){
                mList.clear();}
                mList.addAll(stopBeanList);
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
            adapter=null;
            manager.getRember(pageNum, pageSize, interfaceType);
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
                if (mList.size() % pageSize == 0) {
                    pageNum++;//请求完数据之后,设置下一页
                    manager.getRember(pageNum, pageSize, interfaceType);
                } else {
                    showToast("无更多数据");
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
        iv_error.setVisibility(View.GONE);
    }

    @Override
    public void netFailed(int what, String message) {
        showToast(message);

        iv_error.setVisibility(View.VISIBLE);
    }


    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(final List<RemberBean> mList) {

        if (adapter == null) {
            adapter = new StopAdapter(R.layout.alarm_accident, mList);
            rlv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(StudentHomeAlarmActivity.this, StudentCarfulStopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("StudentCarfulStopActivity.class", mList.get(position));
                intent.putExtra("StudentCarfulStopActivity", bundle);
                startActivity(intent);
            }
        });
    }


}
