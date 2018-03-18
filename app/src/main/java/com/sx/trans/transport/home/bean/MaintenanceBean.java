package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/11,
 * GitHub : https://github.com/liuzhao1006
 * 维护信息详情
 */

public class MaintenanceBean extends BaseBean {
    @Override
    public String toString() {
        return "MaintenanceBean{" +
                "add_word='" + add_word + '\'' +
                ", carframe_code='" + carframe_code + '\'' +
                ", certifivate_code='" + certifivate_code + '\'' +
                ", check_code='" + check_code + '\'' +
                ", check_out=" + check_out +
                ", company_name='" + company_name + '\'' +
                ", defend_remark='" + defend_remark + '\'' +
                ", defend_time='" + defend_time + '\'' +
                ", file_url='" + file_url + '\'' +
                ", group_id='" + group_id + '\'' +
                ", id_vd='" + id_vd + '\'' +
                ", line_service_record_time='" + line_service_record_time + '\'' +
                ", main_parts_change='" + main_parts_change + '\'' +
                ", next_time='" + next_time + '\'' +
                ", operating_mileage='" + operating_mileage + '\'' +
                ", registration_no='" + registration_no + '\'' +
                ", repair_code='" + repair_code + '\'' +
                ", service_company='" + service_company + '\'' +
                ", vehicle_id='" + vehicle_id + '\'' +
                '}';
    }

    /**
     * add_word : 12
     * carframe_code : M1241121
     * certifivate_code : 12
     * check_code : 12
     * check_out : true
     * company_name : 河北亿程交通信息有限公司
     * defend_remark : 备注什么呢
     * defend_time : 2017-08-15 00:00:00
     * file_url : freemarker.txt::/ueditor/jsp/upload/file/20170926/1506409490381073292.txt
     * group_id : 9
     * id_vd : 2
     * line_service_record_time : 1899-12-11 23:54:17
     * main_parts_change : 21
     * next_time : 2017-09-21 00:00:00
     * operating_mileage : 12123
     * registration_no : 贵H18022
     * repair_code : 12
     * service_company : 12112
     * vehicle_id : 3
     */

    private String add_word;
    private String carframe_code;
    private String certifivate_code;
    private String check_code;
    private boolean check_out;
    private String company_name;
    private String defend_remark;
    private String defend_time;
    private String file_url;
    private String group_id;
    private String id_vd;
    private String line_service_record_time;
    private String main_parts_change;
    private String next_time;
    private String operating_mileage;
    private String registration_no;
    private String repair_code;
    private String service_company;
    private String vehicle_id;

    public String getAdd_word() {
        return add_word;
    }

    public void setAdd_word(String add_word) {
        this.add_word = add_word;
    }

    public String getCarframe_code() {
        return carframe_code;
    }

    public void setCarframe_code(String carframe_code) {
        this.carframe_code = carframe_code;
    }

    public String getCertifivate_code() {
        return certifivate_code;
    }

    public void setCertifivate_code(String certifivate_code) {
        this.certifivate_code = certifivate_code;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public boolean isCheck_out() {
        return check_out;
    }

    public void setCheck_out(boolean check_out) {
        this.check_out = check_out;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDefend_remark() {
        return defend_remark;
    }

    public void setDefend_remark(String defend_remark) {
        this.defend_remark = defend_remark;
    }

    public String getDefend_time() {
        return defend_time;
    }

    public void setDefend_time(String defend_time) {
        this.defend_time = defend_time;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getId_vd() {
        return id_vd;
    }

    public void setId_vd(String id_vd) {
        this.id_vd = id_vd;
    }

    public String getLine_service_record_time() {
        return line_service_record_time.split("\\s+")[0];
    }

    public void setLine_service_record_time(String line_service_record_time) {
        this.line_service_record_time = line_service_record_time;
    }

    public String getMain_parts_change() {
        return main_parts_change;
    }

    public void setMain_parts_change(String main_parts_change) {
        this.main_parts_change = main_parts_change;
    }

    public String getNext_time() {
        return next_time;
    }

    public void setNext_time(String next_time) {
        this.next_time = next_time;
    }

    public String getOperating_mileage() {
        return operating_mileage;
    }

    public void setOperating_mileage(String operating_mileage) {
        this.operating_mileage = operating_mileage;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getRepair_code() {
        return repair_code;
    }

    public void setRepair_code(String repair_code) {
        this.repair_code = repair_code;
    }

    public String getService_company() {
        return service_company;
    }

    public void setService_company(String service_company) {
        this.service_company = service_company;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
}
