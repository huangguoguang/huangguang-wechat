package com.huangguang.wechat.message.resp;

/**
 * 回复语音消息
 * Created by huangguang on 2017/9/7.
 */
public class VoiceMessage extends BaseMessage{
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
