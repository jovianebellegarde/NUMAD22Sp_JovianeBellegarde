package edu.neu.numad22sp_jovianebellegarde;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// TODO Add a new activity - done
//  Ask for permission only when the user uses this feature, not on installation
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location

public class LocationActivity extends AppCompatActivity implements LocationListener {
  private static final int PERMISSION = 1;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    // Aashi suggested to use this class instead of ActivityResultLauncher:
    // Prob only need find location since both latitude and longitude are needed
    // https://developer.android.com/reference/androidx/core/app/ActivityCompat
    //locationButton.setOnClickListener(this::getLocationPermission);


  }

  public void getLocationPermission(View view) {
  }

  @Override
  public void onLocationChanged(@NonNull Location location) {
  }
}