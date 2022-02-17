package edu.neu.numad22sp_jovianebellegarde;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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

public class LocationActivity extends AppCompatActivity {
  private static final int PERMISSION = 1;
  private LocationManager locationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);

    Button locationButton = findViewById(R.id.getLocationButton);
    TextView locationTextView = findViewById(R.id.locationTextView);

    // Aashi suggested to use this class instead of ActivityResultLauncher:
    // Prob only need find location since both latitude and longitude are needed
    // https://developer.android.com/reference/androidx/core/app/ActivityCompat
    //locationButton.setOnClickListener(this::getLocationPermission);
  }

  public void getLocationPermission(View view) {
    Toast.makeText(view.getContext(), "helooooo", Toast.LENGTH_LONG).show();

    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                      Boolean fineLocationGranted = result.getOrDefault(
                              Manifest.permission.ACCESS_FINE_LOCATION, false);
                      Boolean coarseLocationGranted = result.getOrDefault(
                              Manifest.permission.ACCESS_COARSE_LOCATION,false);
                      if (fineLocationGranted != null && fineLocationGranted) {
                        // Precise location access granted.
                        Toast.makeText(view.getContext(), "fineLocation", Toast.LENGTH_LONG).show();
                      } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted.
                        Toast.makeText(view.getContext(), "courseLocation", Toast.LENGTH_LONG).show();
                      } else {
                        // No location access granted.
                        Toast.makeText(view.getContext(),"Denied", Toast.LENGTH_LONG).show();
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

  public void getPreciseLocation(View view) {

  }

  public void getCoarseLocation(View view) {

  }

  public double getLatitude(View view) {
    return 1.0;
  }

  public double getLongitude(View view) {
    return 1.0;
  }
}