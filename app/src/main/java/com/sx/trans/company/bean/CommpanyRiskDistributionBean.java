package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/26.
 */

public class CommpanyRiskDistributionBean extends BaseBean {
    @Override
    public String toString() {
        return "CommpanyRiskDistributionBean{" +
                "assessment_name='" + assessment_name + '\'' +
                ", create_date='" + create_date + '\'' +
                ", create_user='" + create_user + '\'' +
                ", exam_info='" + exam_info + '\'' +
                ", id='" + id + '\'' +
                ", is_delete='" + is_delete + '\'' +
                ", level='" + level + '\'' +
                ", parant_id='" + parant_id + '\'' +
                ", total_score='" + total_score + '\'' +
                '}';
    }

    /**
     * assessment_name : 安全目标
     * create_date : 2017-09-13 00:00:00
     * create_user : admin
     * exam_info :
     * id : 1
     * is_delete :
     * level : 1
     * parant_id : 0
     * total_score : 35
     */

    private String assessment_name;
    private String create_date;
    private String create_user;
    private String exam_info;
    private String id;
    private String is_delete;
    private String level;
    private String parant_id;
    private String total_score;

    public String getAssessment_name() {
        return assessment_name;
    }

    public void setAssessment_name(String assessment_name) {
        this.assessment_name = assessment_name;
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

    public String getExam_info() {
        return exam_info;
    }

    public void setExam_info(String exam_info) {
        this.exam_info = exam_info;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParant_id() {
        return parant_id;
    }

    public void setParant_id(String parant_id) {
        this.parant_id = parant_id;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }
}
