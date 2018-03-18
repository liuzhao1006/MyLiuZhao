package com.sx.trans.supervision.activity.Anlysis;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.IndustryAnalysePresnter;
import com.sx.trans.supervision.adapter.IndutryAnaylsisAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.constants.IndustryAnalysisInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.TradeList;
import com.sx.trans.supervision.utils.DateUtils;
import com.sx.trans.supervision.views.LinearLayoutForListView;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.views.RoundProgressBar;
import com.sx.trans.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/8/27.
 * 行业分析
 */

public class IndustryAnalysisActivity extends BaseActivity implements PublicView {

    private TextView tvVehicle;
    private TextView tvClassline;
    private TextView tvTravel;
    private TextView tvDanger;
    private TextView tvOther;
    private RoundProgressBar idRoundprogress;
    private RoundProgressBar idRoundprogress2;
    private RoundProgressBar idRoundprogress3;
    private RoundProgressBar idRoundprogress4;
    private TextView tv_progress1;

    private void initControls() {
        tvVehicle = findViewById(R.id.tv_vehicle);
        tvClassline = findViewById(R.id.tv_classline);
        tvTravel = findViewById(R.id.tv_travel);
        tvDanger = findViewById(R.id.tv_danger);
        tvOther = findViewById(R.id.tv_other);
        idRoundprogress = findViewById(R.id.id_roundprogress);
        idRoundprogress2 = findViewById(R.id.id_roundprogress2);
        idRoundprogress3 = findViewById(R.id.id_roundprogress3);
        idRoundprogress4 = findViewById(R.id.id_roundprogress4);
        tv_progress2 = findViewById(R.id.tv_progress2);
        tv_progress1 = findViewById(R.id.tv_progress1);
        tv_progress3 = findViewById(R.id.tv_progress3);
        tv_progress4 = findViewById(R.id.tv_progress4);
        ll_progress1 = findViewById(R.id.ll_progress1);
        ll_progress2 = findViewById(R.id.ll_progress2);
        ll_progress3 = findViewById(R.id.ll_progress3);
        ll_progress4 = findViewById(R.id.ll_progress4);
        viewList = findViewById(R.id.viewList);


    }

