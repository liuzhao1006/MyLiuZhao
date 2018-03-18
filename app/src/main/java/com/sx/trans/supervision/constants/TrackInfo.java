package com.sx.trans.supervision.constants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mr_wang on 2017/8/1.
 * 轨迹回放
 */

public class TrackInfo implements Serializable {



    private String first;
    private String last;
    private ArrayList<TrackVideo> location;

    public String getFist() {
        return first;
    }

    public void setFist(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public ArrayList<TrackVideo> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<TrackVideo> location) {
        this.location = location;
    }


}
