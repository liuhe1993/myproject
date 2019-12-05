package com.eric.application1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
