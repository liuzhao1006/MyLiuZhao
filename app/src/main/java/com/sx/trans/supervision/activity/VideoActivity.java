package com.sx.trans.supervision.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.Marker.VideoInfoWinAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.app.UrlConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.utils.AMapUtil;
import com.sx.trans.supervision.utils.JSONUtils;
import com.sx.trans.supervision.widget.ijkplayer.media.IjkVideoView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by mr_wang on 2017/8/31.
 * 实时视频
 */

public class VideoActivity extends BaseActivity implements View.OnClickListener {

    private TextureMapView mapView;
    private IjkVideoView mVideoView;
    private ImageView btPlay1;
    private IjkVideoView mVideoView2;
    private ImageView btPlay2;
    private IjkVideoView mVideoView3;
    private ImageView btPlay3;
    private IjkVideoView mVideoView4;
    private ImageView btPlay4;

    private void initControls() {


        mapView = findViewById(R.id.mapView);
        mVideoView = findViewById(R.id.surfaceView);
        btPlay1 = findViewById(R.id.bt_play1);
        btPlay1.setOnClickListener(this);
        mVideoView2 = findViewById(R.id.surfaceView2);
        btPlay2 = findViewById(R.id.bt_play2);
        btPlay2.setOnClickListener(this);
        mVideoView3 = findViewById(R.id.surfaceView3);
        btPlay3 = findViewById(R.id.bt_play3);
        btPlay3.setOnClickListener(this);
        mVideoView4 = findViewById(R.id.surfaceView4);
        btPlay4 = findViewById(R.id.bt_play4);
        btPlay4.setOnClickListener(this);


    }

    private AMap aMap;
    private UiSettings uiSettings;
    private VideoInfoWinAdapter adapter;
    private int vehicleColor = 0;
    private String registrationNo = "";
    private VehicleInfo vehicleInfo;


    private boolean playing1 = false, playing2 = false, playing3 = false, playing4 = false;
    private String path1, path2, path3, path4;
    private Animation operatingAnim, operatingAnim2, operatingAnim3, operatingAnim4;
    private String channel1, channel2, channel3, channel4;//通道号
    private String Hongdian = "127.0.0.1_6102";

