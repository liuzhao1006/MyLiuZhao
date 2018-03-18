package com.sx.trans.supervision.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.sx.trans.supervision.Presenter.VehiclePresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.activity.SearchVehicleActivity;
import com.sx.trans.supervision.activity.TrackActivity;
import com.sx.trans.supervision.activity.VehicleDetailActivity;
import com.sx.trans.supervision.activity.VideoActivity;
import com.sx.trans.supervision.activity.VideoSDListActivity;
import com.sx.trans.supervision.adapter.VehicleAdapter;
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.utils.AMapUtil;
import com.sx.trans.supervision.utils.CommonUtils;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.SlideAndDragList.Menu;
import com.sx.trans.supervision.widget.SlideAndDragList.MenuItem;
import com.sx.trans.supervision.widget.SlideAndDragList.SlideAndDragListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mr_wang on 2017/8/21.
 * 我的车辆
 */

public class FragmentVehicle extends SupportMapFragment implements AdapterView.OnItemClickListener, SlideAndDragListView.OnMenuItemClickListener, SlideAndDragListView.OnSlideListener, PublicView, View.OnClickListener {
    private TextView headTitleTv;
    private MapView MapView;
    private TextView tvSearchCar;
    private LinearLayout ll_gpsdate;
    private TextView tvGpsspeed;
    private RelativeLayout rlTrackBlack;
    private TextView tvMilleage;
    private Button btNavigation;
    private Button btTraffic;
    private Button btLocation;
    private SlideAndDragListView lvVehicle;

    private void initControls(View view) {
        headTitleTv = view.findViewById(R.id.headTitleTv);
        MapView = view.findViewById(R.id.MapView);
        tvSearchCar = view.findViewById(R.id.tv_searchCar);
        ll_gpsdate = view.findViewById(R.id.ll_gpsdate);
        tvGpsspeed = view.findViewById(R.id.tv_gpsspeed);
        rlTrackBlack = view.findViewById(R.id.rl_track_black);
        tvMilleage = view.findViewById(R.id.tv_gpstime);
        lvVehicle = view.findViewById(R.id.lv_vehicle);
        btNavigation = view.findViewById(R.id.bt_Navigation);
        btTraffic = view.findViewById(R.id.bt_traffic);
        btLocation = view.findViewById(R.id.bt_location);
        btNavigation.setOnClickListener(this);
        btTraffic.setOnClickListener(this);
        btLocation.setOnClickListener(this);
        tvSearchCar.setOnClickListener(this);

    }

