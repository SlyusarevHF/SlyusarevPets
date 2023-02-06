package com.example.tryslyus;

import android.graphics.Bitmap;

import java.util.List;

public class Pet {
    private String name;
    private String photo;
    private List<String> photoURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(List<String> photoURL) {
        this.photoURL = photoURL;
    }
}