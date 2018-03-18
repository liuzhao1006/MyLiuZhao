package com.sx.trans.company.safemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.bean.SafeAgreementBean;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.widget.font.LzTextView;

import java.util.List;

/**
 * 安全责任书
 */
public class SafeAgreementActivity extends BaseTransActivity implements BaseNetApi {

    private LzTextView ltv_name;
    private LzTextView ltv_type;
    private LzTextView ltv_xingtai;
    private LzTextView ltv_time;
    private LzTextView ltv_address;
    private LzTextView ltv_xgpgxq;
    private LzTextView ltv_down;
    private SafeAgreementBean safeAgreementBean;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe_agreement;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        initFindView();
        l.setText("安全责任书");
        leftGoBack(this);
        CompanyManager manager = new CompanyManager(this, this);
        manager.getAgreement();
        manager.setOnSafeAgreementData(new CompanyManager.SafeAgreementData() {

            @Override
            public void onSafeAgreementData(int what, List<SafeAgreementBean> safeAgreementList) {
                if (safeAgreementList == null || safeAgreementList.size() <= 0) {
                    return;
                }
                safeAgreementBean = safeAgreementList.get(0);
                initData();
            }
        });
    }

    /**
     * 显示内容包括：
     * 基本信息：标题、类型、年度、甲方、乙方‘
     * 描述：指安全责任书内容描述（建议默认显示50字，其他内容可点击展开显示）；
     * 附件：显示附件名，可点击下载；
     */
    private void initFindView() {
        //基本信息
        //标题
        ltv_name = findViewById(R.id.ltv_name);
        //类型
        ltv_type = findViewById(R.id.ltv_type);
        //年度
        ltv_xingtai = findViewById(R.id.ltv_xingtai);
        //甲方
        ltv_time = findViewById(R.id.ltv_time);
        //乙方
        ltv_address = findViewById(R.id.ltv_address);

        //指安全责任书内容描述：
        //指安全责任书内容描述（建议默认显示50字，其他内容可点击展开显示）
        ltv_xgpgxq = findViewById(R.id.ltv_xgpgxq);
        //附件：显示附件名，可点击下载
        ltv_down = findViewById(R.id.ltv_down);


    }

    /**
     * 设置数据
     */
    private void initData() {
        ltv_name.setText(safeAgreementBean.getTitle());
        ltv_name.ShowAll();
        if (safeAgreementBean.getDuty_type().equals("01")) {
            ltv_type.setText("安全承诺书");
            ltv_type.ShowAll();
        } else if (safeAgreementBean.getDuty_type().equals("02")) {
            ltv_type.setText("押运员责任书");
            ltv_type.ShowAll();
        }
        ltv_xingtai.setText(safeAgreementBean.getYears()+"年");
        ltv_time.setText(safeAgreementBean.getFirst_party());
        ltv_time.ShowAll();
        ltv_address.setText(safeAgreementBean.getSecond_party_name());
        ltv_address.ShowAll();
        ltv_xgpgxq.setText(""+Html.fromHtml(safeAgreementBean.getContent()));
        ltv_xgpgxq.ShowAll();

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
}
