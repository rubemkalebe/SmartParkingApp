package br.ufrn.imd.smartparkingapp.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.smartparkingapp.R;
import br.ufrn.imd.smartparkingapp.model.Spot;
import br.ufrn.imd.smartparkingapp.utils.JSONProcessor;

/**
 * Created by andre on 13/04/2016.
 * Updated by Rubem on 08/18/2016.
 */
public class SpotService extends IntentService {

    public static final String NOTIFICATION = "br.ufrn.imd.smartparkingapp.service.SpotService";
    public static final String SPOTS = "spots";
    public static final String RESULT = "result";

    private static final String SPOT_PATH = "/spot";
    private static final String TAG_APP = "SMARTPARKING";

    private int result = Activity.RESULT_CANCELED;
    private List<Spot> spots = null;

    public SpotService() {
        super(TAG_APP);
        this.spots = new ArrayList<Spot>();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            Log.d("SERVICE::", "SPOTSSERVICE");
            requestGetSpots();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.d("THREAD::ERROR::SPOTS", e.toString());
            }
        }
    }

    private void requestGetSpots() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.base_url) + SPOT_PATH;
        Log.d("GET::SPOTS::SERVICE", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("REQUEST::SERVICE", response);
                        try {
                            spots = JSONProcessor.toList(response, Spot.class);
                            result = Activity.RESULT_OK;
                            publishResults(result);
                        } catch (JSONException e) {
                            Log.d("REQ::ERROR::SPOTS", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("REQ::ERROR::SPOTS", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private void publishResults(int result) {
        Intent intent = new Intent(NOTIFICATION);
        Bundle b = new Bundle();

        b.putSerializable(SPOTS, (Serializable) this.spots);

        intent.putExtras(b);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
