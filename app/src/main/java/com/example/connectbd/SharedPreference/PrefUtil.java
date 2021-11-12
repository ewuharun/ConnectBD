package com.example.connectbd.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefUtil {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "prefUtil";

    public PrefUtil(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setValueWithKey(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }


    public String getValueWithKey(String key) {

        return pref.getString(key, "");
    }

    public void removeValue(String key) {

        editor.remove(key);
        editor.apply();
    }

    public void removeAllPreference() {
        pref.edit().clear().commit();
    }
}
