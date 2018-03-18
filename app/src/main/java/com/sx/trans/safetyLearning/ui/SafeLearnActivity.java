package com.sx.trans.safetyLearning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonSafeMeetingAdapter;
import com.sx.trans.company.adapter.CompeleteNumAdapter;
import com.sx.trans.company.adapter.LearnSearchAdapter;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class SafeLearnActivity extends BaseTransActivity implements BaseNetApi, OnChartValueSelectedListener, View.OnClickListener {


    @InjectView(R.id.layout_top_leftimg)
    ImageView layoutTopLeftimg;
    @InjectView(R.id.layout_top_leftinfo)
    TextView layoutTopLeftinfo;
    @InjectView(R.id.layout_top_with_buttom_tmp)
    LinearLayout layoutTopWithButtomTmp;
    @InjectView(R.id.layout_top_modleinfo)
    TextView layoutTopModleinfo;
    @InjectView(R.id.layout_top_rightinfo)
    ImageView layoutTopRightinfo;
    @InjectView(R.id.layout_top_righttextinfo)
    TextView layoutTopRighttextinfo;
    @InjectView(R.id.tv_annualVerification)
    LinearLayout tvAnnualVerification;
    @InjectView(R.id.tv_accident)
    LinearLayout tvAccident;
    @InjectView(R.id.tv_insurance)
    LinearLayout tvInsurance;
    @InjectView(R.id.tv_maintenance)
    LinearLayout tvMaintenance;
    @InjectView(R.id.tv_hiddenTrouble)
    LinearLayout tvHiddenTrouble;
    @InjectView(R.id.tv_Meetting)
    LinearLayout tvMeetting;
    @InjectView(R.id.tv_serach)
    TextView tvSearch;
    @InjectView(R.id.et_input)
    EditText etInput;

    @InjectView(R.id.ll)
    LinearLayout ll;
   ;
    private LearnSearchAdapter adapter;
    private ArrayList mList = new ArrayList();//搜索结果的存放

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void netStart() {

    }

    @Override
    public void netStop() {

    }

    @Override
    public void netSuccessed(int what, String data) {

    }

    @Override
    public void netFailed(int what, String message) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_learn;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        leftGoBack(this);
        layoutTopModleinfo.setText("安全学习");

        etInput.addTextChangedListener(watcher);
        tvMaintenance.setOnClickListener(this);
        tvHiddenTrouble.setOnClickListener(this);
        tvMeetting.setOnClickListener(this);
        tvSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_maintenance://从业人员完成率
                intent = new Intent(SafeLearnActivity.this, EmployeeCompletionRateActivity.class);

                startActivity(intent);
                break;
            case R.id.tv_hiddenTrouble://
                intent = new Intent(SafeLearnActivity.this, CompletePersonnumberActivity.class);

                startActivity(intent);
                break;
            case R.id.tv_Meetting://
                intent = new Intent(SafeLearnActivity.this, CompletePersonnumberActivity.class);

                startActivity(intent);
                break;
            case R.id.tv_serach://
                mList.add("1");
                mList.add("1");
                mList.add("1");
                dialog(mList);

                if (etInput.getText().length() == 0) {
                    //输入不能为空
                    showToast("输入不能为空");

                } else {

                    if (mList.size() == 0) {
                        showToast("查询结果为空");
                    } else if (mList.size() == 1) {//1.精确到具体的人则跳转
                        intent = new Intent(SafeLearnActivity.this, PersonLearnInfoActivity.class);

                        startActivity(intent);

                    } else {//2.大概搜索则展示列表

                    }

                }

                break;
            default:
                break;


        }
    }

    //搜索Editext监听
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


        }
    };
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private void dialog(List lt) {
        View v = LayoutInflater
                .from(SafeLearnActivity.this).inflate(R.layout.dialog_learn_result_item, null);

        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        TextView name = v.findViewById(R.id.name);
        TextView telephone = v.findViewById(R.id.telephone);

        //姓名、电话
        name.setText("姓名");
        telephone.setText("电话");

        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        LearnSearchAdapter adapter = new LearnSearchAdapter(R.layout.item_learn_search_result, lt);
        rlv.setAdapter(adapter);
        srv.setRefreshing(false);
        srv.setOnRefreshListener(mOnRefreshListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(SafeLearnActivity.this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 400)
                .addDefaultAnimation()
                .show();
    }
    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();

        }
    };
    /**
     * 防止刷新不隐藏
     */
    private void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
            }
        }, 3000);
    }
}
