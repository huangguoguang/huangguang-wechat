package com.huangguang.wechat.message.resp;

/**
 * 音乐model
 * Created by huangguang on 2017/9/7.
 */
public class Music {
    private String Title;	        //音乐标题
    private String Description;     //	否	音乐描述
    private String MusicURL;        //	否	音乐链接
    private String HQMusicUrl;      //	否	高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String ThumbMediaId;    //	是	缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicURL() {
        return MusicURL;
    }

    public void setMusicURL(String musicURL) {
        MusicURL = musicURL;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
