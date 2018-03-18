package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by xxxxxx on 2017/11/20.
 */

public class SendLeaderMailbox extends BaseBean {

    /**
     * code : 0
     * message : 操作成功
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
