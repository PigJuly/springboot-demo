package com.thread.cn.threadlearning.threadcoreknowledge.createthreads;

/**
 * @ProjectName: threadlearning
 * @Package: com.thread.cn.threadlearning.threadcoreknowledge.createthreads
 * @ClassName: RunnableStyle
 * @Author: July
 * @Description: 用Runnable方式创建线程
 * @Date: 2020/7/9 9:17
 * @Version: 1.0
 */
public class RunnableStyle implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.run();
    }

    @Override
    public void run() {
        System.err.println("用Runnable创建线程");
    }
}
