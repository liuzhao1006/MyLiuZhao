package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/9/22.
 * 保险
 */

public class SafeInsuranceBean extends BaseBean {
    /**
     * begin_time : 1506595697000
     * car_body_color : 2
     * carframe_code : dsf
     * end_time : 1540363828000
     * form_code : asdf
     * form_money : 124
     * id_vsfi : 25
     * id_vsi : 110
     * operate_nature : 0
     * operate_type : 3
     * safeKindFormRelations : []
     * safe_company : 0
     * safe_money : 124
     * vehicle_id : 2
     * vehicle_no : 贵H18020
     */

    private long begin_time;
    private String car_body_color;
    private String carframe_code;
    private long end_time;
    private String form_code;
    private String form_money;
    private String id_vsfi;
    private String id_vsi;
    private String operate_nature;
    private String operate_type;
    private String safe_company;
    private String safe_money;
    private String vehicle_id;
    private String vehicle_no;
    private List<?> safeKindFormRelations;



    public long getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(long begin_time) {
        this.begin_time = begin_time;
    }

    public String getCar_body_color() {
        return car_body_color;
    }

    public void setCar_body_color(String car_body_color) {
        this.car_body_color = car_body_color;
    }

    public String getCarframe_code() {
        return carframe_code;
    }

    public void setCarframe_code(String carframe_code) {
        this.carframe_code = carframe_code;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public String getForm_code() {
        return form_code;
    }

    public void setForm_code(String form_code) {
        this.form_code = form_code;
    }

    public String getForm_money() {
        return form_money;
    }

    public void setForm_money(String form_money) {
        this.form_money = form_money;
    }

    public String getId_vsfi() {
        return id_vsfi;
    }

    public void setId_vsfi(String id_vsfi) {
        this.id_vsfi = id_vsfi;
    }

    public String getId_vsi() {
        return id_vsi;
    }

    public void setId_vsi(String id_vsi) {
        this.id_vsi = id_vsi;
    }

    public String getOperate_nature() {
        return operate_nature;
    }

    public void setOperate_nature(String operate_nature) {
        this.operate_nature = operate_nature;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public String getSafe_company() {
        return safe_company;
    }

    public void setSafe_company(String safe_company) {
        this.safe_company = safe_company;
    }

    public String getSafe_money() {
        return safe_money;
    }

    public void setSafe_money(String safe_money) {
        this.safe_money = safe_money;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public List<?> getSafeKindFormRelations() {
        return safeKindFormRelations;
    }

    public void setSafeKindFormRelations(List<?> safeKindFormRelations) {
        this.safeKindFormRelations = safeKindFormRelations;
    }
}
