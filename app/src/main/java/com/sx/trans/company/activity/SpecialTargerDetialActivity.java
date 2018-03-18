package com.sx.trans.company.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonDistributionAdapter;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.adapter.CommonSafeTargerAdapter;
import com.sx.trans.company.bean.CommpanyRiskDistributionBean;
import com.sx.trans.company.bean.SpecialTargerBean;
import com.sx.trans.company.safemanager.SafeRulesDetialActivity;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpecialTargerDetialActivity extends BaseTransActivity {

    private SpecialTargerBean bean;

    private LzTextView ltv_name;
    private LzTextView ltv_type;
    private LzTextView ltv_xingtai;
    private LzTextView ltv_time;
    private LzTextView ltv_address;
    private LzTextView ltv_sky;
    private LzTextView ltv_grade;
    private LzTextView ltv_situation;
    private LzTextView ltv_lmzk;
    private LzTextView ltv_zjyy;
    private LzTextView ltv_jsy;
    private LzTextView ltv_cyzgzh;
    private LzTextView ltv_cyzglb;
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
    private ImageView fj;
    private LzTextView ltv_mark_name;
    private List<String> mList = new ArrayList<>();
    private List<SpecialTargerBean.KpiInfosBean> kpiInfos;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_special_targer_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("安全目标");
            Bundle data = intent.getBundleExtra("SpecialTargerDetialActivity");
            if (data != null) {
                bean = (SpecialTargerBean) data.getSerializable("SpecialTargerDetialActivity.class");
                initData(bean);
            }
        }
    }

    /**
     * 设置数据
     */
    private void initData(SpecialTargerBean bean) {
        ltv_name.setText(bean.getPointName());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getFormulateDate());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getYears()+"年");
        ltv_time.setText(bean.getFileTitle());
        ltv_time.ShowAll();
        ltv_address.setText(bean.getQuestionMark());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getIssueDate());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getCompanyName());
        ltv_grade.ShowAll();
        if (bean.isUse()) {
            ltv_situation.setText("是");
        } else {
            ltv_situation.setText("否");
        }

        ltv_lmzk.setText(bean.getAccidentProbability());
        ltv_lmzk.ShowAll();
        ltv_zjyy.setText(bean.getAccidentDeathRate());
        ltv_zjyy.ShowAll();
        ltv_jsy.setText(bean.getPropertyLoss()+"元");
        ltv_cyzglb.setText(bean.getPersonnelInjuryRate());
        ltv_cyzglb.ShowAll();
        ltv_cyzgzh.setText(""+Html.fromHtml(bean.getFileContents()));
        ltv_cyzgzh.ShowAll();

        kpiInfos = bean.getKpiInfos();
        //显示自定义指标项
        if (kpiInfos != null && kpiInfos.size() > 0) {
            ltv_mark_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //展示信息
                    if (kpiInfos.size() > 0) {
                        dialog2(kpiInfos);
                    } else {
                        Toast.makeText(SpecialTargerDetialActivity.this, "没有数据......", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //下载
        if (!TextUtils.isEmpty(bean.getFileInfo())) {
            ltv_down.setVisibility(View.VISIBLE);
            fj.setVisibility(View.VISIBLE);
            ltv_down_count.setVisibility(View.VISIBLE);
            String[] item = bean.getFileInfo().split(",");
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
                    dialog(mList);
                }
            });


        }

    }


    /**
     * 初始化控件
     */
    private void initFindView() {
        //目标名称
        ltv_name = findViewById(R.id.ltv_name);
        //制定日期
        ltv_type = findViewById(R.id.ltv_type);
        //年度
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //文件标题
        ltv_time = findViewById(R.id.ltv_time);
        //发文号
        ltv_address = findViewById(R.id.ltv_address);
        //签发日期
        ltv_sky = findViewById(R.id.ltv_sky);
        //所属机构
        ltv_grade = findViewById(R.id.ltv_grade);
        //是否启用
        ltv_situation = findViewById(R.id.ltv_situation);
        //年度责任事故率<(单位：次/百万车公里)
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //年度责任事故死亡率<=(单位：人/百万车公里)
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //财产损失<(单位：万元/百万车公里)
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //人员受伤率<(单位：万元/百万车公里)
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);

        //指标项
        ltv_mark_name = findViewById(R.id.ltv_mark_name);
        //内容
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        fj = findViewById(R.id.fj);
        //附件：显示附件名称，可点击下载；
        ltv_down = findViewById(R.id.ltv_down);
        //附件：显示附件名称，可点击下载；数量
        ltv_down_count = findViewById(R.id.ltv_down_count);
    }

    private SwipeRefreshLayout downSrv;
    private SwipeMenuRecyclerView downRlv;

    /**
     * 图表弹窗展示
     *
     * @param mList
     */
    private void dialog(List<String> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        downSrv = v.findViewById(R.id.ref_rlv);
        downRlv = v.findViewById(R.id.rlv);
        downRlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        downRlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        downRlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        downRlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, SpecialTargerDetialActivity.this);
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
                if (downSrv.isRefreshing()) {
                    downSrv.setRefreshing(false);
                }
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
            }
        }, 3000);
    }

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;

    /**
     * 展示指标项
     *
     * @param mList
     */
    private void dialog2(List<SpecialTargerBean.KpiInfosBean> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        TextView tv_mark_name = v.findViewById(R.id.tv_mark_name);
        TextView tv_mark_count = v.findViewById(R.id.tv_mark_count);
        TextView tv_mark_point = v.findViewById(R.id.tv_mark_point);
        tv_mark_name.setText("指标项名称");
        tv_mark_count.setText("开始时间");
        tv_mark_point.setText("结束时间");
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonSafeTargerAdapter adapter = new CommonSafeTargerAdapter(R.layout.item_listview_dialog, mList, this);
        rlv.setAdapter(adapter);
        srv.setRefreshing(false);
        srv.setOnRefreshListener(mOnRefreshListener);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 1000)
                .addDefaultAnimation()
                .show();

    }


}
