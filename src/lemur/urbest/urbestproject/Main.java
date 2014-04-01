package lemur.urbest.urbestproject;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

<<<<<<< HEAD
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
=======
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
>>>>>>> better
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> better

public class Main extends Activity implements OnClickListener {

	LinearLayout mainLayout;
	ArrayList<Button> buttonsList;
	ImageView buttonImg;

	private static final int BUTTON_BASE_ID = 100;
	private static final String TAG = "Ubrest Main";

	private static String[] BUTTON_NAMES = { "Zadania", "Urbest",
<<<<<<< HEAD
			"Zrób Zdjêcie", "Mapa", "QR", "Wyniki", "www", "Facebook",
			"Sponsorzy", "Autorzy" };
	
=======
			"Zrï¿½b Zdjï¿½cie", "Mapa", "QR", "Wyniki", "www", "Facebook",
			"Sponsorzy", "Autorzy" };
>>>>>>> better

	private static int TASKS_BUTTON = 0;
	private static int ABOUT_BUTTON = 1;
	private static int CAMERA_BUTTON = 2;
	private static int MAP_BUTTON = 3;
	private static int QR_BUTTON = 4;
	private static int SCORE_BUTTON = 5;
	private static int WWW_BUTTON = 6;
	private static int FACEBOOK_BUTTON = 7;
	private static int SPONSORS_BUTTON = 8;
	private static int AUTHORS_BUTTON = 9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainLayout = (LinearLayout) findViewById(R.id.main_linear_layout);
<<<<<<< HEAD
		
		buttonsList = getButtonsList();
		Log.d(TAG,"ButtonList size: "+buttonsList.size());
		
		
		for (int i = 0; i < buttonsList.size(); i++) {
			buttonsList.get(i).setText(BUTTON_NAMES[i]);
			buttonsList.get(i).setOnClickListener(this);
			
		}
		buttonsList.get(TASKS_BUTTON).setTextSize(18);
		buttonsList.get(ABOUT_BUTTON).setTextSize(18);
		
	
	}
	
=======

		buttonsList = getButtonsList();
		Log.d(TAG, "ButtonList size: " + buttonsList.size());

		for (int i = 0; i < buttonsList.size(); i++) {
			buttonsList.get(i).setText(BUTTON_NAMES[i]);
			buttonsList.get(i).setOnClickListener(this);

		}
		buttonsList.get(TASKS_BUTTON).setTextSize(18);
		buttonsList.get(ABOUT_BUTTON).setTextSize(18);

	}

