package com.sx.trans.supervision.constants;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/8/1.
 * 轨迹回放
 */

public class TrackVideo implements Serializable {

    private float gpsSpeed;
    private String gpsTime;
    private int head=0;
    private double latitude;
    private double longitude;

    public float getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(float gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
