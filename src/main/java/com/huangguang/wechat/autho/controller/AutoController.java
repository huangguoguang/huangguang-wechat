package com.huangguang.wechat.autho.controller;

import com.alibaba.fastjson.JSONObject;
import com.huangguang.wechat.utils.SHA1;
import com.huangguang.wechat.utils.WeChatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by huangguang on 2017/7/27.
 */
@RestController
@RequestMapping(value = "user")
public class AutoController {

    @RequestMapping(value = "wx", method = {RequestMethod.GET, RequestMethod.POST})
    public Object test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(request.getMethod().toLowerCase().equals("get"));
        if (request.getMethod().toLowerCase().equals("get")) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            System.out.println("signature:" + signature);
            System.out.println("timestamp:" + timestamp);
            System.out.println("nonce:" + nonce);
            System.out.println("echostr:" + echostr);
            String str = access(request, response);
            PrintWriter pw = response.getWriter();
            pw.write(str);
            pw.flush();
        } else {
            // 进入POST聊天处理
            System.out.println("跳转");
        }
        return "success";
    }

    @RequestMapping(value = "wx/login", method = RequestMethod.GET)
    public Object autoh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("#############");
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

    /**
     * 验证URL真实性
     *
     * @author morning
     * @date 2015年2月17日 上午10:53:07
     * @param request
     * @param response
     * @return String
     */
    private String access(HttpServletRequest request, HttpServletResponse response) {
        // 验证URL真实性
        System.out.println("进入验证access");
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        List<String> params = new ArrayList<String>();
        params.add("123456789");
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            return echostr;
        }
        System.out.println("失败 认证");
        return null;
    }
}
