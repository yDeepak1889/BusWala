package com.example.ydeepak.BusWala.GeneralInfo;

import java.sql.Time;

/**
 * Created by ydeepak on 24/3/17.
 */

public class busCurrentInfo {
    private String name;
    private String lastupdated;
    private double lat;
    private double log;
    private String userid;

    public busCurrentInfo() {

    }

    public busCurrentInfo(String time, double lat, double log, String busName, String id) {
        //Time t = Time.valueOf(time);
        this.name = busName;
        this.lastupdated = time;
        this.log = log;
        this.lat = lat;
        this.userid = id;
    }

    public String getName() {
        return name;
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public double getLat() {
        return lat;
    }

    public double getLog() {
        return log;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastupdated(String time) {
        this.lastupdated = time;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String id) {
        this.userid = id;
    }
}
