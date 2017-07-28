package com.huangguang.wechat.controller;

import com.huangguang.wechat.utils.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huangguang on 2017/7/28.
 */
@RequestMapping(value = "wx")
@RestController
public class WeChatUrlController {
    private static final Logger logger = LoggerFactory.getLogger(WeChatUrlController.class);

    @RequestMapping(value = "url", method = {RequestMethod.GET, RequestMethod.POST})
    public Object test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("进入url认证接口, 方法为 ： " + request.getMethod().toLowerCase() );
        if (request.getMethod().toLowerCase().equals("get")) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            logger.info("signature:" + signature);
            logger.info("timestamp:" + timestamp);
            logger.info("nonce:" + nonce);
            logger.info("echostr:" + echostr);
            String str = access(request, response);
            PrintWriter pw = response.getWriter();
            pw.write(str);
            pw.flush();
        } else {
            // 进入POST聊天处理
            logger.info("跳转");
        }
        return "success";
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
        logger.info("进入验证access");
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
        logger.info("失败 认证");
        return null;
    }
}
