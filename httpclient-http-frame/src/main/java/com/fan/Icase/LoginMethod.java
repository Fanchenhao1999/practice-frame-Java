package com.fan.Icase;

import com.fan.utils.HttpClientUtils;

/**
 * @className: LoginMethod
 * @author: ChenHao
 * @date: 2022/11/28
 **/
public class LoginMethod implements  LoginTest {
    @Override
    public Boolean Login(String url, String logName, String password) {
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        try {
            String gg = httpClientUtils.getToken_login(url, logName);
            System.out.println(gg);
            if (gg!=null){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    @Override
    public Boolean Loginb(String url, String logName, String password) {
        return null;
    }

}
