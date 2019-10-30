package com.inducesmile.taxirental.models;


import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CarCategoryObject {

    private int id;
    private String imagePath;
    private String imageName;

    public CarCategoryObject(int id, String imagePath, String imageName) {
        this.id = id;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public CarCategoryObject(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageName() {
        return imageName;
    }
}
