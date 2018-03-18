package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/28.
 * 专项活动
 */

public class SafeSpiecialCampaignBean extends BaseBean {
    @Override
    public String toString() {
        return "SafeSpiecialCampaignBean{" +
                "company_name='" + company_name + '\'' +
                ", create_date='" + create_date + '\'' +
                ", create_user='" + create_user + '\'' +
                ", delete=" + delete +
                ", endtime='" + endtime + '\'' +
                ", file_url='" + file_url + '\'' +
                ", groupid='" + groupid + '\'' +
                ", isarrangement=" + isarrangement +
                ", isimplement=" + isimplement +
                ", ismsn=" + ismsn +
                ", isregular=" + isregular +
                ", purpose='" + purpose + '\'' +
                ", regularcycle='" + regularcycle + '\'' +
                ", regulardate='" + regulardate + '\'' +
                ", regulardecription='" + regulardecription + '\'' +
                ", starttime='" + starttime + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    /**
     * company_name : 广东省
     * create_date : 2017-09-13 11:41:38
     * create_user : admin
     * delete : false
     * endtime : 2017-09-13 11:41:27
     * file_url :
     * groupid : 14
     * isarrangement : false
     * isimplement : true
     * ismsn : false
     * isregular : false
     * purpose : assss
     * regularcycle : 3
     * regulardate : 2017-09-13 11:41:17
     * regulardecription :  <p>wdsds</p>
     * starttime : 2017-09-13 11:41:05
     * title : rere
     */

    private String company_name;
    private String create_date;
    private String create_user;
    private boolean delete;
    private String endtime;
    private String file_url;
    private String groupid;
    private boolean isarrangement;
    private boolean isimplement;
    private boolean ismsn;
    private boolean isregular;
    private String purpose;
    private String regularcycle;
    private String regulardate;
    private String regulardecription;
    private String starttime;
    private String title;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public boolean isIsarrangement() {
        return isarrangement;
    }

    public void setIsarrangement(boolean isarrangement) {
        this.isarrangement = isarrangement;
    }

    public boolean isIsimplement() {
        return isimplement;
    }

    public void setIsimplement(boolean isimplement) {
        this.isimplement = isimplement;
    }

    public boolean isIsmsn() {
        return ismsn;
    }

    public void setIsmsn(boolean ismsn) {
        this.ismsn = ismsn;
    }

    public boolean isIsregular() {
        return isregular;
    }

    public void setIsregular(boolean isregular) {
        this.isregular = isregular;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRegularcycle() {
        return regularcycle;
    }

    public void setRegularcycle(String regularcycle) {
        this.regularcycle = regularcycle;
    }

    public String getRegulardate() {
        return regulardate;
    }

    public void setRegulardate(String regulardate) {
        this.regulardate = regulardate;
    }

    public String getRegulardecription() {
        return regulardecription;
    }

    public void setRegulardecription(String regulardecription) {
        this.regulardecription = regulardecription;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
