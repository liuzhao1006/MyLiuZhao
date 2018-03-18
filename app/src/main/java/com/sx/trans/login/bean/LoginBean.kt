package com.sx.trans.login.bean

import com.sx.trans.base.BaseBean

/**
 * 作者 : 刘朝,
 * on 2017/9/6,
 * GitHub : https://github.com/liuzhao1006
 */

class LoginBean(var user: String?, var pwd: String?, var userType: String?) : BaseBean() {
    override fun toString(): String {
        return "LoginBean(user=$user, pwd=$pwd, userType=$userType)"
    }
}
