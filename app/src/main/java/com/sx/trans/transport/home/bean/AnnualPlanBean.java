package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/27.
 */

public class AnnualPlanBean extends BaseBean {

    @Override
    public String toString() {
        return "AnnualPlanBean{" +
                "check_status='" + check_status + '\'' +
                ", create_date='" + create_date + '\'' +
                ", create_user='" + create_user + '\'' +
                ", files='" + files + '\'' +
                ", formulate_date='" + formulate_date + '\'' +
                ", formulate_man='" + formulate_man + '\'' +
                ", group_id='" + group_id + '\'' +
                ", id='" + id + '\'' +
                ", is_delete='" + is_delete + '\'' +
                ", long_term_id='" + long_term_id + '\'' +
                ", plan_name='" + plan_name + '\'' +
                ", plan_type='" + plan_type + '\'' +
                ", question_mark='" + question_mark + '\'' +
                ", remark='" + remark + '\'' +
                ", years='" + years + '\'' +
                '}';
    }

    /**
     * check_status : 0
     * create_date : 2017-09-14 17:02:02
     * create_user : admin
     * files :
     * formulate_date : 2017/09/14
     * formulate_man : admin
     * group_id : 5
     * id : 6
     * is_delete :
     * long_term_id : 1
     * plan_name : 年度计划
     * plan_type : 2
     * question_mark : 哈新发(2017)1号
     * remark :
     * years : 2017
     */

    private String check_status;
    private String create_date;
    private String create_user;
    private String files;
    private String formulate_date;
    private String formulate_man;
    private String group_id;
    private String id;
    private String is_delete;
    private String long_term_id;
    private String plan_name;
    private String plan_type;
    private String question_mark;
    private String remark;
    private String years;

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getFormulate_date() {
        return formulate_date;
    }

    public void setFormulate_date(String formulate_date) {
        this.formulate_date = formulate_date;
    }

    public String getFormulate_man() {
        return formulate_man;
    }

    public void setFormulate_man(String formulate_man) {
        this.formulate_man = formulate_man;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getLong_term_id() {
        return long_term_id;
    }

    public void setLong_term_id(String long_term_id) {
        this.long_term_id = long_term_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getQuestion_mark() {
        return question_mark;
    }

    public void setQuestion_mark(String question_mark) {
        this.question_mark = question_mark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
