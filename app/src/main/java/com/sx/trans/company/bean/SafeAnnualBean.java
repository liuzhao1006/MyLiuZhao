package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/25.
 */

public class SafeAnnualBean extends BaseBean {


    /**
     * annual_date : 2016-10-21 00:00:00.0
     * driving_license_expiry_date : 2017-06-20 00:00:00.0
     * shipping_annual_date : 2017-03-27 00:00:00.0
     * vehicle_no : 贵HA0222
     */

    private String annual_date;
    private String driving_license_expiry_date;
    private String shipping_annual_date;
    private String vehicle_no;

    public String getAnnual_date() {
        return annual_date;
    }

    public void setAnnual_date(String annual_date) {
        this.annual_date = annual_date;
    }

    public String getDriving_license_expiry_date() {
        return driving_license_expiry_date;
    }

    public void setDriving_license_expiry_date(String driving_license_expiry_date) {
        this.driving_license_expiry_date = driving_license_expiry_date;
    }

    public String getShipping_annual_date() {
        return shipping_annual_date;
    }

    public void setShipping_annual_date(String shipping_annual_date) {
        this.shipping_annual_date = shipping_annual_date;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }
}
