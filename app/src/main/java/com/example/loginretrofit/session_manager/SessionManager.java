package com.example.loginretrofit.session_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences shpref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "SessionManager";
    private static final String KEY_IS_LOGED_IN = "isLoggedIn";

    public SessionManager(Context context) {

        this.context = context;
        shpref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = shpref.edit();

    }

    public void setLogin(boolean isLoggedin) {
        editor.putBoolean(KEY_IS_LOGED_IN,isLoggedin);
        editor.apply();
        Log.e(TAG, "userlogin session");
    }

    public boolean isLoggedin() {
        return shpref.getBoolean(KEY_IS_LOGED_IN, false);
    }

    public void clear(){
        editor.clear();
        editor.apply();
    }


}
