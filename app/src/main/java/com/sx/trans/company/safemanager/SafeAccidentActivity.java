package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.company.adapter.CommonAccidentAdapter;
import com.sx.trans.company.adapter.CommonMaintainAdapter;
import com.sx.trans.company.bean.CommpanySafeMaintainBean;
import com.sx.trans.company.bean.SafeAccidentBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.transport.home.activity.StudentAccidentActivity;
import com.sx.trans.transport.home.activity.StudentAccidentReportActivity;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 事故
 */
public class SafeAccidentActivity extends BaseActivity {

    private ImageView btDeleted;
    EditText et_vehicle;
    private String search;
    TextView tvQuery;

    private RelativeLayout rldeleted;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<AccidentBeans> mList = new ArrayList<>();
    private CommonAccidentAdapter adapter;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private CompanyManager manager;
    private ImageView iv_pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_accident_query);
        LzApp.addActivity(this);
//        findViewById(R.id.ll_search).setVisibility(View.GONE);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal), 0);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        setTopModleText("事故");
        rldeleted = findViewById(R.id.rl_deleted);
        iv_pie=findViewById(R.id.iv_pie);
        btDeleted = findViewById(R.id.bt_deleted);
        et_vehicle = findViewById(R.id.et_vehicle);
        et_vehicle.addTextChangedListener(watcher);
        tvQuery = findViewById(R.id.tv_query);
        tvQuery.setOnClickListener(this);
        rldeleted.setOnClickListener(this);
        setRecycleViewData();
    }

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
                manager.getSafeAccidentList(pageNum, pageSize, search);
            } else {
                //非条件查询
                manager.getSafeAccidentList(pageNum, pageSize, "");
            }

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
                    manager.getSafeAccidentList(++pageNum, pageSize, search);
                } else {
                    //非条件查询
                    manager.getSafeAccidentList(++pageNum, pageSize, "");
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
    private void setCommonContract(final List<AccidentBeans> mList) {

        if (pageNum > 1) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new CommonAccidentAdapter(R.layout.item_accident, mList, this);
                rlv.setAdapter(adapter);
            }
        } else {
            adapter = new CommonAccidentAdapter(R.layout.item_accident, mList, this);
            rlv.setAdapter(adapter);
        }

        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(SafeAccidentActivity.this, StudentAccidentReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("StudentAccidentReportActivity.class", mList.get(position));
                intent.putExtra("StudentAccidentReportActivity", bundle);
                startActivity(intent);
            }
        });
        if (mList == null || mList.size() <= 0) {

            iv_pie.setVisibility(View.VISIBLE);
        } else {
            iv_pie.setVisibility(View.GONE);

        }

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
            search = et_vehicle.getText().toString();
            if (s.toString() == null || s.length() == 0) {
                btDeleted.setVisibility(View.GONE);


            } else {
                btDeleted.setVisibility(View.VISIBLE);
            }
        }
    };
    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        manager = new CompanyManager(this, this);
        manager.getSafeAccidentList(pageNum, pageSize,"");
        manager.setOnSafeAccidentListData(new CompanyManager.SafeAccidentListData() {
            @Override
            public void onSafeAccidentListData(int what, List<AccidentBeans> safeAccidentList) {
                if (safeAccidentList == null || safeAccidentList.size() <= 0) {
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(safeAccidentList);
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
                search = et_vehicle.getText().toString().trim();
                if (pageNum != 1) {
                    pageNum = 1;
                }
                if (TextUtils.isEmpty(search) || "请输入事故名称".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getSafeAccidentList(pageNum, pageSize, "");//餐三,传递空串查询所有
                    isSearch = false;//查询所有
                } else {
                    manager.getSafeAccidentList(pageNum, pageSize, search);
                    isSearch = true;//条件查询
                }
                break;
            case R.id.rl_deleted:
                et_vehicle.setText("");
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



