package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  /**
   * Create to run
   * @param savedInstanceState run the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * Shows the toast message when user clicks the button.
   * @param view to get context of message.
   */
  public void activateToastButton(View view) {
    CharSequence toastText = "Name: Joviane Bellegarde\nEmail: bellegarde.j@northeastern.edu";
    int duration = Toast.LENGTH_SHORT;
    Toast.makeText(view.getContext(), toastText, duration).show();
  }
}
