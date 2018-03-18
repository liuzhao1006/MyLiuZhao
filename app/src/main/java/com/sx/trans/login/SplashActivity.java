package com.sx.trans.login;

import android.os.Bundle;
import android.os.Handler;

import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.activity.CompanyMainActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.transport.ui.MainActivity;

public class SplashActivity extends BaseTransActivity {


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class);
                finish();
//                startActivity(MainActivity.class);
//                startActivity(CompanyMainActivity.class);
            }
        }, 500);
//UserInfo{areaId=2741, auth='94r4v794nw1506341984774', id=9, imgUrl='', name='guizhou',
// tel='12345678', userName='guizhou', areaCode='00002741', areaName='贵州省',
// roleName='普通权限'}
//        LzApp.mSpfProxy.putInt(IConstants.mSpre_Constants.UID, 9).commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.IMAGE_URL, "").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_AUTH, "94r4v794nw1506341984774").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_AREACODE,"00002741").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_PHONE,"12345678").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_AREANAME,"贵州省").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_NAME,"guizhou").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.LOGIN_PWD,"wzry_etrans").commit();
//        LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.LOGIN_ACCOUNT,"guizhou").commit();

    }
}
