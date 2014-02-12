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
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "database.db";
	private static final String DB_TABLE = "Locations";
	
	private static final String KEY_ID = "_id";
	private static final String ID_OPTION = "INTEGER PRIMARY KEY AUTOINCREMENT";
	private static final String KEY_DATE = "date";
	private static final String	DATE_OPTION = "TEXT NOT NULL";
	private static final String KEY_LATITUDE = "latitude";
	private static final String LATITUDE_OPTION = "REAL NOT NULL";
	private static final String KEY_LONGITUDE = "longitude";
	private static final String LONGITUDE_OPTION = "REAL NOT NULL";
	
	private static final String DB_CREATE = "create table " + DB_TABLE + " ("
			+ KEY_ID + " " + ID_OPTION + ", " + KEY_LATITUDE + " " + LATITUDE_OPTION + ", "
			+ KEY_LONGITUDE + " " + LONGITUDE_OPTION + ", " + KEY_DATE + " " + DATE_OPTION  + ");";
	
	
	
	private SQLiteDatabase db;
	private final Context context;
	private DatabaseHelper myDbHelper;
	
	public DatabaseHandler(Context _context){
		context = _context;
		myDbHelper = new DatabaseHelper(_context, DB_NAME, null, DB_VERSION);
	}
	
	public DatabaseHandler open(){
		db = myDbHelper.getWritableDatabase();
		return this;
	}
	
	public long insert(float latitude, float longitude, String date){
		ContentValues cv = new ContentValues();
		cv.put(KEY_LATITUDE,latitude);
		cv.put(KEY_LONGITUDE, longitude);
		cv.put(KEY_DATE, date);
		
		
		return db.insert(DB_TABLE, null, cv);
	}
	
	public Cursor getAllEntries(){
		String[] columns = {KEY_LATITUDE,KEY_LONGITUDE, KEY_DATE};
		return db.query(DB_TABLE, columns, null, null, null, null, KEY_DATE);
	}
	
	public void close(){
		db.close();
	}
	
	public void deleteAll(){
		db.delete(DB_TABLE, "1", null);
	}
	
	public static class DatabaseHelper extends SQLiteOpenHelper {

		
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version){
			super(context, name, factory, version);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVer, int newVer) {
			_db.execSQL("DROP TABLE IF EXIST " + DB_TABLE);
			onCreate(_db);
			
			Log.w("DatabaaseAdapter","Aktualizacja bazy z wersji: "+oldVer+" do: "+newVer+". Wszystkie dane utracone.");
			
		}
		
	}
}
