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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonExerciseAdapter;
import com.sx.trans.company.bean.CommpanySafeExerciseBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentAnnualPlanActivity;
import com.sx.trans.transport.dynamicMonitoring.adapter.AnnualPlanAdapter;
import com.sx.trans.transport.home.bean.AnnualPlanBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全演习
 */
public class SafeExerciseActivity extends BaseTransActivity implements BaseNetApi ,View.OnClickListener{
    EditText et_vehicle;
    private String search;
    TextView tvQuery;
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private ImageView btDeleted;
    private RelativeLayout rldeleted;
    private List<CommpanySafeExerciseBean> mList = new ArrayList<>();
    private CommonExerciseAdapter adapter;
    private CompanyManager manager;
    private int pageNum = 1;//分页
    private int pageSize = 10;//分页中每页显示多少数据
    private ImageView iv_pie;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_yanxi_accident;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        httpRequest();
        leftGoBack(this);
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        iv_pie=findViewById(R.id.iv_pie);
        et_vehicle = findViewById(R.id.et_vehicle);
        tvQuery = findViewById(R.id.tv_query);
        tvQuery.setOnClickListener(this);
        rldeleted = findViewById(R.id.rl_deleted);
        btDeleted = findViewById(R.id.bt_deleted);
        et_vehicle.addTextChangedListener(watcher);
        rldeleted.setOnClickListener(this);
        setRecycleViewData();
        TextView layout_top_modleinfo = findViewById(R.id.layout_top_modleinfo);
        layout_top_modleinfo.setText("安全演习");
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
    private void setRecycleViewData() {
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        srv.setOnRefreshListener(mOnRefreshListener);
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 网络请求
     */
    private void httpRequest() {
        manager = new CompanyManager(SafeExerciseActivity.this, this);
        if (pageNum != 1) {
            pageNum = 1;
        }
        manager.getExercise(pageNum, pageSize,"");
        manager.setOnSafeCompanyExerciseData(new CompanyManager.SafeCompanyExerciseData() {
            @Override
            public void onSafeCompanyExerciseData(int what, List<CommpanySafeExerciseBean> safeCompanyExerciseList) {
                //获取到数据
                if (safeCompanyExerciseList == null || safeCompanyExerciseList.size() <= 0) {
                    showToast("查无此信息");
                    return;
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
                if (adapter == null) {
                    mList.clear();
                }
                for (int i = 0; i < safeCompanyExerciseList.size(); i++) {
                    mList.add(safeCompanyExerciseList.get(i));
                }
                setCommonContract(mList);
            }
        });
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (pageNum != 1) {
                pageNum = 1;
            }
           adapter=null;
            if (isSearch) {
                //条件查询,刷新数据
                manager.getExercise(pageNum, pageSize, search);
            } else {
                //非条件查询
                manager.getExercise(pageNum, pageSize, "");
            }
//            manager.getExercise(pageNum, pageSize,"");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (srv.isRefreshing()) {
                        srv.setRefreshing(false);
                        showToast("请求失败");
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
//                if (mList.size() >= 20) {
//                    manager.getExercise(pageNum++, pageSize,"");
//                }
                if (mList.size() % pageSize != 0) {
                    showToast("没有更多数据了");
                    return;
                }
                if (isSearch) {
                    //条件查询,刷新数据
                    manager.getExercise(++pageNum, pageSize, search);
                } else {
                    //非条件查询
                    manager.getExercise(++pageNum, pageSize, "");
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //10秒之后,请求没有返回来,停止刷新
                        if (srv.isRefreshing()) {
                            srv.setRefreshing(false);
                        }
                    }
                }, 10000);
            }
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
//        showToast("netSuccessed:::" + data);
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
    private void setCommonContract(final List<CommpanySafeExerciseBean> mList) {
        if (pageNum > 1) {
        if (adapter == null) {
            adapter = new CommonExerciseAdapter(R.layout.item_anquanyanxi, mList);
            rlv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        } else {
            adapter = new CommonExerciseAdapter(R.layout.item_anquanyanxi, mList);
            rlv.setAdapter(adapter);
        }
        adapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(SafeExerciseActivity.this, SafeExerciseDetialActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SafeExerciseDetialActivity.class", mList.get(position));
                intent.putExtra("SafeExerciseDetialActivity", bundle);
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
                search = et_vehicle.getText().toString().trim();

                if (TextUtils.isEmpty(search) || "请输入演练名称".equals(search)) {
                    //获取当前选择的类型,请求数据 查询所有
                    manager.getExercise(pageNum, pageSize, "");//餐三,传递空串查询所有
                    isSearch = false;//查询所有
                } else {
                    manager.getExercise(pageNum, pageSize, search);
                    isSearch = true;//条件查询
                }
                break;
            case R.id.rl_deleted:
                et_vehicle.setText("");
                break;
        }
    }
}
