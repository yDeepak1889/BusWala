package com.example.ydeepak.BusWala.GeneralInfo;

/**
 * Created by ydeepak on 24/3/17.
 */

public class GeoLocation {
    private double lat;
    private double log;

    public GeoLocation() {

    }

    public GeoLocation(double lat, double log) {
        this.lat = lat;
        this.log = log;
    }

    public double getLat () {
        return lat;
    }

    public double getLog() {
        return log;
    }

    public double log () {
        return log;
    }

    public void setLat (double lat) {
        this.lat = lat;
    }
    public void setLog (double log) {
        this.log = log;
    }
}
