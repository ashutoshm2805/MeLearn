package me.lr;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class FMService extends FirebaseMessagingService {

    private static final String TAG = "FMService";
    public FMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /*super.onMessageReceived(remoteMessage);*/
        Log.e(TAG, remoteMessage.getNotification().getBody());
        try {
            JSONObject jsonObject = new JSONObject(remoteMessage.getData().toString());
            sendPUSHNotification(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendPUSHNotification(JSONObject jsonObject){
        Log.e(TAG, jsonObject.toString());
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            String title = data.getString("title");
            String message = data.getString("message");
            String image = data.getString("image");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            FCMNotificationManager fcmNotificationManager = new FCMNotificationManager(getApplicationContext());
            fcmNotificationManager.showBigNotification(title, message, image, intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
