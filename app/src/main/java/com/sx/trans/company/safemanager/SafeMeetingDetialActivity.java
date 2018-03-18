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
import android.widget.TextView;
import android.widget.Toast;

import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.activity.SpecialTargerDetialActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.company.adapter.CommonSafeMeetingAdapter;
import com.sx.trans.company.adapter.CommonSafeTargerAdapter;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.company.bean.SpecialTargerBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 会议详情
 */
public class SafeMeetingDetialActivity extends BaseTransActivity {

    private LzTextView tv_swssqk;
    private LzTextView ltv_down;
    private LzTextView ltv_down_count;
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
    private LzTextView ltv_cph;
    private LzTextView ltv_yszh;
    private SafeMeetingBean bean;

    private List<String> mList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_meeting_detial;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("会议详情");
        initFindView();
        Intent intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            Bundle data = intent.getBundleExtra("SafeMeetingDetialActivity");
            if (data != null) {
                bean = (SafeMeetingBean) data.getSerializable("SafeMeetingDetialActivity.class");
                initData(bean);
            }
        }


    }

    private void initData(SafeMeetingBean bean) {
        ltv_name.setText(bean.getTitle());
        ltv_name.ShowAll();
        ltv_type.setText(bean.getMeeting_no());
        ltv_type.ShowAll();
        /**
         * 会议类型
         * 1	专题讲座	AQHYLX	安全会议类型
         2	专项安全会议	AQHYLX	安全会议类型
         3	综合形式	AQHYLX	安全会议类型
         4	领导小组会议	AQHYLX	安全会议类型
         5	安全工作例会	AQHYLX	安全会议类型
         6	安全生产委员会会议	AQHYLX	安全会议类型

         */
        switch (Integer.parseInt(bean.getMeeting_type())) {
            case 1:
                ltv_xingtai.setText("专题讲座");
                break;
            case 2:
                ltv_xingtai.setText("专项安全会议");
                ltv_xingtai.ShowAll();
                break;
            case 3:
                ltv_xingtai.setText("综合形式");
                break;
            case 4:
                ltv_xingtai.setText("领导小组会议");
                ltv_xingtai.ShowAll();
                break;
            case 5:
                ltv_xingtai.setText("安全工作例会");
                ltv_xingtai.ShowAll();
                break;
            case 6:
                ltv_xingtai.setText("安全生产委员会会议");
                ltv_xingtai.ShowAll();
                break;
        }
        /**
         * 会议形式
         * 1	公司会议	AQHYXS	安全会议形式
         2	部门会议	AQHYXS	安全会议形式
         */
        switch (Integer.parseInt(bean.getMeeting_form())) {
            case 1:
                ltv_time.setText("公司会议");
                break;
            case 2:
                ltv_time.setText("部门会议");
                break;
        }
        ltv_address.setText(bean.getMeeting_date());
        ltv_address.ShowAll();
        ltv_sky.setText(bean.getEnd_date());
        ltv_sky.ShowAll();
        ltv_grade.setText(bean.getHolder());
        ltv_grade.ShowAll();
        ltv_situation.setText(bean.getRecorder());
        ltv_situation.ShowAll();
        ltv_lmzk.setText(bean.getFore_number() + "个");
        ltv_zjyy.setText(bean.getCome_numer() + "个");
        ltv_jsy.setText(bean.getCompany_name_meet());
        ltv_jsy.ShowAll();
        ltv_cyzglb.setText(bean.getMeeting_month());
        ltv_cyzglb.ShowAll();
        ltv_cyzgzh.setText(bean.getAddress());
        ltv_cyzgzh.ShowAll();
        ltv_cph.setText(bean.getContent());
        ltv_cph.ShowAll();
        ltv_yszh.setText(bean.getRecorder());
        ltv_yszh.ShowAll();
        final List<SafeMeetingBean.MeetManageRelInfosBean> meetManageRelInfos = bean.getMeetManageRelInfos();

        if (meetManageRelInfos != null && meetManageRelInfos.size() > 0) {
            tv_swssqk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //展示信息
                    if (meetManageRelInfos.size() > 0) {
                        dialog2(meetManageRelInfos);
                    } else {
                        Toast.makeText(SafeMeetingDetialActivity.this, "没有数据......", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
        } else {
            ltv_down.setVisibility(View.GONE);
        }
    }


    private void initFindView() {
        //会议名称
        ltv_name = findViewById(R.id.ltv_name);
        //会议编号
        ltv_type = findViewById(R.id.ltv_type);
        //会议类型
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //会议形式
        ltv_time = findViewById(R.id.ltv_time);
        //会议日期
        ltv_address = findViewById(R.id.ltv_address);
        //截止日期
        ltv_sky = findViewById(R.id.ltv_sky);
        //主持人
        ltv_grade = findViewById(R.id.ltv_grade);
        //记录人
        ltv_situation = findViewById(R.id.ltv_situation);
        //应到人数
        ltv_lmzk = findViewById(R.id.ltv_lmzk);
        //实到人数
        ltv_zjyy = findViewById(R.id.ltv_zjyy);
        //所属机构
        ltv_jsy = findViewById(R.id.ltv_jsy);
        //会议月份
        ltv_cyzglb = findViewById(R.id.ltv_cyzglb);
        //会议地点
        ltv_cyzgzh = findViewById(R.id.ltv_cyzgzh);
        //会议内容
        ltv_cph = findViewById(R.id.ltv_cph);
        //备注
        ltv_yszh = findViewById(R.id.ltv_yszh);
        //参会人员信息（参会人员列表）
        tv_swssqk = findViewById(R.id.tv_swssqk);
        //附件：显示附件名，可点击下载
        ltv_down = findViewById(R.id.ltv_down);
        //附件：显示附件名，可点击下载 数量
        ltv_down_count = findViewById(R.id.ltv_down_count);
    }

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;

    /**
     * 展示指标项
     *
     * @param mList
     */
    private void dialog2(List<SafeMeetingBean.MeetManageRelInfosBean> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_meet_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        TextView tv_mark_name = v.findViewById(R.id.tv_mark_name);
        TextView tv_mark_count = v.findViewById(R.id.tv_mark_count);
        TextView tv_mark_point = v.findViewById(R.id.tv_mark_point);
        TextView tv_mark_company = v.findViewById(R.id.tv_mark_company);
        //姓名、岗位、是否参会、所属机构
        tv_mark_name.setText("姓名");
        tv_mark_count.setText("岗位");
        tv_mark_point.setText("是否参会");
        tv_mark_company.setText("所属机构");
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonSafeMeetingAdapter adapter = new CommonSafeMeetingAdapter(R.layout.item_listview_meeting_dialog, mList, this);
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
    /**
     * 会议人员防止刷新不隐藏
     */
    private void refreshData1() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (downSrv.isRefreshing()) {
                    downSrv.setRefreshing(false);
                }

            }
        }, 3000);
    }

    private SwipeRefreshLayout downSrv;
    private SwipeMenuRecyclerView downRlv;
    /**
     * 会议人员刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListeners = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData1();

        }
    };

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
        downRlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, SafeMeetingDetialActivity.this);
        downRlv.setAdapter(adapter);
        downSrv.setRefreshing(false);
        downSrv.setOnRefreshListener(mOnRefreshListeners);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 600)
                .addDefaultAnimation()
                .show();
    }


}
