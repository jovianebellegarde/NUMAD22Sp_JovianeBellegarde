package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// TODO Add a new activity - done
//  Display location (latitude and longitude) from the location sensor
//  Display these 2 numbers; don't call Google Maps to display the location
//  Ask for permission only when the user uses this feature, not on installation

public class LocationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
  }
}