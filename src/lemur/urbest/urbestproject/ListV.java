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
	
	float latitude[];
	float longitude[];
	String date[];
	
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
	
	

	
	private void fillItemList() {

		itemCursor = db.getAllEntries();
		startManagingCursor(itemCursor);
		updateResultsList();

	}

	
	private void updateResultsList() {

		List<String> latitudeList = new ArrayList<String>();
		List<String> longitudeList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();
		
		itemCursor.requery();
		if (itemCursor.moveToFirst()) {

			do {

				String latitude = itemCursor
						.getString(DatabaseHandler.LATITUDE_COLUMN);
				String longitude = itemCursor
						.getString(DatabaseHandler.LONGITUDE_COLUMN);
				String date = itemCursor
						.getString(DatabaseHandler.DATE_COLUMN);
				
				latitudeList.add(latitude);
				longitudeList.add(longitude);
				dateList.add(date);
				
			} while (itemCursor.moveToNext());
		}
		
		int n = dateList.size();

		float[] latitude = new float[n];
		float[] longitude = new float[n];
		String[] date = new String[n];
		

		for (int i = 0; i < n; i++) {

			latitude[i] = Float.valueOf(latitudeList.get(i));
			longitude[i] = Float.valueOf(longitudeList.get(i));
			date[i] = dateList.get(i);
			
		}

		SpecialAdapter adapter = new SpecialAdapter(this, latitude, longitude, date);
		listV.setAdapter(adapter);
	}

}
