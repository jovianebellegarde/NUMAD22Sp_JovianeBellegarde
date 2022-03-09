package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import edu.neu.numad22sp_jovianebellegarde.databinding.ActivityAtYourServiceBinding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtYourServiceActivity extends AppCompatActivity {

  private static final String TAG = "AtYourServiceActivity";

  @SuppressLint("StaticFieldLeak")
  private static ActivityAtYourServiceBinding binding;

  public static Handler handler = new Handler(Looper.getMainLooper()) {
    @Override
    public void handleMessage(Message msg) {
      Bundle bundle = msg.getData();
      String string = bundle.getString("results");
      binding.textView.setText(string);
    }
  };


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
    List<String> hairList = new ArrayList();

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

        //JSONObject jsonObject = new JSONObject(response);
        //JSONArray jsonArrayOfObjects = jsonObject.getJSONArray("results");
        //JSONArray jsonArray = new JSONArray(response);

        JSONArray results = new JSONArray(response);
        for (int i = 0; i < results.length(); i++) {
          JSONObject result = results.getJSONObject(i);
          String name = result.getString("name");
          String description = result.getString("description");
          if (name.equals(binding.editText.getText().toString())) {
            handler.post(() -> binding.textView.setText(description));
          }
        }
        // loop through the array of objects -> for each object, need to find the name specified
        // return the description
//        for (int i = 0; i < jsonArrayOfObjects.length(); i++) {
//          if (jsonArrayOfObjects.get(i) == binding.editText.getText()) {
//            binding.textView.setText("Wind Caller");
//          }
//        }
        // System.out.println(jsonObject.toString() + "Json Object");
        // String name = jsonObject.getString("name");
        //String description = jsonObject.getString("description");
        // hairList.add(name);
        // hairList.add(description);
        //handler.post(() -> binding.textView.setText(hairList.get(0)));
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
      Message msg = handler.obtainMessage();
      Bundle bundle = new Bundle();
      bundle.putString("results", binding.editText.getText().toString());
      msg.setData(bundle);
      handler.sendMessage(msg);

      //      // try catch block -> https request here
      //      // user input for choice of json file here
      //      // send updates back to the main thread
      //      // JSONObject jsonObject = new JSONObject();
      //
      //      StringBuilder stringBuilder = new StringBuilder();
      //
      //      // this is just one hairstyle
      //      try {
      //
      //        URL url = new URL("https://ffxivcollect.com/api/hairstyles");
      //        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      //        BufferedReader bufferedReader =
      //            new BufferedReader(new InputStreamReader(connection.getInputStream()));
      //        String string;
      //
      //        while ((string = bufferedReader.readLine()) != null) {
      //          stringBuilder.append(string);
      //        }
      //        bufferedReader.close();
      //
      //        if (!stringBuilder.toString().isEmpty()) {
      //          JSONObject jsonObject = new JSONObject(stringBuilder.toString());
      //        }
      //      } catch (Exception e) {
      //        Log.e(TAG, "Exception found");
      //      }
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
