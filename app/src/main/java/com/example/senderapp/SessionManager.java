package com.example.senderapp;

import android.content.Context;
import android.content.SharedPreferences;

public final class SessionManager {

    private static final String PREFS = "senderapp_prefs";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_USER_NAME = "user_name";

    private SessionManager() {
    }

    public static void login(Context context, String userName) {
        getPrefs(context).edit()
                .putBoolean(KEY_LOGGED_IN, true)
                .putString(KEY_USER_NAME, userName)
                .apply();
    }

    public static void logout(Context context) {
        getPrefs(context).edit()
                .putBoolean(KEY_LOGGED_IN, false)
                .remove(KEY_USER_NAME)
                .apply();
    }

    public static boolean isLoggedIn(Context context) {
        return getPrefs(context).getBoolean(KEY_LOGGED_IN, false);
    }

    public static String getUserName(Context context) {
        String name = getPrefs(context).getString(KEY_USER_NAME, null);
        return name != null ? name : "Senderista";
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}
