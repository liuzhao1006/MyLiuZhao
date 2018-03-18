package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/22,
 * GitHub : https://github.com/liuzhao1006
 */

public class CommpanyHidderDrangBean extends BaseBean {
    @Override
    public String toString() {
        return "CommpanyHidderDrangBean{" +
                "check_date='" + check_date + '\'' +
                ", check_user='" + check_user + '\'' +
                ", company_name_plan='" + company_name_plan + '\'' +
                ", create_date_ck='" + create_date_ck + '\'' +
                ", deadline_count='" + deadline_count + '\'' +
                ", id_plan='" + id_plan + '\'' +
                ", investigantion_status='" + investigantion_status + '\'' +
                ", manage_date='" + manage_date + '\'' +
                ", method='" + method + '\'' +
                ", month_plan='" + month_plan + '\'' +
                ", notice_count='" + notice_count + '\'' +
                ", notice_date='" + notice_date + '\'' +
                ", object_plan='" + object_plan + '\'' +
                ", person_number='" + person_number + '\'' +
                ", purpose_plan='" + purpose_plan + '\'' +
                ", qualified=" + qualified +
                ", recheck_user='" + recheck_user + '\'' +
                ", remark_plan='" + remark_plan + '\'' +
                ", reopinion='" + reopinion + '\'' +
                ", trouble=" + trouble +
                ", year_plan='" + year_plan + '\'' +
                ", checkItems=" + checkItems +
                '}';
    }

    /**
     * checkItems : [{"checkitemcontent":"1111","checkitemname":"www","id":3,"investigationid":6}]
     * check_date : 2017-10-09
     * check_user : 张龙
     * company_name_plan : 陕西亿程交通信息有限公司
     * create_date_ck : 2017-10-09 14:51:08
     * deadline_count : 2017-10-31 00:00:00
     * id_plan : 6
     * investigantion_status : 已完成
     * manage_date : 2017-10-31 00:00:00
     * method : sss
     * month_plan : 10
     * notice_count : 1
     * notice_date : 2017-10-09 14:44:57
     * object_plan : 从业人员
     * person_number : 15
     * purpose_plan : asd
     * qualified : true
     * recheck_user : 张龙
     * remark_plan : 综合检查
     * reopinion : aaaa
     * trouble : true
     * year_plan : 2017
     */

    private String check_date;
    private String check_user;
    private String company_name_plan;
    private String create_date_ck;
    private String deadline_count;
    private String id_plan;
    private String investigantion_status;
    private String manage_date;
    private String method;
    private String month_plan;
    private String notice_count;
    private String notice_date;
    private String object_plan;
    private String person_number;
    private String purpose_plan;
    private boolean qualified;
    private String recheck_user;
    private String remark_plan;
    private String reopinion;
    private boolean trouble;
    private String year_plan;
    private List<CheckItemsBean> checkItems;

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getCheck_user() {
        return check_user;
    }

    public void setCheck_user(String check_user) {
        this.check_user = check_user;
    }

    public String getCompany_name_plan() {
        return company_name_plan;
    }

    public void setCompany_name_plan(String company_name_plan) {
        this.company_name_plan = company_name_plan;
    }

    public String getCreate_date_ck() {
        return create_date_ck.split("\\s+")[0];
    }

    public void setCreate_date_ck(String create_date_ck) {
        this.create_date_ck = create_date_ck;
    }

    public String getDeadline_count() {
        return deadline_count.split("\\s+")[0];
    }

    public void setDeadline_count(String deadline_count) {
        this.deadline_count = deadline_count;
    }

    public String getId_plan() {
        return id_plan;
    }

    public void setId_plan(String id_plan) {
        this.id_plan = id_plan;
    }

    public String getInvestigantion_status() {
        return investigantion_status;
    }

    public void setInvestigantion_status(String investigantion_status) {
        this.investigantion_status = investigantion_status;
    }

    public String getManage_date() {
        return manage_date.split("\\s+")[0];
    }

    public void setManage_date(String manage_date) {
        this.manage_date = manage_date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMonth_plan() {
        return month_plan;
    }

    public void setMonth_plan(String month_plan) {
        this.month_plan = month_plan;
    }

    public String getNotice_count() {
        return notice_count;
    }

    public void setNotice_count(String notice_count) {
        this.notice_count = notice_count;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }

    public String getObject_plan() {
        return object_plan;
    }

    public void setObject_plan(String object_plan) {
        this.object_plan = object_plan;
    }

    public String getPerson_number() {
        return person_number;
    }

    public void setPerson_number(String person_number) {
        this.person_number = person_number;
    }

    public String getPurpose_plan() {
        return purpose_plan;
    }

    public void setPurpose_plan(String purpose_plan) {
        this.purpose_plan = purpose_plan;
    }

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }

    public String getRecheck_user() {
        return recheck_user;
    }

    public void setRecheck_user(String recheck_user) {
        this.recheck_user = recheck_user;
    }

    public String getRemark_plan() {
        return remark_plan;
    }

    public void setRemark_plan(String remark_plan) {
        this.remark_plan = remark_plan;
    }

    public String getReopinion() {
        return reopinion;
    }

    public void setReopinion(String reopinion) {
        this.reopinion = reopinion;
    }

    public boolean isTrouble() {
        return trouble;
    }

    public void setTrouble(boolean trouble) {
        this.trouble = trouble;
    }

    public String getYear_plan() {
        return year_plan;
    }

    public void setYear_plan(String year_plan) {
        this.year_plan = year_plan;
    }

    public List<CheckItemsBean> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<CheckItemsBean> checkItems) {
        this.checkItems = checkItems;
    }

    public static class CheckItemsBean extends BaseBean{
        /**
         * checkitemcontent : 1111
         * checkitemname : www
         * id : 3
         * investigationid : 6
         */

        private String checkitemcontent;
        private String checkitemname;
        private int id;
        private int investigationid;

        public String getCheckitemcontent() {
            return checkitemcontent;
        }

        public void setCheckitemcontent(String checkitemcontent) {
            this.checkitemcontent = checkitemcontent;
        }

        public String getCheckitemname() {
            return checkitemname;
        }

        public void setCheckitemname(String checkitemname) {
            this.checkitemname = checkitemname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInvestigationid() {
            return investigationid;
        }

        public void setInvestigationid(int investigationid) {
            this.investigationid = investigationid;
        }
    }
}
