package com.huangguang.wechat.message.resp;

/**
 * 回复视频消息
 * Created by huangguang on 2017/9/7.
 */
public class VideoMessage extends BaseMessage{
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
