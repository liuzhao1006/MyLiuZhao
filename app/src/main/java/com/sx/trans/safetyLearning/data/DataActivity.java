package com.sx.trans.safetyLearning.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.sx.lzlibrary.base.BaseActivity;
import com.sx.lzlibrary.utils.DrableTextUtils;
import com.sx.trans.R;
import com.sx.trans.safetyLearning.data.bean.DataBean;
import com.sx.trans.safetyLearning.data.bean.DataFragmentBean;
import com.sx.trans.safetyLearning.data.manager.DataManager;
import com.sx.trans.safetyLearning.ui.SafeLearnActivity;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.GetDataUtils;
import com.sx.trans.widget.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends BaseActivity implements View.OnClickListener, BaseNetApi {
    private List<DataFragmentBean.ReturnDateBean> list = new ArrayList();
    private LzTextView people_sum,//总人数
            baisi_hours,// 基础学时
            people_finish_sum,//  人云完成数
            people_nofinish_sum,// 未完成人数
            people_finish,//  人员完成比
            Hours_finish;//  学时完成比
    private WindowManager manager;
    private int SumPeople;
    private int width;
    private int position = 0;
    private LzTextView base_name, base_type;//  名称和排名
    private LzTextView center_tv;
    private String date;
    private String CompanyId;
    private SafeLearnActivity activity;
    private DataManager dataManager;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        center_tv = findViewById(R.id.center_tv);
        date = GetDataUtils.getTime();
        center_tv.setText(date);
        base_name = findViewById(R.id.base_name);
        base_type = findViewById(R.id.base_type);
        center_tv = findViewById(R.id.center_tv);
        Drawable drawable1 = this.getResources().getDrawable(R.drawable.xiala_baise);
        DrableTextUtils.setTextDrable(this, center_tv, drawable1, "rigth");
        center_tv.setText(GetDataUtils.getTime() + "");
        width = manager.getDefaultDisplay().getHeight();
        people_sum = findViewById(R.id.people_sum);
        UiUtils.setWidthanHigth(people_sum, width / 5);
        baisi_hours = findViewById(R.id.baisi_hours);
        UiUtils.setWidthanHigth(baisi_hours, width / 5);
        people_finish_sum = findViewById(R.id.people_finish_sum);
        UiUtils.setWidthanHigth(people_finish_sum, width / 5);
        people_nofinish_sum = findViewById(R.id.people_nofinish_sum);
        UiUtils.setWidthanHigth(people_nofinish_sum, width / 5);
        people_finish = findViewById(R.id.people_finish);
        UiUtils.setWidthanHigth(people_finish, width / 5);
        Hours_finish = findViewById(R.id.Hours_finish);
        UiUtils.setWidthanHigth(Hours_finish, width / 5);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dataManager = new DataManager(DataActivity.this, this);
        dataManager.getData(date, PrefUtils.getString(DataActivity.this, LoginConfig.COMPANY, null), PrefUtils.getString(DataActivity.this, LoginConfig.USERTYPE, null));

    }


    @Override
    public void netStart() {

    }

    @Override
    public void netStop() {

    }

    @Override
    public void netSuccessed(int what, String data) {
        String s = data.replace("[", "");
        String d = s.replace("]", "");
        DataBean dataBean = JSON.parseObject(d, DataBean.class);
        people_sum.setText("总人数：" + "\n" + dataBean.getStuNum() + "人");
        baisi_hours.setText("基础学时：" + "\n" + dataBean.getBasicsHours() + "分钟");
        people_finish_sum.setText("完成人数：" + "\n" + dataBean.getFinish() + "人");
        people_nofinish_sum.setText("未完成人数：" + "\n" + dataBean.getNoFinish() + "人");
        double x = Double.valueOf(dataBean.getFinishRatio()) * 100;
        people_finish.setText("人员完成率：" + "\n" + (int) x + "%");
        double p = Double.valueOf(dataBean.getHoursRatio()) * 100;
        Hours_finish.setText("学时完成率：" + "\n" + (int) p + "%");
        base_name.setText(dataBean.getCompanyName());
        base_type.setText(dataBean.getRanking());
    }

    @Override
    public void netFailed(int what, String message) {

    }


    @Override
    public void onClick(View view) {

    }

}
