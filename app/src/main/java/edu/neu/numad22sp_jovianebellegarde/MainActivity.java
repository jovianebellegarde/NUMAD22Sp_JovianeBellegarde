package edu.neu.numad22sp_jovianebellegarde;

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
}
