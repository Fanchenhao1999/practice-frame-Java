package com.fan.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 自定义报道(Custom Reporter)
 * 创建测试用例类 (Create Test Case Class)
 * 要编写自定义报告者类，我们的扩展类应该实现IReporter接口。
 * */
public class SampleTest2 {
    @Test
    public void testMethodOne() {
        Assert.assertTrue(true);
    }
    @Test
    public void testMethodTwo() {
        Assert.assertTrue(false);
    }
    //dependsOnMethods 依赖注释
    @Test(dependsOnMethods = {"testMethodTwo"})
    public void testMethodThree() {
        Assert.assertTrue(true);
    }
}
