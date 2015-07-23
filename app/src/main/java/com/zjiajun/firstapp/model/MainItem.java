package com.zjiajun.firstapp.model;

/**
 * Created by zhujiajun
 * 15/7/7 14:33
 */
public class MainItem {

    private String name;
    private Object object;

    public MainItem(String name, Object object) {
        this.name = name;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

    /**
     * 为ArrayAdapter设置,让listView显示name
     */
    @Override
    public String toString() {
        return name;
    }
}
