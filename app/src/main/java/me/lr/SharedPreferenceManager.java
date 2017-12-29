package me.lr;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 12/18/2017.
 */

public class SharedPreferenceManager {

    private static final String SHARED_PREFERENCE_FILE_NAME = "FCMShared";
    private static final String PREF_TAG_NAME = "PrefToken";

    private static SharedPreferenceManager mInstance;
    private static Context mCtx;

    private SharedPreferenceManager(Context context){
        mCtx = context;
    }

    public static synchronized SharedPreferenceManager getmInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }

    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_TAG_NAME, token);
        editor.apply();
        return true;
    }

    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREF_TAG_NAME, null);
    }
}
