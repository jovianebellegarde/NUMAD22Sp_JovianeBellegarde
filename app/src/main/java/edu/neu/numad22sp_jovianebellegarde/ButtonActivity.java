package edu.neu.numad22sp_jovianebellegarde;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {
  private TextView secondActivityTextView;

  /**
   * Created to run the new activity with the 6 buttons.
   * @param savedInstanceState run the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_button);

    Button buttonA = findViewById(R.id.buttonA);
    Button buttonB = findViewById(R.id.buttonB);
    Button buttonC = findViewById(R.id.buttonC);
    Button buttonD = findViewById(R.id.buttonD);
    Button buttonE = findViewById(R.id.buttonE);
    Button buttonF = findViewById(R.id.buttonF);

    buttonA.setOnClickListener(ButtonActivity.this);
    buttonB.setOnClickListener(ButtonActivity.this);
    buttonC.setOnClickListener(ButtonActivity.this);
    buttonD.setOnClickListener(ButtonActivity.this);
    buttonE.setOnClickListener(ButtonActivity.this);
    buttonF.setOnClickListener(ButtonActivity.this);

    this.secondActivityTextView = findViewById(R.id.buttonActivityTextView);
  }

  /**
   * OnClick method implemented from the View.OnClick interface. Passes a textView and button object
   * into the pressButton method to set the text.
   * @param view object to get the R.id values.
   */
  @SuppressLint("SetTextI18n")
  @Override
  public void onClick(View view) {
    // Referred to https://developer.android.com/reference/android/R.string
    int viewId = view.getId();

    // Avoided switch statement to prevent issues with Resource IDs and Gradle
    if (viewId == R.id.buttonA) {
      pressButton(this.secondActivityTextView, R.string.pressed_a);
    } else if (viewId == R.id.buttonB) {
      pressButton(this.secondActivityTextView, R.string.pressed_b);
    } else if (viewId == R.id.buttonC) {
      pressButton(this.secondActivityTextView, R.string.pressed_c);
    } else if (viewId == R.id.buttonD) {
      pressButton(this.secondActivityTextView, R.string.pressed_d);
    } else if (viewId == R.id.buttonE) {
      pressButton(this.secondActivityTextView, R.string.pressed_e);
    } else {
      pressButton(this.secondActivityTextView, R.string.pressed_f);
    }
  }

  /**
   * Setting the text when the button is pressed for corresponding button/text.
   * @param textView to set the text for each button.
   * @param value the id pertaining to the
   */
  public void pressButton(TextView textView, int value) {
    textView.setText(value);
  }
}
