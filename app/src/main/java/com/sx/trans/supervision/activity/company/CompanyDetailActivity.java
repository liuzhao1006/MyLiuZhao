package com.sx.trans.supervision.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.fragment.CompanyHomeFragment;
import com.sx.trans.company.manager.CompanyManager;
import com.sx.trans.supervision.Presenter.CompanyDetailPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.MonthAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.CompanyDetailInfo;
import com.sx.trans.supervision.constants.CompanyInfo;
import com.sx.trans.supervision.constants.Request.AllCompanyBean;
import com.sx.trans.supervision.constants.Request.CompanyAndMonthBean;
import com.sx.trans.supervision.constants.Request.CompanyListBean;
import com.sx.trans.supervision.constants.Request.HomeAlarBean;
import com.sx.trans.supervision.constants.Request.HomeDAteBean;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.utils.CommonUtils;
import com.sx.trans.supervision.utils.DateUtils;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.HorizontalListView;
import com.sx.trans.widget.utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 业户详情
 */

public class CompanyDetailActivity extends BaseActivity implements PublicView, BaseNetApi {
    //    private static final String[] arrayType = new String[]{"静态风险", "联网监控", "实时报警"};
    private ArrayList<CompanyListBean> companyList = new ArrayList();
    private ArrayList<String> name = new ArrayList();
    private ArrayAdapter<String> adapter;
    private TextView tvCompanyName;
    private RelativeLayout rlDetail;
    private TextView tvAllNum;
    private TextView tvInNum;
    private TextView tvPer;
    private RelativeLayout btDateLeft;
    private HorizontalListView horizontallist;
    private RelativeLayout btDateRight;
    private TextView tvDAllNum;
    private TextView tvDInNum;
    private TextView tvDInPer;
    private TextView tvDLineNum;
    private TextView tvDLinePer;
    private Spinner spinnerType;//选择类型
    private int sum1;
    private int sum2;
    private double sum3;
    private double sum4;

    private void initControls() {
        companyname=findViewById(R.id.companyname);
        spinnerType = findViewById(R.id.spinnerType);
        tvCompanyName = findViewById(R.id.tv_company_name);
        rlDetail = findViewById(R.id.rl_detail);
        rlDetail.setVisibility(View.GONE);
        tvAllNum = findViewById(R.id.tv_all_num);
        tvInNum = findViewById(R.id.tv_in_num);
        tvPer = findViewById(R.id.tv_per);
        btDateLeft = findViewById(R.id.bt_date_left);
        horizontallist = findViewById(R.id.horizontallist);
        btDateRight = findViewById(R.id.bt_date_right);
        tvDAllNum = findViewById(R.id.tv_d_all_num);
        tvDInNum = findViewById(R.id.tv_d_in_num);
        tvDInPer = findViewById(R.id.tv_d_in_per);
        tvDLineNum = findViewById(R.id.tv_d_line_num);
        tvDLinePer = findViewById(R.id.tv_d_line_per);
        tvDAlarmNum = findViewById(R.id.tv_d_alarm_num);
        tvDAlarmPer = findViewById(R.id.tv_d_alarm_per);
        tvDSpeedNum = findViewById(R.id.tv_d_speed_num);
        tvDSpeedPer = findViewById(R.id.tv_d_speed_per);
        tvDTiredNum = findViewById(R.id.tv_d_tired_num);
        tvDTiredPer = findViewById(R.id.tv_d_tired_per);
        tvDClearNum = findViewById(R.id.tv_d_clear_num);
        tvDClearPer = findViewById(R.id.tv_d_clear_per);
        svDetail = findViewById(R.id.sv_detail);
        ivNoData = findViewById(R.id.iv_no_data);


    }
private TextView companyname;
    private TextView tvDAlarmNum;
    private TextView tvDAlarmPer;
    private TextView tvDSpeedNum;
    private TextView tvDSpeedPer;
    private TextView tvDTiredNum;
    private TextView tvDTiredPer;
    private TextView tvDClearNum;
    private TextView tvDClearPer;
    private ScrollView svDetail;
    private ImageView ivNoData;
    private int selectMonth = -1;
    private int unitid;//企业id
    private String currentMonth = "";
    private int seletedpostion = 0;
    private String selectedTime;//所选时间
    private MonthAdapter monthAdapter;
    private String month = DateUtils.getMonthChange(new Date(), CommonUtils.DATE_FORMAT_MOUTH);
    private ArrayList<String> monthList = DateUtils.getLastMonths(month);
    private CompanyManager manager;
    private CompanyDetailPresenter presenter;
    private HomeAlarBean bean;
    private AllCompanyBean info;
    private ArrayList<AllCompanyBean> beanArrayList = new ArrayList<>();
    private CompanyAndMonthBean bean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        manager = new CompanyManager(CompanyDetailActivity.this, this);

