package lemur.urbest.urbestproject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class ListV extends Activity {

	Cursor itemCursor;
	DatabaseHandler db;
	ListView listV;
<<<<<<< HEAD
	
	float latitude[];
	float longitude[];
	String date[];
	
=======

	float latitude[];
	float longitude[];
	String date[];

>>>>>>> better
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listv_layout);
		listV = (ListView) findViewById(R.id.listV);
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

		itemCursor = db.getAllEntries(DatabaseHandler.LOCATIONS_TABLE);
		startManagingCursor(itemCursor);
		updateResultsList();

	}

<<<<<<< HEAD
	
=======
>>>>>>> better
	private void updateResultsList() {

		List<String> latitudeList = new ArrayList<String>();
		List<String> longitudeList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();
<<<<<<< HEAD
		
=======

>>>>>>> better
		itemCursor.requery();
		if (itemCursor.moveToFirst()) {

			do {

				String latitude = itemCursor
						.getString(DatabaseHandler.LATITUDE_COLUMN);
				String longitude = itemCursor
						.getString(DatabaseHandler.LONGITUDE_COLUMN);
<<<<<<< HEAD
				String date = itemCursor
						.getString(DatabaseHandler.DATE_COLUMN);
				
				latitudeList.add(latitude);
				longitudeList.add(longitude);
				dateList.add(date);
				
			} while (itemCursor.moveToNext());
		}
		
=======
				String date = itemCursor.getString(DatabaseHandler.DATE_COLUMN);

				latitudeList.add(latitude);
				longitudeList.add(longitude);
				dateList.add(date);

			} while (itemCursor.moveToNext());
		}

>>>>>>> better
		int n = dateList.size();

		float[] latitude = new float[n];
		float[] longitude = new float[n];
		String[] date = new String[n];
<<<<<<< HEAD
		
=======
>>>>>>> better

		for (int i = 0; i < n; i++) {

			latitude[i] = Float.valueOf(latitudeList.get(i));
			longitude[i] = Float.valueOf(longitudeList.get(i));
			date[i] = dateList.get(i);
<<<<<<< HEAD
			
		}

		SpecialAdapter adapter = new SpecialAdapter(this, latitude, longitude, date);
=======

		}

		SpecialAdapter adapter = new SpecialAdapter(this, latitude, longitude,
				date);
>>>>>>> better
		listV.setAdapter(adapter);
	}

}
