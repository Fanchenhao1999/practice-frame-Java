package com.fan.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 自定义日志记录
 * 创建测试用例类(Create Test Case Class)
 * 1、该方法用于测试中的真值条件断言。Assert.assertTrue(true)；
 *
 * */
public class SampleTest {
    @Test
    public void testMethodOne(){
        Assert.assertTrue(true);
    }

    @Test
    public void testMethodTwo() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods={"testMethodTwo"})
    public void testMethodThree() {
        Assert.assertTrue(true);
    }
}
