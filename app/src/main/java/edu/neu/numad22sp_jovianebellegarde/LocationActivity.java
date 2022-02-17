package edu.neu.numad22sp_jovianebellegarde;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// TODO Add a new activity - done
//  Ask for permission only when the user uses this feature, not on installation
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location

public class LocationActivity extends AppCompatActivity {
  private static final int PERMISSION = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);

    Button locationButton = findViewById(R.id.locationButton);
    TextView locationTextView = findViewById(R.id.locationTextView);

    // Aashi suggested to use this class instead of ActivityResultLauncher:
    // Prob only need find location since both latitude and longitude are needed
    // https://developer.android.com/reference/androidx/core/app/ActivityCompat
    locationButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(LocationActivity.this, "Permission already granted.", Toast.LENGTH_LONG).show();
        } else {
          LocationActivity.this.getLocationPermission(v);
        }
      }
    });
  }

  public void getLocationPermission(View view) {
    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                      Boolean fineLocationGranted = result.getOrDefault(
                              Manifest.permission.ACCESS_FINE_LOCATION, false);
                      Boolean coarseLocationGranted = result.getOrDefault(
                              Manifest.permission.ACCESS_COARSE_LOCATION,false);
                      if (fineLocationGranted != null && fineLocationGranted) {
                        // Precise location access granted.
                      } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted.
                      } else {
                        // No location access granted.
                      }
                    }
            );

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
    locationPermissionRequest.launch(new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    });
  }
 }