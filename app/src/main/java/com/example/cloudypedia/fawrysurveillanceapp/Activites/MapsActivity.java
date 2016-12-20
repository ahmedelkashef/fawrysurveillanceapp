package com.example.cloudypedia.fawrysurveillanceapp.Activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.example.cloudypedia.fawrysurveillanceapp.Classes.GPSHandller;
import com.example.cloudypedia.fawrysurveillanceapp.Classes.Merchant;
import com.example.cloudypedia.fawrysurveillanceapp.DataFetcher.FetchLocationTask;
import com.example.cloudypedia.fawrysurveillanceapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double longitude, latitude;
    ArrayList<MarkerOptions> markers;
   private ArrayList<Merchant>  merchants;

    GPSHandller gpsHandller;
    protected String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE};
    private static final int REQUEST_CODE_PERMISSION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        markers = new ArrayList<MarkerOptions>();
        Bundle extras = getIntent().getExtras();
        markers = extras.getParcelableArrayList("markers");

        merchants = extras.getParcelableArrayList("merchants");



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSION);
        }

        mMap.setMyLocationEnabled(true);

        gpsHandller = new GPSHandller(this);
        Location location = gpsHandller.getLocation();


        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
        final Marker currentMarker = mMap.addMarker(new MarkerOptions().position(currentLocation).title("Mylocation").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 5));


        for (int i = 0 ; i<markers.size() ; i++)
        {
            Marker marker =  mMap.addMarker(markers.get(i));
            marker.setTag(merchants.get(i));

        }


       mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
           @Override

           public boolean onMarkerClick(Marker marker) {
               if(!(marker.equals(currentMarker))) {
                   float []result = new float[1];
                   Location.distanceBetween(currentMarker.getPosition().latitude,currentMarker.getPosition().longitude, marker.getPosition().latitude,marker.getPosition().longitude, result);

                   double distance = result[0];
                   Merchant currentMerchant = (Merchant) marker.getTag();

                   Bundle b = new Bundle ();
                   b.putParcelable("merchant",currentMerchant);

                   Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                   intent.putExtra("distance",distance);
                   intent.putExtras(b);
                   startActivity(intent);
               }
               return false;
           }
       });

    }
}
