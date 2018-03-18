package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;


/**
 * 地方法规政策
 */
public class LawBean extends BaseBean {

    @Override
    public String toString() {
        return "LawBean{" +
                "add_time='" + add_time + '\'' +
                ", department_name='" + department_name + '\'' +
                ", file_url='" + file_url + '\'' +
                ", id='" + id + '\'' +
                ", laws_name='" + laws_name + '\'' +
                ", laws_type=" + laws_type +
                ", laws_type_name='" + laws_type_name + '\'' +
                ", remark='" + remark + '\'' +
                ", use_date='" + use_date + '\'' +
                '}';
    }

    /**
     * add_time : 2017-09-02 00:00:00
     * department_name : 西安华辰交通设施有限责任公司
     * file_url :
     * id : 3
     * laws_name : 人员安全法
     * laws_type : true
     * laws_type_name : 地方性法规
     * remark :
     * use_date : 2017-09-02 00:00:00
     */

    private String add_time;
    private String department_name;
    private String file_url;
    private String id;
    private String laws_name;
    private boolean laws_type;
    private String laws_type_name;
    private String remark;
    private String use_date;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaws_name() {
        return laws_name;
    }

    public void setLaws_name(String laws_name) {
        this.laws_name = laws_name;
    }

    public boolean isLaws_type() {
        return laws_type;
    }

    public void setLaws_type(boolean laws_type) {
        this.laws_type = laws_type;
    }

    public String getLaws_type_name() {
        return laws_type_name;
    }

    public void setLaws_type_name(String laws_type_name) {
        this.laws_type_name = laws_type_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUse_date() {
        return use_date;
    }

    public void setUse_date(String use_date) {
        this.use_date = use_date;
    }
}
