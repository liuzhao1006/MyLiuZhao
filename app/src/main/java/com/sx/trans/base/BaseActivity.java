package com.sx.trans.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.lzlibrary.utils.Utils;
import com.sx.trans.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends Activity implements OnClickListener, BaseNetApi {

    public TextView lefttextTitle;
    public ImageView lefttextImg;
    public ImageView righttext;
    public TextView modletext;

    private long showTime = 0;
    protected Toast moToastInstance;
    private static final int SHORT_TOAST = Toast.LENGTH_SHORT;
    private static final int LONG_TOAST = Toast.LENGTH_LONG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTopView();
        initView();
        initData();
        initAction();
    }

    protected abstract void initView();

    protected abstract void initAction();

    protected abstract void initData();

    private void initTopView() {
        try {
            lefttextImg = (ImageView) findViewById(R.id.layout_top_leftimg);
            lefttextTitle = (TextView) findViewById(R.id.layout_top_leftinfo);
            modletext = (TextView) findViewById(R.id.layout_top_modleinfo);
            righttext = (ImageView) findViewById(R.id.layout_top_rightinfo);


            lefttextImg.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    finishInitAnim();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finishInitAnim();
    }

    public void finishInitAnim() {
        super.finish();

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // TODO Auto-generated method stub
        super.startActivityForResult(intent, requestCode);

    }


    public void cancelTopleftImg() {
        lefttextImg.setVisibility(View.GONE);
    }

    public void setTopleftImg(int leftImg) {
        lefttextImg.setBackgroundResource(leftImg);
        lefttextImg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTopleftText(String leftText) {
        lefttextTitle.setText(leftText);
    }

    public void setTopModleText(String modleText) {
        modletext.setText(modleText);
    }


    public void setTopRightIMG(int leftImg) {
        righttext.setVisibility(View.VISIBLE);
        righttext.setImageResource(leftImg);
    }

    public void setTopModleIMG(int leftImg) {
        modletext.setVisibility(View.VISIBLE);
        modletext.setBackgroundResource(leftImg);
    }


    /**
     * Toast 短时间,常用
     *
     * @param message
     */
    public void showToast(String message) {
        if (System.currentTimeMillis() - showTime > 3000) {
            if (moToastInstance == null)
                moToastInstance = Toast.makeText(this, message, SHORT_TOAST);
            else {
                moToastInstance.setDuration(SHORT_TOAST);
                moToastInstance.setText(message);
            }
            moToastInstance.show();
            showTime = System.currentTimeMillis();
        }
    }


    /**
     * Toast 长时间,不常用
     *
     * @param message
     * @param isShort
     */
    public void showToast(String message, boolean isShort) {
        if (System.currentTimeMillis() - showTime > 3000) {

            if (isShort) {
                if (moToastInstance == null)
                    moToastInstance = Toast.makeText(this, message, SHORT_TOAST);
                else {
                    moToastInstance.setDuration(SHORT_TOAST);
                    moToastInstance.setText(message);
                }
            } else {
                if (moToastInstance == null)
                    moToastInstance = Toast.makeText(this, message, LONG_TOAST);
                else {
                    moToastInstance.setDuration(LONG_TOAST);
                    moToastInstance.setText(message);
                }
            }

            moToastInstance.show();
            showTime = System.currentTimeMillis();
        }
    }
    /**
     * 处理键盘和隐藏的方法
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (Utils.isShouldHideInput(getCurrentFocus(), ev)) {
                Utils.hideSoftInput(getApplicationContext(),
                        getCurrentFocus().getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    //权限
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }
    }

    /**
     *
     * @param permissions
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23){
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer)checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean)shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(com.sx.lzlibrary.R.string.notifyTitle);
        builder.setMessage(com.sx.lzlibrary.R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(com.sx.lzlibrary.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(com.sx.lzlibrary.R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}