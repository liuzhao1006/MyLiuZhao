package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonDistributionAdapter;
import com.sx.trans.company.adapter.CommonSafeHidderAdapter;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.company.bean.CommpanyRiskDistributionBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

/**
 * 隐患详情
 */
public class SafeHidderDetialActivity extends BaseTransActivity {

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
    private LzTextView ltv_cyzglb;
    private LzTextView ltv_cyzgzh;
    private LzTextView ltv_cph;
    private LzTextView ltv_yszh;
    private LzTextView ltv_cx;
    private LzTextView ltv_hdrs;
    private LzTextView ltv_szrshu;
    private LzTextView ltv_wxhxppm;
    private LzTextView ltv_yxxl;

    private CommpanyHidderDrangBean bean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_hidder_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            l.setText("隐患详情");
            Bundle data = intent.getBundleExtra("SafeHidderDetialActivity");
            if (data != null) {
                bean = (CommpanyHidderDrangBean) data.getSerializable("SafeHidderDetialActivity.class");
                LogUtils.i(bean);
                initData(bean);
                final List<CommpanyHidderDrangBean.CheckItemsBean> checkItems = bean.getCheckItems();

                if (checkItems.size() > 0) {
                    ltv_jsy.setText(checkItems.size() + "条");
                    Drawable img = this.getResources().getDrawable(R.drawable.right);
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    ltv_jsy.setCompoundDrawables(null, null, img, null); //设置左图标
                    ltv_jsy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog(checkItems);
                        }
                    });
                } else {
                    showToast("无信息");
                }
            }
        }
    }

    /**
     * 赋值
     * 详情页：展示以下内容；
     * 排查信息：年度、月份、受检机构、受检对象、检查类型、检查内容；
     * 是否存在隐患（下拉框，是、否）*、检查人*、检查日期、检查人数；检查项目（多条）：检查项名称、情况记录；
     * 通知信息：通知日期、整改期限、通知次数；
     * 整改信息：整改日期、方法和措施；
     * 复查信息：复查日期、复查人、是否合格、复查意见；
     * 注：通知信息、整改信息、复查信息 可能有多条，详情中显示最后一条；
     */
    private void initData(CommpanyHidderDrangBean bean) {
        ltv_name.setText(bean.getYear_plan()+"年");
        ltv_type.setText(bean.getMonth_plan()+"月");
        ltv_xingtai.setText(bean.getCompany_name_plan());
        ltv_xingtai.ShowAll();
        ltv_time.setText(bean.getObject_plan());
        ltv_time.ShowAll();
        ltv_address.setText(bean.getRemark_plan());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getPurpose_plan());
        ltv_sky.ShowAll();
        if (bean.isTrouble()) {
            ltv_grade.setText("是");
        } else {
            ltv_grade.setText("否");
        }
        ltv_situation.setText(bean.getCheck_user());
        ltv_situation.ShowAll();
        ltv_lmzk.setText(bean.getCheck_date());
        ltv_lmzk.ShowAll();
        ltv_zjyy.setText(bean.getPerson_number()+"个");


        ltv_cyzglb.setText(bean.getNotice_date().split(" ")[0]);
        ltv_cyzglb.ShowAll();
        ltv_cyzgzh.setText(bean.getDeadline_count());
        ltv_cyzgzh.ShowAll();
        ltv_cph.setText(bean.getNotice_count()+"次");
        ltv_yszh.setText(bean.getManage_date());
        ltv_yszh.ShowAll();
        ltv_cx.setText(bean.getMethod());
        ltv_cx.ShowAll();
        ltv_hdrs.setText(bean.getCreate_date_ck());
        ltv_hdrs.ShowAll();
        ltv_szrshu.setText(bean.getRecheck_user());
        ltv_szrshu.ShowAll();
        if (bean.isQualified()) {
            ltv_wxhxppm.setText("是");
        } else {
            ltv_wxhxppm.setText("否");
        }
        ltv_yxxl.setText(bean.getReopinion());
        ltv_yxxl.ShowAll();
    }

    /**
     * 初始化控件
     */
    private void initFindView() {
        //年度
        ltv_name = findViewById(R.id.ltv_name);
        //月份
        ltv_type = findViewById(R.id.ltv_type);
        //受检机构
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //受检对象
        ltv_time = findViewById(R.id.ltv_time);
        //检查类型
        ltv_address = findViewById(R.id.ltv_address);
        //检查内容
        ltv_sky = findViewById(R.id.ltv_sky);
        //是否存在隐患
        ltv_grade = findViewById(R.id.ltv_grade);
        //检查人
        ltv_situation = findViewById(R.id.ltv_situation);
        //检查日期
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //检查人数
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //检查项目
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //通知日期
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //整改日期
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //通知次数
        ltv_cph = findViewById(R.id.ltv_cph);
        //整改日期
        ltv_yszh = findViewById(R.id.ltv_yszh);
        //方法和措施
        ltv_cx = findViewById(R.id.ltv_cx);

        //整改信息：整改日期、方法和措施；
        //     复查信息：复查日期、复查人、是否合格、复查意见；
        //复查日期
        ltv_hdrs = findViewById(R.id.ltv_hdrs);
        //复查人
        ltv_szrshu = findViewById(R.id.ltv_szrshu);
        //是否合格
        ltv_wxhxppm = findViewById(R.id.ltv_wxhxppm);
        //复查意见
        ltv_yxxl = findViewById(R.id.ltv_yxxl);
    }

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;

    /**
     * 图表弹窗展示
     *
     * @param mList
     */
    private void dialog(List<CommpanyHidderDrangBean.CheckItemsBean> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_safehidder_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonSafeHidderAdapter adapter = new CommonSafeHidderAdapter(R.layout.item_safehidder_dialog, mList, SafeHidderDetialActivity.this);
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
