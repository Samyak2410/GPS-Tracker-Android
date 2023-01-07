package com.example.gpstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_RATE_INTERVAL = 30;
    public static final int FAST_RATE_INTERVAL = 5;

    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_lbladdress, tv_updates, tv_sensor;
    Switch sw_locationsupdates, sw_gps;

    //API location for google services
    FusedLocationProviderClient fusedLocationProviderClient;

    boolean updateOn = false;

    //Config file used for all setting related for FusedLocationProviderClient
    LocationRequest locationRequest; //new instance of this class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_lat = findViewById(R.id.tv_lat);
        tv_lon = findViewById(R.id.tv_lon);
        tv_altitude = findViewById(R.id.tv_altitude);
        tv_accuracy = findViewById(R.id.tv_accuracy);
        tv_speed = findViewById(R.id.tv_speed);
        tv_lbladdress = findViewById(R.id.tv_lbladdress);
        tv_updates = findViewById(R.id.tv_updates);
        tv_sensor = findViewById(R.id.tv_sensor);
        sw_locationsupdates = findViewById(R.id.sw_locationsupdates);
        sw_gps = findViewById(R.id.sw_gps);

        // set all properties of LocationRequest
        locationRequest = new LocationRequest(); //Many times, the new keyword in Java is also
        // used to create the array object. The new keyword is followed by a call to a constructor, which instantiates the new object.

        locationRequest.setInterval(1000 * DEFAULT_RATE_INTERVAL);
        locationRequest.setFastestInterval(1000 * FAST_RATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_gps.isChecked()) {
                    //most accurate - GPS is set.
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensors");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers + Wifi");
                }
            }
        });

    } //end OnCreate method

    private void updateGPS() {
    //get permission from the user to track GPS
        // get current location from the fused client
        //Update the UI,i.e- set all properties in their associated text view items.

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
     if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        { //user provided permission
            //fusedLocationProviderClient.getLastLocation();

     }  else { //permission not granted yet

     }
    }

}