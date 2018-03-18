package com.sx.trans.safetyLearning.progress.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/14,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressDetialBean extends BaseBean {

    @Override
    public String toString() {
        return "ProgressDetialBean{" +
                "StuData=" + StuData +
                ", VideoList=" + VideoList +
                '}';
    }

    private List<StuDataBean> StuData;
    private List<VideoListBean> VideoList;

    public List<StuDataBean> getStuData() {
        return StuData;
    }

    public void setStuData(List<StuDataBean> StuData) {
        this.StuData = StuData;
    }

    public List<VideoListBean> getVideoList() {
        return VideoList;
    }

    public void setVideoList(List<VideoListBean> VideoList) {
        this.VideoList = VideoList;
    }

    public static class StuDataBean {
        @Override
        public String toString() {
            return "StuDataBean{" +
                    "CertiDepartment='" + CertiDepartment + '\'' +
                    ", totalBasicsHours='" + totalBasicsHours + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", StuName='" + StuName + '\'' +
                    ", totalValidHours='" + totalValidHours + '\'' +
                    '}';
        }

        /**
         * CertiDepartment : 陕A23562
         * totalBasicsHours : 0
         * Phone : 15918615577
         * StuName : 周天一
         * totalValidHours : 0
         */

        private String CertiDepartment;
        private String totalBasicsHours;
        private String Phone;
        private String StuName;
        private String totalValidHours;

        public String getCertiDepartment() {
            return CertiDepartment;
        }

        public void setCertiDepartment(String CertiDepartment) {
            this.CertiDepartment = CertiDepartment;
        }

        public String getTotalBasicsHours() {
            return totalBasicsHours;
        }

        public void setTotalBasicsHours(String totalBasicsHours) {
            this.totalBasicsHours = totalBasicsHours;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getStuName() {
            return StuName;
        }

        public void setStuName(String StuName) {
            this.StuName = StuName;
        }

        public String getTotalValidHours() {
            return totalValidHours;
        }

        public void setTotalValidHours(String totalValidHours) {
            this.totalValidHours = totalValidHours;
        }
    }

    public static class VideoListBean {
        @Override
        public String toString() {
            return "VideoListBean{" +
                    "Title='" + Title + '\'' +
                    ", VideoRatio='" + VideoRatio + '\'' +
                    '}';
        }

        /**
         * Title : 第一章 道路运输法规、政策（三）
         * VideoRatio : 0
         */

        private String Title;
        private String VideoRatio;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getVideoRatio() {
            return VideoRatio;
        }

        public void setVideoRatio(String VideoRatio) {
            this.VideoRatio = VideoRatio;
        }
    }
}
