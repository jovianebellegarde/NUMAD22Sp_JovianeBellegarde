package edu.neu.numad22sp_jovianebellegarde;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AtYourServiceActivity extends AppCompatActivity {

  private static final String TAG = "AtYourServiceActivity";
  private final Handler editTextHandler = new Handler();
  protected EditText editText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_at_your_service);
    editText = findViewById(R.id.edit_text);
  }

  // runnable thread
  public void getData(View view) {
    new Thread(new RunnableThread()).start();
  }

  static class RunnableThread implements Runnable {

    @Override
    public void run() {
      // try catch block -> https request here
      // user input for choice of json file here
      // send updates back to the main thread
    }
  }
}
