package com.sx.trans.transport.home.activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.home.adapter.MaintenanceAdapter;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.transport.manager.EmployeesManager.MaintenanceData;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentMaintenanceActivity extends BaseTransActivity implements BaseNetApi ,View.OnClickListener{
    private LinearLayout mLinearLayout;//对应于主布局中用来添加子布局的View
    private View mGridView;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private EmployeesManager manager;
    private static final int MAINTENANCE = 0x0003;//维修
    private List<MaintenanceBean> mList = new ArrayList<>();
    private MaintenanceAdapter adapter;
    private ImageView iv_pie;
    EditText etVehicle;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private ImageView btDeleted;
    private RelativeLayout rldeleted;

    private String search;
    TextView tvQuery;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_maintenance;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        httpRequest();
        initData();
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
    /**
     * 网络请求
     */
    private void httpRequest() {
        manager = new EmployeesManager(StudentMaintenanceActivity.this, this);
        manager.getMaintenance(pageNum, pageSize,"");
        if (mList.size() > 0) {

        }
        manager.setOnMaintenanceData(new MaintenanceData() {
            @Override
            public void onMaintenanceData(int what, List<MaintenanceBean> maintenanceList) {
                //获取到数据
                if (maintenanceList == null || maintenanceList.size() <= 0) {
//                    showToast("查无此信息");

                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                for (int i = 0; i < maintenanceList.size(); i++) {
                    mList.add(maintenanceList.get(i));
                }
                setCommonContract(mList);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initData() {
        etVehicle = findViewById(R.id.et_vehicle);
        tvQuery = findViewById(R.id.tv_query);
        tvQuery.setOnClickListener(this);
        mGridView = View.inflate(this, R.layout.recycle_title, null);
        mLinearLayout = (LinearLayout) findViewById(R.id.insert);
        TextView t1 = mGridView.findViewById(R.id.t1);
        t1.setText("车牌号");
//        findViewById(R.id.wh).setVisibility(View.GONE);
        TextView t2 = mGridView.findViewById(R.id.t2);
        t2.setText("下次维护时间");
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("维护信息");
        leftGoBack(this);
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        iv_pie = findViewById(R.id.iv_pie);
        mLinearLayout.addView(mGridView);
        rldeleted = findViewById(R.id.rl_deleted);
        btDeleted = findViewById(R.id.bt_deleted);
        etVehicle.addTextChangedListener(watcher);
        rldeleted.setOnClickListener(this);
        setRecycleViewData();
    }


    private void setRecycleViewData() {
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        srv.setOnRefreshListener(mOnRefreshListener);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            adapter = null;
            if (isSearch) {
                //条件查询,刷新数据
                manager.getMaintenance(pageNum, pageSize, search);
            } else {
                //非条件查询
                manager.getMaintenance(pageNum, pageSize, "");
            }
            refreshData();
//            manager.getMaintenance("");
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (srv.isRefreshing()) {
//                        srv.setRefreshing(false);
//                        showToast("请求失败");
//                    }
//                }
//            }, 10000);
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
    }

    @Override
    public void netFailed(int what, String message) {
        showToast(message);
        iv_pie.setVisibility(View.VISIBLE);
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
            adapter = new MaintenanceAdapter(R.layout.item_vehicle_maintance, mList, this);
            rlv.setAdapter(adapter);
        }
        } else {
            adapter = new MaintenanceAdapter(R.layout.item_vehicle_maintance, mList,this);
            rlv.setAdapter(adapter);
        }
        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(StudentMaintenanceActivity.this, StudentMaintenanceReportActivity.class);
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
                    manager.getMaintenance(pageNum, pageSize, "");//餐三,传递空串查询所有
                    isSearch = false;//查询所有
                } else {
                    manager.getMaintenance(pageNum, pageSize, search);
                    isSearch = true;//条件查询
                }
                break;
            case R.id.rl_deleted:
                etVehicle.setText("");
                break;

        }

    }
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
                    manager.getMaintenance(++pageNum, pageSize, search);
                } else {
                    //非条件查询
                    manager.getMaintenance(++pageNum, pageSize, "");
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
}
