package com.sx.trans.app;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CoordinateConverter;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.supervision.utils.ImageLoaderUtil;
import com.sx.trans.supervision.utils.SharedPreferenceProxy;
import com.sx.trans.transport.ui.MainActivity;
import com.sx.trans.widget.font.LzTextField;
import com.sx.trans.widget.font.LzTypeface;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import org.litepal.LitePalApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.sx.trans.app.LoginConfig.DEVICE_TOKEN;


/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 * APP入口
 */

public class LzApp extends LitePalApplication implements AMapLocationListener {

    public static Context context;
    public static PushAgent mPushAgent;

    public static Application getInstance() {
        return _instance;
    }

    private static Application _instance;

    public static List<Activity> activityList = new LinkedList<Activity>();
    public static LzApp mInstance;
    public static SharedPreferences mSpf;
    public static SharedPreferences.Editor editor;
    public static SharedPreferenceProxy mSpfProxy;
    public static int mMainThreadId;
    public static Looper mMainLooper;
    public static Thread mMainThread;
    public static CoordinateConverter converter;
    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    public static Double LocLat = 0d;//当前定位纬度
    public static Double LocLong = 0d;//当前定位纬度

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 100;//定位

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        _instance = this;


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/custom_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .addCustomViewWithSetTypeface(LzTypeface.class)
                .addCustomStyle(LzTextField.class, R.attr.textFieldStyle)
                .build()
        );
        Logger.setDebug(true);
        Logger.setTag("LzCloud:");
        NoHttp.initialize(this, new NoHttp.Config()
                .setConnectTimeout(30 * 1000)
                .setReadTimeout(30 * 1000)
                .setCacheStore(
                        new DBCacheStore(this)
                                .setEnable(true)
                )
                .setCacheStore(
                        new DiskCacheStore(this)
                )
                .setCookieStore(
                        new DBCookieStore(this)
                                .setEnable(false)
                )
                .setNetworkExecutor(new OkHttpNetworkExecutor())
        );

        converter = new CoordinateConverter(this);
        converter.from(CoordinateConverter.CoordType.GPS);
        mInstance = this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainLooper = getMainLooper();
        mSpf = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mSpf.edit();
        mSpfProxy = SharedPreferenceProxy.getInstance(this, "DriverApp");

        ImageLoaderUtil.initImageLoaderConfig(this);

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        // 开始定位
        initLoc();
        mPushAgent = PushAgent.getInstance(this);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                PrefUtils.putString(getApplication(), DEVICE_TOKEN, deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.i(s + "23werwerwer");
            }

        });
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.text.toString(), Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                intent.putExtra("yMmsg",msg.text.toString());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        };
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);

    }


    public static Context getContext() {
        return context;
    }


    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public static boolean contains(Activity activity) {
        return activityList.contains(activity);
    }

    public static int activityListSize() {
        return activityList.size();
    }

    public static void exit() {

        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();

    }

    /**
     * 定位
     */
    private void initLoc() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 定位回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                LocLat = amapLocation.getLatitude();//获取纬度
                LocLong = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());

//                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }


    public static LzApp getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }


    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 返回当前程序版本号
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 1;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionCode;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    protected String a() {
        return null;
    }

    protected String b() {
        return null;
    }


}
