package com.eric.newsfeed.server;

import android.app.IntentService;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

public class ViewManager {
    String url;

      private void draw(SurfaceHolder holder, Paint paint) {
          InputStream viewInputStream;
          try {
              URL videourl = new URL(url);
              URLConnection connection = (HttpURLConnection)videourl.openConnection();
              connection.setDoInput(true);
              connection.connect();
              viewInputStream = connection.getInputStream();
              Bitmap bitmap = BitmapFactory.decodeStream(viewInputStream);
              Matrix matrix = new Matrix();
              matrix.postScale(3.65f,2.5f);
              Bitmap dstmap = Bitmap.createBitmap(bitmap,0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix, true);
              Canvas canvas = holder.lockCanvas();
              canvas.drawColor(Color.WHITE);
              canvas.drawBitmap(dstmap, 0, 0, paint);
              holder.unlockCanvasAndPost(canvas);
//              Thread.sleep();

          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }

      }
}
