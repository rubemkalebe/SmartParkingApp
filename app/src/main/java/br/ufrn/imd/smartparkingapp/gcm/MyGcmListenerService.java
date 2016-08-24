package br.ufrn.imd.smartparkingapp.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import br.ufrn.imd.smartparkingapp.service.SpotService;

/**
 * Created by andre on 23/08/2016.
 */
public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        Intent intent = new Intent(this, SpotService.class);
        startService(intent);
    }
}
