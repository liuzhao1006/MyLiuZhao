package com.sx.trans.login.manager;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.activity.CompanyMainActivity;
import com.sx.trans.login.bean.Company;
import com.sx.trans.login.bean.Employee;
import com.sx.trans.login.bean.LoginBean;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.network.request.ResponseData;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.transport.ui.MainActivity;
import com.umeng.message.UTrack;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.List;

import static com.sx.trans.app.LoginConfig.EMPLOYEE_GROUPID;

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 */

public class LoginManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;
    private LoginBean bean;
    private ResponseData data;
    private static final int WHAT = 0x001;

    public LoginManager(Context context, BaseNetApi api, LoginBean bean) {
        this.context = context;
        this.api = api;
        this.bean = bean;
        data = new ResponseData(context, this);
    }

    /**
     * 登录接口 ,  2017年11月06日09:20:19添加
     */
    public void lzNetLogin() {
        //TODO  登录接口,从新修改,主要功能简述: 监管端定位信息数据与用车系统融合. 安全学习端数据后台单独调用安全学习接口返回一个list
        String url = getProperties(context, "loginTrans");
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("userName", bean.getUser())
                .add("password", bean.getPwd())
                .add("userType", bean.getUserType());
        request(WHAT, request, data);
    }

    /**
     * 安全学习 登录接口
     */
    public void safetyLearning() {
        //TODO 此接口是融合安全学习接口. 登录信息,暂时无法调试2017年10月16日09:55:34

    }


    @Override
    public void netStart() {
        api.netStart();
    }

    @Override
    public void netStop() {
        api.netStop();
    }

    @Override
    public void netSuccessed(int what, String data) {
        login(data);
    }

    /**
     * 登录,保存数据
     */
    private void login(String data) {
        if (bean.getUserType().equals("0")) {//从业端
            saveStudentMessage(data);
        } else {//企业端
            saveCompanyMessage(data);
        }
    }


    /**
     * 企业端数据保存
     */
    private void saveCompanyMessage(String data) {
        PrefUtils.putString(context, LoginConfig.LOGINTYPE, "1");
        //保存用户名密码
        PrefUtils.putString(context, LoginConfig.USER_COMPANY_NAME, bean.getUser());
        PrefUtils.putString(context, LoginConfig.PASS_COMPANY_WORD, bean.getPwd());
        List<Company> list = JSON.parseArray(data, Company.class);
        if (list == null || list.size() == 0) {
            api.netFailed(0x0001, "账号故障,请联系管理员");
            return;
        }
        LogUtils.i(list.get(0));
        //  list.get(0).getData2().size() == 0
        if (list.get(0).getData1() == null
                || list.get(0).getData1().size() == 0
                || list.get(0).getData2() == null
                ) {
            api.netFailed(WHAT, "数据有误");
        } else {
            Company.Data1Bean trans = list.get(0).getData1().get(0);
            if(list.get(0).getData2().size() == 0){
                /**
                 * 安全学习接口
                 */
                saveSP(LoginConfig.COMPANY_ACCOUNT_LEARN, "");
                saveSP(LoginConfig.COMPANY_COMPANY_LEARN, "");
                saveSP(LoginConfig.COMPANY_DELETETIME_LEARN,"");
                saveSP(LoginConfig.COMPANY_CREATETIME_LEARN, "");
                saveSP(LoginConfig.COMPANY_CREATEUSERID_LEARN, "");
                saveSP(LoginConfig.COMPANY_ID_LEARN, "");
                saveSP(LoginConfig.COMPANY_EMPLOYEEID_LEARN, "");
                saveSP(LoginConfig.COMPANY_CREATEUSERNAME_LEARN, "");
                saveSP(LoginConfig.COMPANY_NAME_LEARN, "");
                saveSP(LoginConfig.COMPANY_PASSWORD_LEARN, "");

            }else{
                Company.Data2Bean learn = list.get(0).getData2().get(0);
                /**
                 * 安全学习接口
                 */
                saveSP(LoginConfig.COMPANY_ACCOUNT_LEARN, learn.getAccount());
                saveSP(LoginConfig.COMPANY_COMPANY_LEARN, learn.getCompany());
                saveSP(LoginConfig.COMPANY_DELETETIME_LEARN, learn.getDeleteMark());
                saveSP(LoginConfig.COMPANY_CREATETIME_LEARN, learn.getCreatetime());
                saveSP(LoginConfig.COMPANY_CREATEUSERID_LEARN, learn.getCreateUserID());
                saveSP(LoginConfig.COMPANY_ID_LEARN, learn.getID());
                saveSP(LoginConfig.COMPANY_EMPLOYEEID_LEARN, learn.getEmployeeID());
                saveSP(LoginConfig.COMPANY_CREATEUSERNAME_LEARN, learn.getCreateUserName());
                saveSP(LoginConfig.COMPANY_NAME_LEARN, learn.getName());
                saveSP(LoginConfig.COMPANY_PASSWORD_LEARN, learn.getPassword());
            }


            /**public static final String COMPANY_IMGURL = "imgUrl";
             public static final String COMPANY_POSITIONNAME = "positionName";
             public static final String COMPANY_DPNAME = "dpName";
             public static final String COMPANY_AREANAME = "areaName";
             public static final String COMPANY_AUTH = "auth";
             public static final String COMPANY_IDCARD = "idCard";
             public static final String COMPANY_GROUPID = "groupId";
             public static final String COMPANY_NAME = "name";
             public static final String COMPANY_ROLENAME = "roleName";
             public static final String COMPANY_TEL = "tel";
             public static final String COMPANY_ID = "id";
             public static final String COMPANY_USERNAME = "userName";
             *
             */
            saveSP(LoginConfig.COMPANY_IMGURL, trans.getImgUrl());
            saveSP(LoginConfig.COMPANY_POSITIONNAME, trans.getPositionName());
            saveSP(LoginConfig.COMPANY_DPNAME, trans.getDpName());
            saveSP(LoginConfig.COMPANY_AREANAME, trans.getAreaName());
            saveSP(LoginConfig.COMPANY_AUTH, trans.getAuth());
            saveSP(LoginConfig.COMPANY_IDCARD, trans.getIdCard());
            saveSP(LoginConfig.COMPANY_GROUPID, trans.getGroupId() + "");
            saveSP(LoginConfig.COMPANY_NAME, trans.getName());
            saveSP(LoginConfig.COMPANY_ROLENAME, trans.getRoleName());
            saveSP(LoginConfig.COMPANY_TEL, trans.getTel());
            saveSP(LoginConfig.COMPANY_ID, trans.getId() + "");
            saveSP(LoginConfig.COMPANY_USERNAME, trans.getUserName());
            //UserInfo{areaId=2741, auth='94r4v794nw1506341984774', id=9, imgUrl='', name='guizhou',
            // tel='12345678', userName='guizhou', areaCode='00002741', areaName='贵州省',
            // roleName='普通权限'}
            LzApp.mSpfProxy.putInt(IConstants.mSpre_Constants.UID, trans.getId()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.IMAGE_URL, trans.getImgUrl()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_AUTH, trans.getAuth()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_AREACODE, trans.getGroupId() + "").commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_PHONE, trans.getTel()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_AREANAME, trans.getAreaName()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.USER_NAME, trans.getUserName()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.LOGIN_PWD, bean.getPwd()).commit();
            LzApp.mSpfProxy.putString(IConstants.mSpre_Constants.LOGIN_ACCOUNT, bean.getUser()).commit();





            api.netSuccessed(WHAT, "登录成功");
        }
    }

    /**
     * 从业端数据保存
     */
    private void saveStudentMessage(String data) {
        PrefUtils.putString(context, LoginConfig.LOGINTYPE, "0");
        PrefUtils.putString(context, LoginConfig.USER_NAME, bean.getUser());
        PrefUtils.putString(context, LoginConfig.PASS_WORD, bean.getPwd());
        List<Employee> mList = JSON.parseArray(data, Employee.class);
        if (mList == null || mList.size() == 0) {
            api.netFailed(WHAT, "账号故障,请联系管理员");
            return;
        }
        Employee.Data1Bean trans = mList.get(0).getData1().get(0);
        //友盟单点推送.
        LzApp.mPushAgent.addAlias(trans.getId(), "4", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                LogUtils.i("===========Login", s.toString() + "=============" + "来了");
            }
        });
