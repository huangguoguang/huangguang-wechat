package com.huangguang.wechat.constants;

/**
 * Created by huangguang on 2017/7/28.
 */
public class WeChatConstants {

    public static final String APPID = "wx6b421f5d68e983ac";
    public static final String SECRET = "dddb6fc8008e902957a6a10bc2a2f255";
    public static final String ACCESS_TOKEN_STR = "access_token";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String WEB_URL = "http://eedc470a.ngrok.io";

    public static final String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECTURL&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    //自定义菜单创建，查询，删除,事件推送
    public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";//post
    public static final String MENU_SELECT_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";//get
    public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";//get

}
