package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/25.
 * 安全投入
 */

public class SafeInputBean extends BaseBean {


    /**
     * amount : 20.00
     * company_name : 西安华辰交通设施有限责任公司
     * file_url : 研发部绩效考核表-研发部-陈哲-201709.xlsx::/ueditor/jsp/upload/file/20170927/1506510556350027059.xlsx
     * id : 1
     * month : 3
     * purpose : <p>投钱</p>
     * ratio : 4.50
     * remark : 安全使用钱
     * year : 2017
     */

    private String amount;
    private String company_name;
    private String file_url;
    private String id;
    private String month;
    private String purpose;
    private String ratio;
    private String remark;
    private String year;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
