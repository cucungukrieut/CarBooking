package com.inducesmile.taxirental.models;



public class FeatureObject {

    private String featureTitle;
    private String featureValue;

    public FeatureObject(String featureTitle, String featureValue) {
        this.featureTitle = featureTitle;
        this.featureValue = featureValue;
    }

    public String getFeatureTitle() {
        return featureTitle;
    }

    public String getFeatureValue() {
        return featureValue;
    }
}
