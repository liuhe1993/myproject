package com.eric.base.event;

public interface EventHandler {

    void register();

    void unregister();

    void handleEvent(Event event);
}
