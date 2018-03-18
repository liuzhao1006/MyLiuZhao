package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.AnnualPlanAdapter;
import com.sx.trans.transport.home.activity.StudentAccidentReportActivity;
import com.sx.trans.transport.home.bean.AnnualPlanBean;
import com.sx.trans.transport.home.bean.YearPlanBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentAnnualPlanActivity extends BaseTransActivity implements BaseNetApi {

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private ImageView iv_pie;
    private List<YearPlanBean> mList = new ArrayList<>();
    private TimePickerView pvCustomLunar;
    private AnnualPlanAdapter adapter;
    private EmployeesManager manager;
    private int pageNum = 1;//分页
    private int pageSize = 10;//分页中每页显示多少数据
    private String strYear = Utils.getCurrentTime().split("-")[0];


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_year_plan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        httpRequest();
        leftGoBack(this);
        Intent intent = getIntent();
        strYear = intent.getStringExtra("year");
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        iv_pie = findViewById(R.id.iv_pie);
        setRecycleViewData();
        final TextView layout_top_modleinfo = findViewById(R.id.layout_top_modleinfo);

        layout_top_modleinfo.setText(strYear + "年度计划");


    }

    //可根据需要自行截取数据显示
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
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
        manager = new EmployeesManager(StudentAnnualPlanActivity.this, this);
        if (pageNum != 1) {
            pageNum = 1;
        }
        manager.getAnnualPlan(strYear);
        manager.setOnAnnualPlanData(new EmployeesManager.AnnualPlanData() {
            @Override
            public void onAnnualPlanData(int what, List<YearPlanBean> annualPlanList) {
                //获取到数据
                if (annualPlanList == null || annualPlanList.size() <= 0) {
//                    showToast("查无此信息");
                    iv_pie.setVisibility(View.VISIBLE);
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                mList.clear();
                for (int i = 0; i < annualPlanList.size(); i++) {
                    mList.add(annualPlanList.get(i));
                }
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
            manager.getAnnualPlan(strYear);
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

        iv_pie.setVisibility(View.VISIBLE);
    }


    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(final List<YearPlanBean> mList) {

        if (adapter == null) {
            adapter = new AnnualPlanAdapter(R.layout.item_year_plan, mList);
            rlv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(StudentAnnualPlanActivity.this, YearPlanDetailActivity.class);


                intent.putExtra("IndustryId", mList.get(position).getIndustryId());
                startActivity(intent);
            }
        });
    }

}
