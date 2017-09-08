package com.huangguang.wechat.message.resp;

/**
 * 回复音乐消息
 * Created by huangguang on 2017/9/7.
 */
public class MusicMessage extends BaseMessage{
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
