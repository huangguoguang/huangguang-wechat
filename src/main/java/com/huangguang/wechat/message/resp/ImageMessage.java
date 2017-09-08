package com.huangguang.wechat.message.resp;

/**
 * 回复图片消息
 * Created by huangguang on 2017/9/7.
 */
public class ImageMessage extends BaseMessage{
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
