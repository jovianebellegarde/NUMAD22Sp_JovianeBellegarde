package edu.neu.numad22sp_jovianebellegarde;

// TODO:
//  1. new activity needs to have a list of links; will start with 0 items
//  2. floating action button to start a new link
//  3. user enters a name and URL for link which is added to the list
//  4. when user taps a URL in the list, launch url in a web browser
//  5. use a message in a snackbar to inform user the link was successfully/unsuccessfully created
//  6. link data has to persist for a configuration change (bundle/parcel stuff)
//  7. links do not persist if the app is stopped/launched again or if link collector dismissed
//  8. hit back button to make sure app behaves

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  /**
   * Created to run the app.
   * @param savedInstanceState run the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * Shows the toast message when user clicks the About Me button.
   * @param view to get Context of message.
   */
  public void helloWorldActivity(View view) {
    startActivity(new Intent(view.getContext(), NameActivity.class));
  }

  /**
   * Opens a new activity with 6 buttons from A to F.
   * @param view to get the Context of the message.
   */
  public void sixButtonsActivity(View view) {
    startActivity(new Intent(view.getContext(), ButtonActivity.class));
  }

  /**
   * Opens a new activity to collect the links.
   * @param view to get the Context of the message.
   */
  public void linkListActivity(View view) {
    startActivity(new Intent(view.getContext(), LinkCollectorActivity.class));
  }
}
