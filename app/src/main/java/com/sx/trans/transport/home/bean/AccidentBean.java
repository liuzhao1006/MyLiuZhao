package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/11,
 * GitHub : https://github.com/liuzhao1006
 */

public class AccidentBean extends BaseBean {


    /**
     * accidentname : 事故
     * accidentreson :
     * accidenttype : 1
     * closedtype : 0
     * driverid : 1
     * groupid : 0
     * id : 1
     * isclosed : 1
     * ishandle : 1
     */

    private String accidentname;
    private String accidentreson;
    private int accidenttype;
    private int closedtype;
    private int driverid;
    private int groupid;
    private int id;
    private int isclosed;
    private int ishandle;

    @Override
    public String toString() {
        return "AccidentBean{" +
                "accidentname='" + accidentname + '\'' +
                ", accidentreson='" + accidentreson + '\'' +
                ", accidenttype=" + accidenttype +
                ", closedtype=" + closedtype +
                ", driverid=" + driverid +
                ", groupid=" + groupid +
                ", id=" + id +
                ", isclosed=" + isclosed +
                ", ishandle=" + ishandle +
                '}';
    }

    public String getAccidentname() {
        return accidentname;
    }

    public void setAccidentname(String accidentname) {
        this.accidentname = accidentname;
    }

    public String getAccidentreson() {
        return accidentreson;
    }

    public void setAccidentreson(String accidentreson) {
        this.accidentreson = accidentreson;
    }

    public int getAccidenttype() {
        return accidenttype;
    }

    public void setAccidenttype(int accidenttype) {
        this.accidenttype = accidenttype;
    }

    public int getClosedtype() {
        return closedtype;
    }

    public void setClosedtype(int closedtype) {
        this.closedtype = closedtype;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(int isclosed) {
        this.isclosed = isclosed;
    }

    public int getIshandle() {
        return ishandle;
    }

    public void setIshandle(int ishandle) {
        this.ishandle = ishandle;
    }
}
