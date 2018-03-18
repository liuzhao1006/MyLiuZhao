package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/9/22.
 */

public class SafeMeetingBean extends BaseBean {

    @Override
    public String toString() {
        return "SafeMeetingBean{" +
                "address='" + address + '\'' +
                ", come_numer='" + come_numer + '\'' +
                ", company_name_meet='" + company_name_meet + '\'' +
                ", content='" + content + '\'' +
                ", end_date='" + end_date + '\'' +
                ", file_url='" + file_url + '\'' +
                ", fore_number='" + fore_number + '\'' +
                ", holder='" + holder + '\'' +
                ", id_meet='" + id_meet + '\'' +
                ", meeting_date='" + meeting_date + '\'' +
                ", meeting_form='" + meeting_form + '\'' +
                ", meeting_month='" + meeting_month + '\'' +
                ", meeting_no='" + meeting_no + '\'' +
                ", meeting_type='" + meeting_type + '\'' +
                ", recorder='" + recorder + '\'' +
                ", remark='" + remark + '\'' +
                ", title='" + title + '\'' +
                ", meetManageRelInfos=" + meetManageRelInfos +
                '}';
    }

    /**
     * address : SADFSDF
     * come_numer : 11
     * company_name_meet : 西安华辰交通设施有限责任公司
     * content : ASDFASD
     * end_date : 2017-10-10 20:23:31
     * file_url :
     * fore_number : 12
     * holder : ASDF
     * id_meet : 1
     * meetManageRelInfos : [{"companyName":"贵州省运管局","isCome":true,"postName":"贵州省湄潭县鸿运客运有限责任公司","userName":"张三"},{"companyName":"河北交投智能交通技术有限责任公司","isCome":true,"postName":"贵州省湄潭县鸿运客运有限责任公司","userName":"李四"},{"companyName":"河北交投智能交通技术有限责任公司","isCome":false,"postName":"贵州省湄潭县鸿运客运有限责任公司","userName":"测试1"}]
     * meeting_date : 2017-10-11 20:23:24
     * meeting_form : 1
     * meeting_month : 3
     * meeting_no : 123
     * meeting_type : 2
     * recorder : ASDF
     * remark : ASDF
     * title : SDF
     */

    private String address;
    private String come_numer;
    private String company_name_meet;
    private String content;
    private String end_date;
    private String file_url;
    private String fore_number;
    private String holder;
    private String id_meet;
    private String meeting_date;
    private String meeting_form;
    private String meeting_month;
    private String meeting_no;
    private String meeting_type;
    private String recorder;
    private String remark;
    private String title;
    private List<MeetManageRelInfosBean> meetManageRelInfos;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCome_numer() {
        return come_numer;
    }

    public void setCome_numer(String come_numer) {
        this.come_numer = come_numer;
    }

    public String getCompany_name_meet() {
        return company_name_meet;
    }

    public void setCompany_name_meet(String company_name_meet) {
        this.company_name_meet = company_name_meet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnd_date() {
        return end_date.split("\\s+")[0];
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getFore_number() {
        return fore_number;
    }

    public void setFore_number(String fore_number) {
        this.fore_number = fore_number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getId_meet() {
        return id_meet;
    }

    public void setId_meet(String id_meet) {
        this.id_meet = id_meet;
    }

    public String getMeeting_date() {
        return meeting_date.split("\\s+")[0];
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }

    public String getMeeting_form() {
        return meeting_form;
    }

    public void setMeeting_form(String meeting_form) {
        this.meeting_form = meeting_form;
    }

    public String getMeeting_month() {
        return meeting_month;
    }

    public void setMeeting_month(String meeting_month) {
        this.meeting_month = meeting_month;
    }

    public String getMeeting_no() {
        return meeting_no;
    }

    public void setMeeting_no(String meeting_no) {
        this.meeting_no = meeting_no;
    }

    public String getMeeting_type() {
        return meeting_type;
    }

    public void setMeeting_type(String meeting_type) {
        this.meeting_type = meeting_type;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MeetManageRelInfosBean> getMeetManageRelInfos() {
        return meetManageRelInfos;
    }

    public void setMeetManageRelInfos(List<MeetManageRelInfosBean> meetManageRelInfos) {
        this.meetManageRelInfos = meetManageRelInfos;
    }

    public static class MeetManageRelInfosBean extends BaseBean {
        /**
         * companyName : 贵州省运管局
         * isCome : true
         * postName : 贵州省湄潭县鸿运客运有限责任公司
         * userName : 张三
         */

        private String companyName;
        private boolean isCome;
        private String postName;
        private String userName;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public boolean isIsCome() {
            return isCome;
        }

        public void setIsCome(boolean isCome) {
            this.isCome = isCome;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
