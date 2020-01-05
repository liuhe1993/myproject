package com.eric.base.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {
    private Context context;

    private SharedPreferences sp;

   public SPManager(Context context) {
       this.context = context;
   }

   public void  init() {
       if (context != null) {
           sp = context.getSharedPreferences("test", Context.MODE_PRIVATE);
       }

   }

   @androidx.annotation.Nullable
   public String getString(String key) {
          return sp.getString(key, "");
   }

   public void putStringAsync(String key, String value) {
       SharedPreferences.Editor editor = sp.edit();
       editor.putString(key, value);
       editor.apply();
       int i = 0;
   }

   public boolean deleteKey(String key) {
       SharedPreferences.Editor editor = sp.edit();
       editor.remove(key);
       return editor.commit();
   }
}
