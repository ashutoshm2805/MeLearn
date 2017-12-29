package me.lr;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Administrator on 12/18/2017.
 */

public class FirebaseI2DService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseI2DService";
    @Override
    public void onTokenRefresh() {
        /*super.onTokenRefresh();*/
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, refreshToken);
        storeToken(refreshToken);
    }
    private void storeToken(String token){
        SharedPreferenceManager.getmInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
