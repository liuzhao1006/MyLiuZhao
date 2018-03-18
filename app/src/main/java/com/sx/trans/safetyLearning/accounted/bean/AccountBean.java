package com.sx.trans.safetyLearning.accounted.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccountBean extends BaseBean {


    private List<ListBean> list;
    private List<StuDataBean> StuData;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<StuDataBean> getStuData() {
        return StuData;
    }

    public void setStuData(List<StuDataBean> StuData) {
        this.StuData = StuData;
    }

    public static class ListBean {
        /**
         * hoursRatio : 0
         * CertiDepartment : 陕A12345
         * StuName : 张三
         * StudentId : 0
         */

        private String hoursRatio;
        private String CertiDepartment;
        private String StuName;
        private String StudentId;

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
    }

    public static class StuDataBean {
        /**
         * hoursRatio : 0
         * stunum : 55
         */

        private String hoursRatio;
        private String stunum;

        public String getHoursRatio() {
            return hoursRatio;
        }

        public void setHoursRatio(String hoursRatio) {
            this.hoursRatio = hoursRatio;
        }

        public String getStunum() {
            return stunum;
        }

        public void setStunum(String stunum) {
            this.stunum = stunum;
        }
    }
}
