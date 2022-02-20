package edu.neu.numad22sp_jovianebellegarde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
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
  TextView latitudeTextView;
  TextView longitudeTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    // Aashi suggested to use this class instead of ActivityResultLauncher:
    // Prob only need find location since both latitude and longitude are needed
    // https://developer.android.com/reference/androidx/core/app/ActivityCompat
    // Button locationButton = findViewById(R.id.button_get_location);
    // latitudeTextView = findViewById(R.id.latitude_textview);
    // longitudeTextView = findViewById(R.id.longitude_textview);
    // locationButton.setOnClickListener(v -> activityCompatCheckSelfPermission());
  }

  public void getAccessPermission(View view) {
    activityCompatCheckSelfPermission(view);
  }

  public void activityCompatCheckSelfPermission(View view) {
    if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(view.getContext(), "Permission Granted", Toast.LENGTH_LONG)
              .show();
      // call location method
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

  @SuppressLint("MissingSuperCall")
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    switch(requestCode) {
      case PERMISSION:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          // call for precise location (latitude and longitude)
          //Toast.makeText(LocationActivity.this, "switch statement", Toast.LENGTH_SHORT).show();
          activityResultLauncher();
        } else {
          // Explain to the user that the feature is unavailable because
          // the features requires a permission that the user has denied.
          // At the same time, respect the user's decision. Don't link to
          // system settings in an effort to convince the user to change
          // their decision.
          new AlertDialog.Builder(this)
                  .setTitle("Feature Unavailable")
                  .setMessage("This feature is unavailable because access to location was denied.")
                  .create().show();
        }
    }
  }

  public void activityResultLauncher() {
    Toast.makeText(LocationActivity.this, "activity result launcher", Toast.LENGTH_SHORT).show();
    Location location = new Location(Manifest.permission.LOCATION_HARDWARE);
    onLocationChanged(location);
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