package com.sx.trans.supervision.app;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lz.cloud.widget.LzBar;
import com.sx.trans.supervision.widget.ImmerseHelper;
import com.sx.trans.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/21.
 * <p>
 * 修改  将原来在application中声明的定位系统给移植到这里,Android6.0之后需要动态添加权限,
 * 需要activity参考demo中把整个权限都添加进去
 * <p>
 * 继承了Activity，实现Android6.0的运行时权限检测
 * 需要进行运行时权限检测的Activity可以继承这个类
 */
public class BaseActivity extends FragmentActivity {
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

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    /**
     * @param permissions
     * @since 2.5.0
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
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer) checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean) shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
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
     *
     * @param grantResults
     * @return
     * @since 2.5.0
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
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
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
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public Context mContext;
    public static final String TAG = "BaseActivity";
    private Dialog updataDig;
    private ImageView iv_progress, iv_progress2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LzBar bar = new LzBar(this);

        bar.setColorBar(getResources().getColor(R.color.title_ground_normal));
        super.onCreate(savedInstanceState);
        mContext = this;
//        ImmerseHelper.setSystemBarTransparent(this);
    }


    /**
     * 设置页面头部
     *
     * @param isBackNeed 是否显示返回键
     * @param title      标题
     * @param _drawable  图片按钮图片资源
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setTitle(boolean isBackNeed, String title,
                         boolean isMenu, Drawable _drawable) {
        LinearLayout backIv = (LinearLayout) this.findViewById(R.id.backIv);
        TextView titleTv = (TextView) this.findViewById(R.id.headTitleTv);
        Button bt_menu = (Button) this.findViewById(R.id.bt_menu);
        if (isBackNeed) {
            backIv.setVisibility(View.VISIBLE);
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.finish();
                }
            });
        } else backIv.setVisibility(View.GONE);

        titleTv.setText(title);

        if (isMenu) {
            bt_menu.setVisibility(View.VISIBLE);
            bt_menu.setBackground(_drawable);
        } else {
            bt_menu.setVisibility(View.GONE);
        }
    }

    public int screenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public int screenHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
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
        if (updataDig != null) {
            updataDig.dismiss();
        }

    }

    //控制动画
    public void Animation() {
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotate2 = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //默认为0，为-1时一直循环动画
        LinearInterpolator lir = new LinearInterpolator();
        rotate.setInterpolator(lir);
        rotate2.setInterpolator(lir);
        rotate.setRepeatCount(-1);
        rotate2.setRepeatCount(-1);
        rotate.setDuration(1000);
        rotate2.setDuration(1000);
        rotate.setFillAfter(true);
        rotate2.setFillAfter(true);
        iv_progress.startAnimation(rotate);
        iv_progress2.startAnimation(rotate2);
    }
}
