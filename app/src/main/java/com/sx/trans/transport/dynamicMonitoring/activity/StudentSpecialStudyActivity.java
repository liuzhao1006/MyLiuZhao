package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.activity.SpecialTargerDetialActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.adapter.SpecilStudyDownPdfAdapter;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.login.bean.Company;
import com.sx.trans.transport.dynamicMonitoring.adapter.RulesAdapter;
import com.sx.trans.transport.dynamicMonitoring.adapter.SpecialStudyAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.LawBean;
import com.sx.trans.transport.dynamicMonitoring.bean.SpecialLearnBean;
import com.sx.trans.transport.dynamicMonitoring.manager.StudentManager;
import com.sx.trans.transport.home.bean.SafeLearnBean;
import com.sx.trans.transport.manager.EmployeesManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.Utils;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 专项学习
 */
public class StudentSpecialStudyActivity extends BaseTransActivity implements BaseNetApi {
    private List<String> lt = new ArrayList<>();
    private SwipeRefreshLayout ref_rlv;
    private SwipeMenuRecyclerView rlv;
    private List<SpecialLearnBean> mList = new ArrayList<>();
    private CompanyManager manager;
    private int pageNum = 1;//分页,第几页
    private int pageSize = 20;//分页,每页多少条
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
    private ImageView iv_pie;
    private SpecialStudyAdapter mAdapter;
    private LinearLayout mLinearLayout;//对应于主布局中用来添加子布局的View
    private View mGridView;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ltv_down_count = findViewById(R.id.ltv_down_count);
        ltv_down = findViewById(R.id.ltv_down);
        iv_pie = findViewById(R.id.iv_pie);
        mGridView = View.inflate(this, R.layout.recycle_title, null);
        mLinearLayout = (LinearLayout) findViewById(R.id.insert);
        TextView t1 = mGridView.findViewById(R.id.t1);
        t1.setText("学习名称");
        TextView t2 = mGridView.findViewById(R.id.t2);
        t2.setText("学习时长");
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("专项学习");
        mLinearLayout.addView(mGridView);
        leftGoBack(this);
        setRefreshViewData();
        httpClick();

    }

    /**
     * 网络请求
     */
    private void httpClick() {
        manager = new CompanyManager(this, this);
        manager.getSpecialLearn(pageNum, pageSize);
        manager.setOnLearnData(new CompanyManager.LearnData() {
            @Override
            public void onLearn(int what, List<SpecialLearnBean> rulesList) {


                if (rulesList.size() <= 0) {

                    return;
                }
                if (ref_rlv.isRefreshing()) {
                    ref_rlv.setRefreshing(false);
                }
                if(mAdapter==null){
                mList.clear();}
                mList.addAll(rulesList);
                setCommonContract(mList);
            }
        });
    }

    private void setCommonContract(final List<SpecialLearnBean> mList) {
        if (mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }else{
         mAdapter = new SpecialStudyAdapter(R.layout.item_ledger_query, mList);
        rlv.setAdapter(mAdapter);}
        mAdapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //下载
                String[] item = mList.get(position).getUrl().split(",");
                String pdfname=mList.get(position).getSpecialStudyName()+",";
                if (lt.size() > 0) {
                        lt.clear();
                    }
                for (int i = 0; i < item.length; i++) {
                    lt.add(pdfname+item[i]);
                }
                dialog(lt);
//
//                    mList.add(mList.get(position));
//                if (!TextUtils.isEmpty(mList.get(position).getUrl())) {
//                    ltv_down.setVisibility(View.VISIBLE);
//                    ltv_down_count.setVisibility(View.VISIBLE);
//                    String[] item = mList.get(position).getUrl().split(",");
//                    ltv_down_count.setText(item.length + "条");
//                    if (lt.size() > 0) {
//                        lt.clear();
//                    }
//
//                    mList.add(mList.get(position));
//                    ltv_down.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dialog(lt);
//                        }
//                    });
//
//
//                }

            }
        });
    }
    private SwipeRefreshLayout downSrv;
    private SwipeMenuRecyclerView downRlv;

    /**
     * 图表弹窗展示
     *
     * @param
     */
    private void dialog(List<String> lt) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        downSrv = v.findViewById(R.id.ref_rlv);
        downRlv = v.findViewById(R.id.rlv);
        downRlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        downRlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        downRlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        downRlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        SpecilStudyDownPdfAdapter adapter = new SpecilStudyDownPdfAdapter(R.layout.item_rule_pdf_dialog, lt, StudentSpecialStudyActivity.this);
        downRlv.setAdapter(adapter);
        downSrv.setRefreshing(false);
        downSrv.setOnRefreshListener(mOnRefreshListener);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 600)
                .addDefaultAnimation()
                .show();
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
        showToast(message);
        iv_pie.setVisibility(View.VISIBLE);
    }

    /**
     * 设置RecycleView样式,,通用
     */
    private void setRefreshViewData() {
        ref_rlv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        ref_rlv.setOnRefreshListener(mOnRefreshListener);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlv.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mAdapter=null;
            if (pageNum != 1) {
                pageNum = 1;
            }

            manager.getSpecialLearn(pageNum, pageSize);
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
                    showToast("数据加载完毕");
                    return;
                }

//                manager.getLawRules(pageNum++, pageSize);
                manager.getSpecialLearn(++pageNum, pageSize);
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
                if (ref_rlv.isRefreshing()) {
                    ref_rlv.setRefreshing(false);
                }
            }
        }, 10000);
    }


}

