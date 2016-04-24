package com.framgia.lupx.realm.tracking;

import io.realm.RealmObject;

/**
 * Created by LUPX on 4/23/2016.
 */
public class TrackLocation extends RealmObject {
    private double latitude;
    private double longitude;
    private long time;
    private float speed;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public long getTime() {
        return this.time;
    }

    public float getSpeed() {
        return this.speed;
    }
}