package lemur.urbest.urbestproject;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Riddle extends Activity {

	EditText userAnswerET;
	Button checkAnswerButton;
	static protected String CORRECT_ANSWER = "KO� TROYMIEJSKI";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riddle);
		userAnswerET = (EditText) findViewById(R.id.answer);
		checkAnswerButton = (Button) findViewById(R.id.check_the_answer);

		checkAnswerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String userAnswer = userAnswerET.getText().toString();
				userAnswer = userAnswer.trim();
				userAnswer = userAnswer.toUpperCase(Locale.getDefault());

				Log.d("Riddle", "userAnswer: '" + userAnswer + "'");
				if (userAnswer.equals(CORRECT_ANSWER)) {
					Toast.makeText(getApplicationContext(),
							"Gratulacje! Poprawna odpowied�!",
							Toast.LENGTH_LONG).show();

					Saver saver = new Saver();
					saver.saveAchievedPoints(getApplicationContext(),
							TasksList.TASK_RIDDLE, TasksList.MAX_POINTS_RIDDLE,
							DatabaseHandler.KEY_TASK_NAME);

				}
			}
		});

	}
}
