package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuchao on 2017/9/27.
 * 安全演习
 */

public class CommpanySafeExerciseBean extends BaseBean {


    /**
     * commandperson_drill : 张蓉
     * company_name : 贵州好风光旅游巴士有限责任公司惠水分公司
     * create_date_drill : 2018-01-08 18:12:48
     * create_date_review :
     * create_user_drill : 1
     * create_user_groupid_drill : 10013
     * create_user_groupid_review :
     * create_user_review :
     * effectivenessevaluation_drill : 驾驶员掌握灭火器基本使用技巧和对疏散逃生的正确动作与方式
     * emergencydrillid_review :
     * file_url :
     * groupid_drill : 36
     * groupid_review :
     * id_drill : 15
     * id_review :
     * is_delete_drill :  
     * is_delete_review :
     * participantusers_drill : 2
     * perfectopinions_review :
     * personList : [{"chargeperson":"应急实施","chargepersontel":"13025457895","groupid":10016,"id":64,"teamid":14,"teamname":"樊期望","userid":25},{"chargeperson":"应急实施","chargepersontel":"13025457878","groupid":10016,"id":65,"teamid":14,"teamname":"关小龙","userid":26}]
     * planid_drill : 10
     * planname : 贵州好风光旅游巴士有限责任公司 火灾爆炸事故专项应急预案
     * rectificationmeasures_review :
     * rehearsaladdress_drill : 公司停车场
     * rehearsaldate_drill : 2017-12-15 00:00:00
     * rehearsaldescription_drill : 演练日期：2017.12.15   早上10点      演练地点：总公司停车场
     演练前器材准备：1、灭火器3个。2、 火盆2个。3、木材、点火棒、汽油、柴油。4、毛巾40张。5、烟雾弹2枚。6、会议室 、电脑、投影仪。
     提前准备会场
     15号早上10点会议准时开始       请各位领导讲话
     11点给火盆点火    给演练人员分发演练器材      演练开始        内容：教各驾驶员正确使用灭火器等消防器材 及车内着火逃生疏散演练
     * rehearsalid_drill :
     * rehearsalname_drill : 贵州好风光旅游巴士有限责任公司2017消防演练方案
     * rehearsaltype_drill : 2
     * reviewdate_review :
     * reviewperson_review :
     * reviewresult_review :
     * teamid_drill : 14
     * teamname : 重大事故应急小组
     */

    private String commandperson_drill;
    private String company_name;
    private String create_date_drill;
    private String create_date_review;
    private String create_user_drill;
    private String create_user_groupid_drill;
    private String create_user_groupid_review;
    private String create_user_review;
    private String effectivenessevaluation_drill;
    private String emergencydrillid_review;
    private String file_url;
    private String groupid_drill;
    private String groupid_review;
    private String id_drill;
    private String id_review;
    private String is_delete_drill;
    private String is_delete_review;
    private String participantusers_drill;
    private String perfectopinions_review;
    private String planid_drill;
    private String planname;
    private String rectificationmeasures_review;
    private String rehearsaladdress_drill;
    private String rehearsaldate_drill;
    private String rehearsaldescription_drill;
    private String rehearsalid_drill;
    private String rehearsalname_drill;
    private String rehearsaltype_drill;
    private String reviewdate_review;
    private String reviewperson_review;
    private String reviewresult_review;
    private String teamid_drill;
    private String teamname;
    private List<PersonListBean> personList;

    public String getCommandperson_drill() {
        return commandperson_drill;
    }

