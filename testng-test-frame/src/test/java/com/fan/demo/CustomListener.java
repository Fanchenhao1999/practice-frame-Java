package com.fan.demo;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import static org.testng.Reporter.log;

/**
 * https://iowiki.com/testng/testng_custom_logger.html
 *自定义日志记录类
 *创建自定义日志记录类 扩展了TestListenerAdapter接口
 * 我们创建了一个自定义记录器类，它实现了ITestListener接口，
 * 并将自己作为监听器附加到TestNG测试套件。 TestNG在测试开始时，测试失败时，
 * 测试成功时调用此侦听器类的方法，依此类推。
 * */
public class CustomListener extends TestListenerAdapter {

    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName()+ "--Test method failed\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ "--Test method skipped\n");
    }
    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ "--Test method success\n");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }
}
