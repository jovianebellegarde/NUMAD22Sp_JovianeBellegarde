package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  // TODO: check that this updated on gitHub
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
   * Used https://developer.android.com/guide/topics/ui/notifiers/toasts#java for Toast reference.
   */
  public void showToastMessage(View view) {
    CharSequence toastText = "Name: Joviane Bellegarde\nEmail: bellegarde.j@northeastern.edu";
    int duration = Toast.LENGTH_SHORT;
    Toast.makeText(view.getContext(), toastText, duration).show();
   }

  /**
   * Opens a new activity with 6 buttons from A to F.
   * @param view to get the Context of the message.
   */
  public void openButtonActivity(View view) {
    startActivity(new Intent(view.getContext(), ButtonActivity.class));
  }
}
