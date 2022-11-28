package com.fan.known;

import com.alibaba.fastjson.JSONObject;
import com.fan.utils.HttpClientUtils;
import com.fan.utils.HttpClientUtilsDemo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @className: KnownHttpClient
 * @author: ChenHao
 * @date: 2022/11/17
 **/
public class KnownHttpClientUtilsDemo {

    String jnturl = "http://pda.j-net.cn/";
    String jntfield = "http://service-control.j-net.online:82";
    String jntlogin = "http://service-control.j-net.online:82/AuthService/login";
    String token = "";

    // get 请求
    @Test
    public void getRequest() throws Exception{
            System.out.println(HttpClientUtilsDemo.doGet(jnturl,null));
    }

    //Post请求 不带token 表单提交传参 String方式
    @Test
    public void postRequestForm01(){
        System.out.println(HttpClientUtilsDemo.doPost(jntlogin,"account=a&password=123456",null));
    }
    //Post请求 不带token 表单提交传参 map方式
    @Test
    public void postRequestForm02(){
        //表单提交 Map数据 注意：Map<String,Object> Object代表值可以是任何对象类型;
        Map<String,Object> mapParameter = new HashMap<String,Object>();////数据采用的哈希表结构
        mapParameter.put("account","范晨浩");
        mapParameter.put("password","123456");
        System.out.println(mapParameter);

        //请求头
        Map<String,Object> mapHeader = new HashMap<String,Object>();
        mapHeader.put("language","zh-CN");
        System.out.println(mapHeader);
        System.out.println(HttpClientUtilsDemo.doPost(jntlogin,mapParameter,mapHeader));
        //接收返回值
        String response = HttpClientUtilsDemo.doPost(jntlogin,mapParameter,mapHeader);
        //将返回值转换为JSON 获取Token
        JSONObject jsonObject = JSONObject.parseObject(response);
        //获取返回值中的data 因为暂时不能一下取到token
        //String data =  jsonObject.toJSONString(jsonObject.get("data"));
        String data = jsonObject.getString("data");
        //再将data转为Json对象
        JSONObject jsonObjectData = JSONObject.parseObject(data);
        this.token = jsonObjectData.getString("token");
        System.out.println(this.token);
    }

    //Post请求 带token 表单提交传参 String方式
    @Test
    public void postRequestJson01(){
        String url = jntfield + "/ReturnOrderService/tms/return/query";
        String parameter = "{\"depot\":\"\",\"checkinDateMin\":null,\"checkinDateMax\":null,\"isRetrying\":null,\"retryDateMin\":null,\"retryDateMax\":null,\"isCheckout\":null,\"checkoutDateMin\":null,\"checkoutDateMax\":null,\"originalNumber\":[],\"originalReferNumber\":[],\"retryNumber\":[],\"retryReferNumber\":[],\"retryProvider\":null,\"status\":1,\"memo\":null,\"offset\":0,\"limit\":1000}";
        System.out.println(url);
        System.out.println(HttpClientUtilsDemo.doPostJson(url,parameter,"token=80a4f560191248ccacbf3c466a498cde"));
    }


    //Post请求 带token 表单提交传参 String方式
    @Test
    public void postRequestJson02(){
        String url = jntfield + "/ReturnOrderService/tms/return/query";
        String parameter = "{\"depot\":\"\",\"checkinDateMin\":null,\"checkinDateMax\":null,\"isRetrying\":null,\"retryDateMin\":null,\"retryDateMax\":null,\"isCheckout\":null,\"checkoutDateMin\":null,\"checkoutDateMax\":null,\"originalNumber\":[],\"originalReferNumber\":[],\"retryNumber\":[],\"retryReferNumber\":[],\"retryProvider\":null,\"status\":1,\"memo\":null,\"offset\":0,\"limit\":1000}";
        System.out.println(url);
        System.out.println(HttpClientUtilsDemo.doPostJson(url,parameter,"token=80a4f560191248ccacbf3c466a498cde"));
    }
}
