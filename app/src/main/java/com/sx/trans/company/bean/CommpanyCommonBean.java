package com.sx.trans.company.bean;

/**
 * Created by Administrator on 2017/9/13.
 */

public class CommpanyCommonBean {
    private String Title;
    private String Result;
    private String Date;
    private String Grade;

    public CommpanyCommonBean(String title, String result, String date, String grade) {
        Title = title;
        Result = result;
        Date = date;
        Grade = grade;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
