package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.company.adapter.CommonAnnualAdapter;
import com.sx.trans.company.adapter.CommonMaintainAdapter;
import com.sx.trans.company.bean.CommpanySafeMaintainBean;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.transport.home.activity.StudentMaintenanceActivity;
import com.sx.trans.transport.home.activity.StudentMaintenanceReportActivity;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchao on 2017/9/25.
 * 维护
 */

public class SafeMaintainActivity extends BaseActivity {
    private LinearLayout mLinearLayout;//对应于主布局中用来添加子布局的View
    private View mGridView;

    EditText etVehicle;
    private RelativeLayout rldeleted;
    private ImageView btDeleted;

    private String search;
    TextView tvQuery;

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<MaintenanceBean> mList = new ArrayList<>();
    private CommonMaintainAdapter adapter;

    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private CompanyManager manager;
    private LinearLayout ll_search;
    private ImageView iv_pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_weihu_query);
        LzApp.addActivity(this);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal), 0);
        ll_search = findViewById(R.id.ll_search);
//        ll_search.setVisibility(View.GONE);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
//        findViewById(R.id.wh).setVisibility(View.GONE);
        setTopModleText("维护信息");
        iv_pie = findViewById(R.id.iv_pie);
        mGridView = View.inflate(this, R.layout.recycle_title, null);
        mLinearLayout = (LinearLayout) findViewById(R.id.insert);
        TextView t1 = mGridView.findViewById(R.id.t1);
        t1.setText("车牌号");

        TextView t2 = mGridView.findViewById(R.id.t2);
        t2.setText("下次维护时间");
        etVehicle = findViewById(R.id.et_vehicle);
        tvQuery = findViewById(R.id.tv_query);
        mLinearLayout.addView(mGridView);
        tvQuery.setOnClickListener(this);
        rldeleted = findViewById(R.id.rl_deleted);
        btDeleted = findViewById(R.id.bt_deleted);
        etVehicle.addTextChangedListener(watcher);
        rldeleted.setOnClickListener(this);
        setRecycleViewData();
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            search = etVehicle.getText().toString();
            if (s.toString() == null || s.length() == 0) {
                btDeleted.setVisibility(View.GONE);


            } else {
                btDeleted.setVisibility(View.VISIBLE);
            }
        }
    };
    private void setRecycleViewData() {
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
        srv.setOnRefreshListener(mOnRefreshListener);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            adapter=null;
            if (pageNum != 1) {
                pageNum = 1;
            }
            if (isSearch) {
                //条件查询,刷新数据
                manager.getSafeMaintainList(pageNum, pageSize, search);
            } else {
                //非条件查询
                manager.getSafeMaintainList(pageNum, pageSize, "");
            }
//            manager.getSafeMaintainList(pageNum, pageSize,"");
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
                    showToast("没有更多数据了");
                    return;
                }
                if (isSearch) {
                    //条件查询,刷新数据
                    manager.getSafeMaintainList(++pageNum, pageSize, search);
                } else {
                    //非条件查询
                    manager.getSafeMaintainList(++pageNum, pageSize, "");
                }

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
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
            }
        }, 10000);
    }

    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(final List<MaintenanceBean> mList) {

        if (pageNum > 1) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new CommonMaintainAdapter(R.layout.item_safe_protect, mList, this);
                rlv.setAdapter(adapter);
            }
        } else {
            adapter = new CommonMaintainAdapter(R.layout.item_safe_protect, mList, this);
            rlv.setAdapter(adapter);
        }

        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //TODO 跳转到车辆台账页面  功能 未实现
                Intent intent = new Intent(SafeMaintainActivity.this, StudentMaintenanceReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("StudentMaintenanceReportActivity", mList.get(position));
                intent.putExtra("StudentMaintenanceReportActivity.class", bundle);
                startActivity(intent);
            }
        });
        if (mList == null || mList.size() <= 0) {

            iv_pie.setVisibility(View.VISIBLE);
        } else {
            iv_pie.setVisibility(View.GONE);

        }

    }


    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        manager = new CompanyManager(this, this);
        manager.getSafeMaintainList(pageNum, pageSize,"");
        manager.setOnSafeMaintainListData(new CompanyManager.SafeMaintainListData() {
            @Override
            public void onSafeMaintainListData(int what, List<MaintenanceBean> safeMaintainList) {
                if (safeMaintainList == null || safeMaintainList.size() <= 0) {

                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(safeMaintainList);
                setCommonContract(mList);
            }
        });

    }
    /**
     * 默认不是查询,当为true时 为查询
     */
    private boolean isSearch = false;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_query:
                pageNum = 1;
                if (mList.size() > 0) {
                    mList.clear();
                    setCommonContract(mList);
                }
                adapter = null;
                search = etVehicle.getText().toString().trim();
                if (pageNum != 1) {
                    pageNum = 1;
                }
                if (TextUtils.isEmpty(search) || "请输入车牌号".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getSafeMaintainList(pageNum, pageSize, "");//餐三,传递空串查询所有
                    isSearch = false;//查询所有
                } else {
                    manager.getSafeMaintainList(pageNum, pageSize, search);
                    isSearch = true;//条件查询
                }
                break;

            case R.id.rl_deleted:
                etVehicle.setText("");
                break;
        }

    }

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

    }

    @Override
    public void netFailed(int what, String message) {
        iv_pie.setVisibility(View.VISIBLE);
    }


    /**
     * 车辆台账页面...
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(VehicleQueryActivity.this, VehicleLedgerActivity.class);
//        intent.putExtra("VehicleNo", mList.get(i).getVehicleNo());
//        startActivity(intent);
    }
}
