package lemur.urbest.urbestproject;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PopUp {

	public void ShowDialog(Context context, String text) {
		// custom dialog

		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.popup);
		dialog.setTitle("Informacja");

		Button okButton = (Button) dialog.findViewById(R.id.popUpOkButton);
		Button helpButton = (Button) dialog.findViewById(R.id.popUpHelpButton);
		TextView infoText = (TextView) dialog.findViewById(R.id.popUpTextView);
		infoText.setText(text);

		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});

		helpButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});

		dialog.show();

	}

	public void ShowAnswer(Context context, String text, String answer) {
		if (!answer.equals("")) {
			ShowAnswerDialog(context, text, answer);
		} else {
			ShowAnswerDialog(context, text);
		}

	}

	public void ShowAnswerDialog(Context context, String text, String answer) {
		final Dialog dialog = new Dialog(context);

		dialog.setContentView(R.layout.answer_popup);
		dialog.setTitle("Wprowad� odpowied�");

		Button submitButton = (Button) dialog
				.findViewById(R.id.popUpSubmitButton);
		Button cancelButton = (Button) dialog
				.findViewById(R.id.popUpCancelButton);

		EditText edit = (EditText) dialog.findViewById(R.id.popUpEditText);

		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});

		dialog.show();
	}

	public void ShowAnswerDialog(Context context, String text) {

		final Dialog dialog = new Dialog(context);

		dialog.setContentView(R.layout.prompt_popup);
		dialog.setTitle(text);

		Button ok = (Button) dialog.findViewById(R.id.protomptPopUpOkButton);

		TextView textView = (TextView) dialog
				.findViewById(R.id.promptPopUpTextView);

		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});

		dialog.show();
	}
}
