package com.eric.newsfeed.observer;

import java.util.ArrayList;

public class DownlodManager {

    private ArrayList<DownloadObserver<String>> observers;

    private static DownlodManager manager;

    public static DownlodManager getInstance() {
        if (manager == null) {
            manager = new DownlodManager();
        }
        return manager;
    }

    private DownlodManager() {
        observers = new ArrayList<>();
    }

    public void  registerObserver(DownloadObserver<String> observer) {
        observers.add(observer);

    }
    public void removeObserver(DownloadObserver observer){
        int index = observers.indexOf(observer);
        if (index > 0) {
            observers.remove(index);
        }
    }
    private void notifyObdervers(String state) {
        for(DownloadObserver<String> observer : observers) {
            observer.notifyUpdateState(state);
        }
    }

    public void notifyState(String state) {
        notifyObdervers(state);
    }
}
