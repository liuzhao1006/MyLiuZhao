package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.adapter.SafeInputPdfAdapter;
import com.sx.trans.company.bean.SafeInputBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全投入详情
 */
public class SafeInputDetialActivity extends BaseTransActivity {

    private SafeInputBean bean;
    private LzTextView ltv_name;
    private LzTextView ltv_type;
    private LzTextView ltv_xingtai;
    private LzTextView ltv_time;
    private LzTextView ltv_address;
    private LzTextView ltv_sky;
    private LzTextView ltv_grade;
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
    private List<String> mList = new ArrayList<>();
    private String time;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_input_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("安全投入");
            Bundle data = intent.getBundleExtra("SafeInputDetialActivity");
            if (data != null) {
                bean = (SafeInputBean) data.getSerializable("SafeInputDetialActivity.class");
                LogUtils.i(bean);
                initData(bean);
            }
        }
    }

    /**
     * 赋值
     */
    private void initData(SafeInputBean bean) {
        ltv_name.setText(bean.getYear() + "年");
        ltv_type.setText(bean.getMonth() + "月");
        ltv_xingtai.setText(bean.getRatio());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getAmount() + "万元");
        ltv_address.setText(bean.getCompany_name());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getRemark());
        ltv_sky.ShowAll();
        ltv_grade.setText(Html.fromHtml(bean.getPurpose()));
        ltv_grade.ShowAll();

        if (!TextUtils.isEmpty(bean.getFile_url())) {
            ltv_down.setVisibility(View.VISIBLE);
            ltv_down_count.setVisibility(View.VISIBLE);
            String[] item = bean.getFile_url().split(",");
            time = bean.getAmount();
            ltv_down_count.setText(item.length + "条");

            if (mList.size() > 0) {
                mList.clear();

            }
            for (int i = 0; i < item.length; i++) {
                mList.add(item[i]);
            }
            ltv_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog(mList, time);
                }
            });


        }

    }

    /**
     * 初始化控件
     * 详情页：展示以下内容；
     * 基本信息：名称、文号、制度类别、发布日期、所属机构、登记人、登记日期、是否作废、作废人、作废日期；
     * 制度内容：（建议默认显示50字，其他内容可点击展开显示）
     * 附件：显示附件名，可点击下载；
     */

    private void initFindView() {
        //投入年份
        ltv_name = findViewById(R.id.ltv_name);
        //投入月份
        ltv_type = findViewById(R.id.ltv_type);
        //投入比例
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //投入金额（万元）
        ltv_time = findViewById(R.id.ltv_time);
        //所属机构
        ltv_address = findViewById(R.id.ltv_address);
        //备注
        ltv_sky = findViewById(R.id.ltv_sky);
        //用途描述
        ltv_grade = findViewById(R.id.ltv_grade);
        //附件：显示附件名称，可点击下载；
        ltv_down = findViewById(R.id.ltv_down);
        //附件：显示附件名称，可点击下载；数量
        ltv_down_count = findViewById(R.id.ltv_down_count);

    }

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;

    /**
     * 图表弹窗展示
     *
     * @param mList
     */
    private void dialog(List<String> mList, String time) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        SafeInputPdfAdapter adapter = new SafeInputPdfAdapter(R.layout.item_rule_pdf_dialog, mList, time, SafeInputDetialActivity.this);
        rlv.setAdapter(adapter);
        srv.setRefreshing(false);
        srv.setOnRefreshListener(mOnRefreshListener);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 600)
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
