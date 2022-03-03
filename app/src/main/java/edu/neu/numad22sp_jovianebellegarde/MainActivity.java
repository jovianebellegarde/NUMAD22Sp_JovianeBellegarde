package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
  public void launchHelloWorldActivity(View view) {
    startActivity(new Intent(view.getContext(), AboutMeActivity.class));
  }

  /**
   * Opens a new activity with 6 buttons from A to F.
   * @param view to get the Context of the message.
   */
  public void launchSixButtonsActivity(View view) {
    startActivity(new Intent(view.getContext(), ButtonActivity.class));
  }

  /**
   * Opens a new activity to collect the links.
   * @param view to get the Context of the message.
   */
  public void launchLinkListActivity(View view) {
    startActivity(new Intent(view.getContext(), LinkListActivity.class));
  }

  public void launchLocationActivity(View view) {
    startActivity(new Intent(view.getContext(), LocationActivity.class));
  }

  public void launchActivityAtYourService(View view) {
    startActivity(new Intent(view.getContext(), AtYourServiceActivity.class));
  }
}
