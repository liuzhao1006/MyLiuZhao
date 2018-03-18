package com.sx.trans.transport.dynamicMonitoring.activity;

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
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.bean.SafeRulesBean;
import com.sx.trans.company.safemanager.SafeRulesDetialActivity;
import com.sx.trans.transport.dynamicMonitoring.bean.LawBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 地方法规政策详情页
 */
public class StudentEnterPriseDetialActivity extends BaseTransActivity {
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
    private LawBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_enter_prise_detial;
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
                bean = (LawBean) data.getSerializable("SafeRulesDetialActivity.class");
                LogUtils.i(bean);
                l.setText(bean.getLaws_name());
                initData(bean);
            }
        }
    }

    /**
     * 赋值
     */
    private void initData(LawBean bean) {
        ltv_name.setText(bean.getLaws_name());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getAdd_time().split(" ")[0]);
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getLaws_type_name());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getDepartment_name());
        ltv_time.ShowAll();
        ltv_address.setText(bean.getRemark());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getUse_date().split(" ")[0]);
        ltv_sky.ShowAll();
        if (bean.isLaws_type()) {
            ltv_grade.setText("有效");
        } else {
            ltv_grade.setText("无效");
        }

        if (!TextUtils.isEmpty(bean.getFile_url())) {
            ltv_down.setVisibility(View.VISIBLE);
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


        }

    }

    /**
     * 初始化控件
     * 法律法规编号： id; （详情传参）
     * 法规名称： laws_name;
     * 发布日期：add_time;
     * 法规类型：laws_type_name;
     * 发布机构：department_name;
     * 备注：remark;
     * 启用日期：use_date;
     * 法规状态：laws_type; （有效：true，无效：false）
     * <p>
     * 法规文件：file_url; （格式：多个上传文件信息以逗号隔开，上传文件信息包括文件标题和文件路径，以双冒号隔开）
     */

    private void initFindView() {
        //法规名称
        ltv_name = findViewById(R.id.ltv_name);
        //发布日期
        ltv_type = findViewById(R.id.ltv_type);
        //法规类型
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //发布机构
        ltv_time = findViewById(R.id.ltv_time);
        //备注
        ltv_address = findViewById(R.id.ltv_address);
        //启用日期
        ltv_sky = findViewById(R.id.ltv_sky);
        //法规状态
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
    private void dialog(List<String> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, StudentEnterPriseDetialActivity.this);
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
