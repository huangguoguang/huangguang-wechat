package com.huangguang.wechat.message.req;

/**
 * 视频消息
 * Created by huangguang on 2017/9/7.
 */
public class VideoMessage extends BaseMessage{
    private String MediaId;	//视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String ThumbMediaId;	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}