    private TextView tv_progress2;
    private TextView tv_progress3;
    private TextView tv_progress4;
    private LinearLayout ll_progress1;
    private LinearLayout ll_progress2;
    private LinearLayout ll_progress3;
    private LinearLayout ll_progress4;
    private LinearLayoutForListView viewList;
    private IndustryAnalysePresnter industryAnalysePresnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industryays);
        initControls();
        init();
        industryAnalysePresnter.getIndustryAnalyse();
    }

    private void init() {
        setTitle(true, getString(R.string.industry_title), false, null);
        industryAnalysePresnter = new IndustryAnalysePresnter(this, this);
        iniPorgress();
        ArrayList<ArrayList<IndustryAnalysisInfo.RegTrend>> industryAInfoArrayList = new ArrayList<>();
        industryAInfoArrayList.add(getRegTrend(null));
        industryAInfoArrayList.add(getRegTrend(null));
        industryAInfoArrayList.add(getRegTrend(null));
        industryAInfoArrayList.add(getRegTrend(null));
        viewList.setAdapter(new IndutryAnaylsisAdapter(mContext, industryAInfoArrayList));
    }

    //设置行业入网车辆占比
    private void setProgressNetDate(ArrayList<TradeList> tradelist) {
        ll_progress1.setVisibility(View.GONE);
        ll_progress2.setVisibility(View.GONE);
        ll_progress3.setVisibility(View.GONE);
        ll_progress4.setVisibility(View.GONE);
        for (int i = 0; i < tradelist.size(); i++) {
            if (i == 0) {
                idRoundprogress.setMax(tradelist.get(i).getVehCnt());
                idRoundprogress.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress1.setText(tradelist.get(i).getName());
                ll_progress1.setVisibility(View.VISIBLE);
            }
            if (i == 1) {
                idRoundprogress2.setMax(tradelist.get(i).getVehCnt());
                idRoundprogress2.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress2.setText(tradelist.get(i).getName());
                ll_progress2.setVisibility(View.VISIBLE);
            }
            if (i == 2) {
                idRoundprogress3.setMax(tradelist.get(i).getVehCnt());
                idRoundprogress3.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress3.setText(tradelist.get(i).getName());
                ll_progress3.setVisibility(View.VISIBLE);
            }
            if (i == 3) {
                idRoundprogress4.setMax(tradelist.get(i).getVehCnt());
                idRoundprogress4.setProgress(tradelist.get(i).getNoRegVehCnt());
                tv_progress4.setText(tradelist.get(i).getName());
                ll_progress4.setVisibility(View.VISIBLE);
            }
        }
    }

    //设置头部标题数据
    private void setHeadDate(int Vehicle, int Classline, int Travel, int Danger, int Other) {
        tvVehicle.setText(Vehicle + "");
        tvClassline.setText(Classline + "");
        tvTravel.setText(Travel + "");
        tvDanger.setText(Danger + "");
        tvOther.setText(Other + "");
    }

    @Override
    public void showLoading() {
        showDiaLogLoading(false);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    @Override
    public void Success(Result result, int code) {
        if (code == 0) {
            IndustryAnalysisInfo industryAnalysisInfo = result.getData(IndustryAnalysisInfo.class);
            setHeadDate(industryAnalysisInfo.getVehCnt(), industryAnalysisInfo.getLineCnt(), industryAnalysisInfo.getTravleCnt(), industryAnalysisInfo.getDangerCnt(), industryAnalysisInfo.getOtherCnt());
            setProgressNetDate(industryAnalysisInfo.getTrade());


            ArrayList<ArrayList<IndustryAnalysisInfo.RegTrend>> industryAInfoArrayList = new ArrayList<>();
            industryAInfoArrayList.add(getRegTrend(industryAnalysisInfo.getLineRegTrend()));
            industryAInfoArrayList.add(getRegTrend(industryAnalysisInfo.getTravelRegTrend()));
            industryAInfoArrayList.add(getRegTrend(industryAnalysisInfo.getDangerRegTrend()));
            industryAInfoArrayList.add(getRegTrend(industryAnalysisInfo.getOtherRegTrend()));
            viewList.setAdapter(new IndutryAnaylsisAdapter(mContext, industryAInfoArrayList));
        }
    }

    //对趋势数据进行第二次处理
    private ArrayList<IndustryAnalysisInfo.RegTrend> getRegTrend(ArrayList<IndustryAnalysisInfo.RegTrend> regTrendArrayList) {
        ArrayList<IndustryAnalysisInfo.RegTrend> regTrends = new ArrayList<IndustryAnalysisInfo.RegTrend>();

        for (int i = 0; i <= 5; i++) {
            IndustryAnalysisInfo.RegTrend regTrendd = new IndustryAnalysisInfo.RegTrend();
            regTrendd.setMonth(DateUtils.getMonthYear(5 - i));
            regTrendd.setCnt(0);
            regTrends.add(regTrendd);
        }
        if (regTrendArrayList != null && regTrendArrayList.size() > 0) {
            for (IndustryAnalysisInfo.RegTrend regTrend : regTrends) {
                b:
                for (IndustryAnalysisInfo.RegTrend regTrendd : regTrendArrayList) {
                    if (regTrend.getMonth().equals(regTrendd.getMonth().substring(regTrendd.getMonth().length() - 2, regTrendd.getMonth().length()))) {
                        regTrend.setCnt(regTrendd.getCnt());
                        break b;
                    }
                }
            }
        }
        return regTrends;
    }

    @Override
    public void showError(String error) {
        HideDiaLogLoading();
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }


    //初始化进度条
    private void iniPorgress() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);  //size.x就是宽度，size.y就是高度

        idRoundprogress2.setCricleColor(getResources().getColor(R.color.home_blueprogress2));
        idRoundprogress2.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress22));
        idRoundprogress3.setCricleColor(getResources().getColor(R.color.home_blueprogress3));
        idRoundprogress3.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress33));
        idRoundprogress4.setCricleColor(getResources().getColor(R.color.home_blueprogress4));
        idRoundprogress4.setCricleProgressColor(getResources().getColor(R.color.home_blueprogress44));

        Log.d("asd", "size.x:" + size.x + ",size.y:" + size.y);
        if (size.x >= 720 && size.x <= 800 && size.y >= 1100) {
            idRoundprogress.setTextSize(20);
            idRoundprogress.setRoundWidth(10);
            idRoundprogress2.setTextSize(20);
            idRoundprogress2.setRoundWidth(10);
            idRoundprogress3.setTextSize(20);
            idRoundprogress3.setRoundWidth(10);
            idRoundprogress4.setTextSize(20);
            idRoundprogress4.setRoundWidth(10);
        }
    }
}

