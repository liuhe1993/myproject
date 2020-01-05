package com.eric.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.eric.base.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义view（颜色渐变字体）
 */
public class ColorChangeTextView extends View {
    private static final String TAG = "ColorChangeTextView";

    private String mText = "";

    private int mTextSize = 30;
    private int mTextColor = Color.BLACK;
    private int mTextColorChange = Color.RED;
    private float mProgress;

    @Directions
    private int mDirection;

    private Paint mPaint;
    private Paint mLinePaint;

    private Rect mTextBound = new Rect();
    private int mTextWidth;
    private int mTextHeight;

    private int mTextStartX;
    private int mTextStartY;

    /**
     * 代替枚举
     */
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_TOP = 2;
    public static final int DIRECTION_BOTTOM = 3;

    @IntDef(flag = true, value = {DIRECTION_LEFT, DIRECTION_RIGHT, DIRECTION_TOP, DIRECTION_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Directions {
    }

    public ColorChangeTextView(Context context) {
        super(context);
    }

    public ColorChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(3);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.GREEN);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorChangeTextView);
        mText = ta.getString(R.styleable.ColorChangeTextView_text);
        mTextSize = ta.getDimensionPixelSize(R.styleable.ColorChangeTextView_text_size, mTextSize);
        mTextColor = ta.getColor(R.styleable.ColorChangeTextView_text_color, mTextColor);
        mTextColorChange = ta.getColor(R.styleable.ColorChangeTextView_text_color_change, mTextColorChange);
        mProgress = ta.getFloat(R.styleable.ColorChangeTextView_progress, mProgress);
        mDirection = ta.getInt(R.styleable.ColorChangeTextView_direction, 0);
        ta.recycle();

        mPaint.setTextSize(mTextSize);


    }

    public void  setDirection(int direction) {
        mDirection = direction;
    }

    public void setProgress(float progress) {
        mProgress = progress;
        //刷新view
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //应该如何合理的测量view的尺寸？
        //1，测量文字大小
        //把文字长宽信息存在mTextBound中
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

        mTextWidth = Math.round(mPaint.measureText(mText));
        Log.i(TAG, "mTextWidth: " + mTextWidth);

        //字体属性测量数据  https://www.cnblogs.com/baiqiantao/p/59069dfa57617252fca5a1d4b5d447ce.html
        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
        mPaint.getFontMetrics(fontMetrics);

        //一种获取高度的办法
        mTextHeight = Math.round(fontMetrics.descent - fontMetrics.ascent);
        Log.i(TAG, "mTextHeight: " + mTextHeight);

        //2.决定view大小
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        //3. set
        setMeasuredDimension(width, height);

        mTextStartX = width / 2 - mTextWidth / 2;
        mTextStartY = height / 2 - mTextHeight / 2;


    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.UNSPECIFIED:
                result = mTextWidth + getPaddingLeft() + getPaddingRight();
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(mTextWidth + getPaddingLeft() + getPaddingRight(), size);
                break;
        }

        return result;

    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.UNSPECIFIED:
                result = mTextHeight + getPaddingTop() + getPaddingBottom();
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(mTextHeight + getPaddingTop() + getPaddingBottom(), size);
                break;
        }

        return result;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mDirection) {
            case DIRECTION_LEFT:
                Log.i(TAG, "ondraw left");
                //1.先绘制
                mPaint.setColor(mTextColor);
                canvas.drawText(mText, mTextStartX, getMeasuredHeight()/2 - (mPaint.descent()/2 + mPaint.ascent()/2), mPaint);
                mPaint.setColor(mTextColorChange);
                int startX = 0;
                startX = mTextStartX;
                int finalX = 0;
                finalX = mTextStartX + (int)(mProgress * mTextWidth);
                Log.i(TAG, "mProgress: " + mProgress);
                canvas.save();
                //裁剪画布，只绘制给定区域
                canvas.clipRect(startX, 0, finalX, getMeasuredHeight());
//                canvas.drawRect(canvas.getClipBounds(), mLinePaint);

                canvas.drawText(mText, mTextStartX, getMeasuredHeight()/2 - (mPaint.descent()/2 + mPaint.ascent()/2), mPaint);
                canvas.restore();

                break;
            case DIRECTION_RIGHT:
                break;
            case DIRECTION_TOP:
                break;
            case DIRECTION_BOTTOM:
                break;
        }
    }
}
