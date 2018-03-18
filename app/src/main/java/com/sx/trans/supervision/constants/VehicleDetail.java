package com.sx.trans.supervision.constants;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 车辆信息
 */

public class VehicleDetail extends DataSupport implements Serializable {

    /**
     * areaName : 贵阳市
     * color : 1
     * colorName : 黄色
     * entName : 班线客运
     * netTime : 2017-08-23 16:58:14.0
     * platName : 亿程A
     * regisNo : 粤A1111
     * terminalType : 国标(gb)
     * tradeName : 班线客运
     * vehicleKind : SUV
     * vid : 1
     */

    private String areaName;
    private int color=0;
    private String colorName;
    private String entName;
    private String netTime;
    private String platName;
    private String regisNo;
    private String terminalType;
    private String tradeName;
    private String vehicleKind;
    private int vid=0;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getNetTime() {
        return netTime;
    }

    public void setNetTime(String netTime) {
        this.netTime = netTime;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getRegisNo() {
        return regisNo;
    }

    public void setRegisNo(String regisNo) {
        this.regisNo = regisNo;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getVehicleKind() {
        return vehicleKind;
    }

    public void setVehicleKind(String vehicleKind) {
        this.vehicleKind = vehicleKind;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }
}
