package com.fan.demo;

import com.fan.Utils.HttpClientUtils;
import org.testng.annotations.Test;

/**
 * @className: HttpClientUtilsDemo01
 * @author: ChenHao
 * @date: 2022/11/21
 **/
public class HttpClientUtilsDemo01 {
    HttpClientUtils httpClientUtils = new HttpClientUtils();

    /**
     * 发送HttpGet请求,不带请求头和请求参数
     * @param
     * @return
     */
    @Test
    public void doGet() throws Exception{
        String url = "http://jnet.miht.ml:6556/AuthService/login";


        System.out.println("1、获取");

        System.out.println("2、");


    }

}
