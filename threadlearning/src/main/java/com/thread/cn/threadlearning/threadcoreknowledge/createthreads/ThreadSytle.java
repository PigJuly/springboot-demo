package com.thread.cn.threadlearning.threadcoreknowledge.createthreads;

/**
 * @ProjectName: threadlearning
 * @Package: com.thread.cn.threadlearning.threadcoreknowledge.createthreads
 * @ClassName: ThreadSytle
 * @Author: July
 * @Description: 用Thread的方式创建线程
 * @Date: 2020/7/9 9:19
 * @Version: 1.0
 */
public class ThreadSytle extends Thread{

    public static void main(String[] args) {
        new ThreadSytle().start();
    }

    @Override
    public void run() {
        System.err.println("用Thread创建线程");
    }
}
