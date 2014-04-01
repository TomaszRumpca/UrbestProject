package lemur.urbest.urbestproject;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
=======
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
>>>>>>> better
import android.widget.ListView;

public class ScoreList extends Activity {

	Cursor itemCursor;
	DatabaseHandler db;
	ListView scoreList;
	private static String TABLE = "Scores";
<<<<<<< HEAD
	
	String[] task;
	float[] score;
	
	
=======

	String[] task;
	float[] score;

>>>>>>> better
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_list);
		scoreList = (ListView) findViewById(R.id.score_list_view);
		db = new DatabaseHandler(getApplicationContext());
		db.open();

		fillItemList();
	}

	@Override
	protected void onDestroy() {

		db.close();
		super.onDestroy();
	}
<<<<<<< HEAD
	
=======

>>>>>>> better
	private void fillItemList() {

		itemCursor = db.getAllEntries(TABLE);
		startManagingCursor(itemCursor);
		updateResultsList();
<<<<<<< HEAD
		
	}

	private void updateResultsList() {
		
		List<String> tasks = new ArrayList<String>();
		List<String> scores = new ArrayList<String>();
		
		itemCursor.requery();
		if (itemCursor.moveToFirst()) {

			do {				
				tasks.add(itemCursor
						.getString(DatabaseHandler.TAKS_COLUMN));
				scores.add(itemCursor
						.getString(DatabaseHandler.SCORE_COLUMN));
				
			} while (itemCursor.moveToNext());
		}
		
		ScoreListAdapter adapter = new ScoreListAdapter(this,  tasks, scores, null);
		scoreList.setAdapter(adapter);
		
	}


=======

	}

	private void updateResultsList() {

		List<String> tasks = new ArrayList<String>();
		List<String> scores = new ArrayList<String>();

		itemCursor.requery();
		if (itemCursor.moveToFirst()) {

			do {
				tasks.add(itemCursor.getString(DatabaseHandler.TAKS_COLUMN));
				scores.add(itemCursor.getString(DatabaseHandler.SCORE_COLUMN));

			} while (itemCursor.moveToNext());
		}

		ScoreListAdapter adapter = new ScoreListAdapter(this, tasks, scores,
				null);
		scoreList.setAdapter(adapter);

	}
>>>>>>> better

}