    public void setCommandperson_drill(String commandperson_drill) {
        this.commandperson_drill = commandperson_drill;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCreate_date_drill() {
        return create_date_drill;
    }

    public void setCreate_date_drill(String create_date_drill) {
        this.create_date_drill = create_date_drill;
    }

    public String getCreate_date_review() {
        return create_date_review;
    }

    public void setCreate_date_review(String create_date_review) {
        this.create_date_review = create_date_review;
    }

    public String getCreate_user_drill() {
        return create_user_drill;
    }

    public void setCreate_user_drill(String create_user_drill) {
        this.create_user_drill = create_user_drill;
    }

    public String getCreate_user_groupid_drill() {
        return create_user_groupid_drill;
    }

    public void setCreate_user_groupid_drill(String create_user_groupid_drill) {
        this.create_user_groupid_drill = create_user_groupid_drill;
    }

    public String getCreate_user_groupid_review() {
        return create_user_groupid_review;
    }

    public void setCreate_user_groupid_review(String create_user_groupid_review) {
        this.create_user_groupid_review = create_user_groupid_review;
    }

    public String getCreate_user_review() {
        return create_user_review;
    }

    public void setCreate_user_review(String create_user_review) {
        this.create_user_review = create_user_review;
    }

    public String getEffectivenessevaluation_drill() {
        return effectivenessevaluation_drill;
    }

    public void setEffectivenessevaluation_drill(String effectivenessevaluation_drill) {
        this.effectivenessevaluation_drill = effectivenessevaluation_drill;
    }

    public String getEmergencydrillid_review() {
        return emergencydrillid_review;
    }

    public void setEmergencydrillid_review(String emergencydrillid_review) {
        this.emergencydrillid_review = emergencydrillid_review;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getGroupid_drill() {
        return groupid_drill;
    }

    public void setGroupid_drill(String groupid_drill) {
        this.groupid_drill = groupid_drill;
    }

    public String getGroupid_review() {
        return groupid_review;
    }

    public void setGroupid_review(String groupid_review) {
        this.groupid_review = groupid_review;
    }

    public String getId_drill() {
        return id_drill;
    }

    public void setId_drill(String id_drill) {
        this.id_drill = id_drill;
    }

    public String getId_review() {
        return id_review;
    }

    public void setId_review(String id_review) {
        this.id_review = id_review;
    }

    public String getIs_delete_drill() {
        return is_delete_drill;
    }

    public void setIs_delete_drill(String is_delete_drill) {
        this.is_delete_drill = is_delete_drill;
    }

    public String getIs_delete_review() {
        return is_delete_review;
    }

    public void setIs_delete_review(String is_delete_review) {
        this.is_delete_review = is_delete_review;
    }

    public String getParticipantusers_drill() {
        return participantusers_drill;
    }

    public void setParticipantusers_drill(String participantusers_drill) {
        this.participantusers_drill = participantusers_drill;
    }

    public String getPerfectopinions_review() {
        return perfectopinions_review;
    }

    public void setPerfectopinions_review(String perfectopinions_review) {
        this.perfectopinions_review = perfectopinions_review;
    }

    public String getPlanid_drill() {
        return planid_drill;
    }

    public void setPlanid_drill(String planid_drill) {
        this.planid_drill = planid_drill;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getRectificationmeasures_review() {
        return rectificationmeasures_review;
    }

    public void setRectificationmeasures_review(String rectificationmeasures_review) {
        this.rectificationmeasures_review = rectificationmeasures_review;
    }

    public String getRehearsaladdress_drill() {
        return rehearsaladdress_drill;
    }

    public void setRehearsaladdress_drill(String rehearsaladdress_drill) {
        this.rehearsaladdress_drill = rehearsaladdress_drill;
    }

    public String getRehearsaldate_drill() {
        return rehearsaldate_drill;
    }

    public void setRehearsaldate_drill(String rehearsaldate_drill) {
        this.rehearsaldate_drill = rehearsaldate_drill;
    }

    public String getRehearsaldescription_drill() {
        return rehearsaldescription_drill;
    }

    public void setRehearsaldescription_drill(String rehearsaldescription_drill) {
        this.rehearsaldescription_drill = rehearsaldescription_drill;
    }

    public String getRehearsalid_drill() {
        return rehearsalid_drill;
    }

    public void setRehearsalid_drill(String rehearsalid_drill) {
        this.rehearsalid_drill = rehearsalid_drill;
    }

    public String getRehearsalname_drill() {
        return rehearsalname_drill;
    }

    public void setRehearsalname_drill(String rehearsalname_drill) {
        this.rehearsalname_drill = rehearsalname_drill;
    }

    public String getRehearsaltype_drill() {
        return rehearsaltype_drill;
    }

    public void setRehearsaltype_drill(String rehearsaltype_drill) {
        this.rehearsaltype_drill = rehearsaltype_drill;
    }

    public String getReviewdate_review() {
        return reviewdate_review;
    }

    public void setReviewdate_review(String reviewdate_review) {
        this.reviewdate_review = reviewdate_review;
    }

    public String getReviewperson_review() {
        return reviewperson_review;
    }

    public void setReviewperson_review(String reviewperson_review) {
        this.reviewperson_review = reviewperson_review;
    }

    public String getReviewresult_review() {
        return reviewresult_review;
    }

    public void setReviewresult_review(String reviewresult_review) {
        this.reviewresult_review = reviewresult_review;
    }

    public String getTeamid_drill() {
        return teamid_drill;
    }

    public void setTeamid_drill(String teamid_drill) {
        this.teamid_drill = teamid_drill;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public List<PersonListBean> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonListBean> personList) {
        this.personList = personList;
    }

    public static class PersonListBean implements Serializable {
        /**
         * chargeperson : 应急实施
         * chargepersontel : 13025457895
         * groupid : 10016
         * id : 64
         * teamid : 14
         * teamname : 樊期望
         * userid : 25
         */

        private String chargeperson;
        private String chargepersontel;
        private int groupid;
        private int id;
        private int teamid;
        private String teamname;
        private int userid;

        public String getChargeperson() {
            return chargeperson;
        }

        public void setChargeperson(String chargeperson) {
            this.chargeperson = chargeperson;
        }

        public String getChargepersontel() {
            return chargepersontel;
        }

        public void setChargepersontel(String chargepersontel) {
            this.chargepersontel = chargepersontel;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTeamid() {
            return teamid;
        }

        public void setTeamid(int teamid) {
            this.teamid = teamid;
        }

        public String getTeamname() {
            return teamname;
        }

        public void setTeamname(String teamname) {
            this.teamname = teamname;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
