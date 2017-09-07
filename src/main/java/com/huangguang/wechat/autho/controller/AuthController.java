package com.huangguang.wechat.autho.controller;

import com.alibaba.fastjson.JSONObject;
import com.huangguang.wechat.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户授权接口
 * Created by huangguang on 2017/7/27.
 */
@RestController
@RequestMapping(value = "wx")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Object autoh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("进入登录接口");
        String code = request.getParameter("code");
        System.out.println(code);
        System.out.println(request.getParameterMap());
        System.out.println(request.getParameter("orderId"));
        System.out.println(request.getParameter("state"));
        Map<String, String> map =new HashMap<String, String>();
        //获取用户微信信息
        JSONObject wxUserInfo = WeChatUtil.getWeChatUserInfo(code);
        if (wxUserInfo == null || wxUserInfo.isEmpty()) {
            response.sendRedirect(WeChatUtil.getWeChatLoginUrl(request));//重新加载
        } else {
            String openid = wxUserInfo.getString("openid");//微信openId
            String nickname = wxUserInfo.getString("nickname");//微信昵称
            String headimage = wxUserInfo.getString("headimgurl");//微信头像
            map.put("wxOpenId", openid);
            map.put("nickName", nickname);
            map.put("headImage", headimage);
        }
        return map;
    }


}
