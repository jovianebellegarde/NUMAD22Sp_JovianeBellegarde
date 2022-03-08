package edu.neu.numad22sp_jovianebellegarde;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;
import edu.neu.numad22sp_jovianebellegarde.databinding.ActivityMainBinding;

public class AtYourServiceActivity extends AppCompatActivity {

  private static final String TAG = "AtYourServiceActivity";
  private ActivityMainBinding binding;
  private final Handler editTextHandler = new Handler();
  protected EditText editText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);
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
      // JSONObject jsonObject = new JSONObject();

      StringBuilder stringBuilder = new StringBuilder();

      // this is just one hairstyle
      try {
        URL url = new URL("https://ffxivcollect.com/api/hairstyles");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String string;

        while ((string = bufferedReader.readLine()) != null) {
          stringBuilder.append(string);
        }
        bufferedReader.close();

        if (!stringBuilder.toString().isEmpty()) {
          // fill in rest here
        }
      } catch (Exception e) {
        Log.e(TAG, "Exception found");
      }
    }
  }
}
