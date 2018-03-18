package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class VideoListBean extends BaseBean {


    @Override
    public String toString() {
        return "VideoListBean{" +
                "IndustryId='" + IndustryId + '\'' +
                ", CourseId='" + CourseId + '\'' +
                ", VideoId='" + VideoId + '\'' +
                ", JurisdictionType='" + JurisdictionType + '\'' +
                ", StudyType='" + StudyType + '\'' +
                ", IsValid='" + IsValid + '\'' +
                ", Title='" + Title + '\'' +
                ", VideoURL='" + VideoURL + '\'' +
                ", PlayTime='" + PlayTime + '\'' +
                ", ImagePath='" + ImagePath + '\'' +
                ", CoursewareId='" + CoursewareId + '\'' +
                ", BasicsHours='" + BasicsHours + '\'' +
                ", StudyPlanId='" + StudyPlanId + '\'' +
                ", isStudy='" + isStudy + '\'' +
                ", CurrTime='" + CurrTime + '\'' +
                '}';
    }

    /**
     * IndustryId : 2042
     * CourseId : 1084
     * VideoId : 0DEFF55A-AF67-4979-B4F8-7C8387199D2D
     * JurisdictionType : 1
     * StudyType : 0
     * IsValid : 0
     * Title : 第四章 道路旅客运输车辆（一）
     * VideoURL : /UploadFile/Video/KY/jixu_ke_4_01_01.flv
     * PlayTime : 00时18分17秒
     * ImagePath : /UploadFile/VideoImage/KY/jixu_ke_4_01_01.png
     * CoursewareId : 1006
     * BasicsHours : 7200
     * StudyPlanId : 3065
     * isStudy : 1
     * CurrTime : 0.00
     */

    private String IndustryId;
    private String CourseId;
    private String VideoId;
    private String JurisdictionType;
    private String StudyType;
    private String IsValid;
    private String Title;
    private String VideoURL;
    private String PlayTime;
    private String ImagePath;
    private String CoursewareId;
    private String BasicsHours;
    private String StudyPlanId;
    private String isStudy;
    private String CurrTime;

    public String getIndustryId() {
        return IndustryId;
    }

    public void setIndustryId(String IndustryId) {
        this.IndustryId = IndustryId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String CourseId) {
        this.CourseId = CourseId;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String VideoId) {
        this.VideoId = VideoId;
    }

    public String getJurisdictionType() {
        return JurisdictionType;
    }

    public void setJurisdictionType(String JurisdictionType) {
        this.JurisdictionType = JurisdictionType;
    }

    public String getStudyType() {
        return StudyType;
    }

    public void setStudyType(String StudyType) {
        this.StudyType = StudyType;
    }

    public String getIsValid() {
        return IsValid;
    }

    public void setIsValid(String IsValid) {
        this.IsValid = IsValid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getVideoURL() {
        return VideoURL;
    }

    public void setVideoURL(String VideoURL) {
        this.VideoURL = VideoURL;
    }

    public String getPlayTime() {
        return PlayTime;
    }

    public void setPlayTime(String PlayTime) {
        this.PlayTime = PlayTime;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public String getCoursewareId() {
        return CoursewareId;
    }

    public void setCoursewareId(String CoursewareId) {
        this.CoursewareId = CoursewareId;
    }

    public String getBasicsHours() {
        return BasicsHours;
    }

    public void setBasicsHours(String BasicsHours) {
        this.BasicsHours = BasicsHours;
    }

    public String getStudyPlanId() {
        return StudyPlanId;
    }

    public void setStudyPlanId(String StudyPlanId) {
        this.StudyPlanId = StudyPlanId;
    }

    public String getIsStudy() {
        return isStudy;
    }

    public void setIsStudy(String isStudy) {
        this.isStudy = isStudy;
    }

    public String getCurrTime() {
        return CurrTime;
    }

    public void setCurrTime(String CurrTime) {
        this.CurrTime = CurrTime;
    }
}
