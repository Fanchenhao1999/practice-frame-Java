package com.fan.test;

import com.fan.Icase.LoginMethod;
import org.testng.annotations.Test;

/**
 * @className: DemoTest
 * @author: ChenHao
 * @date: 2022/11/28
 **/
public class DemoTest {
    String yv = "http://service-control.j-net.online:82";
    @Test
    public void loginTest(){
        LoginMethod loginMethod = new LoginMethod();
        loginMethod.Login(yv+"/AuthService/login","{}","");
    }
}
