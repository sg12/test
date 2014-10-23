package internet.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	private static final String DATABASE_NAME = "testBD";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_TABLE_Person = "Person";

	public static final String KEY_PersonID = "PersonID";
	public static final String KEY_PersonFirstName = "PersonFirstName";
	public static final String KEY_PersonLastName = "PersonLastName";
	public static final String KEY_PersonDateBirth = "PersonDateBirth";
	public static final String KEY_PersonAddress = "PersonAddress";

	private static final String DATABASE_CREATE_Person = "create table "
			+ DATABASE_TABLE_Person + " (" + KEY_PersonID
			+ " integer primary key autoincrement, " + KEY_PersonFirstName
			+ " text, " + KEY_PersonLastName + " text, " + KEY_PersonDateBirth
			+ " text, " + KEY_PersonAddress + " text);";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_Person);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	public SQLiteDatabase GetDataBaseOpen() throws SQLException {
		return db;
	}

	public DBAdapter Open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public DBAdapter OpenForRead() throws SQLException {
		db = DBHelper.getReadableDatabase();
		return this;
	}

	public void Close() {
		DBHelper.close();
	}

	public long InsertPerson(String PersonFirstName, String PersonLastName,
			String PersonDateBirth, String PersonAddress) {

		ContentValues params = new ContentValues();
		params.put(KEY_PersonFirstName, PersonFirstName);
		params.put(KEY_PersonLastName, PersonLastName);
		params.put(KEY_PersonDateBirth, PersonDateBirth);
		params.put(KEY_PersonAddress, PersonAddress);

		return db.insert(DATABASE_TABLE_Person, null, params);
	}

	public Cursor GetPersonList() {
		return db.query(DATABASE_TABLE_Person, new String[] { KEY_PersonID,
				KEY_PersonFirstName, KEY_PersonLastName, KEY_PersonDateBirth,
				KEY_PersonAddress }, null, null, null, null, KEY_PersonLastName
				+ " ASC");
	}

	public Cursor GetPerson(int id) {
		return db.query(DATABASE_TABLE_Person, new String[] { KEY_PersonID,
				KEY_PersonFirstName, KEY_PersonLastName, KEY_PersonDateBirth,
				KEY_PersonAddress }, KEY_PersonID + "=" + id, null, null, null,
				KEY_PersonLastName + " ASC");
	}

	public boolean UpdatePerson(int PersonID, String PersonFirstName,
			String PersonLastName, String PersonDateBirth, String PersonAddress) {
		ContentValues params = new ContentValues();
		params.put(KEY_PersonFirstName, PersonFirstName);
		params.put(KEY_PersonLastName, PersonLastName);
		params.put(KEY_PersonDateBirth, PersonDateBirth);
		params.put(KEY_PersonAddress, PersonAddress);
		return db.update(DATABASE_TABLE_Person, params, KEY_PersonID
				+ "=" + PersonID, null) > 0;
	}

	public boolean DeletePerson(int PersonID) {
		return db.delete(DATABASE_TABLE_Person, KEY_PersonID + "=" + PersonID,
				null) > 0;
	}

	public static final String KEY_ActivityCategoryID = "ActivityCategoryID";
	public static final String KEY_ActivityCategoryName = "ActivityCategoryName";
	public static final String KEY_ActivityImageName = "ActivityImageName";
	public static final String KEY_ActivityVersion = "ActivityVersion";
	public static final String KEY_ActivityNeedUpdate = "ActivityNeedUpdate";

	public static final String KEY_UtilityName = "UtilityName";
	public static final String KEY_UtilityValue = "UtilityValue";

	private static final String TAG = "DBAdapter";

	private static final String DATABASE_TABLE_ActivityCategory = "ActivityCategory";
	private static final String DATABASE_TABLE_Utility = "Utility";

	private static final String ActivityVersion = "ActivityCategory";

	private static final String DATABASE_CREATE_ActivityCategory = "create table "
			+ DATABASE_TABLE_ActivityCategory
			+ " ("
			+ KEY_ActivityCategoryID
			+ " integer primary key autoincrement, "
			+ KEY_ActivityCategoryName
			+ " text not null, "
			+ KEY_ActivityImageName
			+ " text not null, "
			+ KEY_ActivityVersion
			+ " integer, "
			+ KEY_ActivityNeedUpdate
			+ " integer);";
	private static final String DATABASE_CREATE_Utility = "create table "
			+ DATABASE_TABLE_Utility + " (" + KEY_UtilityName + " text, "
			+ KEY_UtilityValue + " text);";

	// private static class DatabaseHelper extends SQLiteOpenHelper {
	// DatabaseHelper(Context context) {
	// super(context, DATABASE_NAME, null, DATABASE_VERSION);
	// }
	//
	// @Override
	// public void onCreate(SQLiteDatabase db) {
	// db.execSQL(DATABASE_CREATE_ActivityCategory);
	// db.execSQL(DATABASE_CREATE_Utility);
	//
	// String utilityName = "UpdateID";
	// String utilityValue = "0";
	// ContentValues initialValues1 = new ContentValues();
	// initialValues1.put(KEY_UtilityName, utilityName);
	// initialValues1.put(KEY_UtilityValue, utilityValue);
	// db.insert(DATABASE_TABLE_Utility, null, initialValues1);
	// }
	//
	// @Override
	// public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	// {
	// }
	// }

	public long InsertActivityCategory(String categoryName,
			String categoryImage, int activityVersion, int activityNeedUpdate) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ActivityCategoryName, categoryName);
		initialValues.put(KEY_ActivityImageName, categoryImage);
		initialValues.put(KEY_ActivityVersion, activityVersion);
		initialValues.put(KEY_ActivityNeedUpdate, activityNeedUpdate);

		return db.insert(DATABASE_TABLE_ActivityCategory, null, initialValues);
	}

	public Cursor GetActivityCategoryList() {
		return db.query(DATABASE_TABLE_ActivityCategory, new String[] {
				KEY_ActivityCategoryID, KEY_ActivityCategoryName,
				KEY_ActivityImageName, KEY_ActivityVersion,
				KEY_ActivityNeedUpdate }, null, null, null, null,
				KEY_ActivityCategoryName + " ASC");
	}

	public Cursor GetUpdateDB() {
		return db.query(DATABASE_TABLE_Utility,
				new String[] { KEY_UtilityValue }, "(" + KEY_UtilityName
						+ "='UpdateID')", null, null, null, null, "1");
	}

	public boolean UpdateActivityCategory(int categoryID, String categoryName,
			String categoryImage, int activityVersion, int activityNeedUpdate) {
		ContentValues args = new ContentValues();
		args.put(KEY_ActivityCategoryName, categoryName);
		args.put(KEY_ActivityImageName, categoryImage);
		args.put(KEY_ActivityVersion, activityVersion);
		args.put(KEY_ActivityNeedUpdate, activityNeedUpdate);
		return db.update(DATABASE_TABLE_ActivityCategory, args,
				KEY_ActivityCategoryID + "=" + categoryID, null) > 0;
	}

	public boolean DeleteAllActivityCategory() {
		return db.delete(DATABASE_TABLE_ActivityCategory, null, null) > 0;
	}

	public boolean UpdateDBVersion(String versionID) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_UtilityValue, versionID);
		return db.update(DATABASE_TABLE_Utility, initialValues, KEY_UtilityName
				+ "='UpdateID'", null) > 0;
	}

	public boolean DeleteActivityCategory(int activityCategoryID) {
		return db.delete(DATABASE_TABLE_ActivityCategory,
				KEY_ActivityCategoryID + "=" + activityCategoryID, null) > 0;
	}
}
