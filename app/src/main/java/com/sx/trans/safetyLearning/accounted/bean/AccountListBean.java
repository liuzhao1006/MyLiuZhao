package com.sx.trans.safetyLearning.accounted.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccountListBean extends BaseBean {


    private List<ListBean> list;
    private List<StuDataBean> StuData;

    @Override
    public String toString() {
        return "AccountListBean{" +
                "list=" + list +
                ", StuData=" + StuData +
                '}';
    }

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
        @Override
        public String toString() {
            return "ListBean{" +
                    "StuName='" + StuName + '\'' +
                    ", StudentId='" + StudentId + '\'' +
                    ", hoursRatio='" + hoursRatio + '\'' +
                    ", CertiDepartment='" + CertiDepartment + '\'' +
                    '}';
        }

        /**
         * StuName : 谷志新
         * StudentId : 0
         * hoursRatio : 0
         * CertiDepartment : 陕A12345
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

    public static class StuDataBean {
        @Override
        public String toString() {
            return "StuDataBean{" +
                    "stunum='" + stunum + '\'' +
                    ", hoursRatio='" + hoursRatio + '\'' +
                    '}';
        }

        /**
         * stunum : 9
         * hoursRatio : 0
         */

        private String stunum;
        private String hoursRatio;

        public String getStunum() {
            return stunum;
        }

        public void setStunum(String stunum) {
            this.stunum = stunum;
        }

        public String getHoursRatio() {
            return hoursRatio;
        }

        public void setHoursRatio(String hoursRatio) {
            this.hoursRatio = hoursRatio;
        }
    }
}
