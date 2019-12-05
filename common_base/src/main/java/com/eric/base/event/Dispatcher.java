package com.eric.base.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dispatcher {
    private volatile static Dispatcher mInstance;

    private Dispatcher() {}

    @android.support.annotation.NonNull
    private Map<Event, EventHandler> eventHandlerMap = new ConcurrentHashMap<>();

    public Dispatcher getInstance() {
        if (mInstance == null) {
            synchronized (Dispatcher.class) {
                if (mInstance == null) {
                    mInstance = new Dispatcher();
                }
            }
        }
        return mInstance;
    }

    public void register(@android.support.annotation.Nullable Event event, @android.support.annotation.Nullable EventHandler handler){
        if (eventHandlerMap != null && event != null && handler != null) {
            eventHandlerMap.put(event, handler);
        }
    }

    public void unregister(Event event) {
        if (eventHandlerMap != null) {
            eventHandlerMap.remove(event);
        }
    }

    public void sendEvent(Event event) {
        if (eventHandlerMap != null) {
            if (eventHandlerMap.containsKey(event)) {
                eventHandlerMap.get(event).handleEvent(event);
            }
        }
    }

}
