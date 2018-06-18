package jkvillavo.co.com.paymentapp.util.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase que controla la gestion de las preferencias en la app
 */
public class Prefs {

    /**
     * Save in preferences
     *
     * @param applicationContext
     *         application Context
     * @param key
     *         key of preference
     * @param value
     *         value to set
     */
    public static void save ( Context applicationContext, String key, Object value ) {

        SharedPreferences sharedPref = applicationContext.getSharedPreferences(
                PrefsKey.Process.PREFS, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();

        if ( value instanceof Boolean ) {
            editor.putBoolean( key, ( boolean ) value );
        } else if ( value instanceof Integer ) {
            editor.putInt( key, ( int ) value );
        } else if ( value instanceof String ) {
            editor.putString( key, ( String ) value );
        } else if ( value instanceof Float ) {
            editor.putFloat( key, ( float ) value );
        } else if ( value instanceof Long ) {
            editor.putLong( key, ( long ) value );
        }

        editor.commit();

    }

    /**
     * Obtiene un valor boolean de las preferencias
     *
     * @param key
     *         key a buscar
     * @param mContext
     *         contexto de la app
     * @return valor booleano solicitado
     */
    public static boolean getBooleanData ( String key, Context mContext ) {

        SharedPreferences sharedPref = mContext.getSharedPreferences( PrefsKey.Process.PREFS, Context.MODE_PRIVATE );
        return sharedPref.getBoolean( key, false );

    }

    /**
     * Obtiene un valor entero de las preferencias
     *
     * @param key
     *         key a buscar
     * @param mContext
     *         contexto de la app
     * @return valor entero
     */
    public static int getIntData ( String key, Context mContext ) {

        SharedPreferences sharedPref = mContext.getSharedPreferences( PrefsKey.Process.PREFS, Context.MODE_PRIVATE );
        return sharedPref.getInt( key, 0 );

    }

    /**
     * Obtiene un valor tipo long de las preferencias
     *
     * @param key
     *         key a buscar
     * @param mContext
     *         contexto
     * @return valor long solicitado
     */
    public static long getLongData ( String key, Context mContext ) {

        SharedPreferences sharedPref = mContext.getSharedPreferences( PrefsKey.Process.PREFS, Context.MODE_PRIVATE );
        return sharedPref.getLong( key, 0 );

    }

    /**
     * Obtiene un valor tipo String de las preferencias
     *
     * @param key
     *         key a buscar
     * @param mContext
     *         contexto
     * @return valor String solicitado
     */
    public static String getStringData ( String key, Context mContext ) {

        SharedPreferences sharedPref = mContext.getSharedPreferences( PrefsKey.Process.PREFS, Context.MODE_PRIVATE );
        return sharedPref.getString( key, "" );

    }

    /**
     * Elimina una preferencia
     *
     * @param key
     *         key a eliminar
     * @param mContext
     *         contexto
     */
    public static void deletePreference ( String key, Context mContext ) {

        SharedPreferences preferences = mContext.getSharedPreferences( PrefsKey.Process.PREFS, Context.MODE_PRIVATE );
        preferences.edit().remove( key ).commit();

    }
}
