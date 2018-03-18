package com.sx.trans.supervision.constants;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by mr_wang on 2017/8/31.
 */

public class RegionAllAnalsisInfo implements Serializable {

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public Map<String, MapList> getMap() {
        return map;
    }

    public void setMap(Map<String, MapList> map) {
        this.map = map;
    }

    private int areaCode;//区域code,
    private Map<String,MapList> map;
}
