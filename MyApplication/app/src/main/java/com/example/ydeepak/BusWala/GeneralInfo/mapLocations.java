package com.example.ydeepak.BusWala.GeneralInfo;

/**
 * Created by ydeepak on 25/3/17.
 */

public class mapLocations {
    private double lat;
    private double log;

    public mapLocations () {

    }

    public mapLocations (double lat, double log) {
        this.lat = lat;
        this.log = log;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
