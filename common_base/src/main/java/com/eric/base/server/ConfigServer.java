package com.eric.base.server;

import android.content.Context;

import com.eric.base.utils.ContextUtils;
import com.eric.base.utils.FanTest;

import java.util.Comparator;

public class ConfigServer extends BaseConfigServer {
    private static final String IP = "";

    public static Response<NewsFeedResponse> queryInfoFlow(Context context, String method) {
        // TODO: 2019/4/5 build head
        String url = "";
        Response<NewsFeedResponse> newsFeedResponseResponse = request(context, url, NewsFeedResponse.class,20000);
        return newsFeedResponseResponse;

    }

    @android.support.annotation.NonNull
    Comparator<String> com2 = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    };
    @android.support.annotation.NonNull
    Comparator<String> com = (first, second)->first.length() - second.length();
    int i = com.compare("ss", "fgf");
    String id = ContextUtils.chooseOne("id","id2", "id3");
    @android.support.annotation.NonNull
    FanTest<String> fan = new FanTest<>();
    String te = fan.getId();
}
