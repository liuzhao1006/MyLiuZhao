package com.sx.trans.company.safemanager;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseActivity;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.adapter.CommonAnnualAdapter;
import com.sx.trans.company.adapter.CommonQueryAdapter;
import com.sx.trans.company.bean.CommpanyVehicleBean;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.login.LoginActivity;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 年审
 */
public class AnnualVerificationActivity extends BaseActivity implements BaseNetApi {
    private ImageView btDeleted;
    private RelativeLayout rldeleted;
    EditText etVehicle;
    TextView tvQuery;
    private Spinner spinner;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private List<SafeAnnualBean> mList = new ArrayList<>();
    private CommonAnnualAdapter adapter;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 21;//分页,每页多少条
    private CompanyManager manager;
    private static final String[] array1 = new String[]{"运输证", "行驶证", "线路牌"};
    private ArrayAdapter<String> spinnerAdapter;
    private int type;//获取当前选择的类型
    private String search;
    private LinearLayout ll_recycle_view;
    private ImageView iv_pie;
    private LinearLayout mLinearLayout;//对应于主布局中用来添加子布局的View
    private View mGridView;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle_query);
        LzApp.addActivity(this);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal), 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setTopModleText("年审");
        searchData();
        mGridView = View.inflate(this, R.layout.recycle_title_serach, null);
        mLinearLayout = (LinearLayout) findViewById(R.id.insert);
        t1 = mGridView.findViewById(R.id.t1);
        t1.setText("车牌号");
        t2 = mGridView.findViewById(R.id.t2);
        t2.setText("类型");
        t3 = mGridView.findViewById(R.id.t3);
        t3.setText("年审期至");
        etVehicle = findViewById(R.id.et_vehicle);
        tvQuery = findViewById(R.id.tv_query);
        ll_recycle_view = findViewById(R.id.ll_recycle_view);
        iv_pie = findViewById(R.id.iv_pie);
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
    /**
     * 搜索
     */
    private void searchData() {
        spinner = findViewById(R.id.spinner);
        setCoordinate(spinner, array1);
    }

    /**
     * listView初始化
     */
    private void setRecycleViewData() {
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        srv.setOnRefreshListener(mOnRefreshListener);
        rlv.addOnScrollListener(mOnScrollListener);
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
                manager.getSafeAnnualList(pageNum, pageSize, type, search);
            } else {
                //非条件查询
                manager.getSafeAnnualList(pageNum, pageSize, type, "");
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
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                if (srv.isRefreshing()) {
                    return;
                }
                if (mList.size() % pageSize != 0) {
                    showToast("没有更多数据了");
                    return;
                }
                if (isSearch) {
                    //条件查询,刷新数据
                    manager.getSafeAnnualList(++pageNum, pageSize, type, search);
                } else {
                    //非条件查询
                    manager.getSafeAnnualList(++pageNum, pageSize, type, "");
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
    private void setCommonContract(final List<SafeAnnualBean> mList) {
        if (pageNum > 1) {
        if (adapter != null) {
            adapter.notifyDataSetChanged();

        } else {
            adapter = new CommonAnnualAdapter(R.layout.item_year_check, mList, this, type);
            rlv.setAdapter(adapter);
        }
        } else {
            adapter = new CommonAnnualAdapter(R.layout.item_year_check, mList, this,type);
            rlv.setAdapter(adapter);
        }
        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //TODO 跳转到车辆台账页面  功能 未实现
                LogUtils.i(mList.get(position).toString());
            }
        });
        if (mList == null || mList.size() <= 0) {
            ll_recycle_view.setVisibility(View.GONE);
            iv_pie.setVisibility(View.VISIBLE);
        } else {
            iv_pie.setVisibility(View.GONE);
            ll_recycle_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initAction() {
    }

    @Override
    protected void initData() {
        manager = new CompanyManager(this, this);
        //默认传空字符串
        isSearch = false;
        manager.getSafeAnnualList(pageNum, pageSize, type, "");
        manager.setOnSafeAnnualListData(new CompanyManager.SafeAnnualListData() {
            @Override
            public void onSafeAnnualListData(int what, List<SafeAnnualBean> safeAnnualList) {
                if (safeAnnualList == null || safeAnnualList.size() <= 0) {
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                mList.addAll(safeAnnualList);
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
//                if (type == 0) {
//                    t2.setText("运输证");
//                } else if (type == 1) {
//                    t2.setText("行驶证");
//                } else if (type == 2) {
//                    t2.setText("线路牌");
//                }
                //查询数据展示页面
                if (mList.size() > 0) {
                    mList.clear();
                    setCommonContract(mList);
                }
                search = etVehicle.getText().toString().trim();
                LogUtils.i("==" + search + "==" + type);
                if (pageNum != 1) {
                    pageNum = 1;
                }
                if (TextUtils.isEmpty(search) || "请输入车牌号".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getSafeAnnualList(pageNum, pageSize, type, "");
                    isSearch = false;//查询所有
                } else {
                    manager.getSafeAnnualList(pageNum, pageSize, type, search);
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
    public void netFailed(int what, String message) {iv_pie.setVisibility(View.VISIBLE);

    }


    // 设置坐标系Spinner
    private void setCoordinate(Spinner spinner, String[] arr) {
        // 将可选内容与ArrayAdapter连接起来
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr);

        // 设置下拉列表的风格
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(spinner,
                arr));
        // 设置默认值
        spinner.setVisibility(View.VISIBLE);
    }


    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        private Spinner spinner;
        private String[] arr;

        public SpinnerSelectedListener(Spinner spinner, String[] arr) {
            super();
            this.spinner = spinner;
            this.arr = arr;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {

            type = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
