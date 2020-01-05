package com.eric.newsfeed.ui.view;

import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.FrameLayout;
import android.view.View;

public abstract class InfoFlowBaseView extends FrameLayout implements View.OnClickListener {

    public InfoFlowBaseView(@NonNull Context context) {
        super(context);
    }

    @Override
    public abstract void onClick(View v);

    @androidx.annotation.NonNull
    public String removeVowels(@androidx.annotation.NonNull String S) {
        char[] charS = S.toCharArray();
        StringBuilder out = new StringBuilder();
        for (char aChar : charS) {
            if (aChar != 'a' && aChar != 'e' && aChar != 'i' && aChar != 'o' && aChar != 'u') {
                out.append(aChar);
            }
        }
        return out.toString();

    }
}
