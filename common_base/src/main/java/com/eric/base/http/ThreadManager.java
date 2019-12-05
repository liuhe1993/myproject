package com.eric.base.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
    //单例
    private static ThreadManager threadManager = new ThreadManager();
    public static ThreadManager getInstance() {
        return threadManager;
    }

    private LinkedBlockingQueue<Runnable> mQuene = new LinkedBlockingQueue<>();

    /**
     * 王队列中添加task
     */

    public void addTAsk(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mQuene.add(runnable);
    }

    // 需要一个线程池
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadManager() {
        threadPoolExecutor = new ThreadPoolExecutor(3, 5, 30* 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20));
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable runnable = null;
                try {
                    runnable = mQuene.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPoolExecutor.execute(runnable);
            }
        }
    };

}
