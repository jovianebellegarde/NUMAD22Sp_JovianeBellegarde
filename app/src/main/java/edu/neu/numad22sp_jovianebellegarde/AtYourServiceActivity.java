package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import edu.neu.numad22sp_jovianebellegarde.databinding.ActivityMainBinding;

import android.os.Bundle;
import android.view.View;

// TODO - do NOT use volley :(
public class AtYourServiceActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);
  }

}