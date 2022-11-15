package com.fan.known;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * TestNG基本注解(注释) 介绍
 *
 * */
public class TestNGBasicAnnotation {

    @Test(groups = "group1")
    public void test1(){
        System.out.println("test1 from group");
        Assert.assertTrue(true);
    }

    @Test(groups = "group1")
    public void test11(){
        System.out.println("test11 from group");
        Assert.assertTrue(true);
    }

    @Test(groups="group2")
    public void test2()
    {
        System.out.println("test2 from group2");
        Assert.assertTrue(true);
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @BeforeGroups("group1")
    public void beforeGroup(){
        System.out.println("beforeGroup");
    }

    @AfterGroups("group1")
    public void afterGroup(){
        System.out.println("afterGroup");
    }

    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("beforeSuite");
    }

    @AfterSuite
    public void afterSuite()
    {
        System.out.println("afterSuite");
    }

}
