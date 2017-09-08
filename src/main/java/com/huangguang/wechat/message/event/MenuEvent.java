package com.huangguang.wechat.message.event;

/**
 * 自定义菜单事件
 * Created by huangguang on 2017/9/8.
 */
public class MenuEvent extends BaseEvent{
    // 事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
