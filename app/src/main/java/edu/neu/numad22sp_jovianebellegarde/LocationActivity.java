package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// TODO Add a new activity - done
//  Ask for permission only when the user uses this feature, not on installation
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location

public class LocationActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
  }

  // Aashi suggested to use this class instead: https://developer.android.com/reference/androidx/core/app/ActivityCompat
  public void showLocation(View view) {
    // TODO leaving implementation to Thursday morning
  }
}