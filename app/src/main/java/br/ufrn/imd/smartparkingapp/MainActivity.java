package br.ufrn.imd.smartparkingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import java.util.List;

import br.ufrn.imd.smartparkingapp.model.Spot;
import br.ufrn.imd.smartparkingapp.service.SpotService;

/**
 * @author André, Rubem
 * @version 23/08/2016
 */
@EActivity(R.layout.main_activity)
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng localizacao = new LatLng(-5.832458, -35.205562);

    private List<Spot> spots;
    private GoogleMap googleMap;

    @FragmentById(R.id.map)
    MapFragment mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.traffic_jam_48);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver, new IntentFilter(SpotService.NOTIFICATION));
        Intent intent = new Intent(this, SpotService.class);
        startService(intent);

        // Exibe toast introdutorio
        CharSequence introMsg = getString(R.string.introMsg);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(getApplicationContext(), introMsg, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
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
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap = googleMap;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                spots = (List<Spot>) bundle.getSerializable(SpotService.SPOTS);
                int resultCode = bundle.getInt(SpotService.RESULT);
                if (resultCode == RESULT_OK) {
                    Log.i("REQ::SPOTS", "SUCESSO");
                    updateMap();
                }
            }
        }
    };

    private void updateMap() {
        if (this.googleMap != null) {
            for (Spot spot: spots) {
                BitmapDescriptor icon;
                if(spot.getReserved() && !spot.getBusy()) {
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                } else if(spot.getBusy()) {
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                } else {
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                }

                LatLng vaga = new LatLng(spot.getLatitude(), spot.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(vaga).title(spot.toString()).icon(icon));
            }
        } else {
            Log.i("UPDATE::MAP", "ERRO");
        }
    }

}
