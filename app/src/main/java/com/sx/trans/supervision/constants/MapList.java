package com.sx.trans.supervision.constants;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/8/31.
 * 地图模块数据
 */

public class MapList implements Serializable {
//2742	520100	贵阳市
//2754	520200	六盘水市
//2759	520300	遵义市
//2775	520400	安顺市
//2783	522200	铜仁地区
//2794	522300	黔西南布依族苗族自治州
//2803	522400	毕节地区
//2812	522600	黔东南苗族侗族自治州
//2829	522700	黔南布依族苗族自治州
    private  int id=0;//区域id
    private String  name;//区域名称,
    private int onLineVehCnt=0;//在线总数
    private int vehCnt=0;//车辆总数
    private String areaCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnLineVehCnt() {
        return onLineVehCnt;
    }

    public void setOnLineVehCnt(int onLineVehCnt) {
        this.onLineVehCnt = onLineVehCnt;
    }

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