>>>>>>> better
	private ArrayList<Button> getButtonsList() {

		ArrayList<Button> buttons = new ArrayList<Button>();
		int buttonCounter = 2;

		buttons.add((Button) findViewById(R.id.task_button));
		buttons.add((Button) findViewById(R.id.urbest_button));

		LinearLayout smallButtonsSet1_Vertical = (LinearLayout) findViewById(R.id.first_small_buttons_set);
		LinearLayout smallButtonsSet2_Vertical = (LinearLayout) findViewById(R.id.second_small_buttons_set);

		for (int i = 0; i < smallButtonsSet1_Vertical.getChildCount(); i++) {
			if (smallButtonsSet1_Vertical.getChildAt(i) instanceof LinearLayout) {
				LinearLayout smallButtonsSet1_Horizontal = (LinearLayout) smallButtonsSet1_Vertical
						.getChildAt(i);
				for (int j = 0; j < smallButtonsSet1_Horizontal.getChildCount(); j++) {
					if (smallButtonsSet1_Horizontal.getChildAt(j) instanceof LinearLayout) {
						LinearLayout buttonLayout = (LinearLayout) smallButtonsSet1_Horizontal
								.getChildAt(j);
						if (buttonLayout.getChildAt(0) instanceof Button) {
							Button nextSmallButton = (Button) buttonLayout
									.getChildAt(0);
<<<<<<< HEAD
							
							nextSmallButton.setId(BUTTON_BASE_ID
									+ buttonCounter);
							buttons.add(nextSmallButton);
							Log.d(TAG,"Button id: "+buttons.get(buttonCounter).getId());
=======

							nextSmallButton.setId(BUTTON_BASE_ID
									+ buttonCounter);
							buttons.add(nextSmallButton);
							Log.d(TAG,
									"Button id: "
											+ buttons.get(buttonCounter)
													.getId());
>>>>>>> better
							buttonCounter++;
						}
					}
				}
			}
		}

		for (int i = 0; i < smallButtonsSet2_Vertical.getChildCount(); i++) {
			if (smallButtonsSet2_Vertical.getChildAt(i) instanceof LinearLayout) {
				LinearLayout smallButtonsSet2_Horizontal = (LinearLayout) smallButtonsSet2_Vertical
						.getChildAt(i);
				for (int j = 0; j < smallButtonsSet2_Horizontal.getChildCount(); j++) {
					if (smallButtonsSet2_Horizontal.getChildAt(j) instanceof LinearLayout) {
						LinearLayout buttonLayout = (LinearLayout) smallButtonsSet2_Horizontal
								.getChildAt(j);
						if (buttonLayout.getChildAt(0) instanceof Button) {
							Button nextSmallButton = (Button) buttonLayout
									.getChildAt(0);
							nextSmallButton.setId(BUTTON_BASE_ID
									+ buttonCounter);
							buttons.add(nextSmallButton);
<<<<<<< HEAD
							Log.d(TAG,"Button id: "+buttons.get(buttonCounter).getId());
=======
							Log.d(TAG,
									"Button id: "
											+ buttons.get(buttonCounter)
													.getId());
>>>>>>> better
							buttonCounter++;
						}
					}
				}
			}
		}

		return buttons;
	}

	@Override
	public void onClick(View view) {

<<<<<<< HEAD
			if (buttonsList.get(TASKS_BUTTON).getId() == view.getId()) {
			showTasksList();
		} else if (buttonsList.get(ABOUT_BUTTON).getId() == view.getId()) {
			//sendFile();
=======
		if (buttonsList.get(TASKS_BUTTON).getId() == view.getId()) {
			showTasksList();
		} else if (buttonsList.get(ABOUT_BUTTON).getId() == view.getId()) {
			// sendFile();
>>>>>>> better
		} else if (buttonsList.get(CAMERA_BUTTON).getId() == view.getId()) {
			takePhoto();
		} else if (buttonsList.get(MAP_BUTTON).getId() == view.getId()) {
			showMap();
		} else if (buttonsList.get(QR_BUTTON).getId() == view.getId()) {
			scanQRcode();
		} else if (buttonsList.get(SCORE_BUTTON).getId() == view.getId()) {
			showScoreList();
		} else if (buttonsList.get(WWW_BUTTON).getId() == view.getId()) {
			openWWWpage();
		} else if (buttonsList.get(FACEBOOK_BUTTON).getId() == view.getId()) {
			openFacebook();
		} else if (buttonsList.get(SPONSORS_BUTTON).getId() == view.getId()) {

<<<<<<< HEAD

		} else if (buttonsList.get(AUTHORS_BUTTON).getId() == view.getId()) {


		}
		
	}
	

=======
		} else if (buttonsList.get(AUTHORS_BUTTON).getId() == view.getId()) {

		}

	}
>>>>>>> better

	private void scanQRcode() {
		try {

			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for
															// bar codes

			startActivityForResult(intent, 0);

		} catch (Exception e) {

			Uri marketUri = Uri
					.parse("market://details?id=com.google.zxing.client.android");
			Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
			startActivity(marketIntent);

		}
	}

	private void takePhoto() {

		Uri photoUri;

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		photoUri = getContentResolver().insert(EXTERNAL_CONTENT_URI,
				new ContentValues());
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		startActivity(intent);
	}

	private void showMap() {

		Intent intent = new Intent(getApplicationContext(), MapActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("mode", 0);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	private void showTasksList() {
		Intent intent = new Intent(getApplicationContext(), TasksList.class);
		startActivity(intent);
	}

	private void openFacebook() {
		String url = "https://www.facebook.com/events/169274446567019/?ref=3&ref_newsfeed_story_type=regular";
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
	}

	private void openWWWpage() {
		String url = "http://urbest.urbancloud.pl/";
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
	}

	private void showScoreList() {
		Intent intent = new Intent(getApplicationContext(), ScoreList.class);
		startActivity(intent);
	}
<<<<<<< HEAD
	
=======

>>>>>>> better
}
