package com.sx.trans.supervision.constants;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/9/4.
 * 查岗记录
 */

public class PostCheckInfo implements Serializable {

    private String time ;//查岗时间,
    private String question; //查岗内容,
    private int entCnt=0; //业户数,
    private double onLineRatio; //在岗率,
    private String name; //行业名称,

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getEntCnt() {
        return entCnt;
    }

    public void setEntCnt(int entCnt) {
        this.entCnt = entCnt;
    }

    public double getOnineRatio() {
        return onLineRatio;
    }

    public void setOnineRatio(double onLineRatio) {
        this.onLineRatio = onLineRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
