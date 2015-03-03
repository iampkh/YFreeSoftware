package com.freedom.yfreesoftware;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class YFS_DataBase {
	public static String Y_FREE_SOFTWARE_DB="YFSDB";
	public static String CONTACT_READER_TABLE_NAME="contactReaderTable";
	public static String IMAGE_READER_TABLE_NAME="imageReaderTable";
	public static String BROWER_HISTORY_READER_TABLE_NAME="broswerReaderTable";
	public static String MESSAGE_READER_TABLE_NAME="messageReaderTable";
	
	public static String CONTACT_KEY_ID="CONTACT_KEY_ID";
	public static String CONTACT_NAME="CONTACT_NAME";
	public static String CONTACT_NUMBER="CONTACT_NUMBER";
	
	public static String MESSAGE_KEY_ID="MESSAGE_KEY_ID";
	public static String MESSAGE_NAME="MESSAGE_NAME";
	public static String MESSAGE_NUMBER="MESSAGE_NUMBER";
	public static String MESSAGE_SUBJECT="MESSAGE_SUBJECT";
	public static String MESSAGE_TYPE="MESSAGE_TYPE";
	public static String MESSAGE_BODY="MESSAGE_BODY";
	
	public static String BROWSER_KEY_ID="BROWSER_KEY_ID";
	public static String BROWSER_NAME="BROWSER_NAME";
	
	
	public static String IMAGE_KEY_ID="IMAGE_KEY_ID";
	public static String IMAGE_NAME="IMAGE_NAME";
	
	/**
	 * INDEX for the colum specified in database for Contacts
	 */
	public static int CONTACT_ID_INDEX=0;
	public static int CONTACT_NAME_INDEX=1;
	public static int CONTACT_NUMBER_INDEX=2;
	
	/**
	 * INDEX for the column specified for database Mesages
	 */
	public static int MESSAGE_ID_INDEX=0;
	public static int MESSAGE_NAME_INDEX=1;
	public static int MESSAGE_NUMBER_INDEX=2;
	public static int MESSAGE_SUBJECT_INDEX=3;
	public static int MESSAGE_TYPE_INDEX=4;
	public static int MESSAGE_BODY_INDEX=5;
	
	/**
	 * Query string to create database for 
	 * 1.Contact
	 * 2.Message
	 * 3.Browser
	 * 4.Images
	 */
	private static final String CREATE_TABLE_CONTACT = "CREATE TABLE "
            + CONTACT_READER_TABLE_NAME + "(" + CONTACT_KEY_ID + " TEXT," + CONTACT_NAME
            + " TEXT," + CONTACT_NUMBER + " TEXT )";
	
	private static final String CREATE_TABLE_MESSAGE = "CREATE TABLE "
            + MESSAGE_READER_TABLE_NAME + "(" + MESSAGE_KEY_ID + " TEXT," + MESSAGE_NAME
            + " TEXT," + MESSAGE_NUMBER + " TEXT,"+MESSAGE_SUBJECT+" TEXT,"+MESSAGE_TYPE+" TEXT,"+MESSAGE_BODY+ " TEXT )";
	
	private static final String CREATE_TABLE_BROWSER = "CREATE TABLE "
            + BROWER_HISTORY_READER_TABLE_NAME + "(" + BROWSER_KEY_ID + " TEXT," + BROWSER_NAME + " INTEGER )";
	
	private static final String CREATE_TABLE_IMAGES = "CREATE TABLE "
            + IMAGE_READER_TABLE_NAME + "(" + IMAGE_KEY_ID + " TEXT," + IMAGE_NAME+ " INTEGER )";
	
	
	
	
	DataBaseSqlite mDatabaseSqlite;
	SQLiteDatabase mSqliteDataBase;
	
	public YFS_DataBase(Context context) {
		// TODO Auto-generated constructor stub
		mDatabaseSqlite=new DataBaseSqlite(context, Y_FREE_SOFTWARE_DB, null, 1);
	
	}
	/**
	 * Databae api to open : which give access of reading and writing permission to the data for the database
	 */
	public void openDataBase() {
		// TODO Auto-generated method stub
		mSqliteDataBase=mDatabaseSqlite.getWritableDatabase();

	}
	/**
	 * CloseDataBase api to close: which restricts the application to read and write the data to database
	 */
	public void closeDataBase() {
		// TODO Auto-generated method stub
		mDatabaseSqlite.close();
		
	}
	/**
	 * addDataToDatabase : database api to add a data to last row in table
	 * @param tableName  :Table name for where the data to be inserted 
	 * @param contentValues : Content values contains data for the table in key and value pair 
	 */
	public void addDataToDatabase(String tableName,ContentValues contentValues) {
		// TODO Auto-generated method stub
		
		mSqliteDataBase.insert(tableName, null	, contentValues);

	}
	
	/**
	 * getCountDatabaser : database api to get the size of list in table
	 * @param tableName:Table name to specify and get the size of the particular
	 */
	public Integer getCountDatabase(String tableName) {
		// TODO Auto-generated method stub

		return mSqliteDataBase.query(tableName, null, null, null, null, null, null).getCount();
	}
	/**
	 * queryDataBase : database api to get the cursor for the particular table
	 * @param tableName : Table name to retrieve cursor of particular .
	 * @return
	 */
	public Cursor queryDataBase(String tableName) {
		// TODO Auto-generated method stub

		return mSqliteDataBase.query(tableName, null, null, null, null, null, null);
	}
	
	/**
	 * deleteAllData();database api to delete all the table data
	 * 
	 */
	public void deleteAllData(){
		mSqliteDataBase.execSQL("delete from "+ CONTACT_READER_TABLE_NAME);
		mSqliteDataBase.execSQL("delete from "+ BROWER_HISTORY_READER_TABLE_NAME);
		mSqliteDataBase.execSQL("delete from "+ MESSAGE_READER_TABLE_NAME);
		mSqliteDataBase.execSQL("delete from "+ IMAGE_READER_TABLE_NAME);
	}
	
	/**
	 * 
	 * DataBaseSqlite extends SqliteOpenHelper
	 * class used to create the database and updgrade the database
	 * @author pkh
	 *
	 */
	
	class DataBaseSqlite extends SQLiteOpenHelper{

		@SuppressLint("NewApi")
		public DataBaseSqlite(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		/**
		 * executing the query to create database in the applicatino
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE_CONTACT);
			db.execSQL(CREATE_TABLE_MESSAGE);
			db.execSQL(CREATE_TABLE_BROWSER);
			db.execSQL(CREATE_TABLE_IMAGES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
		
	
	}

}
