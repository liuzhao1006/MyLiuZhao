package com.sx.trans.supervision.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.VehicleDetailPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleDetail;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.views.PublicView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 车辆详情
 */

public class VehicleDetailActivity extends BaseActivity implements PublicView {

   private TextView tvDetailIn;
   private TextView tvDetailOwner;
   private TextView tvDetailTrade;
   private TextView tvDetailRegion;
   private TextView tvDetailType;
   private TextView tvDetailColor;
   private TextView tvDetailInTime;
   private TextView tvDetailNum;


    private void initControls(){

        tvDetailIn = findViewById(R.id.tv_detail_in);
        tvDetailOwner = findViewById(R.id.tv_detail_owner);
        tvDetailTrade = findViewById(R.id.tv_detail_trade);
        tvDetailRegion = findViewById(R.id.tv_detail_region);
        tvDetailType = findViewById(R.id.tv_detail_type);
        tvDetailColor = findViewById(R.id.tv_detail_color);
        tvDetailInTime = findViewById(R.id.tv_detail_in_time);
        tvDetailNum = findViewById(R.id.tv_detail_num);
    }

    private Context mContext;

    private VehicleDetailPresenter vehicleDetailPresenter;
    private VehicleDetail vehicleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);
        initControls();
        init();

    }

    private void init(){
        mContext = this;
        setTitle(true, mContext.getString(R.string.detail_title), false, null);
        vehicleDetailPresenter = new VehicleDetailPresenter(this, mContext);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                VehicleInfo vehicleInfo = (VehicleInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHINFO);
                if (vehicleInfo != null) {
                    vehicleDetailPresenter.getVehicleDetail(vehicleInfo.getVid()+"");
                }
            }
        }
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
        vehicleDetail = result.getData(VehicleDetail.class);

        tvDetailIn.setText(vehicleDetail.getPlatName());
        tvDetailInTime.setText(vehicleDetail.getNetTime());
        tvDetailOwner.setText(vehicleDetail.getEntName());
        tvDetailTrade.setText(vehicleDetail.getTradeName());
        tvDetailRegion.setText(vehicleDetail.getAreaName());
        tvDetailType.setText(vehicleDetail.getVehicleKind());
        tvDetailColor.setText(vehicleDetail.getColorName());
        tvDetailNum.setText(vehicleDetail.getRegisNo());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}
