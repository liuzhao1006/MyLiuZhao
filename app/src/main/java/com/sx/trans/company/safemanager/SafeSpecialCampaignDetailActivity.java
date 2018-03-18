package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lz.cloud.widget.LzBar;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.bean.SafeRulesDetialBean;
import com.sx.trans.company.bean.SafeSpiecialCampaignBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 专项活动
 */
public class SafeSpecialCampaignDetailActivity extends BaseTransActivity implements BaseNetApi {
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
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
    private LzTextView ltv_jsy;
    private LzTextView ltv_cyzglb;
    private SafeSpiecialCampaignBean bean;
    private ImageView iv_pie;
    private List<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LzBar bar = new LzBar(this);
//        bar.setTransparentBar(getResources().getColor(R.color.blue), 100);
        bar.setColorBar(getResources().getColor(R.color.title_ground_normal),0);
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("SafeSpecialCampaignActivity");
        if (data != null) {
            bean = (SafeSpiecialCampaignBean) data.getSerializable("SafeSpecialCampaignActivity.class");
            LogUtils.i(bean);

            initData();
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_special_campaign;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        iv_pie=findViewById(R.id.iv_pie);
        initFindView();
        l.setText("专项活动详情");
        leftGoBack(this);
//        CompanyManager manager = new CompanyManager(this, this);
//        manager.getSpecialCampaign();
//        manager.setOnSafeSpecialCampaignData(new CompanyManager.SafeSpecialCampaignData() {
//            @Override
//            public void onSafeSpecialCampaignData(int what, List<SafeSpiecialCampaignBean> safeSpecialCampaignList) {
//                if (safeSpecialCampaignList == null || safeSpecialCampaignList.size() <= 0) {
//
//                    return;
//                }
//                bean = safeSpecialCampaignList.get(0);
//                initData();
//
//            }
//        });

    }

    /**
     * 基本信息：
     * 主题、
     * 开始日期、
     * 结束日期、短信通知、是否布置、是否落实、所属机构、目的；
     * 报送信息：定期报送资料、报送资料周期、定期报送时间、报送资料描述；
     * 附件：显示附件名，可点击下载；
     */
    private void initFindView() {
        //基本信息
        //主题
        ltv_name = findViewById(R.id.ltv_name);
        //开始日期
        ltv_type = findViewById(R.id.ltv_type);
        //结束日期
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //短信通知
        ltv_time = findViewById(R.id.ltv_time);
        //是否布置
        ltv_address = findViewById(R.id.ltv_address);
        //是否落实
        ltv_sky = findViewById(R.id.ltv_sky);
        //所属机构
        ltv_grade = findViewById(R.id.ltv_grade);
        //目的
        ltv_situation = findViewById(R.id.ltv_situation);
        //报送信息
        //定期报送资料
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //报送资料周期
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //定期报送时间
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //报送资料描述
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //附件：显示附件名，可点击下载
        ltv_down = findViewById(R.id.ltv_down);
        ltv_down_count = findViewById(R.id.ltv_down_count);

    }

    /**
     * 设置数据
     */
    private void initData() {
        ltv_name.setText(bean.getTitle());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getStarttime().split(" ")[0]);
        ltv_type.ShowAll();
        ltv_xingtai.setText(bean.getEndtime().split(" ")[0]);
        ltv_xingtai.ShowAll();
        if (bean.isIsmsn()) {
            ltv_time.setText("是");
        } else {
            ltv_time.setText("否");
        }

        if (bean.isIsarrangement()) {
            ltv_address.setText("是");
        } else {
            ltv_address.setText("否");
        }

        if (bean.isIsimplement()) {
            ltv_sky.setText("是");
        } else {
            ltv_sky.setText("否");
        }

        if (bean.isIsregular()) {
            ltv_lmzk.setText("是");
        } else {
            ltv_lmzk.setText("否");
        }
        ltv_grade.setText(bean.getCompany_name());
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getPurpose());
        ltv_situation.ShowAll();
        ltv_zjyy.setText(bean.getRegularcycle());
        ltv_zjyy.ShowAll();
        ltv_jsy.setText(bean.getRegulardate().split(" ")[0]);
        ltv_jsy.ShowAll();
        ltv_cyzglb.setText(""+ Html.fromHtml(bean.getRegulardecription()));
        ltv_cyzglb.ShowAll();
        if (TextUtils.isEmpty(bean.getFile_url())) {
            showToast("暂无下载资料...");
        } else {
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
        iv_pie.setVisibility(View.VISIBLE);
    }
    /**
     * 图表弹窗展示
     *
     * @param mList
     */
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private void dialog(List<String> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, SafeSpecialCampaignDetailActivity.this);
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
