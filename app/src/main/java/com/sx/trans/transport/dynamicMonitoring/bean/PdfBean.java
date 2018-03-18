package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/14,
 * GitHub : https://github.com/liuzhao1006
 */

public class PdfBean extends BaseBean {
    public PdfBean(String name, String path,String time) {
        this.name = name;
        this.time = time;
        this.path = path;

    }
    public String name;
    public String path;
    public String time;
}
