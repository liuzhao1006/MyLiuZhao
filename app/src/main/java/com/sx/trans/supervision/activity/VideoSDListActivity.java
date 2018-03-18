package com.sx.trans.supervision.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

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
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.app.UrlConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.utils.AMapUtil;
import com.sx.trans.supervision.utils.CommonRequest;
import com.sx.trans.supervision.utils.CommonRequest.ErrorResponseListener;
import com.sx.trans.supervision.utils.CommonRequest.SuccessResponseListener;
import com.sx.trans.supervision.utils.JSONUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 顺达视频
 *
 * @author Administrator
 */
public class VideoSDListActivity extends BaseActivity implements OnErrorListener, View.OnClickListener {

    /**
     * 正在播放的VideoView对应CheckBox集合
     */
    private Map<String, Integer> map = new HashMap<String, Integer>();
    private List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
    /**
     * VideoView对应url集合
     */
    private Map<Integer, String> urlMap = new HashMap<Integer, String>();
    private int[] checkBoxIDs;

    /**
     * VideoView数组
     */
    private VideoView[] videoViews;
    /**
     * 旋转框数组
     */
    private ProgressBar[] progressBars;
    private int currentVideoIndex;
    /**
     * 正在播放的地址集合
     */
    private List<Integer> checkVideoPath = new ArrayList<Integer>();

    /**
     * 加载失败提示语
     */
    private String requestError = "加载失败 , 请稍后重试";

    /**
     * 播放超时提示语
     */
    private String timeoutError = "请求超时 , 请稍后重试";

    /**
     * 发送心跳间隔时间
     */
    private long SEND_HEART_TIME = 10 * 1000;

    /**
     * 当前视频模块绑定计时器
     */
    private VideoTimer firstVideoTimer, secondVideoTimer, thirdVideoTimer,
            fourthVideoTimer;

    /**
     * 计时器集合
     */
    private VideoTimer[] videoTimers;

    /**
     * 上次点击时间设置防爆点击
     */
    private long lastClickTime;

    /**
     * 计时器集合页面关闭时关闭计时器
     */
    private List<CountDownTimer> timeOutTimers = new ArrayList<CountDownTimer>();

    private String sessionID = getTime();//时间戳
    private String carNum = "";
    private String IP;
    private String simID = "13000010009";//  * sim卡号
    private String PhonePort;

    private Button bt_play1;
    private Button bt_play2;
    private Button bt_play3;
    private Button bt_play4;
    private VideoView mVvFirst;
    private VideoView mVvSecond;
    private VideoView mVvThird;
    private VideoView mVvFourth;
    private ProgressBar mPbFirst;
    private ProgressBar mPbSecond;
    private ProgressBar mPbThird;
    private ProgressBar mPbFourth;
    private TextureMapView mapView;

    private void initControls() {
        bt_play1 = findViewById(R.id.bt_play1);
        bt_play2 = findViewById(R.id.bt_play2);
        bt_play3 = findViewById(R.id.bt_play3);
        bt_play4 = findViewById(R.id.bt_play4);
        mVvFirst = findViewById(R.id.first_video_view);
        bt_play1.setOnClickListener(this);
        mVvSecond = findViewById(R.id.second_video_view);
        bt_play2.setOnClickListener(this);
        mVvThird = findViewById(R.id.third_video_view);
        bt_play3.setOnClickListener(this);
        mVvFourth = findViewById(R.id.fourth_video_view);
        bt_play4.setOnClickListener(this);
        mPbFirst = findViewById(R.id.first_progressBar);
        mPbSecond = findViewById(R.id.second_progressBar);
        mPbThird = findViewById(R.id.third_progressBar);
        mPbFourth = findViewById(R.id.fourth_progressBar);
        mapView = findViewById(R.id.map);
    }


