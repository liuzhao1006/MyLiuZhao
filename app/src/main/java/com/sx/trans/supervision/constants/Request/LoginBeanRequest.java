package com.sx.trans.supervision.constants.Request;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/6/17.
 * 登入参数
 */

public class LoginBeanRequest implements Serializable{

    public LoginBeanRequest(String userName, String password, String deviceType,String addr) {
        this.userName = userName;
        this.password = password;
        this.deviceType = deviceType;
        this.addr=addr;
    }

    private String userName;
    private String password;
    private String deviceType;//设备类型 android:0 ios:1
    private String addr;//当前定位地址



    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
