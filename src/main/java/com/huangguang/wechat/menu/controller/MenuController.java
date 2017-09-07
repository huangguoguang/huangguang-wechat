package com.huangguang.wechat.menu.controller;

import com.huangguang.wechat.service.CommonService;
import com.huangguang.wechat.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangguang on 2017/9/6.
 */
@RestController
@RequestMapping(value = "wx/menu")
public class MenuController {
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "create")
    public void createMenu() throws Exception {
        String accessToken = commonService.getAccessToken();
        MenuUtil.createMenu(accessToken);
    }

    @RequestMapping(value = "select")
    public void selectMenu() throws Exception {
        String accessToken = commonService.getAccessToken();
        MenuUtil.selectMenu(accessToken);
    }

    @RequestMapping(value = "delete")
    public void deleteMenu() throws Exception {
        String accessToken = commonService.getAccessToken();
        MenuUtil.deleteMenu(accessToken);
    }
}
