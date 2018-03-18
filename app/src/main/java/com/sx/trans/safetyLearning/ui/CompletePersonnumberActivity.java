package com.sx.trans.safetyLearning.ui;

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

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CompeleteNumAdapter;
import com.sx.trans.company.adapter.CompeleteRateAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xxxxxx on 2018/2/2.
 */

public class CompletePersonnumberActivity extends BaseTransActivity implements BaseNetApi, OnChartValueSelectedListener, View.OnClickListener {
    @InjectView(R.id.layout_top_leftimg)
    ImageView layoutTopLeftimg;
    @InjectView(R.id.layout_top_leftinfo)
    TextView layoutTopLeftinfo;
    @InjectView(R.id.layout_top_with_buttom_tmp)
    LinearLayout layoutTopWithButtomTmp;
    @InjectView(R.id.layout_top_modleinfo)
    TextView layoutTopModleinfo;
    @InjectView(R.id.layout_top_rightinfo)
    ImageView layoutTopRightinfo;
    @InjectView(R.id.layout_top_righttextinfo)
    TextView layoutTopRighttextinfo;
    @InjectView(R.id.rlv)
    SwipeMenuRecyclerView rlv;
    @InjectView(R.id.ref_rlv)
    SwipeRefreshLayout srv;
    @InjectView(R.id.ll_recycle_view)
    LinearLayout llRecycleView;
    @InjectView(R.id.iv_pie)
    ImageView ivPie;
    private ArrayList mList = new ArrayList();
    private CompeleteNumAdapter adapter;
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void netStart() {

            srv.setRefreshing(false);

    }

    @Override
    public void netStop() {
        if (srv.isRefreshing()) {
            srv.setRefreshing(false);
        }
    }

    @Override
    public void netSuccessed(int what, String data) {

    }

    @Override
    public void netFailed(int what, String message) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_learn_complete;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        leftGoBack(this);
        layoutTopModleinfo.setText("完成人数");
        setRecycleViewData();
        setCommonContract(mList);
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
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

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
//
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

    private void setCommonContract(List mList) {
        mList.add("1");
        mList.add("1");
        adapter = new CompeleteNumAdapter(R.layout.item_compelete_num, mList);
        rlv.setAdapter(adapter);

    }

}