    private AMap aMap;
    private VideoInfoWinAdapter adapter;
    private UiSettings uiSettings;
    private int vehicleColor = 0;
    private String registrationNo = "";
    private VehicleInfo vehicleInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdvideo);
        x.view().inject(this);
        initControls();
        init();
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        setMapView();//初始化地图

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                VehicleInfo vehicleInfo = (VehicleInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHINFO);
                if (vehicleInfo != null) {
                    vehicleColor = vehicleInfo.getColor();
                    registrationNo = vehicleInfo.getRegistrationNo();
                    carNum = vehicleInfo.getRegistrationNo() != null ? vehicleInfo.getRegistrationNo() : "-";
                    setTitle(true, registrationNo, false, null);
                    String paramValue = vehicleInfo.getParamValue();
                    String[] Value = paramValue.split(",");
                    for (int i = 0; i < Value.length; i++) {
                        if (Value[i].startsWith("TransmitIP")) {
                            IP = Value[i].substring(Value[i].indexOf("=") + 1);
                        }
                        if (Value[i].startsWith("ID")) {
                            simID = Value[i].substring(Value[i].indexOf("=") + 1);
                        }
                        if (Value[i].startsWith("PhonePort")) {
                            PhonePort = Value[i].substring(Value[i].indexOf("=") + 1);
                        }
                    }
                    showDiaLogLoading(false);
                    timeHandler.postDelayed(runnable, 0);// 打开定时器，延迟这个时长后执行操作 获取车辆定位信息
                }
            }
        }
    }

    // 初始化VideoView//////five six seven eight
    private void init() {
        videoViews = new VideoView[]{mVvFirst, mVvSecond, mVvThird,
                mVvFourth};
        for (int i = 0; i < videoViews.length; i++) {
            videoViews[i].requestFocus();
            videoViews[i].setClickable(true);
            videoViews[i].setOnErrorListener(this);
        }
        progressBars = new ProgressBar[]{mPbFirst, mPbSecond, mPbThird,
                mPbFourth};
        checkBoxIDs = new int[]{R.id.bt_play1, R.id.bt_play2,
                R.id.bt_play3, R.id.bt_play4,
        };
        // ////five six seven eight
        videoTimers = new VideoTimer[]{firstVideoTimer, secondVideoTimer,
                thirdVideoTimer, fourthVideoTimer};
        // 给每个VideoView默认赋空URL
        for (int i = 0; i < 4; i++) {
            urlMap.put(i, "");
        }
        mVvFirst.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer arg0) {
                mPbFirst.setVisibility(View.GONE);
            }
        });
        mVvSecond.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer arg0) {
                mPbSecond.setVisibility(View.GONE);
            }
        });
        mVvThird.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer arg0) {
                mPbThird.setVisibility(View.GONE);
            }
        });
        mVvFourth.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer arg0) {
                mPbFourth.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < checkBoxIDs.length; i++) {
            if (checkBoxIDs[i] == v.getId()) {
                checkVideoPath.clear();
                for (int k = 0; k < list.size(); k++) {
                    checkVideoPath.add(list.get(k).get("VideoView"));
                }
                if (!checkVideoPath.contains(i)) {//j
                    map = new HashMap<String, Integer>();
                    currentVideoIndex = i;//j
                    progressBars[i].setVisibility(View.VISIBLE);//j
                    setBtPlay(i, false);
                    map.put("VideoView", i);//j
                    map.put("CheckBox", i);
                    list.add(map);
                    // 设置请求超时Timer
                    TimeOutTimer timer = new TimeOutTimer(i, i);// j  i
                    timer.start();
                    timeOutTimers.add(timer);
                    requestVideo(i + 1, 1, successResponseListener, errorListener);
                    return;
//						}

                } else {// 线路选中后的点击事件
                    for (int j = 0; j < list.size(); j++) {
                        if (i == list.get(j).get("CheckBox")) {
                            int checkedVideo = list.get(j).get("VideoView");
                            progressBars[checkedVideo].setVisibility(View.GONE);
                            list.remove(j);
                            if (videoTimers[checkedVideo] != null) {
                                videoTimers[checkedVideo].cancel();
                            }
                            videoViews[checkedVideo].stopPlayback();
                            // 清空当前View播放地址
                            videoViews[checkedVideo].setVideoURI(Uri.parse(""));
                            // 设置当前VideoView变黑，不展示缓存图片
                            videoViews[checkedVideo]
                                    .setVisibility(View.INVISIBLE);
                            videoViews[checkedVideo]
                                    .setVisibility(View.VISIBLE);
                            // 关闭时告知服务器
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = i + 1;
                            handler.sendMessage(msg);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void onViewClicked(View view) {
        for (int i = 0; i < checkBoxIDs.length; i++) {
            if (checkBoxIDs[i] == view.getId()) {
                checkVideoPath.clear();
                for (int k = 0; k < list.size(); k++) {
                    checkVideoPath.add(list.get(k).get("VideoView"));
                }
                if (!checkVideoPath.contains(i)) {//j
                    map = new HashMap<String, Integer>();
                    currentVideoIndex = i;//j
                    progressBars[i].setVisibility(View.VISIBLE);//j
                    setBtPlay(i, false);
                    map.put("VideoView", i);//j
                    map.put("CheckBox", i);
                    list.add(map);
                    // 设置请求超时Timer
                    TimeOutTimer timer = new TimeOutTimer(i, i);// j  i
                    timer.start();
                    timeOutTimers.add(timer);
                    requestVideo(i + 1, 1, successResponseListener, errorListener);
                    return;
//						}

                } else {// 线路选中后的点击事件
                    for (int j = 0; j < list.size(); j++) {
                        if (i == list.get(j).get("CheckBox")) {
                            int checkedVideo = list.get(j).get("VideoView");
                            progressBars[checkedVideo].setVisibility(View.GONE);
                            list.remove(j);
                            if (videoTimers[checkedVideo] != null) {
                                videoTimers[checkedVideo].cancel();
                            }
                            videoViews[checkedVideo].stopPlayback();
                            // 清空当前View播放地址
                            videoViews[checkedVideo].setVideoURI(Uri.parse(""));
                            // 设置当前VideoView变黑，不展示缓存图片
                            videoViews[checkedVideo]
                                    .setVisibility(View.INVISIBLE);
                            videoViews[checkedVideo]
                                    .setVisibility(View.VISIBLE);
                            // 关闭时告知服务器
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = i + 1;
                            handler.sendMessage(msg);
                            return;
                        }
                    }
                }
            }
        }
    }


    /**
     * 通用Handler
     */
    private Handler handler = new Handler(new Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // 请求失败时关闭线路
                    requestVideo(Integer.valueOf("" + msg.obj), 2, listener,
                            errorListener);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    /**
     * 发送心跳线程
     */
    private Runnable videoHeartRunnable = new Runnable() {

        @Override
        public void run() {
            if (list.size() > 0) {
                // 心跳固定发线路15
                requestVideo(15, 3, listener, errorListener);
            }
            handler.postDelayed(videoHeartRunnable, SEND_HEART_TIME);
        }
    };

    /**
     * 一.请求视频流地址； 二.打开、关闭、发送心跳给相应视频流
     *
     * @param devChn        线路号
     * @param cmdType       视频流开启或关闭（1:开启2:关闭 3:心跳）
     * @param listener      成功回调
     * @param errorListener 失败回调
     */
    private void requestVideo(int devChn, int cmdType,
                              SuccessResponseListener listener,
                              ErrorResponseListener errorListener) {
//		 String url = "http://58.67.161.53:6104/";// 外网地址
        String url = "http://" + IP + ":" + PhonePort;// 外网地址 亿程
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("simID", simID);// sim卡号
        map.put("sessionID", sessionID);// 时间戳(长度不大于4294967295的随机数，请求同一台设备需为同一台)
        map.put("devType", "0");// 0:GPRS-evo1:GPRS-wcdma
        map.put("devChn", String.valueOf(devChn));// 线路号
        map.put("cmdType", String.valueOf(cmdType));// 1:开启2:关闭 3:心跳
		Log.e("VideoSDListActivity","url:="+url+map);
        CommonRequest.sendRequest(this, url, map, listener, errorListener);
    }

    /**
     * 通用请求接口成功回调
     */
    private SuccessResponseListener successResponseListener = new SuccessResponseListener() {

        @Override
        public void onResponse(String result) {
            {
                // 请求地址失败
                if (result.contains("open fail")) {
//                    Toast.makeText(VideoSDListActivity.this, "请求视频失败", Toast.LENGTH_SHORT).show();
                    int currentVideo = 0, currentCheck = 0;
                    for (int p = 0; p < list.size(); p++) {
                        if (list.get(p).get(
                                "CheckBox") == Integer.valueOf(result.substring(
                                result.length() - 1,
                                result.length()))) {
                            currentVideo = list
                                    .get(p)
                                    .get("VideoView");
                            currentCheck = list
                                    .get(p)
                                    .get("CheckBox");
                        }
                    }
                    if (!videoViews[currentVideo]
                            .isPlaying()
                            && progressBars[currentVideo]
                            .getVisibility() == View.VISIBLE
                            && videoViews[currentVideo]
                            .getVisibility() == View.VISIBLE) {
                        // 通知服务器
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = currentCheck + 1;
                        handler.sendMessage(msg);
                        videoViews[currentVideo]
                                .stopPlayback();
                        progressBars[currentVideo]
                                .setVisibility(View.GONE);
                        setNoplayView(currentVideo);

                        for (int k = 0; k < list
                                .size(); k++) {
                            if (list.get(k).get(
                                    "VideoView") == currentVideo) {
                                list.remove(k);
                                return;
                            }
                        }
                    }
                    return;
                }
                // 请求地址成功

                if (result.contains("open ok:")) {
                    String url = result.replace(
                            "open ok:", "");
//                    Toast.makeText(VideoSDListActivity.this, "请求视频成功 , 正在加载", Toast.LENGTH_SHORT).show();
                    for (int m = 0; m < list.size(); m++) {
                        if (list.get(m).get(
                                "CheckBox") == Integer
                                .valueOf(url
                                        .subSequence(
                                                url.length() - 6,
                                                url.length() - 5)
                                        .toString())) {
                            currentVideoIndex = list
                                    .get(m)
                                    .get("VideoView");

                        }
                    }
                    // 缓存每个VideoView对应的URL
                    urlMap.put(currentVideoIndex,
                            url);
                    // 开启计时器，延时播放
                    VideoTimer currentTimer = new VideoTimer(
                            30000,
                            currentVideoIndex,
                            result.replace(
                                    "open ok:", ""));
                    currentTimer.start();
                    timeOutTimers.add(currentTimer);
                    videoTimers[currentVideoIndex] = currentTimer;
                    // 定时发送心跳
                    handler.removeCallbacks(videoHeartRunnable);
                    handler.postDelayed(
                            videoHeartRunnable,
                            SEND_HEART_TIME);
                }
            }
        }
    };

    /**
     * 播放视频
     *
     * @param videoView 当前VideoView
     * @param url       当前URL
     */
    private void playVideos(VideoView videoView, String url) {
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
    }


    /**
     * 请求视频url超时Timer
     */
    class TimeOutTimer extends CountDownTimer {
        private int currentCheck, currentVideo;

        public TimeOutTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 创建播放超时计时器
         *
         * @param currentVideoIndex 当前VideoView
         * @param currentCheckIndex 当前选中线路
         */
        public TimeOutTimer(int currentVideoIndex, int currentCheckIndex) {
            super(40 * 1000, 1000);
            this.currentVideo = currentVideoIndex;
            this.currentCheck = currentCheckIndex;
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            // TODO
            // 倒计时结束后如果未请求到有效播放地址则设置为请求超时
            if (!videoViews[currentVideo].isPlaying()
                    && progressBars[currentVideo].getVisibility() == View.VISIBLE
                    && videoViews[currentVideo].getVisibility() == View.VISIBLE) {
                if (handler != null) {
                    // 通知服务器
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = currentCheck + 1;
                    handler.sendMessage(msg);
                    Toast.makeText(getApplicationContext(), timeoutError, Toast.LENGTH_SHORT).show();
                    videoViews[currentVideo].stopPlayback();
                    progressBars[currentVideo].setVisibility(View.GONE);
                    setBtPlay(currentVideo, true);
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).get("VideoView") == currentVideo) {
                            list.remove(k);
                            return;
                        }
                    }
                }
            } else {
                setBtPlay(currentVideo, false);
            }
        }
    }

    /**
     * 延时播放视频Timer
     */
    class VideoTimer extends CountDownTimer {
        private int index;
        private String url;

        public VideoTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 延迟播放视频
         *
         * @param millisInFuture 延迟总时间（毫秒为单位）
         * @param url            播放地址
         */
        public VideoTimer(long millisInFuture, int videoViewIndex, String url) {
            super(millisInFuture, 1000);
            this.index = videoViewIndex;
            this.url = url;
            // this.url = "http://dlhls.cdn.zhanqi.tv/zqlive/45338_MRc2N.m3u8";
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 隔三秒尝试播放一次，若缓存区有数据，则立即播放
            if (millisUntilFinished / 1000 == 15
                    || millisUntilFinished / 1000 == 12
                    || millisUntilFinished / 1000 == 9
                    || millisUntilFinished / 1000 == 6
                    || millisUntilFinished / 1000 == 3) {
                if (!videoViews[index].isPlaying()) {
                    playVideos(videoViews[index], url);
                }
            }
        }

        @Override
        public void onFinish() {
            // 倒计时结束后视频如果不能播放则设置为请求超时（此处为请求到m3u8后不能播放超时）
            if (!videoViews[index].isPlaying()
                    && progressBars[index].getVisibility() == View.VISIBLE) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).get("VideoView") == index) {
                        list.remove(i);
                    }
                }
//                Toast.makeText(getApplicationContext(), requestError, Toast.LENGTH_SHORT).show();
                progressBars[index].setVisibility(View.GONE);
                setBtPlay(index, true);
                videoViews[index].setVideoURI(Uri.parse(""));// 清空缓存路径
            }
        }
    }

    //设置播放键是否显示
    private void setBtPlay(int index, boolean boolan) {
        if (bt_play1 != null && bt_play2 != null && bt_play3 != null && bt_play4 != null) {
            if (index == 0) {
                if (boolan == true) {
                    bt_play1.setVisibility(View.VISIBLE);
                } else {
                    bt_play1.setVisibility(View.GONE);
                }
            }
            if (index == 1) {
                if (boolan == true) {
                    bt_play2.setVisibility(View.VISIBLE);
                } else {
                    bt_play2.setVisibility(View.GONE);
                }
            }
            if (index == 2) {
                if (boolan == true) {
                    bt_play3.setVisibility(View.VISIBLE);
                } else {
                    bt_play3.setVisibility(View.GONE);
                }
            }
            if (index == 3) {
                if (boolan == true) {
                    bt_play4.setVisibility(View.VISIBLE);
                } else {
                    bt_play4.setVisibility(View.GONE);
                }
            }
        }
    }

    //地址无效禁止播放
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setNoplayView(int position) {
        if (position == 0) {
            bt_play1.setVisibility(View.VISIBLE);
            bt_play1.setBackground(getResources().getDrawable(R.drawable.video_none));
            bt_play1.setClickable(false);
        }
        if (position == 1) {
            bt_play2.setVisibility(View.VISIBLE);
            bt_play2.setBackground(getResources().getDrawable(R.drawable.video_none));
            bt_play2.setClickable(false);
        }
        if (position == 2) {
            bt_play3.setVisibility(View.VISIBLE);
            bt_play3.setBackground(getResources().getDrawable(R.drawable.video_none));
            bt_play3.setClickable(false);
        }
        if (position == 3) {
            bt_play4.setVisibility(View.VISIBLE);
            bt_play4.setBackground(getResources().getDrawable(R.drawable.video_none));
            bt_play4.setClickable(false);
        }
    }

    /**
     * 通用请求接口成功回调
     */
    private SuccessResponseListener listener = new SuccessResponseListener() {

        @Override
        public void onResponse(String result) {
            Log.d("video", "result:" + result);
            System.out.println(result);
        }
    };

    /**
     * 通用请求接口失败回调
     */
    private ErrorResponseListener errorListener = new ErrorResponseListener() {

        @Override
        public void onErrorResponse(String reason) {
            Log.d("video", "result:" + reason);
            System.out.println(reason);
        }
    };

    @Override
    public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
        return true;
    }

    /**
     * 获取当前的时间戳
     *
     * @return 时间戳
     */
    public static String getTime() {
        long time = System.currentTimeMillis() / 1000;// 获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        return str;
    }

    //地图初始化
    private void setMapView() {
        if (aMap == null) {
            aMap = mapView.getMap();
            uiSettings = aMap.getUiSettings();
            uiSettings.setTiltGesturesEnabled(false);// 禁用倾斜手势。
            uiSettings.setRotateGesturesEnabled(false);// 禁用旋转手势。
            uiSettings.setZoomControlsEnabled(false);//去除默认放大缩小按钮
            uiSettings.setLogoBottomMargin(-50);//隐藏高德地图图标
//            uiSettings.setAllGesturesEnabled(false);//禁止所有手势
        }
//        LatLng latLng = new LatLng(LzApp.LocLat, LzApp.LocLong);
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
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
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_location)));
                            marker.showInfoWindow();
                            aMap.animateCamera(CameraUpdateFactory.newLatLng(vehicleLatlng), 500, null);
                        }
                    }
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


    @Override
    protected void onStop() {
        try {
            super.onStop();
            timeOutTimers.clear();
            finish();
        } catch (Exception e) {
            Log.d("vlc-stop", e.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        timeHandler.removeCallbacks(runnable);
        handler.removeCallbacks(videoHeartRunnable);
        handler = null;
        timeOutTimers.clear();
        ButterKnife.reset(this);
    }
}
