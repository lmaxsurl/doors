package ru.mail1998.logunov.maxim.doors.presentation.screens.map;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseActivity;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final double latitude = 54.884390;
    private final double longitude = 26.938822;
    private final float zoom = 15f;

    public static Intent getIntent(Activity activity){
        return new Intent(activity, MapsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker on Door shop and move the camera
        LatLng shop = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(shop).title("Двери и окна"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shop, zoom));
    }
}