        initControls();
        name.add("全部");
        setCoordinate(spinnerType, name);
        setTitle(true, mContext.getString(R.string.title_company_detail), false, null);
        manager.getCompanylist();
        manager.setQueryCompanyList(new CompanyManager.QueryCompanyList() {
            @Override
            public void onQueryCompanyDataList(int what, List<CompanyListBean> vehicleLedgetList) {
                companyList.clear();
                name.clear();
                name.add("全部");
//                companyList.add(new CompanyListBean("全部",-2));
                for (int i = 0; i < vehicleLedgetList.size(); i++) {
                    companyList.add(vehicleLedgetList.get(i));
                    name.add(vehicleLedgetList.get(i).getCompanyName());
                }
                setCoordinate(spinnerType, name);
            }
        });
//        manager.getAllDetail();
        manager.setQueryDetaliall(new CompanyManager.QueryDetaliAll() {
            @Override
            public void onQueryAll(int what, List<AllCompanyBean> vehicleLedgetList) {
                if (vehicleLedgetList.size() != 0) {
                    beanArrayList.clear();
                    beanArrayList.addAll(vehicleLedgetList);
                    for (int i = 0; i < beanArrayList.size(); i++) {

                        sum1 = sum1 + beanArrayList.get(i).getVehicleCount();
                        sum2 = sum2 + beanArrayList.get(i).getVehicleAccessCount();

                    }
                    sum3 = (double) sum2 / sum1;

                    if (!currentMonth.equals("") && currentMonth != null) {
                        for (int i = 0; i < beanArrayList.size(); i++) {
                            if (beanArrayList.get(i).getMonths().equals(currentMonth)) {
                                setAllData(beanArrayList.get(i));

                            }
                        }

                    } else {
                        setAllData(beanArrayList.get(0));
                    }
                    tvAllNum.setText(sum1 + "");
                    tvInNum.setText(sum2 + "");
                    tvPer.setText(new DecimalFormat("#0.0").format( (sum3 * 100)) + "%");
                    sum1=0;
                    sum2=0;
                }
            }
        });
        manager.setQueryByCompanyMonth(new CompanyManager.QueryDetaliByCompanyMonth() {
            @Override
            public void onQueryByCompanyMonth(int what, List<CompanyAndMonthBean> vehicleLedgetList) {
                bean2 = vehicleLedgetList.get(0);
                setDetailData(bean2);
            }
        });


