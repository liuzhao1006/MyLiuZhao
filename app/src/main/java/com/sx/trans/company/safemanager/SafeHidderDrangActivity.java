package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.adapter.CommonHidderDrangAdapter;
import com.sx.trans.company.adapter.CommonPersonAdapter;
import com.sx.trans.company.bean.CommpanyEmployeeInfoBean;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.transport.home.activity.StudentAccidentActivity;
import com.sx.trans.transport.home.activity.StudentAccidentReportActivity;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 隐患列表
 */
public class SafeHidderDrangActivity extends BaseActivity implements BaseNetApi,View.OnClickListener {
    EditText etPersonnel;
    private String search;
    TextView tvQuery;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<CommpanyHidderDrangBean> mList = new ArrayList<>();

    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private CompanyManager manager;
    private CommonHidderDrangAdapter adapter;
    private ImageView iv_pie;
    private ImageView btDeleted;
    private RelativeLayout rldeleted;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_hidder_drang;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal), 0);
    }

    /**
     * 初始化布局
     */
    private void initData() {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        ImageView iv = findViewById(R.id.layout_top_leftimg);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        l.setText("隐患");
        etPersonnel = findViewById(R.id.et_personnel);
        tvQuery = findViewById(R.id.tv_query);
        tvQuery.setOnClickListener(this);
        iv_pie = findViewById(R.id.iv_pie);
        rldeleted = findViewById(R.id.rl_deleted);
        btDeleted = findViewById(R.id.bt_deleted);
        etPersonnel.addTextChangedListener(watcher);
        rldeleted.setOnClickListener(this);
        setRecycleViewData();
        httpRequest();

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
            search = etPersonnel.getText().toString();
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
        manager = new CompanyManager(this, this);
        manager.getSafeManagerDrangList(pageNum, pageSize,"");
        manager.setOnSafeDrangListData(new CompanyManager.SafeDrangListData() {
            @Override
            public void onSafeDrangListData(int what, List<CommpanyHidderDrangBean> safeDrangList) {
                if (safeDrangList == null || safeDrangList.size() <= 0) {

                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(safeDrangList);
                setCommonContract(mList);
            }
        });
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
                manager.getSafeManagerDrangList(pageNum, pageSize, search);
            } else {
                //非条件查询
                manager.getSafeManagerDrangList(pageNum, pageSize, "");
            }
//            manager.getSafeManagerDrangList(pageNum, pageSize,"");
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
    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                if (mList.size() % pageSize != 0) {
                    showToast("数据加载完毕");
                    return;
                }
                if (isSearch) {
                    //条件查询,刷新数据
                    manager.getSafeManagerDrangList(++pageNum, pageSize, search);
                } else {
                    //非条件查询
                    manager.getSafeManagerDrangList(++pageNum, pageSize, "");
                }

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
    };

    /**
     * listView展示
     *
     * @param mList
     */
    private void setCommonContract(final List<CommpanyHidderDrangBean> mList) {

        if (pageNum > 1) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new CommonHidderDrangAdapter(R.layout.item_drang_list, mList, this);
                rlv.setAdapter(adapter);
            }
        } else {
            adapter = new CommonHidderDrangAdapter(R.layout.item_drang_list, mList, this);
            rlv.setAdapter(adapter);
        }

        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(SafeHidderDrangActivity.this, SafeHidderDetialActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SafeHidderDetialActivity.class", mList.get(position));
                intent.putExtra("SafeHidderDetialActivity", bundle);
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
                search = etPersonnel.getText().toString().trim();
                if (pageNum != 1) {
                    pageNum = 1;
                }
                if (TextUtils.isEmpty(search) || "请输入受检单位".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getSafeManagerDrangList(pageNum, pageSize, "");//餐三,传递空串查询所有
                    isSearch = false;//查询所有
                } else {
                    manager.getSafeManagerDrangList(pageNum, pageSize, search);
                    isSearch = true;//条件查询
                }
                break;
            case R.id.rl_deleted:
                etPersonnel.setText("");
                break;

        }
    }
}
