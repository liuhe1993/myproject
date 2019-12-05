package com.eric.application1.activity;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.eric.application1.R;
import com.eric.base.view.ColorChangeTextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity implements SurfaceHolder.Callback{

    private static final String TAG = "MainActivity";
    private SurfaceView surfaceView;
    private SurfaceHolder holder;

//    @BindView(R.id.change_text_view)
//    protected ColorChangeTextView mView;

//    @BindView(R.id.text_change_btn)
//    protected Button mButton;

//    @OnClick(R.id.text_change_btn)
//    void change() {
//        Log.i(TAG, "onlick text change");
////        mView.setDirection(ColorChangeTextView.DIRECTION_LEFT);
////        //只能主动去调view对应属性的set函数，需要在set函数里面去刷新
////        ObjectAnimator.ofFloat(mView, "progress", 0,1).setDuration(2500).start();
//    }

   private static Handler handler = new MyHandler();

    static class MyHandler extends Handler {
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
       }
   }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, @android.support.annotation.NonNull Throwable e) {
                Log.i("Main", e.getMessage());
            }
        });

        try {
            Class<?> clazz = Class.forName("com.eric.plugin.Test");
            Method printMethod = clazz.getMethod("print");
            printMethod.invoke(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

//    private void getContent() {
//        Uri uri = Uri.parse("");    //ContentProvider 中注册的 URI
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("_id", "");
//        contentValues.put("name", "");
//        contentValues.put("description", "beautiful girl");
//        ContentResolver contentResolver = getContentResolver();    //获取内容处理器
//        contentResolver.insert(uri, contentValues);    //插入一条数据
//
//        //再查询一次
//        Cursor cursor = contentResolver.query(uri, new String[]{"name", "description"}, null, null, null, null);
//        if (cursor == null) {
//            return;
//        }
//
//    }
}
