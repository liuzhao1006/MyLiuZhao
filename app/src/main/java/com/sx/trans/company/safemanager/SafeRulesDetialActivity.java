package com.sx.trans.company.safemanager;

import android.content.Context;
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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.adapter.CommonSafeHidderAdapter;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.company.bean.SafeRulesBean;
import com.sx.trans.company.bean.SafeRulesDetialBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 制度规范详情页
 */
public class SafeRulesDetialActivity extends BaseTransActivity {

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
    private SafeRulesDetialBean bean;
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
    private ImageView fj;
    private List<String> mList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_rules_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("制度规范详情");
            Bundle data = intent.getBundleExtra("SafeRulesDetialActivity");
            if (data != null) {
                bean = (SafeRulesDetialBean) data.getSerializable("SafeRulesDetialActivity.class");
                LogUtils.i(bean);
                l.setText(bean.getInstitutionName());
                initData(bean);
            }
        }
    }

    /**
     * 赋值
     */
    private void initData(SafeRulesDetialBean bean) {
        ltv_name.setText(bean.getInstitutionName());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getSeNo());
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getInstitutionType());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getPublishDate().split(" ")[0]);
        ltv_time.ShowAll();
        ltv_address.setText(bean.getCompanyName());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getPublishPerson());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getExecuteDate().split(" ")[0]);
        ltv_grade.ShowAll();
        if (bean.isUse()) {
            ltv_situation.setText("是");
        } else {
            ltv_situation.setText("否");
        }
        ltv_lmzk.setText(bean.getUUseMan());
        ltv_lmzk.ShowAll();
        ltv_zjyy.setText(bean.getUUseDate().split(" ")[0]);
        ltv_zjyy.ShowAll();
        ltv_jsy.setText(""+Html.fromHtml(bean.getDocumentInfo()));
        ltv_jsy.ShowAll();
        if (!TextUtils.isEmpty(bean.getFileInfo())) {
            fj.setVisibility(View.VISIBLE);
            ltv_down.setVisibility(View.VISIBLE);
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
     * 详情页：展示以下内容；
     * 基本信息：名称、文号、制度类别、发布日期、所属机构、登记人、登记日期、是否作废、作废人、作废日期；
     * 制度内容：（建议默认显示50字，其他内容可点击展开显示）
     * 附件：显示附件名，可点击下载；
     */

    private void initFindView() {
        //名称
        ltv_name = findViewById(R.id.ltv_name);
        //文号
        ltv_type = findViewById(R.id.ltv_type);
        //制度类别
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //发布日期
        ltv_time = findViewById(R.id.ltv_time);
        //所属机构
        ltv_address = findViewById(R.id.ltv_address);
        //登记人
        ltv_sky = findViewById(R.id.ltv_sky);
        //登记日期
        ltv_grade = findViewById(R.id.ltv_grade);
        //是否作废
        ltv_situation = findViewById(R.id.ltv_situation);
        //作废人
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //作废日期
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //制度内容
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //附件：显示附件名称，可点击下载；
        fj=findViewById(R.id.fj);
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
    private void dialog(List<String> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, SafeRulesDetialActivity.this);
        rlv.setAdapter(adapter);
        srv.setRefreshing(false);
        srv.setOnRefreshListener(mOnRefreshListener);

        WindowManager wm = this.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, height / 3)//高度的三分之一
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
