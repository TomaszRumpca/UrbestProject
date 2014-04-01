<<<<<<< HEAD
<<<<<<< HEAD
package lemur.urbest.urbestproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MapActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		int k = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());
		Log.i("GooglePlayServicesUtil", "isGooglePlayServicesAvailable: " + k);
		// Log.i("GooglePlayServicesUtil","GOOGLE_PLAY_SERVICES_VERSION_CODE: "+GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
		// Log.i("GooglePlayServicesUtil","GOOGLE_PLAY_STORE_PACKAGE: "+GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);

		GoogleMap map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		map.setMyLocationEnabled(true);

		//map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(
		//		"Zadanie 1"));

		
		//Tracker gps = new Tracker(this);
		startService(new Intent(this, Tracker2.class));
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		MenuInflater menuInflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.showList:
			Intent intent = new Intent(getApplicationContext(), ListV.class);
			startActivity(intent);
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;

	}


}
=======
package lemur.urbest.urbestproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
=======
package lemur.urbest.urbestproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
>>>>>>> better

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MapActivity extends FragmentActivity implements OnMarkerDragListener{
=======
public class MapActivity extends FragmentActivity implements
		OnMarkerDragListener {
>>>>>>> better

	GoogleMap map;
	private int mode;
	private Polygon investmentAreas;
<<<<<<< HEAD
	public int numberOfMarkers=0;
	Button info, answer;
	Context context;
	String popupText="Informacja";
	String answerCorrect="";
	
=======
	public int numberOfMarkers = 0;
	Button info, answer;
	Context context;
	String popupText = "Informacja";
	String answerCorrect = "";

>>>>>>> better
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
<<<<<<< HEAD
		info = (Button)findViewById(R.id.mapInfoButton);
		answer = (Button)findViewById(R.id.mapAnswerButton);
		info.setText("Info");
		answer.setText("OdpowiedŸ");
		
		context = this;
		info.setOnClickListener(new OnClickListener() {
			
=======
		info = (Button) findViewById(R.id.mapInfoButton);
		answer = (Button) findViewById(R.id.mapAnswerButton);
		info.setText("Info");
		answer.setText("Odpowiedï¿½");

		context = this;
		info.setOnClickListener(new OnClickListener() {

>>>>>>> better
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopUp popup = new PopUp();
				popup.ShowDialog(context, popupText);
<<<<<<< HEAD
				
			}
		});
		
		answer.setOnClickListener(new OnClickListener() {
			
=======

			}
		});

		answer.setOnClickListener(new OnClickListener() {

>>>>>>> better
			@Override
			public void onClick(View v) {
				PopUp popup = new PopUp();
				popup.ShowAnswer(context, popupText, answerCorrect);
<<<<<<< HEAD
				
			}
		});
		
		Bundle bundle = getIntent().getExtras();
		mode = bundle.getInt("mode");
		Log.d("Map onCreate","mode: "+mode);
=======

			}
		});

		Bundle bundle = getIntent().getExtras();
		mode = bundle.getInt("mode");
		Log.d("Map onCreate", "mode: " + mode);
>>>>>>> better
		int googlePlayServicesAviable = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());
		Log.i("GooglePlayServicesUtil", "isGooglePlayServicesAvailable: "
				+ googlePlayServicesAviable);
		// Log.i("GooglePlayServicesUtil","GOOGLE_PLAY_SERVICES_VERSION_CODE: "+GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
		// Log.i("GooglePlayServicesUtil","GOOGLE_PLAY_STORE_PACKAGE: "+GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);

		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		map.setMyLocationEnabled(true);
		map.setOnMarkerDragListener(this);
	}

	@Override
	protected void onStart() {

		startService(new Intent(this, Tracker2.class));

		CameraPosition cameraPosition = new CameraPosition.Builder()
<<<<<<< HEAD
				.target(new LatLng(54.407396, 18.591902))
						.zoom(12) 
						.bearing(0) // Sets the orientation of the camera to east
						.tilt(30) // Sets the tilt of the camera to 30 degrees
						.build(); // Creates a CameraPosition from the builder
=======
				.target(new LatLng(54.407396, 18.591902)).zoom(12).bearing(0) // Sets
																				// the
																				// orientation
																				// of
																				// the
																				// camera
																				// to
																				// east
				.tilt(30) // Sets the tilt of the camera to 30 degrees
				.build(); // Creates a CameraPosition from the builder
>>>>>>> better
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		createFunAreaOnMap();

<<<<<<< HEAD
		switch(mode){
			case 0:{
				
				break;
			}
			case 1:{
				ActiveTaskOne();
				break;
			}
			case 2:{
				
				
				popupText = getResources().getString(R.string.info_sculptures);
				restoreMarkers();
				
				map.setOnMapLongClickListener(new OnMapLongClickListener() {
					
					@Override
					public void onMapLongClick(LatLng point) {		
						
						if(numberOfMarkers<6){
							MarkerOptions newMarker = new MarkerOptions().position(point);
							Marker marker = map.addMarker(newMarker);
							
							marker.setDraggable(true);
							
							Saver saver = new Saver();
							saver.saveMarkSculptures(getApplicationContext(), marker.getId(), marker.getPosition(), DatabaseHandler.KEY_MARKER_ID);
							numberOfMarkers++;
							
						}else{
							Toast.makeText(getApplicationContext(), "Nie ma wiêcej rzêŸb. Przesuñ znaczniki w nowe miejsca", Toast.LENGTH_LONG).show();
						}
					}
				});
				break;
			}
			default:{
				break;
			}
				
		}
		
=======
		switch (mode) {
		case 0: {

			break;
		}
		case 1: {
			ActiveTaskOne();
			break;
		}
		case 2: {

			popupText = getResources().getString(R.string.info_sculptures);
			restoreMarkers();

			map.setOnMapLongClickListener(new OnMapLongClickListener() {

				@Override
				public void onMapLongClick(LatLng point) {

					if (numberOfMarkers < 6) {
						MarkerOptions newMarker = new MarkerOptions()
								.position(point);
						Marker marker = map.addMarker(newMarker);

						marker.setDraggable(true);

						Saver saver = new Saver();
						saver.saveMarkSculptures(getApplicationContext(),
								marker.getId(), marker.getPosition(),
								DatabaseHandler.KEY_MARKER_ID);
						numberOfMarkers++;

					} else {
						Toast.makeText(
								getApplicationContext(),
								"Nie ma wiï¿½cej rzï¿½b. Przesuï¿½ znaczniki w nowe miejsca",
								Toast.LENGTH_LONG).show();
					}
				}
			});
			break;
		}
		default: {
			break;
		}

		}

>>>>>>> better
		// map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(
		// "Zadanie 1"));

		// Tracker gps = new Tracker(this);
		super.onStart();
	}

