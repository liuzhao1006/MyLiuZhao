package com.sx.trans.transport.dynamicMonitoring;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

import com.hwj.juneng.yp.YearPicker;
import com.sx.trans.R;
import com.sx.trans.app.Config;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseFragment;
import com.sx.trans.base.ItemApi;
import com.sx.trans.company.adapter.HomePagerAdapter;
import com.sx.trans.company.safemanager.SafeRulesActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentAnnualPlanActivity;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentEnterpriseRulesActivity;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentSpecialStudyActivity;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentLocalLawActivity;
import com.sx.trans.transport.dynamicMonitoring.activity.StudyPlanActivity;
import com.sx.trans.transport.dynamicMonitoring.activity.VideoListActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.LearnStudyAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.LearnStudyBean;
import com.sx.trans.transport.dynamicMonitoring.bean.VideoListBean;
import com.sx.trans.transport.dynamicMonitoring.inter.DLearnApi;
import com.sx.trans.transport.dynamicMonitoring.manager.DManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 * 学员端 学习
 */

public class DMFragment extends BaseFragment implements DLearnApi, ItemApi, View.OnClickListener {
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);


    private LinearLayout llContainer;
    private LearnStudyAdapter adapter;
    //图片id集合
    private int[] mImageIds = new int[]{R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d, R.drawable.e};

    // 图片标题集合
    private final String[] mImageDes = Config.mImageDes;

    private LzTextView tvTitle;



    private List<LearnStudyBean> mList = new ArrayList<>();
    /*RecycleView*/
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private static final int D_LEARN = 0x0010;//法律法规
    private DManager manager;
    private String companyId;
    private String professionId;
    private String studId;
    private int year;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dm;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        TextView l = mRootView.findViewById(R.id.layout_top_modleinfo);
        mRootView.findViewById(R.id.layout_top_leftimg).setVisibility(View.INVISIBLE);
        l.setText("学习");
        if (mList.size() > 0) {
            mList.clear();
        }
        /**
         * CompanyId
         * ProfessionId
         * StuId
         *
         * StudyYear
         *
         public void getData(String CompanyId, String ProfessionId, String StuId, String StudyYear) {
         */
        companyId = PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_COMPANY_LEARN, null);
        professionId = PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_PROFESSIONID_LEARN, null);
        studId = PrefUtils.getString(getActivity(), LoginConfig.EMPLOYEE_STATUS_LEARN, null);
        year = Calendar.getInstance().get(Calendar.YEAR);
        manager = new DManager(D_LEARN, getActivity(), this, this);
        manager.getData(companyId, professionId, studId, year);

        mRootView.findViewById(R.id.ll_dm_learn_plan).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_local_law).setOnClickListener(this);
//        mRootView.findViewById(R.id.ll_fragment_dm_special_study).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_local_law).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_safetyGoals).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_SystemSpecification).setOnClickListener(this);


        setRefreshViewData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_dm_learn_plan://学习计划

                // TODO   未实现

//                startActivity(new Intent(getActivity(), StudyPlanActivity.class));
                break;

//            case R.id.ll_fragment_dm_special_study://专项学习
//                startActivity(new Intent(getActivity(), StudentSpecialStudyActivity.class));
//                break;
            case R.id.ll_local_law://地方法规政策
                startActivity(new Intent(getActivity(), StudentEnterpriseRulesActivity.class));

                break;
            case R.id.tv_safetyGoals://企业规章制度
//                Intent intent2 = new Intent(getActivity(), StudentLocalLawActivity.class);
//                intent2.putExtra("StudentLocalLawActivity", "SystemSpecification");
//                startActivity(intent2);
                setIntent(getActivity(), SafeRulesActivity.class, "企业规章制度");
//                Toast.makeText(getActivity(),"开发中...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_SystemSpecification://年度计划
                showNormalDialog();
//                startActivity(new Intent(getActivity(), StudentAnnualPlanActivity.class));
                break;
        }
    }

    private void setIntent(Context context, Class clazz, String interfaceType) {

        Intent intent = new Intent(context, clazz);
        intent.putExtra("interfaceType", interfaceType);
        startActivity(intent);

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

    }

    /**
     * 设置RecycleView样式,,通用
     */
    private void setRefreshViewData() {
        ref_rlv = mRootView.findViewById(R.id.ref_rlv);
        rlv = mRootView.findViewById(R.id.rlv);
        ref_rlv.setOnRefreshListener(mOnRefreshListener);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
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
            manager.getData(companyId, professionId, studId, year);
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
        LogUtils.i(learnStudyBeans.get(0).toString());

        if (learnStudyBeans == null || learnStudyBeans.size() <= 0) {
//            showToast("查无此信息");
            return;
        }
        if (mList.size() > 0) {
            mList.clear();
        }
        if (ref_rlv.isRefreshing()) {
            ref_rlv.setRefreshing(false);
        }
        LogUtils.i(learnStudyBeans.get(0));
        mList = learnStudyBeans;
        setCommonContract(mList);
    }

    @Override
    public void getVideoData(List<VideoListBean> videoListBeans) {

    }

    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(List<LearnStudyBean> mList) {
        adapter = new LearnStudyAdapter(getActivity(), mList, this);
        rlv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), VideoListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("LearnStudyBean", mList.get(position));
        intent.putExtra("LearnStudyBeanList", bundle);
        startActivity(intent);
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final int[] years = new int[1];
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_customize, null);
        normalDialog.setTitle("请选择年份");
        normalDialog.setView(dialogView);
        YearPicker yearPicker = (YearPicker) dialogView.findViewById(R.id.yp_year);
        yearPicker.setOnItemSelectedListener(new YearPicker.OnItemSelectedListener() {
            @Override
            public void onSelected(int year) {
//            years=year;
                years[0] = year;
            }
        });
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent(getActivity(), StudentAnnualPlanActivity.class);
                        if (years[0] != 0) {
                            Log.i("d", "ok" + years[0]);

                            intent.putExtra("year", years[0] + "");

                        } else {
                            intent.putExtra("year", currentYear + "");
                        }
                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}
