package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
  }

  public void chooseButton(View view) {
    if (view.getId() == R.id.buttonA) {
      showButtonMessage(R.id.buttonA);
    }
  }

  public void showButtonMessage(int value) {
    Button button = findViewById(value);
    TextView textView = findViewById(value);

    button.setOnClickListener(new View.OnClickListener() {
      @SuppressLint("SetTextI18n")
      @Override
      public void onClick(View v) {
        textView.setText("Pressed: %s", (TextView.BufferType) button.getTag());
      }
    });
  }
}
