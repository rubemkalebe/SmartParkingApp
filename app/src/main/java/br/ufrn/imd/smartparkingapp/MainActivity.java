package br.ufrn.imd.smartparkingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import java.util.List;

import br.ufrn.imd.smartparkingapp.model.Sensor;
import br.ufrn.imd.smartparkingapp.service.SensorService;

/**
 * Created by andre on 25/03/2016.
 */
@EActivity(R.layout.main_activity)
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng localizacao = new LatLng(-5.832407, -35.205447);

    private List<Sensor> sensores;
    private GoogleMap googleMap;

    @FragmentById(R.id.map)
    MapFragment mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver, new IntentFilter(SensorService.NOTIFICATION));
        Intent intent = new Intent(this, SensorService.class);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @AfterViews
    public void configureMap() {
        mapa.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacao, 20));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.8f), 2000, null);
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        this.googleMap = googleMap;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                sensores = (List<Sensor>) bundle.getSerializable(SensorService.SENSORES);
                int resultCode = bundle.getInt(SensorService.RESULT);
                if (resultCode == RESULT_OK) {
                    Log.i("REQ::SENSORES", "SUCESSO");
                    updateMap();
                }
            }
        }
    };

    private void updateMap() {
        if (this.googleMap != null) {
            for (Sensor sensor : sensores) {
                BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(sensor.getBusy() ? BitmapDescriptorFactory.HUE_RED : BitmapDescriptorFactory.HUE_AZURE);
                LatLng vaga = new LatLng(sensor.getLatitude(), sensor.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(vaga).title("VAGA " + sensor.getId()).icon(icon));
            }
        } else {
            Log.i("UPDATE::MAP", "ERRO");
        }
    }


}
