package com.tongdun.data.utils;

/**
 * 
 * @author yxw
 *
 */
public class APIConfig {
    private String appName;
    private String secretKey;
    private String eventId;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "APIConfig{" +
                "appName='" + appName + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", eventId='" + eventId + '\'' +
                '}';
    }
}
