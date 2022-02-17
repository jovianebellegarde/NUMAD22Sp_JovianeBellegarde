package edu.neu.numad22sp_jovianebellegarde;

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
    Log.d("TAG", "getLocationPermission:" + ActivityCompat.shouldShowRequestPermissionRationale((Activity) view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION));
    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
      Toast.makeText(view.getContext(), "YEsssss", Toast.LENGTH_LONG).show();
      new AlertDialog.Builder(view.getContext())
              .setMessage("This app needs access to current device location.")
              .setTitle("Location Permission Access")
              .setPositiveButton("Approve Access", (dialog, which) ->
                      ActivityCompat
                              .requestPermissions((Activity) view.getContext(),
                                      new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION))
              .setNegativeButton("Deny Access", (dialog, which) -> dialog.cancel()).create().show();

    } else {
      ActivityCompat.requestPermissions((Activity) view.getContext(),
              new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION);
      Toast.makeText(view.getContext(), "WHYYYY", Toast.LENGTH_LONG).show();
    }
  }
 }