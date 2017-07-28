package com.huangguang.wechat.autho.controller;

import com.alibaba.fastjson.JSONObject;
import com.huangguang.wechat.utils.SHA1;
import com.huangguang.wechat.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * 用户授权接口
 * Created by huangguang on 2017/7/27.
 */
@RestController
@RequestMapping(value = "user")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "wx/login", method = RequestMethod.GET)
    public Object autoh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("进入登录接口");
        String code = request.getParameter("code");
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
