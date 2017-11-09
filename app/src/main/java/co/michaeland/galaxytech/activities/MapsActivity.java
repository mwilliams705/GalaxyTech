package co.michaeland.galaxytech.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import co.michaeland.galaxytech.R;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        LocationListener,
        ActivityCompat.OnRequestPermissionsResultCallback
{

    private LocationManager locationManager;

    final static String TAG = "Map Activity";

    double lat;
    double lon;

    private GoogleMap mMap;

    final static int MY_PERMISSION_REQUEST_FINE_LOCATION= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        try {
            locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                    2000, 20, this
            );
            Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show();

        }catch (SecurityException e){
            permissionCheck();
            System.out.println("Permission not granted");
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng myLocation = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
    }

    @Override
    public void onLocationChanged(Location location ) {
        lat = formatDouble(location.getLatitude());
        lon = formatDouble(location.getLongitude());

        onMapReady(mMap);

    }

    public double formatDouble(double d){
        NumberFormat formatter = new DecimalFormat("#0.00");
        formatter.format(d);
        return d;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getBaseContext(), "Derp", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSION_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(
                            this,
                            "Location Permission Granted!!!",
                            Toast.LENGTH_LONG
                    ).show();
                }else {
                    return;
                }

            }
        }

    }

    public void permissionCheck(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )){
                Toast.makeText(this,"Need Location Permission", Toast.LENGTH_SHORT).show();

            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_REQUEST_FINE_LOCATION
                );
            }
        }
    }

}
