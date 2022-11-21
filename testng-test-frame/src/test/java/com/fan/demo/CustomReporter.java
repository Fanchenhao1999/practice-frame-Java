package com.fan.demo;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

/**
 * https://iowiki.com/testng/testng_custom_reporter.html
 * 创建自定义报告器类
 *它在控制台上为所述测试执行中包含的每个套件打印失败，传递和跳过测试的数量。
 *Reporter主要用于生成测试执行的最终报告。
 * xmlSuite ，它是正在执行的testng XML中提到的套件列表。
 * suites ，包含测试执行后的套件信息。 此对象包含有关包，类，测试方法及其测试执行结果的所有信息。
 * outputDirectory ，包含将生成报告的输出文件夹路径的信息。
 * */
public class CustomReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        //Iterating over each suite included in the test
        for (ISuite suite : suites) {
            //Following code gets the suite name
            String suiteName = suite.getName();
            //Getting the results for the said suite
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                System.out.println("Passed tests for suite '" + suiteName +
                        "' is:" + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed tests for suite '" + suiteName +
                        "' is:" + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped tests for suite '" + suiteName +
                        "' is:" + tc.getSkippedTests().getAllResults().size());
            }
        }
    }
}
