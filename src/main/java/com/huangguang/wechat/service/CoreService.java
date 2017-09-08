package com.huangguang.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 核心服务类
 * Created by huangguang on 2017/9/8.
 */
public interface CoreService {
    String processRequest(HttpServletRequest request, HttpServletResponse response);
}
