package com.sx.trans.supervision.adapter.Marker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.sx.trans.R;

import com.sx.trans.supervision.activity.VehicleLocationActivity;
import com.sx.trans.supervision.app.IConstants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 业户-车辆分布 marker 气泡
 */

public class CompanyWinAdapter implements AMap.InfoWindowAdapter {
   private TextView tvRegsNo;
   private LinearLayout LinearLayoutPopup;
    private String regsNo;
    private String color;
    private Context mContext;

    public CompanyWinAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void initData(Marker marker) {
        regsNo = marker.getTitle();
        color = marker.getSnippet();
    }

    @NonNull
    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_company_vehicle_popup, null);
       tvRegsNo = view.findViewById(R.id.tv_regsNo);
        LinearLayoutPopup = view.findViewById(R.id.LinearLayoutPopup);
        tvRegsNo.setText(regsNo);
        return view;
    }

    @OnClick(R.id.LinearLayoutPopup)
    void ClickPop() {
       Intent intent=new Intent(mContext, VehicleLocationActivity.class);
       intent.putExtra(IConstants.mSpre_Constants.COLOR,color);
       intent.putExtra(IConstants.mSpre_Constants.VEHICLENOMBER,regsNo);
       mContext.startActivity(intent);
    }

}
