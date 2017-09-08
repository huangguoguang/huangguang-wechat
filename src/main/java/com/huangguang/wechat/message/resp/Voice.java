package com.huangguang.wechat.message.resp;

/**
 * 语音model
 * Created by huangguang on 2017/9/8.
 */
public class Voice {
    private String MediaId;    //通过素材管理中的接口上传多媒体文件，得到的id。

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
