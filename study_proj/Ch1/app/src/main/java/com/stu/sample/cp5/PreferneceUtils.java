package com.stu.sample.cp5;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Script;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

public class PreferneceUtils {

    /**
     * Gson 으로 변경도 가능
     *
     * @param context
     * @param key
     * @param defaultString
     * @param <T>
     * @return
     */
    static <T>T getPrefSerialize(Context context,String key, String defaultString) {
        try {

            SharedPreferences preferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
            String data = preferences.getString(key, defaultString);

            InputStream stream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
            BufferedInputStream bis = new BufferedInputStream(stream);
            ObjectInputStream in = new ObjectInputStream(bis);


            return (T) in.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    static void setPrefSerialize(Context context, String key, String data) {
        SharedPreferences preferences = context.getSharedPreferences("MODE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, data);
        editor.commit();
    }



    static String getPrefString(Context context,String key, String defaultString) {
        SharedPreferences preferences = context.getSharedPreferences("MODE" , Context.MODE_PRIVATE);
        return preferences.getString(key, defaultString);
    }


    static void setPrefStringInstant(Context context, String key, String data) {
        SharedPreferences preferences = context.getSharedPreferences("MODE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, data);
        editor.commit();
    }

    static void setPrefStringDelay(Context context, String key, String data) {
        SharedPreferences preferences = context.getSharedPreferences("MODE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, data);
        editor.apply();
    }
}
