package edu.neu.numad22sp_jovianebellegarde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback, LocationListener {
  private final int PERMISSION = 777;
  TextView latitudeTextView;
  TextView longitudeTextView;
  Location location;
  LocationManager locationManager;
  // Referred for code flow: https://developer.android.com/training/permissions/requesting

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
  }

  public void getAccessPermission(View view) {
    activityCompatCheckSelfPermission(view);
  }

  public void activityCompatCheckSelfPermission(View view) {
    if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(view.getContext(), "Permission Granted", Toast.LENGTH_LONG).show();
    } else {
      requestLocationPermission();
    }
  }

  public void requestLocationPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
            Manifest.permission.ACCESS_FINE_LOCATION)) {
      new AlertDialog.Builder(this)
              .setTitle("Permission Requested")
              .setMessage("This app is requesting permission to access the device location.")
              .setPositiveButton("Accept Access", (dialog, which) ->
                      ActivityCompat.requestPermissions(LocationActivity.this,
                              new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION))
              .setNegativeButton("Cancel", (dialog, which) ->
                      dialog.cancel()).create().show();
    } else {
      ActivityCompat.requestPermissions(this,
              new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION);
    }
  }

  @SuppressLint("MissingSuperCall")
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    if (requestCode == PERMISSION) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Aashi suggested to use this class instead of ActivityResultLauncher:
        // Prob only need find location since both latitude and longitude are needed
        // https://developer.android.com/reference/androidx/core/app/ActivityCompat
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION);
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
      } else {
        new AlertDialog.Builder(this)
                .setTitle("Feature Unavailable")
                .setMessage("This feature is unavailable because access to location was denied.")
                .create().show();
      }
    }
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onLocationChanged(@NonNull Location location) {
    latitudeTextView = findViewById(R.id.latitude_textview);
    longitudeTextView = findViewById(R.id.longitude_textview);
    latitudeTextView.setText("Latitude: " + location.getLatitude());
    longitudeTextView.setText("Longitude: " + location.getLongitude());
  }
}