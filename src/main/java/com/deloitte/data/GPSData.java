package com.deloitte.data;

import java.io.Serializable;

/**
 * Simple POJO used for sending data over Kafka
 */
public class GPSData implements Serializable {

    private String IMEI;
    private String IMSI;
    private double latitude;
    private double longitude;
    private double speed;
    private double distance;

    public GPSData() {}

    public GPSData(String IMEI, String IMSI) {
        this.IMEI = IMEI;
        this.IMSI = IMSI;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getIMSI() {
        return IMSI;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GPSData{" +
                "IMEI='" + IMEI + '\'' +
                ", IMSI='" + IMSI + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", distance=" + distance +
                '}';
    }
}
