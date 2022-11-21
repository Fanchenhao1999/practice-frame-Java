package com.fan.known;

import org.testng.annotations.Test;

/**
 * TestNG并发测试
 *@Test注解上可通过配置threadPoolSize来实现并发，threadPoolSize和invocationCount是结合使用的
 *invocationCount调用次数
 * */
public class TestNGConcurrenttesting {

    //多线程测试,没有关联的用例可以使用多线程减少执行时间
    @Test(invocationCount = 5,threadPoolSize = 3)
    public void testConcurrent01(){
        System.out.println(1);
        System.out.printf("Thread id : %s%n",Thread.currentThread().getId());
    }


  
        @Test(groups = { "t8"})
        public void aThreadPool() {
            System.out.println("#ThreadA: " +Thread.currentThread().getId());
        }
        @Test(groups = { "t8"})
        public void bThreadPool() {
            System.out.println("#ThreadB: " +Thread.currentThread().getId());
        }
        @Test(groups = { "t8"})
        public void cThreadPool() {
            System.out.println("#ThreadC: " +Thread.currentThread().getId());
        }

}
