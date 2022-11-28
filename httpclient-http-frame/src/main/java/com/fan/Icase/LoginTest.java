package com.fan.Icase;

import com.alibaba.fastjson.JSON;

/**
 * @className: LoginTest
 * @author: ChenHao
 * @date: 2022/11/28
 **/
public interface LoginTest {

    //登录接口规范，定义方法接口
    Boolean Login(String url,String logName,String password);

    Boolean Loginb(String url,String logName,String password);

}
