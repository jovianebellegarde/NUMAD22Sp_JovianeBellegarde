package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
  private Button buttonA, buttonB, buttonC, buttonD, buttonE, buttonF;
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    buttonA.setOnClickListener(this);
    buttonB.setOnClickListener(this);
    buttonC.setOnClickListener(this);
    buttonD.setOnClickListener(this);
    buttonE.setOnClickListener(this);
    buttonF.setOnClickListener(this);
  }

  @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.buttonA:
        textView.setText("Pressed: A");
    }
  }
}
