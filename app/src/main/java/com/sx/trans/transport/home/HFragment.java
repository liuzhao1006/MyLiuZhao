package com.sx.trans.transport.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.lzlibrary.widget.MyProgressBar;
import com.sx.trans.R;
import com.sx.trans.base.BaseFragment;
import com.sx.trans.transport.dynamicMonitoring.adapter.StopAdapter;
import com.sx.trans.transport.home.activity.StudentAccidentActivity;
import com.sx.trans.transport.home.activity.StudentCarfulStopActivity;
import com.sx.trans.transport.home.activity.StudentHomeAlarmActivity;
import com.sx.trans.transport.home.activity.StudentMaintenanceActivity;
import com.sx.trans.transport.home.activity.StudentYearCarefulActivity;
import com.sx.trans.transport.home.bean.AlamBean;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.transport.home.bean.SafeLearnBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 * 学员端
 */

public class HFragment extends BaseFragment implements View.OnClickListener {


    private static final int REMINDING = 0x0004;//提醒

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<RemberBean> mList = new ArrayList<>();
    private StopAdapter adapter;
    private EmployeesManager manager;
    private LzTextView ltv_fragment_home_currentday;
    private LzTextView ltv_fragment_home_no;
    private LzTextView ltv_fragment_home_count;
//    private MyProgressBar bar;
//    private LzTextView tv_learn_time;
    private int PageNum = 1;
    private int PageSize = 20;
    private LinearLayout ll_alarm_count;
    private LinearLayout ll_alarm_cur;
    private LinearLayout ll_alarm_no;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_h;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        httpRequest();
    }

    @Override
    protected void initData() {
        mRootView.findViewById(R.id.layout_top_leftimg).setVisibility(View.INVISIBLE);
        TextView layout_top_modleinfo = mRootView.findViewById(R.id.layout_top_modleinfo);
        layout_top_modleinfo.setText("首页");
        mRootView.findViewById(R.id.ll_student_accident).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_student_year_careful).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_student_maintenance).setOnClickListener(this);
        srv = mRootView.findViewById(R.id.ref_rlv);
        rlv = mRootView.findViewById(R.id.rlv);
        setRecycleViewData();
        ltv_fragment_home_currentday = mRootView.findViewById(R.id.ltv_fragment_home_currentday);
        ltv_fragment_home_no = mRootView.findViewById(R.id.ltv_fragment_home_no);
        ltv_fragment_home_count = mRootView.findViewById(R.id.ltv_fragment_home_count);
//        bar = mRootView.findViewById(R.id.mpb_learn);
//        tv_learn_time = mRootView.findViewById(R.id.tv_learn_time);

        ll_alarm_count = mRootView.findViewById(R.id.ll_alarm_count);
        ll_alarm_count.setOnClickListener(this);
        ll_alarm_cur = mRootView.findViewById(R.id.ll_alarm_cur);
        ll_alarm_cur.setOnClickListener(this);
        ll_alarm_no = mRootView.findViewById(R.id.ll_alarm_no);
        ll_alarm_no.setOnClickListener(this);
    }

    /**
     * 网络请求
     */
    private void httpRequest() {
        manager = new EmployeesManager(getActivity(), this);
        manager.getRember(PageNum, PageSize, 0);
        manager.getAlam();
        manager.getLearningOver();
        if (mList.size() > 0) {
            mList.clear();
        }
        manager.setOnRemberData(new EmployeesManager.RemberData() {
            @Override
            public void onRemberData(int what, List<RemberBean> stopBeanList) {
                LogUtils.i("onRemberData");
                if (stopBeanList == null || stopBeanList.size() <= 0) {
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(stopBeanList);
                setCommonContract(mList);
            }
        });
        manager.setOnAlamData(new EmployeesManager.AlamData() {
            @Override
            public void onAlamData(int what, List<AlamBean> alamList) {
                if (alamList != null && alamList.size() > 0) {
                    if (alamList.get(0) != null) {
                        ltv_fragment_home_currentday.setText(alamList.get(0).getDayRisk() + "次");
                        ltv_fragment_home_no.setText(alamList.get(0).getALLNORisk() + "次");
                        ltv_fragment_home_count.setText(alamList.get(0).getALLRisk() + "次");
                    }
                }
            }
        });
        manager.setOnLearnData(new EmployeesManager.LearnData() {
            @Override
            public void onLearnData(int what, List<SafeLearnBean> learnList) {
                if (learnList == null || learnList.size() <= 0) {
                    return;
                }
                SafeLearnBean bean = learnList.get(0);
                float progress = Float.parseFloat(bean.getMPer());
                int per = (int) (progress * 100);
                LogUtils.i(per);
//                bar.setProgress(per);
                float countLearn = Float.parseFloat(bean.getMBasicsLearn());
                int count = (int) countLearn;
//                tv_learn_time.setText(count + "学时");

            }
        });
    }

    private void setRecycleViewData() {
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        srv.setOnRefreshListener(mOnRefreshListener);
    }

    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(final List<RemberBean> mList) {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new StopAdapter(R.layout.alarm_accident, mList);
            rlv.setAdapter(adapter);
        }
        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getActivity(), StudentCarfulStopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("StudentCarfulStopActivity.class", mList.get(position));
                intent.putExtra("StudentCarfulStopActivity", bundle);
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
           adapter=null;
            manager.getRember(PageNum, PageSize, 0);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (srv.isRefreshing()) {
                        srv.setRefreshing(false);
                    }
                }
            }, 10000);
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
//        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void netFailed(int what, String message) {
//        Toast.makeText(getActivity(), "服务器连接故障", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_student_accident://事故
                startActivity(new Intent(getActivity(), StudentAccidentActivity.class));
                break;
            case R.id.ll_student_year_careful://年审
                startActivity(new Intent(getActivity(), StudentYearCarefulActivity.class));
                break;
            case R.id.ll_student_maintenance://维护
                startActivity(new Intent(getActivity(), StudentMaintenanceActivity.class));
                break;
            case R.id.ll_alarm_cur://当天
                setIntent(getActivity(), StudentHomeAlarmActivity.class, "1");
                break;
            case R.id.ll_alarm_no://未处理
                setIntent(getActivity(), StudentHomeAlarmActivity.class, "2");
                break;
            case R.id.ll_alarm_count://总次数
                setIntent(getActivity(), StudentHomeAlarmActivity.class, "3");
                break;
        }
    }

    private void setIntent(Context context, Class clazz, String interfaceType) {

        Intent intent = new Intent(context, clazz);
        intent.putExtra("interfaceType", interfaceType);
        startActivity(intent);

    }

}
