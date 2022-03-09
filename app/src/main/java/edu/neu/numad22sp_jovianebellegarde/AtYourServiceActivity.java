package edu.neu.numad22sp_jovianebellegarde;

import edu.neu.numad22sp_jovianebellegarde.databinding.ActivityAtYourServiceBinding;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Map;
import java.util.Scanner;

public class AtYourServiceActivity extends AppCompatActivity {

  private static final String TAG = "AtYourServiceActivity";

  @SuppressLint("StaticFieldLeak")
  private static ActivityAtYourServiceBinding binding;

  private static final Handler handler = new Handler();
  private static final Map<String, String> hairStyleHashMap = new HashMap<>();
  private static final String userInput = binding.editText.getText().toString().toLowerCase();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAtYourServiceBinding.inflate(getLayoutInflater());
    binding.progressBar.setVisibility(View.GONE);
    View view = binding.getRoot();
    setContentView(view);
  }

  /** onClick method stated in the xml file. */
  public void searchHairStyles(View view) {
    Runnable runnable = new RunnableThread();
    Thread runnableThread = new Thread(runnable);
    runnableThread.start();
  }

  /** New runnable -> AsyncTask is deprecated now, so need runnable/thread to get around this. */
  static class RunnableThread implements Runnable {

    @Override
    public void run() {

      handler.post(() -> binding.progressBar.setVisibility(View.VISIBLE));
      parseJSONFile();
      passUpdatedTextViewToMainThread();
    }

    private void parseJSONFile() {
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

        // TA sent me this for reference:
        // https://www.tutorialspoint.com/android/android_json_parser.htm
        for (int i = 0; i < results.length(); i++) {
          JSONObject result = results.getJSONObject(i);
          String name = result.getString("name");
          String description = result.getString("description");

          hairStyleHashMap.put(name.toLowerCase(), description);
        }
      } catch (JSONException e) {
        Log.e(TAG, "JSON Exception Detected");
        e.printStackTrace();
      } catch (ProtocolException e) {
        Log.e(TAG, "Protocol Exception Detected");
        e.printStackTrace();
      } catch (MalformedURLException e) {
        Log.e(TAG, "Malformed URL Exception Detected");
        e.printStackTrace();
      } catch (IOException e) {
        Log.e(TAG, "IO Exception Detected");
        e.printStackTrace();
      }
    }

    /**
     * Converting strings into streams in order to parse the JSON file.
     *
     * @return String object.
     */
    private String convertTheStringIntoStream(InputStream inputStream) {
      // Referenced code from code given to us
      Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
      String returnString;
      if (scanner.hasNext()) {
        returnString = scanner.next().replace(",", ",\n");
      } else {
        returnString = "";
      }
      return returnString;
    }

    /** Passes updated textValue and progressBar to the main thread. */
    private void passUpdatedTextViewToMainThread() {
      if (hairStyleHashMap.containsKey(userInput)) {
        handler.post(() -> binding.progressBar.setVisibility(View.GONE));
        handler.post(() -> binding.textView.setText(hairStyleHashMap.get(userInput)));
      }
    }
  }
}
