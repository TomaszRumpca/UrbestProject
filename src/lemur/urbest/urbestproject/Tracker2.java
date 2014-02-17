package lemur.urbest.urbestproject;

import java.security.acl.LastOwnerException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class Tracker2 extends Service implements LocationListener,
		android.location.GpsStatus.Listener {

	private Context mContext;
	private Location location;
	private LocationManager locationManager;
	private String provider;
	private int gpsStatus;

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 0;// = 1000 * 60 * 1; // 1
														// minute

	public Tracker2() {
		Log.i("Tracker2", "constructor");

	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("Tracker2", "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		mContext = getApplicationContext();

		locationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);

		
		provider = locationManager
				.getBestProvider(createCriteria(), true);

		
		List<String> providers = locationManager.getProviders(true);
		
		int a = providers.size();
		
		while(a>0){
			Log.d("Tracker2 DEBUG","enabled provider: "+providers.get(a-1));
			a--;
		}
		
		//locationManager.requestLocationUpdates(provider,
		//		MIN_DISTANCE_CHANGE_FOR_UPDATES, MIN_TIME_BW_UPDATES, this);

		//location = locationManager.getLastKnownLocation(provider);

		Log.i("Tracker2", "onCreate + choosen provider: " + provider);

		super.onCreate();
		
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.i("onLocationChanged",
				"Location changed to latitude: " + location.getLatitude()
						+ " longitude: " + location.getLongitude());

	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.i("Tracker", "Provider " + provider + " - disabled ");

	}

	
	@Override
	public void onProviderEnabled(String provider) {
		Log.i("Tracker", "Provider " + provider + " - enabled ");

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

		this.provider = locationManager.getBestProvider(createCoarseCriteria(),
				true);

		locationManager.requestLocationUpdates(provider,
				MIN_DISTANCE_CHANGE_FOR_UPDATES, MIN_TIME_BW_UPDATES, this);

		Log.i("Tracker2", "StatusChanged: " + provider + " status: " + status
				+ ", new provider: " + this.provider);
	}

	@Override
	public void onGpsStatusChanged(int event) {
		
		if (event == GpsStatus.GPS_EVENT_STARTED) {
			Log.d("Tracker2 DEBUG", "GPS event started ");
			GpsStatus gs = locationManager.getGpsStatus(null);

		}else if (event == GpsStatus.GPS_EVENT_STOPPED) {
			Log.d("Tracker2 DEBUG", "GPS event stopped ");
			GpsStatus gs = locationManager.getGpsStatus(null);
			Log.d("Tracker2 DEBUG", String.valueOf(gs));

		}else if (event == GpsStatus.GPS_EVENT_FIRST_FIX) {
			Log.d("Tracker2 DEBUG", "GPS fixace ");
			
		}else if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
			Log.d("Tracker2 DEBUG", "GPS EVET NECO ");
			
		}

	}

	private void getLocation() {

	}

	private void storeCurrentLocation(Location location) {

		DatabaseHandler db = new DatabaseHandler(mContext);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateStr = dateFormat.format(date);

		db.open();

		db.insert((float) location.getLatitude(),
				(float) location.getLongitude(), dateStr);

		db.close();
	}

	public void showGPSSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

		alertDialog.setTitle("GPS is settings");

		alertDialog
				.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		// Setting Icon to Dialog
		// alertDialog.setIcon(R.drawable.delete);

		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						mContext.startActivity(intent);
					}
				});

		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		alertDialog.show();
	}

	/** this criteria will settle for less accuracy, high power, and cost */
	public static Criteria createCoarseCriteria() {

		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_COARSE);
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_HIGH);
		return c;

	}

	/** this criteria needs high accuracy, high power, and cost */
	public static Criteria createFineCriteria() {

		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE);
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_HIGH);
		return c;

	}

	public static Criteria createCriteria() {

		Criteria c = new Criteria();
		
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_HIGH);
		return c;

	}
}