    private String TransmitIP = "";//ip地址
    private String ID = "";//设备ID号
    private String PhonePort = "";//端口号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initControls();
        init(savedInstanceState);
    }

    //初始化操作
    private void init(Bundle savedInstanceState) {
        setMapView(savedInstanceState);
        getIntentDate();
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        setVideoPreparedListener();
//        mVideoView.setRender(IRenderView.AR_ASPECT_FIT_PARENT);
//        mVideoView2.setRender(IRenderView.AR_ASPECT_FIT_PARENT);
//        mVideoView3.setRender(IRenderView.AR_ASPECT_FIT_PARENT);
//        mVideoView4.setRender(IRenderView.AR_ASPECT_FIT_PARENT);
    }

    //获得车辆基本信息
    private void getIntentDate() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                VehicleInfo vehicleInfo = (VehicleInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHINFO);
                if (vehicleInfo != null) {
                    vehicleColor = vehicleInfo.getColor();
                    registrationNo = vehicleInfo.getRegistrationNo();
                    setTitle(true, registrationNo, false, null);
                    setVideoUrl(vehicleInfo.getParamValue());
                    showDiaLogLoading(false);
                    timeHandler.postDelayed(runnable, 0);// 打开定时器，延迟这个时长后执行操作 获取车辆信息
                }
            }
        }
    }

    //设置视频监听
    private void setVideoPreparedListener() {
        mVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                if (what != 10001) {
                    stopLoadAnimal(0);
                } else {
                    setNoplayView(0);
                }
                return false;
            }
        });
        mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                setNoplayView(0);
                return true;
            }
        });
        mVideoView2.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                if (what != 10001) {
                    stopLoadAnimal(1);
                } else {
                    setNoplayView(1);
                }
                return false;
            }
        });
        mVideoView2.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                setNoplayView(1);
                return true;
            }
        });
        mVideoView3.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                if (what != 10001) {
                    stopLoadAnimal(2);
                } else {
                    setNoplayView(2);
                }
                return false;
            }
        });
        mVideoView3.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                setNoplayView(2);
                return true;
            }
        });
        mVideoView4.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                if (what != 10001) {
                    stopLoadAnimal(3);
                } else {
                    setNoplayView(3);
                }
                return false;
            }
        });
        mVideoView4.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                setNoplayView(3);
                return true;
            }
        });
    }

    //设置宏电视频源url
    public void setMediaHDUrl(String _path1, String _path2, String _path3, String _path4) {
        path1 = _path1;
        path2 = _path2;
        path3 = _path3;
        path4 = _path4;
        if (path1 != null || path1.length() > 0) {
            playing1 = true;
        } else {
            setNoplayView(0);
        }
        if (path2 != null && path2.length() > 0) {
            playing2 = true;
        } else {
            setNoplayView(1);
        }

        if (path3 != null && path3.length() > 0) {
            playing3 = true;
        } else {
            setNoplayView(2);
        }

        if (path4 != null && path4.length() > 0) {
            playing4 = true;
        } else {
            setNoplayView(3);
        }
    }

    //设置无法播放的视频view
    private void setNoplayView(int position) {
        if (position == 0) {
            btPlay1.setBackground(getResources().getDrawable(R.drawable.video_none));
            btPlay1.setClickable(false);
            btPlay1.setVisibility(View.VISIBLE);
            if (operatingAnim != null)
                btPlay1.clearAnimation();
        }
        if (position == 1) {
            btPlay2.setBackground(getResources().getDrawable(R.drawable.video_none));
            btPlay2.setClickable(false);
            btPlay2.setVisibility(View.VISIBLE);
            if (operatingAnim2 != null)
                btPlay2.clearAnimation();
        }
        if (position == 2) {
            btPlay3.setBackground(getResources().getDrawable(R.drawable.video_none));
            btPlay3.setClickable(false);
            btPlay3.setVisibility(View.VISIBLE);
            if (operatingAnim3 != null)
                btPlay3.clearAnimation();
        }
        if (position == 3) {
            btPlay4.setBackground(getResources().getDrawable(R.drawable.video_none));
            btPlay4.setClickable(false);
            btPlay4.setVisibility(View.VISIBLE);
            if (operatingAnim4 != null)
                btPlay4.clearAnimation();
        }
    }


    //停止对应加载动画
    private void stopLoadAnimal(int postion) {
        if (postion == 0) {
            if (operatingAnim != null)
                btPlay1.clearAnimation();
            btPlay1.setVisibility(View.GONE);
        }
        if (postion == 1) {
            if (operatingAnim2 != null)
                btPlay2.clearAnimation();
            btPlay2.setVisibility(View.GONE);
        }
        if (postion == 2) {
            if (operatingAnim3 != null)
                btPlay3.clearAnimation();
            btPlay3.setVisibility(View.GONE);
        }
        if (postion == 3) {
            if (operatingAnim4 != null)
                btPlay4.clearAnimation();
            btPlay4.setVisibility(View.GONE);
        }
    }

    //初始化地图控件
    private void setMapView(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();
        uiSettings = aMap.getUiSettings();
        uiSettings.setTiltGesturesEnabled(false);// 禁用倾斜手势。
        uiSettings.setRotateGesturesEnabled(false);// 禁用旋转手势。
        uiSettings.setZoomControlsEnabled(false);//去除默认放大缩小按钮
//        //隐藏高德地图图标
        uiSettings.setLogoBottomMargin(-50);
//        //设置自定义marker弹窗
        adapter = new VideoInfoWinAdapter();
        aMap.setInfoWindowAdapter(adapter);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getVehiclePosition(vehicleColor, registrationNo);
            timeHandler.postDelayed(this, 20 * 1000);// 每隔20秒去一次
        }
    };


    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (vehicleInfo != null) {
                        if (vehicleInfo.getLatitude() > 1 && vehicleInfo.getLongitude() > 1) {
                            LatLng vehicleLatlng = AMapUtil.getGpsToLatlng(new LatLng(vehicleInfo.getLatitude(), vehicleInfo.getLongitude()));
                            Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0f)
                                    .position(vehicleLatlng)
                                    .title(vehicleInfo.getAccStatus() + "")
                                    .snippet(vehicleInfo.getGpsSpeed() + "km/h")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.video_location)));
                            marker.showInfoWindow();
                            aMap.animateCamera(CameraUpdateFactory.newLatLng(vehicleLatlng), 500, null);
                        }
                    }
                    break;
                case 1:
                    setNoplayView(0);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }
    };

    //请求车辆定位信息
    public void getVehiclePosition(int color, String regisNo) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("regisNo", regisNo);
        OkHttpUtils
                .get()//
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH, ""))
                .url(UrlConstants.VEHICLEPOSITION + color)//
                .params(params)
                .build()//
                .execute(new MyStringCallback());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_play1:
                if (playing1) {
                    mVideoView.setVideoPath(path1);
                    mVideoView.start();
                    btPlay1.setBackground(getResources().getDrawable(R.drawable.video_loading));
                    // 加载动画
                    operatingAnim = AnimationUtils.loadAnimation(this, R.anim.video_loading);
                    LinearInterpolator lin = new LinearInterpolator();
                    operatingAnim.setInterpolator(lin);
                    // 使用ImageView显示动画
                    btPlay1.startAnimation(operatingAnim);
                    playing1 = false;
                }
                break;

            case R.id.bt_play2:
                if (playing2) {
                    mVideoView2.setVideoPath(path2);
                    mVideoView2.start();
                    btPlay2.setBackground(getResources().getDrawable(R.drawable.video_loading));
                    // 加载动画
                    operatingAnim2 = AnimationUtils.loadAnimation(this, R.anim.video_loading);
                    LinearInterpolator lin2 = new LinearInterpolator();
                    operatingAnim2.setInterpolator(lin2);
                    // 使用ImageView显示动画
                    btPlay2.startAnimation(operatingAnim2);
                    playing2 = false;
                }
                break;

            case R.id.bt_play3:
                if (playing3) {
                    mVideoView3.setVideoPath(path3);
                    mVideoView3.start();
                    btPlay3.setBackground(getResources().getDrawable(R.drawable.video_loading));
                    // 加载动画
                    operatingAnim3 = AnimationUtils.loadAnimation(this, R.anim.video_loading);
                    LinearInterpolator lin3 = new LinearInterpolator();
                    operatingAnim3.setInterpolator(lin3);
                    // 使用ImageView显示动画
                    btPlay3.startAnimation(operatingAnim3);
                    playing3 = false;
                }
                break;

            case R.id.bt_play4:
                if (playing4) {
                    mVideoView4.setVideoPath(path4);
                    mVideoView4.start();
                    btPlay4.setBackground(getResources().getDrawable(R.drawable.video_loading));
                    // 加载动画
                    operatingAnim4 = AnimationUtils.loadAnimation(this, R.anim.video_loading);
                    LinearInterpolator lin4 = new LinearInterpolator();
                    operatingAnim4.setInterpolator(lin4);
                    // 使用ImageView显示动画
                    btPlay4.startAnimation(operatingAnim4);
                    playing4 = false;
                }
                break;
        }
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.e(TAG, "loading");
        }

        @Override
        public void onAfter(int id) {
            Log.e(TAG, "Sample-okHttp");
            HideDiaLogLoading();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            if (e.toString().contains("SocketTimeout")) {//连接超时提示
                Toast.makeText(mContext, R.string.socket_time_out, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, R.string.no_network, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onResponse(String response, int id) {
            try {
                Result result = JSONUtils.fromJson(response, Result.class);
                if (result.getCode() == 0) {
                    vehicleInfo = result.getData(VehicleInfo.class);
                    Message msg = new Message();
                    msg.what = 0;
                    timeHandler.sendMessage(msg);
                }
            } catch (Exception e) {
                Log.d(TAG, "异常信息:" + e.getMessage());
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

    /**
     * 设置视频源对象
     * TransmitIP->ip地址
     * ID->设备ID号
     * PhonePort->//端口号
     * Channel->通道号
     */
    public void setVideoUrl(String paramValue) {
        if (paramValue != null && paramValue.length() > 0) {//宏电
            getHongdianUrl(paramValue);
        } else {
            setNoplayView(0);
            setNoplayView(1);
            setNoplayView(2);
            setNoplayView(3);
        }
    }

    //获得宏电的url地址
    private void getHongdianUrl(String paramValue) {
        String HDpath1 = null;
        String HDpath2 = null;
        String HDpath3 = null;
        String HDpath4 = null;
        try {
            String[] Value = paramValue.split(",");
            if (Value.length > 0) {
                String ChannelAll = "";
                for (int i = 0; i < Value.length; i++) {
                    if (Value[i].startsWith("ip")) {//ip地址
                        TransmitIP = Value[i].substring(Value[i].indexOf("=") + 1);
                    }
                    if (Value[i].startsWith("deviceId")) {//设备ID号
                        ID = Value[i].substring(Value[i].indexOf("=") + 1);
                    }
                    if (Value[i].startsWith("PhonePort")) {//端口号
                        PhonePort = Value[i].substring(Value[i].indexOf("=") + 1);
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "paramValue解析异常");
        }
        channel1 = "1";
        channel2 = "2";
        channel3 = "3";
        channel4 = "4";
        //rtsp://58.67.161.51:52470/127.0.0.1_6102_013282701441_1_30868.iscan
        HDpath1 = "rtsp://" + TransmitIP + ":" + PhonePort + "/" + Hongdian + "_" + ID + "_" + channel1 + "_" + getNumber() + ".iscan";
        Log.d(TAG, "setVideoUrl:" + HDpath1);
        HDpath2 = "rtsp://" + TransmitIP + ":" + PhonePort + "/" + Hongdian + "_" + ID + "_" + channel2 + "_" + getNumber() + ".iscan";
        Log.d(TAG, "setVideoUrl:" + HDpath2);
        HDpath3 = "rtsp://" + TransmitIP + ":" + PhonePort + "/" + Hongdian + "_" + ID + "_" + channel3 + "_" + getNumber() + ".iscan";
        Log.d(TAG, "setVideoUrl:" + HDpath3);
        HDpath4 = "rtsp://" + TransmitIP + ":" + PhonePort + "/" + Hongdian + "_" + ID + "_" + channel4 + "_" + getNumber() + ".iscan";
        Log.d(TAG, "setVideoUrl:" + HDpath4);

        if (TransmitIP == null || TransmitIP.length() == 0 || PhonePort == null || PhonePort.length() == 0 || ID == null || ID.length() == 0) {
            HDpath1 = null;
            HDpath2 = null;
            HDpath3 = null;
            HDpath4 = null;
        } else {
            if (channel1 == null || channel1.length() == 0) {
                HDpath1 = null;
            }
            if (channel2 == null || channel2.length() == 0) {
                HDpath2 = null;
            }
            if (channel3 == null || channel3.length() == 0) {
                HDpath3 = null;
            }
            if (channel3 == null || channel4.length() == 0) {
                HDpath4 = null;
            }
        }
        setMediaHDUrl(HDpath1, HDpath2, HDpath3, HDpath4);
    }

    //获得随机数
    private String getNumber() {
        Random random = new Random();
        return random.nextInt(65535) + 1 + "";
    }

    //停止播放视频
    private void stopVideo() {
        if (!mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        if (!mVideoView2.isBackgroundPlayEnabled()) {
            mVideoView2.stopPlayback();
            mVideoView2.release(true);
            mVideoView2.stopBackgroundPlay();
        } else {
            mVideoView2.enterBackground();
        }
        if (!mVideoView3.isBackgroundPlayEnabled()) {
            mVideoView3.stopPlayback();
            mVideoView3.release(true);
            mVideoView3.stopBackgroundPlay();
        } else {
            mVideoView3.enterBackground();
        }
        if (!mVideoView4.isBackgroundPlayEnabled()) {
            mVideoView4.stopPlayback();
            mVideoView4.release(true);
            mVideoView4.stopBackgroundPlay();
        } else {
            mVideoView4.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    protected void onStop() {
        Log.d("activity", "onStop");
        try {
            super.onStop();
            stopVideo();
            stopLoadAnimal(0);
            stopLoadAnimal(1);
            stopLoadAnimal(2);
            stopLoadAnimal(3);
            timeHandler.removeCallbacks(runnable);
            finish();
        } catch (Exception e) {
            Log.d("vlc-stop", e.toString());
        }
    }

    @Override
    public void onResume() {
        try {
            super.onResume();
            mapView.onResume();
        } catch (Exception e) {
            Log.d("vlc-resume", e.toString());
        }
    }

    @Override
    public void onPause() {
        try {
            super.onPause();
            mapView.onPause();
        } catch (Exception e) {
            Log.d("vlc-pause", e.toString());
        }
    }

    @Override
    public void onDestroy() {
        try {
            super.onDestroy();
            mapView.onDestroy();
            ButterKnife.reset(this);
        } catch (Exception e) {
            Log.d("vlc-destroy", e.toString());
        }
    }
}
