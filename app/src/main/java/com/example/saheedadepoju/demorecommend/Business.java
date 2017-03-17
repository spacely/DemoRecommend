package com.example.saheedadepoju.demorecommend;

/**
 * Created by saheedadepoju on 2/26/17.
 */

public class Business {

    String businessID;
    String category;
    double longitude;
    double latitude;
    double stars;
    String city;
    String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Business(){

    }
    public Business(String businessID, String category, double longitude, double latitude) {
        this.businessID = businessID;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }
}
