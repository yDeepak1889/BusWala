package com.example.ydeepak.BusWala.GeneralInfo;

/**
 * Created by ydeepak on 24/3/17.
 */

public class busAdd {
    private String busName;
    private String userId;

    public busAdd () {

    }

    public busAdd (String busName, String userId) {
        this.busName = busName;
        this.userId = userId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
