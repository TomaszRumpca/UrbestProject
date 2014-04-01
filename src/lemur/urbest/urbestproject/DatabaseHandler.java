package lemur.urbest.urbestproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler {

	public static final int LATITUDE_COLUMN = 0;
	public static final int LONGITUDE_COLUMN = 1;
	public static final int DATE_COLUMN = 2;

	public static final int DATE_OF_COMPLETION_COLUMN = 0;
	public static final int TAKS_COLUMN = 1;
	public static final int SCORE_COLUMN = 2;

	public static final int MARKER_ID_COLUMN = 0;
	public static final int MARKER_LATITUDE_COLUMN = 1;
	public static final int MARKER_LONGITUDE_COLUMN = 2;

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "urbest.db";
	public static final String LOCATIONS_TABLE = "Locations";
	private static final String SCORE_TABLE = "Scores";
	public static final String MARKER_TABLE = "Markers";

	private static final String KEY_ID = "_id";
	private static final String ID_OPTION = "INTEGER PRIMARY KEY AUTOINCREMENT";
	private static final String KEY_DATE = "date";
	private static final String DATE_OPTION = "TEXT NOT NULL";
	private static final String KEY_LATITUDE = "latitude";
	private static final String LATITUDE_OPTION = "REAL NOT NULL";
	private static final String KEY_LONGITUDE = "longitude";
	private static final String LONGITUDE_OPTION = "REAL NOT NULL";

	private static final String KEY_SCORE_ID = "_id";
	private static final String SCORE_ID_OPTION = "INTEGER PRIMARY KEY AUTOINCREMENT";
	private static final String KEY_DATE_OF_COMPLETION = "date";
	private static final String DATE_OF_COMPLETION_OPTION = "TEXT NOT NULL";
	private static final String KEY_SCORE = "score";
	private static final String SCORE_OPTION = "REAL NOT NULL";
	public static final String KEY_TASK_NAME = "task";
	private static final String TASK_NAME_OPTION = "TEXT NOT NULL";

	public static final String KEY_MARKER_ID = "_id";
	private static final String MARKER_ID_OPTION = "TEXT NOT NULL";
	private static final String KEY_MARKER_LATITUDE = "latitude";
	private static final String MARKER_LATITUDE_OPTION = "REAL NOT NULL";
	private static final String KEY_MARKER_LONGITUDE = "longitude";
	private static final String MARKER_LONGITUDE_OPTION = "REAL NOT NULL";

	private static final String CREATE_LOCATION_TABLE = "create table "
			+ LOCATIONS_TABLE + " (" + KEY_ID + " " + ID_OPTION + ", "
			+ KEY_LATITUDE + " " + LATITUDE_OPTION + ", " + KEY_LONGITUDE + " "
			+ LONGITUDE_OPTION + ", " + KEY_DATE + " " + DATE_OPTION + ");";

	private static final String CREATE_SCORE_TABLE = "create table "
			+ SCORE_TABLE + " (" + KEY_SCORE_ID + " " + SCORE_ID_OPTION + ", "
			+ KEY_DATE_OF_COMPLETION + " " + DATE_OF_COMPLETION_OPTION + ", "
			+ KEY_TASK_NAME + " " + TASK_NAME_OPTION + ", " + KEY_SCORE + " "
			+ SCORE_OPTION + ");";

	private static final String CREATE_MARKERS_TABLE = "create table "
			+ MARKER_TABLE + " (" + KEY_MARKER_ID + " " + MARKER_ID_OPTION
			+ ", " + KEY_MARKER_LATITUDE + " " + MARKER_LATITUDE_OPTION + ", "
			+ KEY_MARKER_LONGITUDE + " " + MARKER_LONGITUDE_OPTION + ");";

	private SQLiteDatabase db;
	private final Context context;
	private DatabaseHelper myDbHelper;

	public DatabaseHandler(Context _context) {
		context = _context;
		myDbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
	}

	public DatabaseHandler open() {
		db = myDbHelper.getWritableDatabase();
		return this;
	}

	public long insert(float latitude, float longitude, String date) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_LATITUDE, latitude);
		cv.put(KEY_LONGITUDE, longitude);
		cv.put(KEY_DATE, date);

		return db.insert(LOCATIONS_TABLE, null, cv);
	}

	public long insert(String date, String task, float score) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_DATE_OF_COMPLETION, date);
		cv.put(KEY_TASK_NAME, task);
		cv.put(KEY_SCORE, score);

		return db.insert(SCORE_TABLE, null, cv);
	}

	public long update(String date, String task, float score,
			String whereClause, String[] whereArgs) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_DATE_OF_COMPLETION, date);
		cv.put(KEY_TASK_NAME, task);
		cv.put(KEY_SCORE, score);

		return db.update(SCORE_TABLE, cv, whereClause, whereArgs);
	}

	public long insert(String id, double latitude, double longitude) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_MARKER_ID, id);
		cv.put(KEY_MARKER_LATITUDE, latitude);
		cv.put(KEY_MARKER_LONGITUDE, longitude);

		return db.insert(MARKER_TABLE, null, cv);
	}

	public long update(double latitude, double longitude, String whereClause,
			String[] whereArgs) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_MARKER_LATITUDE, latitude);
		cv.put(KEY_MARKER_LONGITUDE, longitude);

		return db.update(MARKER_TABLE, cv, whereClause, whereArgs);
	}

	public Cursor getAllEntries(String fromTable) {

		if (fromTable.equals(SCORE_TABLE)) {
			String[] columns = { KEY_DATE_OF_COMPLETION, KEY_TASK_NAME,
					KEY_SCORE };
			return db.query(SCORE_TABLE, columns, null, null, null, null,
					KEY_DATE_OF_COMPLETION);
		} else if (fromTable.equals(LOCATIONS_TABLE)) {
			String[] columns = { KEY_LATITUDE, KEY_LONGITUDE, KEY_DATE };
			return db.query(LOCATIONS_TABLE, columns, null, null, null, null,
					KEY_DATE);
		} else if (fromTable.equals(MARKER_TABLE)) {
			String[] columns = { KEY_MARKER_ID, KEY_MARKER_LATITUDE,
					KEY_MARKER_LONGITUDE };
			return db.query(MARKER_TABLE, columns, null, null, null, null,
					KEY_MARKER_ID);
		} else {
			return null;
		}
	}

	public void close() {
		db.close();
	}

	public void deleteAll() {
		db.delete(LOCATIONS_TABLE, "1", null);
		db.delete(SCORE_TABLE, "1", null);
		db.delete(MARKER_TABLE, "1", null);
	}

	public static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_LOCATION_TABLE);
			db.execSQL(CREATE_SCORE_TABLE);
			db.execSQL(CREATE_MARKERS_TABLE);
			Log.i("database Helper", "stworzono tabele");

		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVer, int newVer) {
			_db.execSQL("DROP TABLE IF EXIST " + LOCATIONS_TABLE);
			_db.execSQL("DROP TABLE IF EXIST " + SCORE_TABLE);
			_db.execSQL("DROP TABLE IF EXIST " + MARKER_TABLE);
			onCreate(_db);

			Log.w("DatabaaseAdapter", "Aktualizacja bazy z wersji: " + oldVer
					+ " do: " + newVer + ". Wszystkie dane utracone.");

		}

	}
}
