package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import edu.neu.numad22sp_jovianebellegarde.databinding.ActivityAtYourServiceBinding;
import android.annotation.SuppressLint;

import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class AtYourServiceActivity extends AppCompatActivity {

  private static final String TAG = "AtYourServiceActivity";

  @SuppressLint("StaticFieldLeak")
  private static ActivityAtYourServiceBinding binding;
  public static Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAtYourServiceBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);
  }

  // runnable thread
  public void getData(View view) {
    new Thread(new RunnableThread()).start();
  }

  static class RunnableThread implements Runnable {
    HashMap<String, String> hairStyleHashMap = new HashMap<>();
    String userInput = binding.editText.getText().toString().toLowerCase();

    @Override
    public void run() {

      try {
        URL url = new URL("https://ffxivcollect.com/api/hairstyles");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        final String response = convertTheStringIntoStream(inputStream);

        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("results");

        /* TA sent me this for reference: https://www.tutorialspoint.com/android/android_json_parser.htm */
        for (int i = 0; i < results.length(); i++) {
          JSONObject result = results.getJSONObject(i);
          String name = result.getString("name");
          String description = result.getString("description");

          hairStyleHashMap.put(name.toLowerCase(), description);
        }
      } catch (MalformedURLException e) {
        Log.e(TAG, "MalformedURLException");
        e.printStackTrace();
      } catch (ProtocolException e) {
        Log.e(TAG, "ProtocolException");
        e.printStackTrace();
      } catch (IOException e) {
        Log.e(TAG, "IOException");
        e.printStackTrace();
      } catch (JSONException e) {
        Log.e(TAG, "JSONException");
        e.printStackTrace();
      }
      if (hairStyleHashMap.containsKey(userInput)) {
        handler.post(() -> binding.textView.setText(hairStyleHashMap.get(userInput)));
      }
    }

    private String convertTheStringIntoStream(InputStream inputStream) {
      Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
      String returnString;
      if (scanner.hasNext()) {
        returnString = scanner.next().replace(",", ",\n");
      } else {
        returnString = "";
      }
      return returnString;
    }
  }
}
