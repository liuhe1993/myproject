package com.eric.base.event;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public  class InitEventHandler implements EventHandler {
    //允许同时运行的线程数量
    @android.support.annotation.NonNull
    private Semaphore semaphore = new Semaphore(2);
    @Override
    public void register() {
        Handler handler = new Handler();
        ExecutorService service = Executors.newFixedThreadPool(3);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.HOURS, new SynchronousQueue<>());
        executor.getActiveCount();
    }

    @Override
    public void unregister() {

    }

    @Override
    public void handleEvent(Event event) {
        List<? extends Number> list  = new ArrayList<Number>();

        List<? extends Number> list2  = new ArrayList<Integer>();
        List<? super Integer> list3  = new ArrayList<Number>();
    }
}
