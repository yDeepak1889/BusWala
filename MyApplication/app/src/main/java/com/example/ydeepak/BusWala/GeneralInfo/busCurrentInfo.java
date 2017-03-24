package com.example.ydeepak.BusWala.GeneralInfo;

/**
 * Created by ydeepak on 24/3/17.
 */

public class busCurrentInfo {
    private String busName;
    private String lastupdated;
    private double lat;
    private double log;
    private String id;

    public busCurrentInfo() {

    }

    public busCurrentInfo(String name, String time, double lat, double log, String id) {
        this.busName = name;
        this.lastupdated = time;
        this.log = log;
        this.lat = lat;
        this.id = id;
    }

    public String getName() {
        return busName;
    }

    public String getTime() {
        return lastupdated;
    }

    public double getLat() {
        return lat;
    }

    public double getLog() {
        return log;
    }

    public void setName(String name) {
        this.busName = name;
    }

    public void setTime(String time) {
        this.lastupdated = time;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
