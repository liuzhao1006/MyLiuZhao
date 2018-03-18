package com.sx.trans.supervision.constants.Request;

/**
 * 修改密码参数
 */

public class UpdatePwdRequest {
    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public UpdatePwdRequest(String oldPwd, String newPwd, String confirm) {
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.confirm = confirm;
    }

    private String oldPwd;
    private String newPwd;
    private String confirm;
}
