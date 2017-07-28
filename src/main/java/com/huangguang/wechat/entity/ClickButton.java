package com.huangguang.wechat.entity;

/**
 * click：点击推事件用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event的结构给开发者（参考消息接口指南），
 * 并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互
 * Created by huangguang on 2017/7/28.
 */
public class ClickButton extends Button {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
