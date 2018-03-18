package com.sx.trans.supervision.constants.Request;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/9/4.
 * 查岗记录详情
 */

public class CheckDetailInfo implements Serializable {

    private String question;//问题
    private String time;//查岗时间(yyyy-mm-dd h:m:s:ssS)
    private int limit;
    private int page;


    public CheckDetailInfo(String question, String time, int limit, int page) {
        this.question = question;
        this.time = time;
        this.limit = limit;
        this.page = page;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


}
