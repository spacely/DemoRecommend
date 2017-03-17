package com.example.saheedadepoju.demorecommend;

/**
 * Created by saheedadepoju on 2/26/17.
 */

public class Review {

    private String reviewID;
    private int stars;
    private String BusinessID;
    private String UserID;
    private String reviewText;

    public Review(String reviewID, int stars, String businessID, String reviewText, String userID) {
        this.reviewID = reviewID;
        this.stars = stars;
        BusinessID = businessID;
        this.reviewText = reviewText;
        UserID = userID;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getBusinessID() {
        return BusinessID;
    }

    public void setBusinessID(String businessID) {
        BusinessID = businessID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
