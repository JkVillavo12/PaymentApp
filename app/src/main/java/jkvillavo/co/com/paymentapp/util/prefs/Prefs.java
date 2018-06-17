package jkvillavo.co.com.paymentapp.util.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    /**
     * Save in preferences
     *
     * @param applicationContext application Context
     * @param key                key of preference
     * @param value              value to set
     */
    public static void save(Context applicationContext, String key, Object value) {

        SharedPreferences sharedPref = applicationContext.getSharedPreferences(
                PrefsKey.Process.PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (long) value);
        }

        editor.commit();

    }

    public static boolean getBooleanData(String key, Context mContext) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(PrefsKey.Process.PREFS, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);

    }

    public static int getIntData(String key, Context mContext) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(PrefsKey.Process.PREFS, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);

    }

    public static long getLongData(String key, Context mContext) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(PrefsKey.Process.PREFS, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, 0);

    }

    public static String getStringData(String key, Context mContext) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(PrefsKey.Process.PREFS, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");

    }

    public static void deletePreference(String key, Context mContext) {

        SharedPreferences preferences = mContext.getSharedPreferences(PrefsKey.Process.PREFS, Context.MODE_PRIVATE);
        preferences.edit().remove(key).commit();

    }
}
