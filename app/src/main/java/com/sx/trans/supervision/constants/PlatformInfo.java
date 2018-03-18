package com.sx.trans.supervision.constants;

/**
 * 业户分析-公司
 */

public class PlatformInfo  {
//    平台分析类 {
//        id (integer, optional): 平台id,
//                name (string, optional): 平台名称,
//                vehCnt (integer, optional): 车辆总数,
//                regVehCnt (integer, optional): 入网总数,
//                link (integer, optional): 连通率,
//                quarters (integer, optional): 查岗响应率,
//                regVehRadio (integer, optional): 车辆入网率
//    }

    /**
     * id : 1
     * link : 80
     * name : 亿程A
     * quarters : 0
     * regVehCnt : 46
     * regVehRadio : 94
     * vehCnt : 30
     */

    private int id=0;
    private double link=0;
    private String name;
    private int quarters=0;
    private int regVehCnt=0;
    private double regVehRatio=0;;//入网率
    private int vehCnt=0;

//    [{"gnssId":"52260030","id":1.0,"link":27.56,"name":"亿程卫星定位动态车辆监控平台",
//            "quarters":0.0,"regVehCnt":3746.0,"regVehRatio":58.72,"vehCnt":6379.0}]
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLink() {
        return link;
    }

    public void setLink(double link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getRegVehCnt() {
        return regVehCnt;
    }

    public void setRegVehCnt(int regVehCnt) {
        this.regVehCnt = regVehCnt;
    }

    public double getRegVehRadio() {
        return regVehRatio;
    }

    public void setRegVehRadio(double regVehRatio) {
        this.regVehRatio = regVehRatio;
    }

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }
}
