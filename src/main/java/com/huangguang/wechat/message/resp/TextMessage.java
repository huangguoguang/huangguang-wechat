package com.huangguang.wechat.message.resp;

/**
 * 响应文本消息
 * Created by huangguang on 2017/9/7.
 */
public class TextMessage extends BaseMessage{
    // 消息内容
    private String Content;

    public void setContent(String content) {
        Content = content;
    }

    public String getContent() {
        return Content;
    }
}
