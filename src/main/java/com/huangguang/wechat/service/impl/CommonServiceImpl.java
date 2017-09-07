package com.huangguang.wechat.service.impl;

import com.huangguang.wechat.constants.WeChatConstants;
import com.huangguang.wechat.service.CommonService;
import com.huangguang.wechat.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangguang on 2017/9/6.
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getAccessToken() throws Exception {
        String accessToken = redisTemplate.opsForValue().get(WeChatConstants.ACCESS_TOKEN_STR);
        if (accessToken == null) {
            accessToken = WeChatUtil.getAccessToken().getString(WeChatConstants.ACCESS_TOKEN_STR);
            redisTemplate.opsForValue().set(WeChatConstants.ACCESS_TOKEN_STR, accessToken, 7200, TimeUnit.SECONDS);
        }
        return accessToken;
    }
}
