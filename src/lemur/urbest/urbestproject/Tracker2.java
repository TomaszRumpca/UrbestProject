package lemur.urbest.urbestproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
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
	private LocationManager locationManager;
	private String provider;
	private GpsStatus gpsStatus;

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;// = 10; // 10
																	// meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("Tracker2", "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();

		locationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);

		// provider = locationManager.getBestProvider(createCriteria(), true);

		provider = LocationManager.GPS_PROVIDER;

		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			storeCurrentLocation(location);
		}

		locationManager.requestLocationUpdates(provider, MIN_TIME_BW_UPDATES,
				0, this);

		locationManager.addGpsStatusListener(this);

	}

	@Override
	public void onLocationChanged(Location location) {
		Log.i("onLocationChanged",
				"Location changed to latitude: " + location.getLatitude()
						+ " longitude: " + location.getLongitude());

		storeCurrentLocation(location);

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

		this.provider = locationManager.getBestProvider(createCriteria(), true);

		locationManager.requestLocationUpdates(provider,
				MIN_DISTANCE_CHANGE_FOR_UPDATES, MIN_TIME_BW_UPDATES, this);

		Log.i("Tracker2", "StatusChanged: " + provider + " status: " + status
				+ ", new provider: " + this.provider);
	}

	@Override
	public void onGpsStatusChanged(int event) {

		if (event == GpsStatus.GPS_EVENT_STARTED) {
			Log.d("Tracker2 DEBUG", "GPS event started ");
			locationManager.getGpsStatus(null);

		} else if (event == GpsStatus.GPS_EVENT_STOPPED) {
			Log.d("Tracker2 DEBUG", "GPS event stopped ");
			gpsStatus = locationManager.getGpsStatus(null);
			Log.d("Tracker2 DEBUG", String.valueOf(gpsStatus));

		} else if (event == GpsStatus.GPS_EVENT_FIRST_FIX) {
			Log.d("Tracker2 DEBUG", "GPS fixace ");

		} else if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
			gpsStatus = locationManager.getGpsStatus(gpsStatus);
			Iterable<GpsSatellite> satelites = gpsStatus.getSatellites();

			for (GpsSatellite satelite : satelites) {
				Log.d("Tracker2 DEBUG", satelite.toString());
			}
			Log.d("Tracker2 DEBUG",
					"GPS EVET NECO :" + gpsStatus.getTimeToFirstFix());

		}

	}

	private void storeCurrentLocation(Location location) {

		DatabaseHandler db = new DatabaseHandler(mContext);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.getDefault());
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
