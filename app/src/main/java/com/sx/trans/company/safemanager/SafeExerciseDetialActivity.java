package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.adapter.CommonSafePractiseAdapter;
import com.sx.trans.company.bean.CommpanySafeExerciseBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全演习详情页
 */
public class SafeExerciseDetialActivity extends BaseTransActivity {
    private ImageView fj;
    private CommpanySafeExerciseBean bean;
    private LzTextView ltv_name;
    private LzTextView ltv_type;
    private LzTextView ltv_xingtai;
    private LzTextView ltv_time;
    private LzTextView ltv_address;
    private LzTextView ltv_sky;
    private LzTextView ltv_grade;
    private LzTextView ltv_situation;
    private LzTextView ltv_lmzk;
    private LzTextView tv_swssqk;
    private LzTextView ltv_ylgcxq;
    private LzTextView ltv_xgpgxq;
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
    private List<String> mList = new ArrayList<>();
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_exercise_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        Intent intent = getIntent();
        initFindView();
        leftGoBack(this);
        if (intent != null) {
            l.setText("安全演习详情");
            Bundle data = intent.getBundleExtra("SafeExerciseDetialActivity");
            if (data != null) {
                bean = (CommpanySafeExerciseBean) data.getSerializable("SafeExerciseDetialActivity.class");
                LogUtils.i(bean.toString());
                initData();
            }
        }

    }

    /**
     * 初始化控件
     * 详情页：展示以下内容；
     * 基本信息：演练名称、应急预案、应急队伍、演练类型、指挥人、演练日期、演练地点、参与人数、所属机构；
     * 救援队伍信息（人员列表）：姓名、电话、职责；
     * 演练过程：演练过程内容（建议默认显示50字，其他内容可点击展开显示）；
     * 效果评估：效果评估内容（建议默认显示50字，其他内容可点击展开显示）；
     * 附件：显示附件名，可点击下载；
     */
    private void initFindView() {
        //基本信息
        //演练名称
        ltv_name = findViewById(R.id.ltv_name);
        //应急预案
        ltv_type = findViewById(R.id.ltv_type);
        //应急队伍
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //演练类型
        ltv_time = findViewById(R.id.ltv_time);
        //指挥人
        ltv_address = findViewById(R.id.ltv_address);
        //演练日期
        ltv_sky = findViewById(R.id.ltv_sky);
        //演练地点
        ltv_grade = findViewById(R.id.ltv_grade);
        //参与人数
        ltv_situation = findViewById(R.id.ltv_situation);
        //所属机构
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //救援队伍信息（人员列表）
        //救援队伍信息（人员列表）
        tv_swssqk = findViewById(R.id.tv_swssqk);

        //演练过程
        //演练过程内容（建议默认显示50字，其他内容可点击展开显示）
        ltv_ylgcxq = findViewById(R.id.ltv_ylgcxq);
        //效果评估：
        //效果评估内容（建议默认显示50字，其他内容可点击展开显示）
        ltv_xgpgxq = findViewById(R.id.ltv_xgpgxq);
        //附件：显示附件名，可点击下载
        ltv_down = findViewById(R.id.ltv_down);
        tv_swssqk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2(bean.getPersonList());
            }
        });
        fj = findViewById(R.id.fj);
        //附件：显示附件名称，可点击下载；
        ltv_down = findViewById(R.id.ltv_down);
        //附件：显示附件名称，可点击下载；数量
        ltv_down_count = findViewById(R.id.ltv_down_count);



    }

    /**
     * 设置数据
     */
    private void initData() {
        ltv_name.setText(bean.getRehearsalname_drill());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getPlanname());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getTeamname());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getRehearsaltype_drill());
        ltv_time.ShowAll();
        ltv_address.setText(bean.getCommandperson_drill());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getRehearsaldate_drill().split(" ")[0]);
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getRehearsaladdress_drill());
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getParticipantusers_drill()+"个");
        ltv_lmzk.setText(bean.getCompany_name());
        ltv_lmzk.ShowAll();
        ltv_ylgcxq.setText(bean.getRehearsaldescription_drill());
        ltv_ylgcxq.ShowAll();
        ltv_xgpgxq.setText(bean.getEffectivenessevaluation_drill());
        ltv_xgpgxq.ShowAll();
     //下载
        if (!TextUtils.isEmpty(bean.getFile_url())) {
            ltv_down.setVisibility(View.VISIBLE);
            fj.setVisibility(View.VISIBLE);
            ltv_down_count.setVisibility(View.VISIBLE);
            String[] item = bean.getFile_url().split(",");
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

    }        }

    /**
     * 展示指标项
     *
     * @param mList
     */
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private void dialog2(List<CommpanySafeExerciseBean.PersonListBean> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_safe_practise_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        TextView tv_mark_name = v.findViewById(R.id.tv_mark_name);
        TextView tv_mark_count = v.findViewById(R.id.tv_mark_count);
        TextView tv_mark_point = v.findViewById(R.id.tv_mark_point);

        //姓名、岗位、是否参会、所属机构
        tv_mark_name.setText("姓名");
        tv_mark_count.setText("电话");
        tv_mark_point.setText("职责");

        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonSafePractiseAdapter adapter = new CommonSafePractiseAdapter(R.layout.item_listview_safe_practise_dialog, mList, this);
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
    /**
     * 图表弹窗展示
     *
     * @param mList
     */
    private SwipeRefreshLayout downSrv;
    private SwipeMenuRecyclerView downRlv;
    private void dialog(List<String> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        downSrv = v.findViewById(R.id.ref_rlv);
        downRlv = v.findViewById(R.id.rlv);
        downRlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        downRlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        downRlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        downRlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, SafeExerciseDetialActivity.this);
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
}
