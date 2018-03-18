package com.sx.trans.supervision.constants;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/9/4.
 * 查岗记录详情
 */

public class PostCheckMoreInfo implements Serializable {

    private String checkedName;//查岗业户名称,
    private String question;//查岗问题,
    private String answer;//答案,
    private String time;//响应时间,
    private String reply;//回应

    public String getCheckedName() {
        return checkedName;
    }

    public void setCheckedName(String checkedName) {
        this.checkedName = checkedName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }



}
