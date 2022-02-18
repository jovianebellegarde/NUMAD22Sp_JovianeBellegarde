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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// TODO Add a new activity - done
//  Ask for permission only when the user uses this feature, not on installation
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location

public class LocationActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback, LocationListener {
  private final int PERMISSION = 777;
  private TextView latitudeTextView;
  private TextView longitudeTextView;
  private LocationManager locationManager;
  private Location location;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(LocationActivity.this, "Permission Granted", Toast.LENGTH_LONG)
              .show();
      // call location method
    } else {
      requestLocationPermission();
    }
    // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    // location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    Button locationButton = findViewById(R.id.button_get_location);
    latitudeTextView = findViewById(R.id.latitude_textview);
    longitudeTextView = findViewById(R.id.longitude_textview);
    locationButton.setOnClickListener(v -> requestLocationPermission());
  }

  public void requestLocationPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
            Manifest.permission.ACCESS_FINE_LOCATION)) {
      new AlertDialog.Builder(this)
              .setTitle("Permission Requested")
              .setMessage("This app is requesting permission to access the device location.")
              .setPositiveButton("Accept Access", (dialog, which) ->
                      ActivityCompat.requestPermissions(LocationActivity.this,
                              new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                              PERMISSION))
              .setNegativeButton("Cancel", (dialog, which) ->
                      dialog.cancel()).create().show();
    } else {
      ActivityCompat.requestPermissions(this,
              new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
              PERMISSION);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSION) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Location location = new Location(Manifest.permission.ACCESS_FINE_LOCATION);
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
    latitudeTextView.setText("Latitude:" + location.getLatitude());
    longitudeTextView.setText("Longitude:" + location.getLongitude());
  }
}