package com.zjiajun.firstapp.model;

/**
 * Created by zhujiajun
 * 15/7/7 20:31
 */
public class MsgItem {

    public static final int TYPE_RECEIVED = 0;

    public static final int TYPE_SENT = 1;

    private String content;

    private int type;

    public MsgItem(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
