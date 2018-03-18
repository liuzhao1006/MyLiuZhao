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
import com.sx.trans.safetyLearning.bean.ProgressListBean.ListBean;
import com.sx.trans.safetyLearning.progress.activity.ProgressDetailsActivity;
import com.sx.trans.safetyLearning.progress.adapter.ProgressAdapter;
import com.sx.trans.safetyLearning.progress.bean.ProgressBossBean;
import com.sx.trans.safetyLearning.progress.manager.ProgressManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.DataUtils;
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

public class ProgressFragment extends BaseFragment implements DataUtils.DataLinstener {

    private SwipeRefreshLayout refreshView;
    private SwipeMenuRecyclerView rlv;
    private ImageView back;
    private LzTextView tvMiddle;
    private LzTextView ivRight;
    private List<ListBean> list = new ArrayList<>();
    private ProgressAdapter adapter;
    private ProgressManager manager;
    private String companyId_e;
    private String month;
    private String year;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_progress;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        companyId_e = PrefUtils.getString(getActivity(), LoginConfig.COMPANY, null);

        year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        back = mRootView.findViewById(R.id.back);
        tvMiddle = mRootView.findViewById(R.id.center_tv);
        ivRight = mRootView.findViewById(R.id.right_iv);
        refreshView = mRootView.findViewById(R.id.ref_rlv);
        rlv = mRootView.findViewById(R.id.rlv);
        manager = new ProgressManager(getActivity(), this);
        manager.getProgressBoss(year + "-" + month, PrefUtils.getString(getActivity(), LoginConfig.COMPANY, null), PrefUtils.getString(getActivity(), LoginConfig.USERTYPE, null));
        setRefreshViewData();

    }

    private void setRefreshViewData() {

        refreshView.setOnRefreshListener(mOnRefreshListener);
        rlv.setLayoutManager(new LinearLayoutManager(mActivity));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    private void setRecycleView() {
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
            manager.getProgress(year + "-" + month, companyId_e, PrefUtils.getString(getActivity(), LoginConfig.USERTYPE, null));
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
        refreshView.setRefreshing(true);
    }

    @Override
    public void netStop() {
        refreshView.setRefreshing(false);
    }

    @Override
    public void netSuccessed(int what, String data) {
        String s = "[{\"CompanyName\":\"广西亿程\",\"CompanyId\":\"4146\",\"stuNum\":\"2\"," +
                "\"BasicsHours\":\"0\",\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\"," +
                "\"FinishRatio\":\"0.00\",\"ranking\":\"1\"},{\"CompanyName\":\"北海亿程\"," +
                "\"CompanyId\":\"4147\",\"stuNum\":\"2\",\"BasicsHours\":\"0\",\"Finish\":\"0\"," +
                "\"NoFinish\":\"0\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\",\"ranking\":\"2\"}," +
                "{\"CompanyName\":\"北海信达\",\"CompanyId\":\"5146\",\"stuNum\":\"4\",\"BasicsHours\":\"0\"," +
                "\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\"," +
                "\"ranking\":\"3\"},{\"CompanyName\":\"tes单位\",\"CompanyId\":\"5147\",\"stuNum\":\"4\"," +
                "\"BasicsHours\":\"0\",\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\"," +
                "\"FinishRatio\":\"0.00\",\"ranking\":\"4\"},{\"CompanyName\":\"测试单位\",\"CompanyId\":\"5148\"," +
                "\"stuNum\":\"9\",\"BasicsHours\":\"0\",\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\"," +
                "\"FinishRatio\":\"0.00\",\"ranking\":\"5\"},{\"CompanyName\":\"芳芳测试单位\"," +
                "\"CompanyId\":\"5149\",\"stuNum\":\"1\",\"BasicsHours\":\"0\",\"Finish\":\"0\"," +
                "\"NoFinish\":\"0\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\",\"ranking\":\"6\"}," +
                "{\"CompanyName\":\"楠森测试单位\",\"CompanyId\":\"5151\",\"stuNum\":\"9\"," +
                "\"BasicsHours\":\"0\",\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\"," +
                "\"FinishRatio\":\"0.00\",\"ranking\":\"7\"},{\"CompanyName\":\"陕西测试单位\"," +
                "\"CompanyId\":\"5152\",\"stuNum\":\"4\",\"BasicsHours\":\"0\",\"Finish\":\"0\"," +
                "\"NoFinish\":\"0\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\",\"ranking\":\"8\"}," +
                "{\"CompanyName\":\"lff0818test单位\",\"CompanyId\":\"5153\",\"stuNum\":\"1\"," +
                "\"BasicsHours\":\"0\",\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\"," +
                "\"FinishRatio\":\"0.00\",\"ranking\":\"9\"},{\"CompanyName\":\"陕测试单位中心\"," +
                "\"CompanyId\":\"5154\",\"stuNum\":\"3\",\"BasicsHours\":\"0\",\"Finish\":\"0\"," +
                "\"NoFinish\":\"0\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\",\"ranking\":\"10\"}," +
                "{\"CompanyName\":\"test协会\",\"CompanyId\":\"5155\",\"stuNum\":\"\"," +
                "\"BasicsHours\":\"0\",\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\"," +
                "\"FinishRatio\":\"0.00\",\"ranking\":\"11\"},{\"CompanyName\":\"test0906单位\"," +
                "\"CompanyId\":\"5156\",\"stuNum\":\"9\",\"BasicsHours\":\"0\",\"Finish\":\"0\"," +
                "\"NoFinish\":\"9\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\",\"ranking\":\"12\"}," +
                "{\"CompanyName\":\"新单位\",\"CompanyId\":\"5159\",\"stuNum\":\"3\",\"BasicsHours\":\"0\"," +
                "\"Finish\":\"0\",\"NoFinish\":\"0\",\"hoursRatio\":\"0\",\"FinishRatio\":\"0.00\"," +
                "\"ranking\":\"13\"}]}";
        ProgressBossBean.ReturnDateBean progressBossBean = JSON.parseObject(s, ProgressBossBean.ReturnDateBean.class);

        LogUtils.i(progressBossBean.toString());
        setRecycleView();
    }

    @Override
    public void netFailed(int what, String message) {

    }


    @Override
    public void setDataTime(String startTime, String endTime) {

    }
}
