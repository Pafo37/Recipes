package com.example.pavelkovachev.recipes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPreferencesUtil() {
    }

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public void savePreference(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void clearAll() {
        editor.clear();
        editor.apply();
    }
}