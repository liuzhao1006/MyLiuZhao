package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.base.ItemApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.dynamicMonitoring.adapter.VideoListAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.LearnStudyBean;
import com.sx.trans.transport.dynamicMonitoring.bean.VideoListBean;
import com.sx.trans.transport.dynamicMonitoring.inter.DLearnApi;
import com.sx.trans.transport.dynamicMonitoring.manager.DManager;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends BaseTransActivity implements BaseNetApi, DLearnApi, ItemApi {
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private VideoListAdapter adapter;
    private List<VideoListBean> mList = new ArrayList<>();
    private static final int VIDEO_LIST = 0x0011;//视频列表
    private DManager manager;
    private LearnStudyBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("视频列表");

        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("LearnStudyBeanList");
            bean = (LearnStudyBean) data.getSerializable("LearnStudyBean");
            if (bean != null) {
                String companyId = bean.getCompanyId();
                String studyMonth = bean.getStudyMonth();
                //StudyMonth=201709&CompanyId=4146&ProfessionId=2042&userId=19
                String cardId = PrefUtils.getString(this, LoginConfig.EMPLOYEE_CARDID, null);
                String industryId = bean.getIndustryId();

                manager = new DManager(VIDEO_LIST, this, this, this);
                manager.getVideoListData(studyMonth, companyId, industryId, "19");
            }

        }

        leftGoBack(this);
        setRefreshViewData();
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
            if (bean != null) {
                String companyId = bean.getCompanyId();
                String studyMonth = bean.getStudyMonth();
                //StudyMonth=201709&CompanyId=4146&ProfessionId=2042&userId=19
                String cardId = PrefUtils.getString(VideoListActivity.this, LoginConfig.EMPLOYEE_CARDID, null);
                String industryId = bean.getIndustryId();
                manager.getVideoListData(studyMonth, companyId, industryId, cardId);
            }
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
    public void getListData(List<LearnStudyBean> learnStudyBeans) {

    }

    @Override
    public void getVideoData(List<VideoListBean> videoListBeans) {
        if (videoListBeans == null || videoListBeans.size() <= 0) {
            showToast("查无此信息");
            return;
        }
        if (mList.size() > 0) {
            mList.clear();
        }
        if (ref_rlv.isRefreshing()) {
            ref_rlv.setRefreshing(false);
        }
        mList = videoListBeans;
        setCommonContract(mList);
    }

    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(List<VideoListBean> mList) {
        adapter = new VideoListAdapter(this, mList, this);
        rlv.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, VideoShowActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("VideoShowActivity.class", mList.get(position));
        intent.putExtra("VideoShowActivity", bundle);
        startActivity(intent);
    }
}
