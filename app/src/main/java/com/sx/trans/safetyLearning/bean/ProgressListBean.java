package com.sx.trans.safetyLearning.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressListBean extends BaseBean {


    private List<ListBean> list;
    private List<StuDataBean> StuData;

    public List<ListBean> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ProgressListBean{" +
                "list=" + list +
                ", StuData=" + StuData +
                '}';
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

    public static class ListBean extends BaseBean {
        @Override
        public String toString() {
            return "ListBean{" +
                    "hoursRatio='" + hoursRatio + '\'' +
                    ", CertiDepartment='" + CertiDepartment + '\'' +
                    ", StuName='" + StuName + '\'' +
                    ", StudentId='" + StudentId + '\'' +
                    '}';
        }

        /**
         * hoursRatio : 0
         * CertiDepartment : 桂A·1333J
         * StuName : 李客运
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

    public static class StuDataBean extends BaseBean {
        @Override
        public String toString() {
            return "StuDataBean{" +
                    "hoursRatio='" + hoursRatio + '\'' +
                    ", stunum='" + stunum + '\'' +
                    '}';
        }

        /**
         * hoursRatio : 0
         * stunum : 6
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
