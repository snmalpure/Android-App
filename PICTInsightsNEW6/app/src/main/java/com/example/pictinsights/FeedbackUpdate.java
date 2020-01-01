package com.example.pictinsights;

public class FeedbackUpdate {
    String star;
    String starId;
    FeedbackUpdate()
    {}

    public FeedbackUpdate(String star, String starId) {
        this.star = star;
        this.starId = starId;
    }

    public String getStar() {
        return star;
    }

    public String getStarId() {
        return starId;
    }
}
