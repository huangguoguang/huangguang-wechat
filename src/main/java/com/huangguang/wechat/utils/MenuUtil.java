package com.huangguang.wechat.utils;

import com.alibaba.fastjson.JSONObject;
import com.huangguang.wechat.constants.WeChatConstants;
import com.huangguang.wechat.entity.Button;
import com.huangguang.wechat.entity.ClickButton;
import com.huangguang.wechat.entity.Menu;
import com.huangguang.wechat.entity.ViewButton;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * 菜单工具类
 * Created by huangguang on 2017/7/28.
 */
public class MenuUtil {
    public static Menu initMenu() throws Exception {

        Menu menu = new Menu();
        ClickButton clickButton = new ClickButton();
        clickButton.setKey("V1001_TODAY_MUSIC");
        clickButton.setType("click");
        clickButton.setName("今日歌曲");

        ViewButton viewButton = new ViewButton();
        viewButton.setType("view");
        viewButton.setName("友情链接");
        viewButton.setUrl("http://www.qq.com");

        ViewButton viewButton2 = new ViewButton();
        viewButton2.setType("view");
        viewButton2.setName("登录");
        String url = WeChatConstants.AUTH_URL.replace("APPID", WeChatConstants.APPID).replace("REDIRECTURL", URLEncoder.encode(WeChatConstants.WEB_URL + "/wx/login", "utf-8"));
        viewButton2.setUrl(url);

        Button button = new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{viewButton, viewButton2});

        menu.setButton(new Button[]{clickButton, button});
        return menu;
    }

    /**
     * 自定义菜单创建
     */
    public static void createMenu(String accessToken) throws Exception{
        String url = WeChatConstants.MENU_CREATE_URL.replace(WeChatConstants.ACCESS_TOKEN, accessToken);
        String result = HttpClientUtil.jsonPost(url, JSONObject.toJSONString(initMenu()), "utf-8");
        System.out.println(result);
    }

    public static void selectMenu(String accessToken) throws Exception {
        String url = WeChatConstants.MENU_SELECT_URL.replace(WeChatConstants.ACCESS_TOKEN, accessToken);
        System.out.println(url);
        String result = HttpClientUtil.httpGet(url, new HashMap<String, String>(), "utf-8");
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        String accessToken = WeChatUtil.getAccessToken().getString("access_token");
        createMenu(accessToken);
        //selectMenu(accessToken);
    }
}