//mList.get(0).getData2().size() == 0
        if (mList.get(0).getData2() == null) {
            api.netFailed(WHAT, "数据有误");
        } else {
            if (mList.get(0).getData2().size() == 0) {
                /**
                 * 保存安全学习接口数据 从业端
                 */
                saveSP(LoginConfig.EMPLOYEE_ISUPLOAD_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_STATUS_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_COMPANY_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_VALIDITYDATE_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_ADDRESS_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_ISPAY_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_PROFESSIONID_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_ARCHIVENO_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_SEX_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_DRIVINGTYPE_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_BRITHDAY_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_CRETIFICATENO_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_PROFESSIONNAME_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_COMPANYNAME_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_STUNAME_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_PHONE_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_ID_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_PHOTOIMAGE_LEARN, "");
                saveSP(LoginConfig.EMPLOYEE_CRETIFICATE_LEARN, "");


            } else {
                Employee.Data2Bean learn = mList.get(0).getData2().get(0);
                /**
                 * 保存安全学习接口数据 从业端
                 */
                saveSP(LoginConfig.EMPLOYEE_ISUPLOAD_LEARN, learn.getIsUpload());
                saveSP(LoginConfig.EMPLOYEE_STATUS_LEARN, learn.getStatus());
                saveSP(LoginConfig.EMPLOYEE_COMPANY_LEARN, learn.getCompany());
                saveSP(LoginConfig.EMPLOYEE_VALIDITYDATE_LEARN, learn.getValidityDate());
                saveSP(LoginConfig.EMPLOYEE_ADDRESS_LEARN, learn.getAddress());
                saveSP(LoginConfig.EMPLOYEE_ISPAY_LEARN, learn.getIsPay());
                saveSP(LoginConfig.EMPLOYEE_PROFESSIONID_LEARN, learn.getProfessionId());
                saveSP(LoginConfig.EMPLOYEE_ARCHIVENO_LEARN, learn.getArchiveNo());
                saveSP(LoginConfig.EMPLOYEE_SEX_LEARN, learn.getSex());
                saveSP(LoginConfig.EMPLOYEE_DRIVINGTYPE_LEARN, learn.getDrivingType());
                saveSP(LoginConfig.EMPLOYEE_BRITHDAY_LEARN, learn.getBirthday());
                saveSP(LoginConfig.EMPLOYEE_CRETIFICATENO_LEARN, learn.getCertificateNo());
                saveSP(LoginConfig.EMPLOYEE_PROFESSIONNAME_LEARN, learn.getProfessionName());
                saveSP(LoginConfig.EMPLOYEE_COMPANYNAME_LEARN, learn.getCompanyName());
                saveSP(LoginConfig.EMPLOYEE_STUNAME_LEARN, learn.getStuName());
                saveSP(LoginConfig.EMPLOYEE_PHONE_LEARN, learn.getPhone());
                saveSP(LoginConfig.EMPLOYEE_ID_LEARN, learn.getId());
                saveSP(LoginConfig.EMPLOYEE_PHOTOIMAGE_LEARN, learn.getPhotoImage());
                saveSP(LoginConfig.EMPLOYEE_CRETIFICATE_LEARN, learn.getCertificate());


            }

            //单点推送功能.


            /**
             * 保存返回的数据,用车数据
             */

            saveSP(LoginConfig.EMPLOYEE_CARDID, trans.getCarId());
            saveSP(LoginConfig.EMPLOYEE_DEPARTMENTID, trans.getDepartmentId() + "");
            saveSP(LoginConfig.EMPLOYEE_DRIVERTYPE, trans.getDriverType() + "");
            saveSP(LoginConfig.EMPLOYEE_DRIVERYEAR, trans.getDriverYear() + "");
            saveSP(LoginConfig.EMPLOYEE_GROUPID, trans.getGroupId());
            saveSP(LoginConfig.EMPLOYEE_ID, trans.getId());
            saveSP(LoginConfig.EMPLOYEE_PHONE, trans.getPhone());
            saveSP(LoginConfig.EMPLOYEE_PHOTO, trans.getPhoto());
            saveSP(LoginConfig.EMPLOYEE_REGISTARTIONNO, trans.getRegistrationNo());
            saveSP(LoginConfig.EMPLOYEE_SEX, trans.getSex() + "");
            saveSP(LoginConfig.EMPLOYEE_UNITID, trans.getUnitId() + "");
            saveSP(LoginConfig.EMPLOYEE_USERNAME, trans.getUserName());


            api.netSuccessed(WHAT, "登录成功");
        }
    }

    /**
     * 保存登录数据公共方法,从业人员端
     */
    private void saveSP(String key, String value) {
        PrefUtils.putString(context, key, value);
    }

    @Override
    public void netFailed(int what, String message) {
        api.netFailed(what, message);
    }


    public static class Builder {
        private Context context;
        private BaseNetApi api;
        private LoginBean bean;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setBaseNetApi(BaseNetApi api) {
            this.api = api;
            return this;
        }

        public Builder setLoginBean(LoginBean bean) {
            this.bean = bean;
            return this;
        }

        public LoginManager create() {
            return new LoginManager(context, api, bean);
        }
    }
}