        setDate();

//        Intent intent = getIntent();
//        if (intent != null) {
//            Bundle bundle = this.getIntent().getExtras();
//            if (bundle != null) {
//                CompanyInfo info = (CompanyInfo) bundle.getSerializable(IConstants.mSpre_Constants.COMPANY_INFO);
//                if (info != null) {
//                    unitid = info.getUid();
//                    tvCompanyName.setText(info.getName());
//                    tvAllNum.setText(info.getVehCnt() + "");
//                    tvInNum.setText(info.getRegVehCnt() + "");
//                    if (0 < info.getVehCnt()) {
//                        DecimalFormat df = new DecimalFormat("#0.0");
//                        tvPer.setText(df.format((float) info.getRegVehCnt() * 100 / info.getVehCnt()) + "%");
//                    } else {
//                        tvPer.setText("-");
//                    }
//                }
//            }
//        }

//        presenter = new CompanyDetailPresenter(this, this);
//        presenter.getCompanyDetail(unitid, selectedTime.substring(0, 4) + "-" + selectedTime.substring(5, 7));
    }

    // 设置坐标系Spinner
    private void setCoordinate(Spinner spinner, ArrayList list) {
        // 将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(spinner,
                list));
        // 设置默认值
        spinner.setVisibility(View.VISIBLE);
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

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        private Spinner spinner;
        private ArrayList arr;

        public SpinnerSelectedListener(Spinner spinner, ArrayList arr) {
            super();
            this.spinner = spinner;
            this.arr = arr;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            companyname.setText(name.get(position));
            if (position == 0) {
                unitid = -1;
                manager.getAllDetail();


            } else {
                sum4=(double) (companyList.get(position - 1).getVehicleAccessCount())/(companyList.get(position - 1).getVehicleCount());
                tvPer.setText(new DecimalFormat("#0.0").format(sum4*100) + "%");
//                tvPer.setText(new DecimalFormat("#0.0").format(companyList.get(position - 1).getVehicleAccessRate()) + "%");
                tvAllNum.setText(companyList.get(position - 1).getVehicleCount() + "");
                tvInNum.setText(companyList.get(position - 1).getVehicleAccessCount() + "");
                unitid = companyList.get(position - 1).getId();
                sum4=0;
                manager.getDetailBycompany(unitid + "", currentMonth);
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    //设置日期控件
    private void setDate() {

        selectedTime = monthList.get(monthList.size() - 1);
        monthAdapter = new MonthAdapter(this, monthList);
        horizontallist.setAdapter(monthAdapter);
        seletedpostion = DateUtils.getLastMonths(month).size() - 1;

        seletedpostion = seletedpostion - 1;
        horizontallist.setSelection(seletedpostion);

        horizontallist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!selectedTime.equals(monthList.get(position))) {
                    monthAdapter.mPosition = position;
                    monthAdapter.notifyDataSetChanged();
                    selectedTime = monthList.get(position);
                    currentMonth = selectedTime.substring(0, 4) + "-" + selectedTime.substring(5, 7);
                    if (unitid == -1) {
                        //说明是全部
                        for (int i = 0; i < beanArrayList.size(); i++) {
                            if (beanArrayList.get(i).getMonths().equals(currentMonth)) {
                                selectMonth = i;
                            }
                        }
                        if (selectMonth != -1) {
                            setAllData(beanArrayList.get(selectMonth));
                        }
                    } else {
                        manager.getDetailBycompany(unitid + "", currentMonth);

                    }
//               String     Utils.toURLEncoded(getTime(date).replace("-", "/"));
//                    presenter.getCompanyDetail(unitid, selectedTime.substring(0, 4) + "-" + selectedTime.substring(5, 7));
                }
            }
        });
    }

    @Override
    public void showLoading() {
        showDiaLogLoading(false);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    //设置全部公司的分月份数据
    private void setAllData(AllCompanyBean detailInfo) {
        if (null == detailInfo) {
            svDetail.setVisibility(View.GONE);
            ivNoData.setVisibility(View.VISIBLE);
        } else {
            svDetail.setVisibility(View.VISIBLE);
            ivNoData.setVisibility(View.GONE);

            tvDAllNum.setText(detailInfo.getVehicleCount() + "");
            tvDInNum.setText(detailInfo.getVehicleAccessCount() + "");
//            DecimalFormat df = new DecimalFormat("0.00");
            if (0 < detailInfo.getVehicleAccessRate()) {
//                tvDInPer.setText(df.format((float) detailInfo.getRegVehCnt() * 100 / detailInfo.getVehCnt()) + "%");
                DecimalFormat df = new DecimalFormat("#0.0");
                tvDInPer.setText(df.format(detailInfo.getVehicleAccessRate()) + "%");
            } else {
                tvDInPer.setText("-");
            }
            tvDLineNum.setText(detailInfo.getVehicleOnlineCount() + "");
            tvDLinePer.setText(detailInfo.getVehicleOnlineRate() + "%");
            tvDAlarmNum.setText(detailInfo.getAlarmVehicleCount() + "");
            tvDAlarmPer.setText(detailInfo.getAlarmVehicleRate() + "%");
            tvDSpeedNum.setText(detailInfo.getOverSpeedCount() + "");
            tvDSpeedPer.setText(detailInfo.getOverSpeedCountAvg() + "");
            tvDTiredNum.setText(detailInfo.getTiredCount() + "");
            tvDTiredPer.setText(detailInfo.getTiredCountAvg() + "");
            tvDClearNum.setText(detailInfo.getHandleCount() + "");
            tvDClearPer.setText(detailInfo.getHandleCountRate() + "%");
        }
    }

    //设置各个分公司的按月份数据
    private void setDetailData(CompanyAndMonthBean detailInfo) {
        if (null == detailInfo) {
            svDetail.setVisibility(View.GONE);
            ivNoData.setVisibility(View.VISIBLE);
        } else {
            svDetail.setVisibility(View.VISIBLE);
            ivNoData.setVisibility(View.GONE);
//            tvPer.setText(new DecimalFormat("#0.0").format(detailInfo.getVehicleAccessRate()) + "%");
//            tvAllNum.setText(detailInfo.getVehicleCount() + "");
//            tvInNum.setText(detailInfo.getVehicleAccessCount() + "");
            tvDAllNum.setText(detailInfo.getVehicleCount() + "");
            tvDInNum.setText(detailInfo.getVehicleAccessCount() + "");
//            DecimalFormat df = new DecimalFormat("0.00");
            if (0 < detailInfo.getVehicleAccessRate()) {
//                tvDInPer.setText(df.format((float) detailInfo.getRegVehCnt() * 100 / detailInfo.getVehCnt()) + "%");
                DecimalFormat df = new DecimalFormat("#0.0");
                tvDInPer.setText(df.format(detailInfo.getVehicleAccessRate()) + "%");
            } else {
                tvDInPer.setText("-");
            }
            tvDLineNum.setText(detailInfo.getVehicleOnlineCount() + "");
            tvDLinePer.setText(detailInfo.getVehicleOnlineRate() + "%");
            tvDAlarmNum.setText(detailInfo.getAlarmVehicleCount() + "");
            tvDAlarmPer.setText(detailInfo.getAlarmVehicleRate() + "%");
            tvDSpeedNum.setText(detailInfo.getOverSpeedCount() + "");
            tvDSpeedPer.setText(detailInfo.getOverSpeedCountAvg() + "");
            tvDTiredNum.setText(detailInfo.getTiredCount() + "");
            tvDTiredPer.setText(detailInfo.getTiredCountAvg() + "");
            tvDClearNum.setText(detailInfo.getHandleCount() + "");
            tvDClearPer.setText(detailInfo.getHandleCountRate() + "%");
        }
    }

    @Override
    public void Success(Result result, int code) {
//        CompanyDetailInfo detailInfo = result.getData(CompanyDetailInfo.class);
//        if (null == detailInfo) {
//            svDetail.setVisibility(View.GONE);
//            ivNoData.setVisibility(View.VISIBLE);
//        } else {
//            svDetail.setVisibility(View.VISIBLE);
//            ivNoData.setVisibility(View.GONE);
//
//            tvDAllNum.setText(detailInfo.getVehCnt() + "");
//            tvDInNum.setText(detailInfo.getRegVehCnt() + "");
////            DecimalFormat df = new DecimalFormat("0.00");
//            if (0 < detailInfo.getRegVehRatio()) {
////                tvDInPer.setText(df.format((float) detailInfo.getRegVehCnt() * 100 / detailInfo.getVehCnt()) + "%");
//                DecimalFormat df = new DecimalFormat("#0.0");
//                tvDInPer.setText(df.format(detailInfo.getRegVehRatio()) + "%");
//            } else {
//                tvDInPer.setText("-");
//            }
//            tvDLineNum.setText(detailInfo.getOnline() + "");
//            tvDLinePer.setText(detailInfo.getOnlineRatio() + "%");
//            tvDAlarmNum.setText(detailInfo.getAlarm() + "");
//            tvDAlarmPer.setText(detailInfo.getAlarmRatio() + "%");
//            tvDSpeedNum.setText(detailInfo.getSpeedAlarm() + "");
//            tvDSpeedPer.setText(detailInfo.getAvgSpeed() + "");
//            tvDTiredNum.setText(detailInfo.getTiredAlarm() + "");
//            tvDTiredPer.setText(detailInfo.getAvgTired() + "");
//            tvDClearNum.setText(detailInfo.getHandler() + "");
//            tvDClearPer.setText(detailInfo.getHandlerRatio() + "%");
//        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.rl_detail, R.id.bt_date_left, R.id.bt_date_right})
    void OnClick(View v) {
        switch (v.getId()) {
//            case R.id.rl_detail:
//                Intent intent = new Intent(mContext, CompanyVehicleActivity.class);
//                intent.putExtra("id", unitid);
//                startActivity(intent);
//                break;
            case R.id.bt_date_left:
                if (seletedpostion != 0) {
                    seletedpostion = seletedpostion - 3;
                    if (seletedpostion < 0) {
                        seletedpostion = 0;
                    }
                    monthAdapter = new MonthAdapter(this, monthList);
                    horizontallist.setAdapter(monthAdapter);
                    horizontallist.setSelection(seletedpostion);
                } else {
                    seletedpostion = 0;
                }
                break;
            case R.id.bt_date_right:
                if (seletedpostion != DateUtils.getLastMonths(month).size() - 3) {
                    seletedpostion = seletedpostion + 3;
                    if (seletedpostion > DateUtils.getLastMonths(month).size() - 3) {
                        seletedpostion = DateUtils.getLastMonths(month).size() - 3;
                    }
                    monthAdapter = new MonthAdapter(this, monthList);
                    horizontallist.setAdapter(monthAdapter);
                    horizontallist.setSelection(seletedpostion);
                } else {
                    seletedpostion = DateUtils.getLastMonths(month).size() - 3;
                }
                break;
        }

    }
}
