package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// TODO Add a new activity - done
//  Ask for permission only when the user uses this feature, not on installation
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location

public class LocationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);

    Button locationButton = findViewById(R.id.locationButton);

    // Aashi suggested to use this class instead of ActivityResultLauncher:
    // https://developer.android.com/reference/androidx/core/app/ActivityCompat
    locationButton.setOnClickListener(v -> {
      if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
              == PackageManager.PERMISSION_GRANTED
              && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
              == PackageManager.PERMISSION_GRANTED
              && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
              == PackageManager.PERMISSION_GRANTED) {
         Toast.makeText(LocationActivity.this, "Permission already granted.",
                 Toast.LENGTH_LONG).show();
      } else {
        getLocationPermission(v);
      }
    });
  }

  public void getLocationPermission(View view) {
    Toast.makeText(LocationActivity.this, "Permission has not been granted yet.",
            Toast.LENGTH_LONG).show();
  }
}