    private AMap aMap;
    private UiSettings uiSettings;
    private boolean blTraffic = true;
    private LatLng myLatlng, vehicleLatlng;//个人定位点、车辆定位点
    private Context mContext;
    private ArrayList<VehicleInfo> SearchvehicleInfoList;
    private List<Menu> menus;
    private VehiclePresenter vehiclePresenter;
    private boolean firstSearch = true;
    private boolean firstlocation = false;//首次获得车辆定位数据
    private VehicleInfo searchvehicleInfo;//当前搜索的车辆
    private VehicleInfo vehicleInfo;//搜索所得车辆定位信息
    private Dialog updataDig;//加载动画
    private ImageView iv_progress, iv_progress2;
    private Boolean lvVehicleIsOpen = false;//侧拉在打开状态下刷新定位不自动关闭
    private VehicleAdapter vehicleAdapter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_vehicle, null);
        initControls(view);
        setMapView(bundle);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timeHandler.postDelayed(runnable, 1000);// 打开定时器，延迟这个时长后执行操作 获取车辆信息
    }

    private void init(View view) {
        headTitleTv.setText(getResources().getString(R.string.car_title));
        mContext = getActivity();
        SearchvehicleInfoList = new ArrayList<VehicleInfo>();
        menus = new ArrayList<Menu>();
        vehiclePresenter = new VehiclePresenter(this, mContext);

        initMenu();
        lvVehicle.setMenu(menus);
        lvVehicle.setOnItemClickListener(this);
        lvVehicle.setOnMenuItemClickListener(this);
        lvVehicle.setOnSlideListener(this);

        rlTrackBlack.getBackground().setAlpha(150);

        //搜索
        TextView viewById = view.findViewById(R.id.tv_searchCar);
        viewById.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(mContext, SearchVehicleActivity.class);
                startActivityForResult(intent, 0);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
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
//        adapter = new WarmInfoWinAdapter();
//        aMap.setInfoWindowAdapter(adapter);
        DrawPosition();
        aMap.animateCamera(CameraUpdateFactory.newLatLng(myLatlng), 10, null);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeHandler.postDelayed(this, 20 * 1000);// 每隔20秒
            Message msg = new Message();
            msg.what = 1;
            timeHandler.sendMessage(msg);
        }
    };


    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DrawPosition();
            switch (msg.what) {
                case 0://设置定位数据
                    setVehicleInfo();
                    break;
                case 1://绘制地图，并请求定位数据
                    if (vehicleInfo != null) {
                        vehiclePresenter.getVehiclePosition(searchvehicleInfo.getColor(), searchvehicleInfo.getRegistrationNo());
                    }
                    break;
            }
        }
    };


    //侧拉监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        aMap.animateCamera(CameraUpdateFactory.newLatLng(vehicleLatlng), 500, null);
    }

    @Override
    public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        Intent intent = null;
        if (buttonPosition == 2) {//车辆详情
            intent = new Intent(mContext, VehicleDetailActivity.class);
        }
        if (buttonPosition == 1) {//实时视频
            String paramValue = SearchvehicleInfoList.get(itemPosition).getParamValue();
            if (paramValue != null && paramValue.length() > 0) {
                int VideoType = SearchvehicleInfoList.get(itemPosition).getVideoType();
                if (VideoType == 0 || VideoType == 1 || VideoType == 2) {//顺达
                    intent = new Intent(mContext, VideoSDListActivity.class);
                } else {
                    intent = new Intent(mContext, VideoActivity.class);
                }
            } else {//不存在视频时，跳转至播放界面，车辆详情
                intent = new Intent(mContext, VehicleDetailActivity.class);
            }
        }
        if (buttonPosition == 0) {//轨迹回放
            intent = new Intent(mContext, TrackActivity.class);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(IConstants.mSpre_Constants.SEARCHINFO, SearchvehicleInfoList.get(itemPosition));
        intent.putExtras(bundle);
        mContext.startActivity(intent);
        return 0;
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
                if (vehicleInfo.getStatus() == 0) {
                    aMap.addMarker(new MarkerOptions().anchor(0.5f, 1f)
                            .position(vehicleLatlng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_offline)));
                } else {
                    aMap.addMarker(new MarkerOptions().anchor(0.5f, 1f)
                            .position(vehicleLatlng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_location)));
                }
                if (firstlocation) {//首次获得车辆定位
                    aMap.animateCamera(CameraUpdateFactory.newLatLng(vehicleLatlng), 500, null);
                    firstlocation = false;
                }

            } else {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.nothing_position), Toast.LENGTH_SHORT);
            }
        }
    }

    //设置车辆定位数据
    private void setVehicleInfo() {
        if (vehicleInfo != null) {

            //设置底部数据
            SearchvehicleInfoList.clear();
            searchvehicleInfo.setGpsTime(vehicleInfo.getGpsTime());
            SearchvehicleInfoList.add(searchvehicleInfo);
            vehicleAdapter.notifyDataSetChanged();
//            vehicleAdapter= new VehicleAdapter(mContext, SearchvehicleInfoList);
//            lvVehicle.setAdapter(vehicleAdapter);

            tvGpsspeed.setText(vehicleInfo.getGpsSpeed() + "");
            if (vehicleInfo.getGpsTime() != null && vehicleInfo.getGpsTime().length() > 8) {
                DecimalFormat df = new DecimalFormat("#0.0");
//                tvMilleage.setText(vehicleInfo.getGpsTime().substring(vehicleInfo.getGpsTime().length() - 8, vehicleInfo.getGpsTime().length()) + "");
                tvMilleage.setText(df.format(vehicleInfo.getMileage()) + "KM");
            }
        }
        if (firstSearch) {//首次加载车辆信息
            lvVehicle.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(
                    mContext, R.anim.from_the_bottom_up);
            lvVehicle.setAnimation(animation);
            firstSearch = false;
            ll_gpsdate.setVisibility(View.VISIBLE);
            btNavigation.setVisibility(View.VISIBLE);

        }

//        aMap.animateCamera(CameraUpdateFactory.newLatLng(vehicleLatlng), 500, null);
    }

    //搜索返回结果处理
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                tvGpsspeed.setText("0.0");
                tvMilleage.setText("");
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    showDiaLogLoading(false);
                    searchvehicleInfo = (VehicleInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHINFO);
                    if (searchvehicleInfo != null) {
                        vehiclePresenter.getVehiclePosition(searchvehicleInfo.getColor(), searchvehicleInfo.getRegistrationNo());
                        firstlocation = true;
                        lvVehicleIsOpen = false;

                        SearchvehicleInfoList.clear();
                        SearchvehicleInfoList.add(searchvehicleInfo);
                        if (!lvVehicleIsOpen) {
                            vehicleAdapter = new VehicleAdapter(mContext, SearchvehicleInfoList);
                            lvVehicle.setAdapter(vehicleAdapter);
                        }
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
            vehicleInfo = result.getData(VehicleInfo.class);
            Message msg = new Message();
            msg.what = 0;
            timeHandler.sendMessage(msg);
        } else {
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    //添加侧拉弹窗
    public void initMenu() {
        Menu mMenu = new Menu(false, 0);
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(CommonUtils.getDrawable(mContext, R.drawable.carteam_runningback))
                .setIcon(getResources().getDrawable(R.drawable.ico_locus_play_back))
                .setText(getString(R.string.my_service_2))
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setTextColor(Color.WHITE)
                .setTextSize(13)
                .build());

        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(CommonUtils.getDrawable(mContext, R.drawable.carteam_surveillanceback))
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setIcon(getResources().getDrawable(R.drawable.ico_video))
                .setText(getString(R.string.my_service_4))
                .setTextColor(Color.WHITE)
                .setTextSize(13)
                .build());
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(CommonUtils.getDrawable(mContext, R.drawable.carteam_positionback))
                .setDirection(MenuItem.DIRECTION_RIGHT)//设置右侧展开
                .setIcon(getResources().getDrawable(R.drawable.ico_car))
                .setText(getString(R.string.my_service_3))
                .setTextColor(Color.WHITE)
                .setTextSize(13)
                .build());
        menus.add(mMenu);

        Menu mMenu2 = new Menu(false, 1);
        mMenu2.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(CommonUtils.getDrawable(mContext, R.drawable.carteam_runningback))
                .setIcon(getResources().getDrawable(R.drawable.ico_locus_play_back))
                .setText(getString(R.string.my_service_2))
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setTextColor(Color.WHITE)
                .setTextSize(13)
                .build());
        mMenu2.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(CommonUtils.getDrawable(mContext, R.drawable.carteam_positionback))
                .setDirection(MenuItem.DIRECTION_RIGHT)//设置右侧展开
                .setIcon(getResources().getDrawable(R.drawable.ico_car))
                .setText(getString(R.string.my_service_3))
                .setTextColor(Color.WHITE)
                .setTextSize(13)
                .build());
        menus.add(mMenu2);

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
    public void onDestroyView() {
        super.onDestroyView();
        MapView.onDestroy();
        timeHandler.removeCallbacks(runnable);
        ButterKnife.reset(this);
    }


    //显示加载动画
    public void showDiaLogLoading(boolean cancelable) {
        updataDig = new Dialog(mContext, R.style.loading_dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.loading_dialog, null);
        updataDig.setContentView(view);
        updataDig.setCancelable(cancelable);//设置背景是否可以点击
        iv_progress = (ImageView) view.findViewById(R.id.iv_progress);
        iv_progress2 = (ImageView) view.findViewById(R.id.iv_progress2);
        Animation();
        updataDig.show();
    }

    //隐藏加载动画
    public void HideDiaLogLoading() {
        if (iv_progress != null) {
            iv_progress.clearAnimation();
        }
        if (iv_progress2 != null) {
            iv_progress2.clearAnimation();
        }
        if (updataDig != null)
            updataDig.dismiss();
    }

    //控制动画
    public void Animation() {
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotate2 = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //默认为0，为-1时一直循环动画
        rotate.setRepeatCount(-1);
        rotate2.setRepeatCount(-1);
        rotate.setDuration(1000);
        rotate2.setDuration(1000);
        rotate.setFillAfter(true);
        rotate2.setFillAfter(true);
        LinearInterpolator lir = new LinearInterpolator();
        rotate.setInterpolator(lir);
        rotate2.setInterpolator(lir);
        iv_progress.startAnimation(rotate);
        iv_progress2.startAnimation(rotate2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_Navigation://导航
                if (AMapUtil.isInstallByRead("com.autonavi.minimap")) {
                    AMapUtil.goToNaviActivity(mContext, "test", null, vehicleInfo.getLatitude() + "", vehicleInfo.getLongitude() + "", "1", "2");
                } else {
                    Toast.makeText(mContext, "未安装高德地图", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_traffic://路况
                if (blTraffic) {
                    blTraffic = false;
                    btTraffic.setBackground(getResources().getDrawable(R.drawable.btn_road_condition_pre));
                    aMap.setTrafficEnabled(true);
                } else {
                    blTraffic = true;
                    btTraffic.setBackground(getResources().getDrawable(R.drawable.btn_road_condition));
                    aMap.setTrafficEnabled(false);
                }
                break;
            case R.id.bt_location://定位
                aMap.animateCamera(CameraUpdateFactory.newLatLng(myLatlng), 500, null);
                break;
            case R.id.tv_searchCar://搜索
                Intent intent = new Intent(mContext, SearchVehicleActivity.class);
                startActivityForResult(intent, 0);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    //重复点击按钮处理
    public abstract class NoDoubleClickListener implements View.OnClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 1500;
        private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            }
        }

        protected abstract void onNoDoubleClick(View v);
    }


    @Override
    public void onSlideOpen(View view, View parentView, int position, int direction) {
        lvVehicleIsOpen = true;
    }

    @Override
    public void onSlideClose(View view, View parentView, int position, int direction) {
        lvVehicleIsOpen = false;
    }
}
