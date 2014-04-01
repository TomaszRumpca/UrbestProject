package lemur.urbest.urbestproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class Tracker extends Service {

	private final Context mContext;

	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;

	Location location;
	double latitude;
	double longitude;

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	protected LocationManager locationManager;

	public Tracker(Context context) {
		this.mContext = context;
		GetLowAccurracyLocation();
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

	@Override
	public void onCreate() {

		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
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

	public void GetLowAccurracyLocation() {

		Location location = null;
		locationManager = null;
		String provider;

		try {
			locationManager = (LocationManager) mContext
					.getSystemService(Context.LOCATION_SERVICE);

		} catch (NullPointerException e) {
			Log.e("Tracker", "Error: " + e.getMessage());
		}

		try {
			provider = locationManager.getBestProvider(createFineCriteria(),
					false);

			Log.i("Tracker", "Choosen provider: " + provider);

			location = locationManager.getLastKnownLocation(provider);

			// onLocationChanged(location);

			locationManager.requestLocationUpdates(provider,
					MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,
					new LocationListener() {
						@Override
						public void onLocationChanged(Location location) {
							storeCurrentLocation(location);
							Log.i("onLocationChanged",
									"Location changed to latitude: "
											+ location.getLatitude()
											+ " longitude: "
											+ location.getLongitude());
						}

						@Override
						public void onProviderDisabled(String provider) {
							Log.i("Tracker", "Provider " + provider
									+ " - disabled ");

							if (!locationManager.isProviderEnabled(provider)
									&& provider
											.equals(LocationManager.GPS_PROVIDER)) {
								showGPSSettingsAlert();
							}

						}

						@Override
						public void onProviderEnabled(String provider) {
							Log.i("Tracker", "Provider " + provider
									+ " - enabled ");
						}

						@Override
						public void onStatusChanged(String provider,
								int status, Bundle extras) {
							Log.i("Tracker", "StatusChanged: " + provider
									+ " status: " + status);
						}
					});

		} catch (NullPointerException e) {
			Log.e("Tracker", "Error2: " + e.getMessage());
		}

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
						mContext.startActivity(new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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

}