<<<<<<< HEAD

	private void restoreMarkers(){

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		
		db.open();
		Cursor markerCursor = db.getAllEntries(DatabaseHandler.MARKER_TABLE);
		
		if (markerCursor.moveToFirst()) {

			do {
				
				LatLng position = new LatLng(
						markerCursor.getDouble(DatabaseHandler.MARKER_LATITUDE_COLUMN), 
						markerCursor.getDouble(DatabaseHandler.MARKER_LONGITUDE_COLUMN));
				
				MarkerOptions newMarker = new MarkerOptions().position(position);
				Marker marker = map.addMarker(newMarker);
				marker.setDraggable(true);
				
			} while (markerCursor.moveToNext());
		}
		
		numberOfMarkers = markerCursor.getCount();
		db.close();
		
	}
=======
	private void restoreMarkers() {

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());

		db.open();
		Cursor markerCursor = db.getAllEntries(DatabaseHandler.MARKER_TABLE);

		if (markerCursor.moveToFirst()) {

			do {

				LatLng position = new LatLng(
						markerCursor
								.getDouble(DatabaseHandler.MARKER_LATITUDE_COLUMN),
						markerCursor
								.getDouble(DatabaseHandler.MARKER_LONGITUDE_COLUMN));

				MarkerOptions newMarker = new MarkerOptions()
						.position(position);
				Marker marker = map.addMarker(newMarker);
				marker.setDraggable(true);

			} while (markerCursor.moveToNext());
		}

		numberOfMarkers = markerCursor.getCount();
		db.close();

	}

>>>>>>> better
	private void createFunAreaOnMap() {

		Polygon polygon = map.addPolygon(new PolygonOptions()
				.add(new LatLng(54.423561, 18.567834),
						new LatLng(54.423412, 18.570817),
						new LatLng(54.427656, 18.586245),
						new LatLng(54.422887, 18.594184),
						new LatLng(54.410408, 18.619751),
						new LatLng(54.407086, 18.615030),
						new LatLng(54.400703, 18.622734),
						new LatLng(54.388636, 18.608789),
						new LatLng(54.384257, 18.600871),
						new LatLng(54.395733, 18.582246),
						new LatLng(54.406843, 18.572051),
						new LatLng(54.423561, 18.567834),
						new LatLng(54.423561, 18.567834))
				.strokeColor(Color.GRAY).strokeWidth(5.0f));

		polygon.setVisible(true);
	}

<<<<<<< HEAD

	
	@Override
	protected void onPause() {
		try{
			investmentAreas.remove();
		}catch(NullPointerException e){
			
=======
	@Override
	protected void onPause() {
		try {
			investmentAreas.remove();
		} catch (NullPointerException e) {

>>>>>>> better
		}
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void ActiveTaskOne() {

		investmentAreas = map.addPolygon(new PolygonOptions()
				.add(new LatLng(54.397132, 18.581040),
						new LatLng(54.397595, 18.582328),
						new LatLng(54.397007, 18.583122),
						new LatLng(54.398394, 18.585375),
						new LatLng(54.399293, 18.588314),
						new LatLng(54.399880, 18.592585),
						new LatLng(54.399256, 18.592627),
						new LatLng(54.398744, 18.591962),
						new LatLng(54.398431, 18.591748),
						new LatLng(54.396233, 18.587091),
						new LatLng(54.395159, 18.585053),
						new LatLng(54.394384, 18.585847),
						new LatLng(54.394759, 18.586769),
						new LatLng(54.393634, 18.587628),
						new LatLng(54.392660, 18.588658),
						new LatLng(54.391661, 18.590310),
						new LatLng(54.390798, 18.590052),
						new LatLng(54.397132, 18.581040))
				.strokeColor(Color.RED).strokeWidth(4.0f));

		investmentAreas.setVisible(true);
	}

	@Override
	public void onMarkerDrag(Marker marker) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		
=======

>>>>>>> better
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
<<<<<<< HEAD
		 String markerId = marker.getId();
		 LatLng markerPosition = marker.getPosition();
		 Log.d("MapActivity","onMarkerDragEnd");
		 //TODO update marker position
=======
		String markerId = marker.getId();
		LatLng markerPosition = marker.getPosition();
		Log.d("MapActivity", "onMarkerDragEnd");
		// TODO update marker position
>>>>>>> better
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		
	}

	

}
>>>>>>> better
=======

	}

}
>>>>>>> better
