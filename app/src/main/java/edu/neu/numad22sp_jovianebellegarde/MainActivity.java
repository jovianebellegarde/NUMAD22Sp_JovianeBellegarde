package edu.neu.numad22sp_jovianebellegarde;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  /**
   * Create to run app.
   * @param savedInstanceState run the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * Shows the toast message when user clicks the clickyClicky.
   * @param view to get context of message.
   * Used https://developer.android.com/guide/topics/ui/notifiers/toasts#java for Toast reference.
   */
  public void showButtonMessage(View view) {
    int id = view.getId();
    CharSequence toastText = "Name: Joviane Bellegarde\nEmail: bellegarde.j@northeastern.edu";
    int duration = Toast.LENGTH_SHORT;
    Toast.makeText(view.getContext(), toastText, duration).show();
   }

  public void openSecondActivity(View view) {
    Intent intent = new Intent(view.getContext(), SecondActivity.class);
    startActivity(intent);
  }
}
