package com.huangguang.wechat.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangguang on 2017/7/27.
 */
public class WeChatUtil {
    private static final String APPID = "wx6b421f5d68e983ac";
    private static final String SECRET = "dddb6fc8008e902957a6a10bc2a2f255";
    private static final String TOKENURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private static final String REFRESH_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    private static final String OAUTH_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    private static final String LOGINURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    private static final String WEB_URL = "http://d5d7a48e.ngrok.io";
    public static JSONObject getWeChatUserInfo(String code) {
        //1、通过code换取授权access_token
        JSONObject accessToken = getAccessToken(code);
        //2、如果需要，刷新access_token
        JSONObject refreshAccessToken = refreshAccessToken(accessToken.get("refresh_token").toString());
        //3、拉取用户信息(需scope为 snsapi_userinfo)
        //如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
        JSONObject userInfo = getUserInfo(refreshAccessToken);
        return userInfo;
    }

    private static JSONObject getUserInfo(JSONObject refreshAccessToken) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", refreshAccessToken.get("access_token").toString());
        paramMap.put("openid", refreshAccessToken.get("openid").toString());
        paramMap.put("lang", "zh_CN");
        String result = HttpClientUtil.httpGet(url, paramMap);
        System.out.println(result);
        JSONObject json = JSONObject.parseObject(result);
        return json;
        //{"openid":"ob5DywlPB9KLE6Dgwmz3L3WycEOQ","nickname":"天嘉广告&印刷（黄光）","sex":1,"language":"zh_CN","city":"武汉","province":"湖北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/AeL1agRcBba3UhAsvPTpQGbqib0gstqP1oJROKiaPbj8w6Lp4YgickPdXjQsb3rLF9qsN25YfGh5aNvFDm9mBNKrFn0M7w63Ade\/0","privilege":[]}
    }

    private static JSONObject refreshAccessToken(String refresh_token) {
        //由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
        // refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", APPID);
        paramMap.put("grant_type","refresh_token");
        paramMap.put("refresh_token", refresh_token);
        String result = HttpClientUtil.httpGet(url, paramMap);
        System.out.println(result);
        JSONObject json = JSONObject.parseObject(result);
        return json;
        //{"openid":"ob5DywlPB9KLE6Dgwmz3L3WycEOQ","access_token":"kAREsNy7PndDnWzi5aXp-7Y9nmnu-5gMXwketUqcB3w8cFVQShSZ7kur58yDAf7PJ2Pw8CjOLzD90bBgyvQdlAnJo65TW8trzRH1jds_8E8","expires_in":7200,"refresh_token":"8Pw2MyXcXCsI9QGhxrQ0O4FBiZX2P6plefpL7M-p0wkk5TQPeN8xnhRipvWey1O2ktLJYDZ6WM6dTayOtcdCIAgSKUbhtcTiarCCz7ZXBu0","scope":"snsapi_base,snsapi_userinfo,"}

    }

    private static JSONObject getAccessToken(String code) {
        //通过code换取网页授权access_token
        //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
        //code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
        String accTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", APPID);
        paramMap.put("secret",SECRET);
        paramMap.put("code",code);
        paramMap.put("grant_type","authorization_code");

        String result = HttpClientUtil.httpGet(accTokenUrl, paramMap);
        System.out.println(result);
        JSONObject json = JSONObject.parseObject(result);
        return json;

        //{"access_token":"pYItcHOAlJDsh5fBnPNgzbfJaafagejgSpVw9uSu44cQ-sGYGlLf_dayEX1Qqzl2eSPjnVLHpdw3n48bTO84y7TNdwbJctWz688ex-BFqA0","expires_in":7200,"refresh_token":"WEim7K-VdlzU_DxMH--ATQcpQ9Rrlxw04rFx4GOFq223g81okEGUsFm9p4eF6qv6uAa8cvoTukDkyMuD7N3hyMs_RBh-6XXKhOsfqfW9HXQ","openid":"ob5DywlPB9KLE6Dgwmz3L3WycEOQ","scope":"snsapi_userinfo"}
    }


    public static String getWeChatLoginUrl(HttpServletRequest request) {
        String redirectUrl = request.getServletPath();
        System.out.println(redirectUrl);
        redirectUrl = WEB_URL + redirectUrl;
        /**
         比如客户端发送http://localhost/test.do?a=b&c=d&e=f
         通过request.getQueryString()得到的是a=b&c=d&e=f
         意思是：获取带参数查询。post方法传的参数，getQueryString（）得不到，
         它只对get方法得到的数据有效。
         */
        if (request.getQueryString() != null) {
            redirectUrl += "?" + request.getQueryString();
        }
        System.out.println(redirectUrl);
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return LOGINURL.replace("APPID", APPID).replace("REDIRECT_URL", redirectUrl);
    }
}
