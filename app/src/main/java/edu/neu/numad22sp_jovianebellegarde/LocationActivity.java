package edu.neu.numad22sp_jovianebellegarde;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// TODO Add a new activity - done
//  Ask for permission only when the user uses this feature, not on installation
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location

public class LocationActivity extends AppCompatActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback {
  private Button locationButton;
  private int PERMISSION = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    // Aashi suggested to use this class instead of ActivityResultLauncher:
    // Prob only need find location since both latitude and longitude are needed
    // https://developer.android.com/reference/androidx/core/app/ActivityCompat
    locationButton = findViewById(R.id.button_get_location);
    locationButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showLocationPreview();
      }
    });
  }

  public void showLocationPreview() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(LocationActivity.this, "Permission Granted", Toast.LENGTH_LONG)
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
                      PERMISSION)).setNegativeButton("Cancel", (dialog, which) ->
              dialog.cancel()).create().show();
    } else {
      ActivityCompat.requestPermissions(this,
              new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
              PERMISSION);
    }
  }
}