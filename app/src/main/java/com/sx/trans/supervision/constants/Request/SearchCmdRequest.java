package com.sx.trans.supervision.constants.Request;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/9/4.
 */

public class SearchCmdRequest implements Serializable {

//    {"tradeId":"1","name":"客运行业","question":"题目内容","answer":"题目答案","checker":"用户名称"}

    private String tradeId;
    private String name;
    private String question;
    private String answer;
    private String checker;

    public SearchCmdRequest(String tradeId, String name, String question, String answer, String checker) {
        this.tradeId = tradeId;
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.checker = checker;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
}
