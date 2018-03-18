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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.company.adapter.CommonMaintainAdapter;
import com.sx.trans.company.adapter.CommonRulesAdapter;
import com.sx.trans.company.bean.CommpanySafeMaintainBean;
import com.sx.trans.company.bean.SafeRulesBean;
import com.sx.trans.company.bean.SafeRulesDetialBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 制度规范
 */
public class SafeRulesActivity extends BaseActivity {
    private ImageView btDeleted;
    private RelativeLayout rldeleted;
    EditText etVehicle;


    private String search;
    TextView tvQuery;

    private LinearLayout mLinearLayout;//对应于主布局中用来添加子布局的View
    private View mGridView;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<SafeRulesDetialBean> mList = new ArrayList<>();
    private CommonRulesAdapter adapter;
    private ImageView iv_pie;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private CompanyManager manager;
    private LinearLayout ll_search;
    private int type;//区分是哪个页面传递过来的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_zhidu_query);
        LzApp.addActivity(this);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal), 0);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        if (intent != null) {
            if ("制度规范".equals(intent.getStringExtra("interfaceType"))) {
                setTopModleText("制度规范");
                type = 1;
            } else if ("企业规章制度".equals(intent.getStringExtra("interfaceType"))) {
                setTopModleText("企业规章制度");
                type = 2;
            }
        }
        iv_pie=findViewById(R.id.iv_pie);
        etVehicle = findViewById(R.id.et_vehicle);
        tvQuery = findViewById(R.id.tv_query);
        ll_search = findViewById(R.id.ll_search);
//        ll_search.setVisibility(View.GONE);
        mGridView = View.inflate(this, R.layout.recycle_title, null);
        mLinearLayout = (LinearLayout) findViewById(R.id.insert);
        TextView t1 = mGridView.findViewById(R.id.t1);
        t1.setText("制度名称");
        TextView t2 = mGridView.findViewById(R.id.t2);
        t2.setText("发布日期");
        mLinearLayout.addView(mGridView);
        tvQuery.setOnClickListener(this);
        rldeleted = findViewById(R.id.rl_deleted);
        btDeleted = findViewById(R.id.bt_deleted);
        etVehicle.addTextChangedListener(watcher);
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
                manager.getSafeRulesList(type, pageNum, pageSize,search);
            } else {
                //非条件查询
                manager.getSafeRulesList(type, pageNum, pageSize,"");
            }
//            manager.getSafeRulesList(type, pageNum, pageSize,"");
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
                    manager.getSafeRulesList(type, ++pageNum, pageSize,search);
                } else {
                    //非条件查询
                    manager.getSafeRulesList(type,++pageNum, pageSize,"");
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
    private void setCommonContract(final List<SafeRulesDetialBean> mList) {

        if (pageNum > 1) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new CommonRulesAdapter(R.layout.item_ledger_query, mList, this);
                rlv.setAdapter(adapter);
            }
        } else {
            adapter = new CommonRulesAdapter(R.layout.item_ledger_query, mList, this);
            rlv.setAdapter(adapter);
        }

        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(SafeRulesActivity.this, SafeRulesDetialActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SafeRulesDetialActivity.class", mList.get(position));
                intent.putExtra("SafeRulesDetialActivity", bundle);
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
        manager.getSafeRulesList(type, pageNum, pageSize,"");
        manager.setOnSafeRulesListData(new CompanyManager.SafeRulesListData() {
            @Override
            public void onSafeRulesListData(int what, List<SafeRulesDetialBean> safeRulesList) {
                if (safeRulesList == null || safeRulesList.size() <= 0) {

                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(safeRulesList);
                setCommonContract(mList);
            }
        });
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
                if (TextUtils.isEmpty(search) || "请输入制度名称".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getSafeRulesList(type, pageNum, pageSize,"");//餐三,传递空串查询所有
                    isSearch = false;//查询所有
                } else {
                    manager.getSafeRulesList(type, pageNum, pageSize,search);
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
