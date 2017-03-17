package com.example.saheedadepoju.demorecommend;

/**
 * Created by saheedadepoju on 3/8/17.
 */

public class Recommend {
    private String businessID;
    private double longitude;
    private double latitude;
    private int stars;
    private double confidence;
    private String businessname;
    private double average_stars;

    public double getAverage_stars() {
        return average_stars;
    }

    public void setAverage_stars(double average_stars) {
        this.average_stars = average_stars;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public Recommend(){

    }

    public Recommend(String businessID, double longitude, double latitude, int stars, double confidence) {
        this.businessID = businessID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.stars = stars;
        this.confidence = confidence;
    }

    public String getBusinessID() {

        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
