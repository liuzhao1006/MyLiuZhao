package com.sx.trans.safetyLearning.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/13,
 * GitHub : https://github.com/liuzhao1006
 */

public class PieBean extends BaseBean {


    /**
     * StuName : 周天一
     * StudentId : 7
     * hoursRatio : 0
     * CertiDepartment : 陕A23562
     */

    private String StuName;
    private String StudentId;
    private String hoursRatio;
    private String CertiDepartment;

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String StuName) {
        this.StuName = StuName;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getHoursRatio() {
        return hoursRatio;
    }

    public void setHoursRatio(String hoursRatio) {
        this.hoursRatio = hoursRatio;
    }

    public String getCertiDepartment() {
        return CertiDepartment;
    }

    public void setCertiDepartment(String CertiDepartment) {
        this.CertiDepartment = CertiDepartment;
    }
}
