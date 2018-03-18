package com.sx.trans.supervision.activity.company;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.sx.trans.supervision.Presenter.CompanyVehiclePresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.activity.SearchVehicleActivity;
import com.sx.trans.supervision.adapter.Marker.CompanyWinAdapter;
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.utils.AMapUtil;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.SlideAndDragList.SlideAndDragListView;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 业户公司-车辆分布
 */

public class CompanyVehicleActivity extends BaseActivity implements PublicView, AMap.OnMarkerClickListener, AMap.OnMapClickListener {
   private TextView headTitleTv;
   private MapView MapView;
   private TextView tvSearchCar;
   private SlideAndDragListView lvVehicle;

    private void initControls(){
        headTitleTv = findViewById(R.id.headTitleTv);
        MapView = findViewById(R.id.MapView);
        tvSearchCar = findViewById(R.id.tv_searchCar);
        lvVehicle = findViewById(R.id.lv_vehicle);


    }
    private AMap aMap;
    private UiSettings uiSettings;
    private boolean blTraffic = true;
    private LatLng myLatlng, vehicleLatlng;//个人定位点、车辆定位点
    private Context mContext;
    private ArrayList<VehicleInfo> SearchvehicleInfoList;
    private CompanyVehiclePresenter vehiclePresenter;
    private boolean firstlocation = false;//首次获得车辆定位数据
    private VehicleInfo searchvehicleInfo;//当前搜索的车辆
    private VehicleInfo vehicleInfo;//搜索所得车辆定位信息

    private int unitid;//企业id

    private ArrayList<VehicleInfo> companyVehicleList = new ArrayList<VehicleInfo>();
    private CompanyWinAdapter winAdapter;
    Marker currentMarker;


    MarkerOptions option;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_vehicle);
        mContext = this;
        initControls();
        unitid = getIntent().getIntExtra("id", -1);
        setMapView(savedInstanceState);
        setTitle(true, mContext.getString(R.string.title_company_vehicle), false, null);

        SearchvehicleInfoList = new ArrayList<VehicleInfo>();

        vehiclePresenter = new CompanyVehiclePresenter(this, mContext);
        vehiclePresenter.getCompanyVehiclePosition(unitid, "");

    }

    //初始化地图控件
    private void setMapView(Bundle savedInstanceState) {
        MapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = MapView.getMap();
        uiSettings = aMap.getUiSettings();
        uiSettings.setTiltGesturesEnabled(false);// 禁用倾斜手势。
        uiSettings.setRotateGesturesEnabled(false);// 禁用旋转手势。
        uiSettings.setZoomControlsEnabled(false);//去除默认放大缩小按钮
//        //隐藏高德地图图标
        uiSettings.setLogoBottomMargin(-50);
//        //设置自定义marker弹窗
        winAdapter = new CompanyWinAdapter(mContext);
        aMap.setInfoWindowAdapter(winAdapter);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapClickListener(this);

        DrawPosition();
        aMap.animateCamera(CameraUpdateFactory.newLatLng(myLatlng), 10, null);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({ R.id.tv_searchCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_searchCar://搜索
                Intent intent = new Intent(mContext, SearchVehicleActivity.class);
                startActivityForResult(intent, 0);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }


    //绘制我的位置，和车辆位置
    private void DrawPosition() {
        aMap.clear();
        myLatlng = new LatLng(LzApp.LocLat, LzApp.LocLong);
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(myLatlng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.vehicle_location)));

        if (vehicleInfo != null) {
            if (vehicleInfo.getLatitude() > 1 && vehicleInfo.getLongitude() > 1) {
                vehicleLatlng = AMapUtil.getGpsToLatlng(new LatLng(vehicleInfo.getLatitude(), vehicleInfo.getLongitude()));
                aMap.addMarker(new MarkerOptions().anchor(0.5f, 1f)
                        .position(vehicleLatlng)
                        .title(vehicleInfo.getRegistrationNo())
                         .snippet(vehicleInfo.getColor()+"")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_location)));
                if (firstlocation) {//首次获得车辆定位
                    aMap.animateCamera(CameraUpdateFactory.newLatLng(vehicleLatlng), 500, null);
                    firstlocation = false;
                }

            } else {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.nothing_position), Toast.LENGTH_SHORT);
            }
        }
    }


    //搜索返回结果处理
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    showDiaLogLoading(false);
                    searchvehicleInfo = (VehicleInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHINFO);
                    if (searchvehicleInfo != null) {
                        vehiclePresenter.getCompanyVehiclePosition(unitid, searchvehicleInfo.getRegistrationNo());
                        firstlocation = true;
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    @Override
    public void Success(Result result, int code) {
        if (code == 0) {
            aMap.clear();
            companyVehicleList.clear();
            companyVehicleList.addAll(result.getData(new TypeToken<ArrayList<VehicleInfo>>() {
            }));
            LatLngBounds.Builder builder = LatLngBounds.builder();

            for (VehicleInfo vehicleInfo : companyVehicleList) {
                if (vehicleInfo.getLatitude() == 0 && vehicleInfo.getLongitude() == 0) {//排除经纬度都为0的坐标
                } else {
                    switch (vehicleInfo.getStatus()) {
                        case 0://离线
                            latLng = AMapUtil.getGpsToLatlng(new LatLng(vehicleInfo.getLatitude(), vehicleInfo.getLongitude()));
                            option = new MarkerOptions();
                            option.position(latLng);
                            option.anchor(0.5f, 0.9f);
                            option.title(vehicleInfo.getRegisNo());
                            option.snippet(vehicleInfo.getColor()+"");
                            option.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.drawable.car_location_offline)));
                            aMap.addMarker(option);
                            builder.include(latLng);
                            break;
                        case 1://在线
                            latLng = AMapUtil.getGpsToLatlng(new LatLng(vehicleInfo.getLatitude(), vehicleInfo.getLongitude()));
                            option = new MarkerOptions();
                            option.position(latLng);
                            option.anchor(0.5f, 0.9f);
                            option.title(vehicleInfo.getRegisNo());
                            option.snippet(vehicleInfo.getColor()+"");
                            option.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.drawable.car_location_nor)));
                            aMap.addMarker(option);
                            builder.include(latLng);
                            break;
                        case 2://报警
                            latLng = AMapUtil.getGpsToLatlng(new LatLng(vehicleInfo.getLatitude(), vehicleInfo.getLongitude()));
                            option = new MarkerOptions();
                            option.position(latLng);
                            option.anchor(0.5f, 0.9f);
                            option.title(vehicleInfo.getRegisNo());
                            option.snippet(vehicleInfo.getColor()+"");
                            option.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.drawable.car_location_alarm)));
                            aMap.addMarker(option);
                            builder.include(latLng);
                            break;
                    }
                }
            }
            aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 30));
        } else {
            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        MapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        MapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MapView.onDestroy();
        ButterKnife.reset(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        if (!marker.isInfoWindowShown()) {
            aMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude)));
            marker.showInfoWindow();
        } else {
            marker.hideInfoWindow();
        }
        return true;
    }

    @Override
    public void onMapClick(LatLng arg0) {
        if (currentMarker.isInfoWindowShown()) {
            currentMarker.hideInfoWindow();
        }
    }

}
