package lemur.urbest.urbestproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

public class Saver {

	public Saver() {

	}

	public void saveAchievedPoints(Context context, String taskName,
			float score, String taskColumn) {

		DatabaseHandler db = new DatabaseHandler(context);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.getDefault());
		Date date = new Date();
		String dateStr = dateFormat.format(date);

		String where = taskColumn + " ='" + taskName + "'";

		db.open();

		long result = db.update(dateStr, taskName, score, where, null);

		if (result == 0) {
			db.insert(dateStr, taskName, score);
		}

		db.close();
	}

	public void saveMarkSculptures(Context context, String markerId,
			LatLng marker, String markerColumn) {

		DatabaseHandler db = new DatabaseHandler(context);

		String where = markerColumn + " ='" + markerId + "'";

		db.open();

		long result = db.update(marker.latitude, marker.longitude, where, null);

		if (result == 0) {
			db.insert(markerId, marker.latitude, marker.longitude);
		}

		db.close();

	}

}
