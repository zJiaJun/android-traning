package com.zjiajun.firstapp.model;

/**
 * Created by zhujiajun
 * 15/7/6 21:29
 */
public class CustomItem {

    private String name;
    private int imageId;

    public CustomItem(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
