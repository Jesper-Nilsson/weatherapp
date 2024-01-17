package se.miun.dt170g;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GPSManager gpsManager = new GPSManager(this); // 'this' refers to an Activity context


        double longi = gpsManager.getLongitude();
        double lati = gpsManager.getLatitude();
        // grab api and put into variable in future here
        String grabbedAPI = "longitude is: " + longi + "latitude is : " + lati;
        TextView displayAPI = findViewById(R.id.apiWrapper);
        displayAPI.setText(grabbedAPI);
    }
}