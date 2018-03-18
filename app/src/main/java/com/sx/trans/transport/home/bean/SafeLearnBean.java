package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/29.
 */

public class SafeLearnBean extends BaseBean {


    /**
     * mPer : 0.00
     * mNotLearn : 60
     * mBasicsLearn : 60.00
     * pNotLearn : 60
     * pBasicsLearn : 60.00
     */

    private String mPer;
    private String mNotLearn;
    private String mBasicsLearn;
    private String pNotLearn;
    private String pBasicsLearn;

    public String getMPer() {
        return mPer;
    }

    public void setMPer(String mPer) {
        this.mPer = mPer;
    }

    public String getMNotLearn() {
        return mNotLearn;
    }

    public void setMNotLearn(String mNotLearn) {
        this.mNotLearn = mNotLearn;
    }

    public String getMBasicsLearn() {
        return mBasicsLearn;
    }

    public void setMBasicsLearn(String mBasicsLearn) {
        this.mBasicsLearn = mBasicsLearn;
    }

    public String getPNotLearn() {
        return pNotLearn;
    }

    public void setPNotLearn(String pNotLearn) {
        this.pNotLearn = pNotLearn;
    }

    public String getPBasicsLearn() {
        return pBasicsLearn;
    }

    public void setPBasicsLearn(String pBasicsLearn) {
        this.pBasicsLearn = pBasicsLearn;
    }
}
