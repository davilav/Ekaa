package com.pma.ekaa.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

public class PreferencesHelper {

    public static final String KEY_NATIONALITY = "key_nationality";
    public static final String KEY_DOCUMENTS = "key_documents";
    public static final String KEY_GENDERS = "key_genders";
    public static final String KEY_MARITAL = "key_marital";
    public static final String KEY_MIGRATORY = "key_migratory";
    public static final String KEY_RECIPIENT = "key_recipient";
    public static final String KEY_HOUSESHOLD = "key_household";
    public static final String KEY_DISABILITIES = "key_disabilities";
    public static final String KEY_PROGRAMS = "key_programs";
    public static final String KEY_GROUPS = "key_groups";
    public static final String KEY_PARTNER = "key_partner";

    /** Called to save supplied value in shared preferences against given key.
     * @param context Context of caller activity
     * @param key Key of value to save against
     * @param value Value to save
     */
    public static void setPreference(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * Called to retrieve required value from shared preferences, identified by given key.
     * Default value will be returned of no value found or error occurred.
     * @param context Context of caller activity
     * @param key Key to find value against
     * @param defaultValue Value to return if no data found against given key
     * @return Return the value found against given key, default if not found or any error occurs
     */
    public static String getPreference(Context context, String key, String defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            Log.println(Log.ERROR,"", String.valueOf(e));
            return defaultValue;
        }
    }
    /**
     *
     * @param context Context of caller activity
     * @param key Key to delete from SharedPreferences
     */
    public static void removePreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }

}
