package com.fan.test;

import com.fan.core.MyBatisUtils;
import com.fan.dao.CaseDao;
import com.fan.entity.Case;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @className: LoginTest
 * @author: ChenHao
 * @date: 2022/11/28
 **/
public class LoginTest implements com.fan.Icase.LoginTest {

    @Override
    public Boolean Login(String url, String logName, String password) {
        SqlSession session = MyBatisUtils.getSession(); //获取SqlSession连接
        CaseDao mapper = session.getMapper(CaseDao.class);
        List<Case> cases = mapper.CaseAll();//调方法
        for (com.fan.entity.Case c: cases){
            System.out.println(c);
        }
        return true;
    }

    @Override
    public Boolean Loginb(String url, String logName, String password) {
        return null;
    }
}
