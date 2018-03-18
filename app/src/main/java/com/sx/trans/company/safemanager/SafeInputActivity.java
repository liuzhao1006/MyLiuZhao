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
import com.sx.trans.company.adapter.CommonInputAdapter;
import com.sx.trans.company.bean.SafeAccidentBean;
import com.sx.trans.company.bean.SafeInputBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全投入
 */
public class SafeInputActivity extends BaseActivity {


    EditText etVehicle;
    private String search;
    TextView tvQuery;

    private ImageView btDeleted;
    private RelativeLayout rldeleted;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<SafeInputBean> mList = new ArrayList<>();
    private CommonInputAdapter adapter;

    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private CompanyManager manager;
    private ImageView iv_pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_aqtr_query);
        LzApp.addActivity(this);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal), 0);
        super.onCreate(savedInstanceState);

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
    @Override
    public void initView() {
        setTopModleText("安全投入");
        etVehicle = findViewById(R.id.et_vehicle);
        tvQuery = findViewById(R.id.tv_query);
//        findViewById(R.id.ll_search).setVisibility(View.GONE);
        rldeleted = findViewById(R.id.rl_deleted);
        btDeleted = findViewById(R.id.bt_deleted);
        etVehicle.addTextChangedListener(watcher);
        rldeleted.setOnClickListener(this);
        tvQuery.setOnClickListener(this);
        setRecycleViewData();
    }

    private void setRecycleViewData() {
        iv_pie = findViewById(R.id.iv_pie);
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
        srv.setOnRefreshListener(mOnRefreshListener);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            adapter = null;
            if (pageNum != 1) {
                pageNum = 1;
            }
            if (isSearch) {
                //条件查询,刷新数据
                manager.getSafeInputList(pageNum, pageSize, search);
            } else {
                //非条件查询
                manager.getSafeInputList(pageNum, pageSize, "");
            }
//            manager.getSafeInputList(pageNum, pageSize);
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
                    manager.getSafeInputList(++pageNum, pageSize, search);
                } else {
                    //非条件查询
                    manager.getSafeInputList(++pageNum, pageSize, "");
                }
//                manager.getSafeInputList(pageNum++, pageSize,"");
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
    private void setCommonContract(final List<SafeInputBean> mList) {

        if (pageNum > 1) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new CommonInputAdapter(R.layout.item_anqutr, mList, this);
                rlv.setAdapter(adapter);
            }
        } else {
            adapter = new CommonInputAdapter(R.layout.item_anqutr, mList, this);
            rlv.setAdapter(adapter);
        }

        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(SafeInputActivity.this, SafeInputDetialActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SafeInputDetialActivity.class", mList.get(position));
                intent.putExtra("SafeInputDetialActivity", bundle);
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
        manager.getSafeInputList(pageNum, pageSize,"");
        manager.setOnSafeInputListData(new CompanyManager.SafeInputListData() {
            @Override
            public void onSafeInputListData(int what, List<SafeInputBean> safeInputList) {
                if (safeInputList == null || safeInputList.size() <= 0) {

                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(safeInputList);
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
                //查询数据展示页面
                if (mList.size() > 0) {
                    mList.clear();
                    setCommonContract(mList);
                }
                adapter=null;
                search = etVehicle.getText().toString().trim();
                if (pageNum != 1) {
                    pageNum = 1;
                }
                if (TextUtils.isEmpty(search) || "请输入查询月份如：2017-1".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getSafeInputList(pageNum, pageSize, "");
                    isSearch = false;//查询所有
                } else {
                    manager.getSafeInputList(pageNum, pageSize, search);
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

}
