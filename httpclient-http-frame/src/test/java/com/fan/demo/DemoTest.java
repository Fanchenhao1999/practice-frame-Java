package com.fan.demo;

import com.fan.Icase.LoginMethod;
import com.fan.core.MyBatisUtils;
import com.fan.dao.CaseDao;
import com.fan.test.LoginTest;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @className: DemoTest
 * @author: ChenHao
 * @date: 2022/11/25
 **/
public class DemoTest {

    String yv = "http://service-control.j-net.online:82";
    String yvv = "http://service-control.j-net.online:82/AuthService/login";
   @Test
    public void getListCase(){

       SqlSession session = MyBatisUtils.getSession(); //获取SqlSession连接
       CaseDao mapper = session.getMapper(CaseDao.class);
       List<com.fan.entity.Case> cases = mapper.CaseAll();//调方法
       for (com.fan.entity.Case c: cases){
           System.out.println(c);
       }
    }

    @Test
    public void loginTest(){
        LoginMethod loginMethod = new LoginMethod();
        loginMethod.Login(yvv,"张三","123456");
    }

    @Test
    public void test01(){
       CloseableHttpClientDemo01 ccc = new CloseableHttpClientDemo01();
       ccc.postForm();
    }
}
