package com.example.ydeepak.BusWala.GeneralInfo;

/**
 * Created by ydeepak on 24/3/17.
 */

public class busCurrentInfo {
    private String name;
    private String time;
    private double lat;
    private double log;

    public busCurrentInfo (){

    }

    public busCurrentInfo (String name, String time, double lat, double log) {
        this.name = name;
        this.time = time;
        this.log = log;
        this.lat = lat;
    }

    public String getName () {
        return name;
    }
    public String getTime () {
        return time;
    }
    public double getLat () {
        return lat;
    }
    public double getLog () {
        return log;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setTime (String time) {
        this.time = time;
    }
    public void setLat (double lat) {
        this.lat = lat;
    }
    public void setLog (double log) {
        this.log  = log;
    }
}
