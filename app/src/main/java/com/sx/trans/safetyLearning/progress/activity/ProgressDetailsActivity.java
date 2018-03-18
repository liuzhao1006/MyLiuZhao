package com.sx.trans.safetyLearning.progress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.safetyLearning.bean.ProgressListBean;
import com.sx.trans.safetyLearning.progress.adapter.ProgressDatialAdapter;
import com.sx.trans.safetyLearning.progress.bean.ProgressDetialBean;
import com.sx.trans.safetyLearning.progress.manager.ProgressManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.DataUtils;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressDetailsActivity extends BaseTransActivity implements BaseNetApi, DataUtils.DataLinstener {
    private ImageView back;
    private TextView lvMiddle;
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private List<ProgressListBean.ListBean> list = new ArrayList<>();
    private List<ProgressListBean.StuDataBean> stuList = new ArrayList<>();

    private LzTextView people_name, people_card, people_phone;//  姓名 车牌 电话
    private LzTextView baisi_Minute_finish, baisi_Minute;// 已完成学时   基础学时，
    private static final int PROGRESS_COMPANY_DETIAL = 0x041;//企业人员完成进度详细列表
    private ProgressManager manager;
    private LinearLayout ll_detial;
    private ProgressListBean.ListBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_progress_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle();
        ref_rlv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        people_name = findViewById(R.id.people_name);
        people_card = findViewById(R.id.people_card);
        people_phone = findViewById(R.id.people_phone);
        baisi_Minute_finish = findViewById(R.id.baisi_Minute_finish);
        baisi_Minute = findViewById(R.id.baisi_Minute);
        setRefreshViewData();
    }

    /**
     * 设置头布局
     */
    private void setTitle() {
        String currentTime = Utils.getCurrentTime();
        leftGoBack(this);
        lvMiddle = findViewById(R.id.layout_top_modleinfo);
        lvMiddle.setText(currentTime.split("-")[0] + currentTime.split("-")[1]);
        lvMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DataUtils(ProgressDetailsActivity.this, ProgressDetailsActivity.this).show();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("AgencyDataJson");
            bean = (ProgressListBean.ListBean) bundle.getSerializable("AgencyDataJson.class");
            if (bean != null) {
                manager = new ProgressManager(this, this);
                String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
                manager.getProgressDetialInfo(bean.getStudentId(), year + month);
            }

        }

        ll_detial = findViewById(R.id.ll_detial);

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


    @Override
    public void setDataTime(String startTime, String endTime) {
        lvMiddle.setText(startTime);
    }

    /**
     * 设置显示RecycleView 合适的时机调用
     */
    private void setRecycleView() {
//        String name = stuList.get(0).getStunum() + "人";
//        String progress = stuList.get(0).getHoursRatio() + "%";
//        rlv.setAdapter(adapter);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

//            if (bean != null) {
//                String currentTime = Utils.getCurrentTime();
//                manager.getProgressDetialInfo(EmptyId_E, currentTime.split("-")[0] + currentTime.split("-")[1]);
//            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (ref_rlv.isRefreshing()) {
                        ref_rlv.setRefreshing(false);
                    }
                }
            }, 3000);

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
        ll_detial.setVisibility(View.INVISIBLE);
    }

    @Override
    public void netStop() {
        ref_rlv.setRefreshing(false);
        ll_detial.setVisibility(View.VISIBLE);
    }


    @Override
    public void netSuccessed(int what, String data) {
        LogUtils.d(data);
        ProgressDetialBean progressDetialBean = JSON.parseObject(data, ProgressDetialBean.class);
        List<ProgressDetialBean.StuDataBean> stuData = progressDetialBean.getStuData();
        if (stuData != null && stuData.size() > 0) {
            people_name.setText(stuData.get(0).getStuName());
            people_card.setText(stuData.get(0).getCertiDepartment());
            people_phone.setText(stuData.get(0).getPhone());
            baisi_Minute.setText(stuData.get(0).getTotalBasicsHours() + "分钟");
            if (!TextUtils.isEmpty(stuData.get(0).getTotalValidHours())) {
                double total = Double.parseDouble(stuData.get(0).getTotalValidHours()) / 100;
                baisi_Minute_finish.setText(total + "分钟");
            }
        }
        List<ProgressDetialBean.VideoListBean> videoList = progressDetialBean.getVideoList();
        if (videoList != null && videoList.size() > 0) {
            ProgressDatialAdapter adapter = new ProgressDatialAdapter(this, videoList);
            rlv.setAdapter(adapter);
        }

    }

    @Override
    public void netFailed(int what, String message) {
        ll_detial.setVisibility(View.VISIBLE);
    }
}
