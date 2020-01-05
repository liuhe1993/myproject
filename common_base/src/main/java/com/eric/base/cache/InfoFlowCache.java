package com.eric.base.cache;

import java.lang.reflect.Type;
import java.util.concurrent.ThreadPoolExecutor;

public class InfoFlowCache extends Cache<InfoFlow> {



    public InfoFlowCache(ThreadPoolExecutor executor, long validInterval, StoreManager manager) {
        super(executor, validInterval, manager);
    }

    @androidx.annotation.NonNull
    @Override
    public Type getDataType() {
        return InfoFlow.class;
    }

    @androidx.annotation.NonNull
    @Override
    public InfoFlow getData() {
        InfoFlow infoFlow= new InfoFlow();
        infoFlow.setId(1);
        infoFlow.setName("liuhe");
        return infoFlow;
    }
}
