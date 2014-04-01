package lemur.urbest.urbestproject;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Crossword extends Activity implements OnTouchListener,
		OnFocusChangeListener, TextWatcher {

	int currentFocus = R.id.first1;
	int nextFocus = R.id.first2;
	boolean rightValueInEditText = true;
	RelativeLayout myRelativeLayout;
	ArrayList<EditText> myEditTextList;
	ArrayList<LinearLayout> nextLineLayouts;

	protected static String FIRST_ANSWER = "MASZYNISTA";
	protected static String SECOND_ANSWER = "PUDEL";
	protected static String THIRD_ANSWER = "CZTERY";
	protected static String FOURTH_ANSWER = "WROC�AW";
	protected static String FIFTH_ANSWER = "LOKOMOTYWA";
	protected static String SIXTH_ANSWER = "BEZA";

	protected static int FIRST_LINE = 0;
	protected static int SECOND_LINE = 1;
	protected static int THIRD_LINE = 2;
	protected static int FOURTH_LINE = 3;
	protected static int FIFTH_LINE = 4;
	protected static int SIXTH_LINE = 5;

	private float currentScore = 0;
	private boolean[] correctEntries = { false, false, false, false, false,
			false };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_crossword);

		myRelativeLayout = (RelativeLayout) findViewById(R.id.my_relative_layout);

		Log.d("Murale", "Number of RelativeLayout childs found: "
				+ myRelativeLayout.getChildCount());

		nextLineLayouts = getAllNextLineLayouts(myRelativeLayout);

		Log.d("Murale", "Number of LinearLayout childs found: "
				+ nextLineLayouts.size());

		myEditTextList = getAllEditTexts(nextLineLayouts);

		Log.d("Murale", "Number of EditText found: " + myEditTextList.size());

		addAllListeners(myEditTextList);

		Button checkAnswers = (Button) findViewById(R.id.check_crossword);

		checkAnswers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkCrossword();
			}
		});
	}

	private void addAllListeners(ArrayList<EditText> myEditTextList) {

		for (int i = 0; i < myEditTextList.size(); i++) {
			myEditTextList.get(i).addTextChangedListener(this);
			myEditTextList.get(i).setOnTouchListener(this);
		}

	}

	private ArrayList<EditText> getAllEditTexts(
			ArrayList<LinearLayout> nextLineLayouts) {

		ArrayList<EditText> myEditTextList = new ArrayList<EditText>();

		for (int i = 0; i < nextLineLayouts.size(); i++) {

			LinearLayout nextLineLayout = nextLineLayouts.get(i);

			for (int k = 0; k < nextLineLayout.getChildCount(); k++) {

				if (nextLineLayout.getChildAt(k) instanceof EditText) {
					myEditTextList.add((EditText) nextLineLayout.getChildAt(k));
				}

			}

		}

		return myEditTextList;
	}

	private ArrayList<LinearLayout> getAllNextLineLayouts(
			RelativeLayout myRelativeLayout) {

		ArrayList<LinearLayout> nextLineLayouts = new ArrayList<LinearLayout>();

		for (int i = 0; i < myRelativeLayout.getChildCount(); i++) {

			if (myRelativeLayout.getChildAt(i) instanceof LinearLayout) {

				LinearLayout linearLayout = (LinearLayout) myRelativeLayout
						.getChildAt(i);

				for (int j = 0; j < linearLayout.getChildCount(); j++) {

					if (linearLayout.getChildAt(j) instanceof LinearLayout) {
						nextLineLayouts.add((LinearLayout) linearLayout
								.getChildAt(j));
					}
				}
			}

		}

		return nextLineLayouts;
	}

	private void afterTextChangeHandler(Editable s) {

		rightValueInEditText = false;
		String str = "";

		try {
			str = s.subSequence(0, s.length()).toString();
			str = str.substring(0, 1);
			s.clear();
			s.insert(0, str);
		} catch (Exception e) {
			Log.d("Crossword", "...");
		} finally {

			EditText et = (EditText) findViewById(nextFocus);
			currentFocus = nextFocus;
			nextFocus = et.getNextFocusRightId();
			et.requestFocus();

			try {
				et.setSelection(0);
			} catch (Exception e) {
				Log.d("Murale", "setSelection can't rich corectly posiotion");
			}

		}
		rightValueInEditText = true;

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		Log.d("Murale", "onTouch");
		currentFocus = v.getId();
		EditText et = (EditText) findViewById(currentFocus);
		try {
			et.setSelection(0);
		} catch (Exception e) {
			Log.d("Murale", "setSelection can't rich corectly posiotion");
		}
		nextFocus = v.getNextFocusRightId();

		return false;
	}

	@Override
	protected void onStop() {

		super.onStop();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {

		Log.d("Crossword", "onFocusChanged");
		currentFocus = v.getId();
		nextFocus = v.getNextFocusRightId();
	}

	@Override
	public void afterTextChanged(Editable s) {
		Log.d("Crossword", "afterTextChanged :" + rightValueInEditText);

		if (rightValueInEditText) {
			afterTextChangeHandler(s);
		}

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	private void checkCrossword() {

		for (int i = 0; i < nextLineLayouts.size(); i++) {
			checkSelectedLine(i);
		}
		this.currentScore = calculateScore();

		Saver saver = new Saver();
		saver.saveAchievedPoints(getApplicationContext(),
				TasksList.TASK_CROSSWORD, this.currentScore,
				DatabaseHandler.KEY_TASK_NAME);

	}

	private void checkSelectedLine(int selectedLine) {

		String userAnswer = "";

		for (int i = 0; i < myEditTextList.size(); i++) {
			if (myEditTextList.get(i).getParent()
					.equals(nextLineLayouts.get(selectedLine))) {
				userAnswer = userAnswer
						+ myEditTextList.get(i).getText().toString()
								.toUpperCase(Locale.getDefault());
			}
		}

		if (userAnswer.equals(getCorrectAnswer(selectedLine))) {
			colorSelectedLine(selectedLine);
			correctEntries[selectedLine] = true;
		}

	}

	private String getCorrectAnswer(int selectedLine) {

		String correctAnswer = "";
		switch (selectedLine) {
		case 0: {
			correctAnswer = FIRST_ANSWER;
			break;
		}
		case 1: {
			correctAnswer = SECOND_ANSWER;
			break;
		}
		case 2: {
			correctAnswer = THIRD_ANSWER;
			break;
		}
		case 3: {
			correctAnswer = FOURTH_ANSWER;
			break;
		}
		case 4: {
			correctAnswer = FIFTH_ANSWER;
			break;
		}
		case 5: {
			correctAnswer = SIXTH_ANSWER;
			break;
		}
		}

		return correctAnswer;
	}

	private void colorSelectedLine(int selectedLine) {

		for (int i = 0; i < myEditTextList.size(); i++) {
			if (myEditTextList.get(i).getParent()
					.equals(nextLineLayouts.get(selectedLine))) {
				myEditTextList.get(i).setBackgroundColor(Color.GREEN);
			}
		}

	}

	private float calculateScore() {

		float currentScore = 0;

		for (int i = 0; i < correctEntries.length; i++) {
			if (correctEntries[i]) {
				currentScore++;
			}
		}
		return currentScore;
	}

}
