package com.fan.testcase;

import com.alibaba.fastjson.JSONObject;
import com.fan.request.RequestFieldUrl;
import com.fan.request.RequestInterfaceUrl;
import com.fan.utils.HttpClientUtilsDemo;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: GetLoginToken
 * @author: ChenHao
 * @date: 2022/11/28
 **/
public class GetLoginToken {

    @Test
    public String tmsOrderReturnLogin() {
        System.out.println("1、获取登录接口url");
        String url = new RequestFieldUrl().getFieldUrl() + new RequestInterfaceUrl().getLogin_url();
        System.out.println("登录接口："+url);
        System.out.println("2、执行HttpClient请求登录接口");
        //准备表单格式的 请求参数、请求头
        Map<String,Object> heard = new HashMap<String,Object>();
        heard.put("language","zh-CN");

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("account","范晨浩");
        map1.put("password","123456");
        //传入错误的账户密码
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("account","范晨浩Test");
        map2.put("password","123456.0.");
        //传入空账户密码
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("account","");
        map3.put("password","");

       String login_response1 = HttpClientUtilsDemo.doPost(url,map1,heard);
       String login_response2 = HttpClientUtilsDemo.doPost(url,map2,heard);
       String login_response3 = HttpClientUtilsDemo.doPost(url,map3,heard);
       //校验请求成功和请求失败
        System.out.println("3、校验请求成功和请求失败");
        System.out.println(map1);
        System.out.println(heard);
        System.out.println(login_response1);
        System.out.println("4、转换JSON对象获取token反回出来");
        //将返回值转换为JSON 获取Token
        JSONObject jsonObject = JSONObject.parseObject(login_response1);
        //返回token的值
        String data_message = jsonObject.getString("data");
        JSONObject jsonObject1 = JSONObject.parseObject(data_message);
        String token_message = jsonObject1.getString("token");
        return token_message;
    }

}
