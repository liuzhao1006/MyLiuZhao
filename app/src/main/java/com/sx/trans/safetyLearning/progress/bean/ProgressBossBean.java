package com.sx.trans.safetyLearning.progress.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressBossBean extends BaseBean {


    /**
     * returnCode : 1
     * returnMsg : 获取成功
     * returnDate : [{"CompanyName":"广西亿程","CompanyId":"4146","stuNum":"2","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"1"},{"CompanyName":"北海亿程","CompanyId":"4147","stuNum":"2","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"2"},{"CompanyName":"北海信达","CompanyId":"5146","stuNum":"4","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"3"},{"CompanyName":"tes单位","CompanyId":"5147","stuNum":"4","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"4"},{"CompanyName":"测试单位","CompanyId":"5148","stuNum":"9","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"5"},{"CompanyName":"芳芳测试单位","CompanyId":"5149","stuNum":"1","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"6"},{"CompanyName":"楠森测试单位","CompanyId":"5151","stuNum":"9","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"7"},{"CompanyName":"陕西测试单位","CompanyId":"5152","stuNum":"4","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"8"},{"CompanyName":"lff0818test单位","CompanyId":"5153","stuNum":"1","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"9"},{"CompanyName":"陕测试单位中心","CompanyId":"5154","stuNum":"3","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"10"},{"CompanyName":"test协会","CompanyId":"5155","stuNum":"","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"11"},{"CompanyName":"test0906单位","CompanyId":"5156","stuNum":"9","BasicsHours":"0","Finish":"0","NoFinish":"9","hoursRatio":"0","FinishRatio":"0.00","ranking":"12"},{"CompanyName":"新单位","CompanyId":"5159","stuNum":"3","BasicsHours":"0","Finish":"0","NoFinish":"0","hoursRatio":"0","FinishRatio":"0.00","ranking":"13"}]
     */

    private String returnCode;
    private String returnMsg;
    private List<ReturnDateBean> returnDate;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public List<ReturnDateBean> getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(List<ReturnDateBean> returnDate) {
        this.returnDate = returnDate;
    }

    public static class ReturnDateBean {
        /**
         * CompanyName : 广西亿程
         * CompanyId : 4146
         * stuNum : 2
         * BasicsHours : 0
         * Finish : 0
         * NoFinish : 0
         * hoursRatio : 0
         * FinishRatio : 0.00
         * ranking : 1
         */

        private String CompanyName;
        private String CompanyId;
        private String stuNum;
        private String BasicsHours;
        private String Finish;
        private String NoFinish;
        private String hoursRatio;
        private String FinishRatio;
        private String ranking;

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(String CompanyId) {
            this.CompanyId = CompanyId;
        }

        public String getStuNum() {
            return stuNum;
        }

        public void setStuNum(String stuNum) {
            this.stuNum = stuNum;
        }

        public String getBasicsHours() {
            return BasicsHours;
        }

        public void setBasicsHours(String BasicsHours) {
            this.BasicsHours = BasicsHours;
        }

        public String getFinish() {
            return Finish;
        }

        public void setFinish(String Finish) {
            this.Finish = Finish;
        }

        public String getNoFinish() {
            return NoFinish;
        }

        public void setNoFinish(String NoFinish) {
            this.NoFinish = NoFinish;
        }

        public String getHoursRatio() {
            return hoursRatio;
        }

        public void setHoursRatio(String hoursRatio) {
            this.hoursRatio = hoursRatio;
        }

        public String getFinishRatio() {
            return FinishRatio;
        }

        public void setFinishRatio(String FinishRatio) {
            this.FinishRatio = FinishRatio;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }
    }
}
