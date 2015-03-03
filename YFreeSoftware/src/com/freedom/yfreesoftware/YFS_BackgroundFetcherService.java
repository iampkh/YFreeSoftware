package com.freedom.yfreesoftware;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class YFS_BackgroundFetcherService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * onCreate : called once when service is started
	 * (i). Initiating Database
	 * (ii) Opening DataBase to access
	 */
	@Override
	public void onCreate() {
		YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB = new YFS_DataBase(
				getApplicationContext());
		YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB.openDataBase();
		
		super.onCreate();
	}

	/**
	 * onDestroy() Android api called once the service is stopped or destroyed
	 * (i) Closing the database to avoid SqliteDatabase exception
	 */
	@Override
	public void onDestroy() {
		YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB.closeDataBase();
		
		Log.e(YFS_SourceUtil.TAG, "OnDestroy Background Fetcher");
		super.onDestroy();
	}

	/**
	 * onStartCommand Android api :  
	 * (i). Read the Phone contacts and stored in Database
	 *  
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
Log.e("pkh", "OnStartCommand");

	Integer contactCount=YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB.getCountDatabase(YFS_DataBase.CONTACT_READER_TABLE_NAME);
	Integer messageCount=YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB.getCountDatabase(YFS_DataBase.MESSAGE_READER_TABLE_NAME);
	
	Log.e(YFS_SourceUtil.TAG, "Reading Contact="+contactCount+"_Message="+messageCount);
	
	/**
	 * fetching data Only if the contact is not read and stored
	 */
	if(contactCount==null || contactCount==0){
		Log.e(YFS_SourceUtil.TAG, "Reading and Storing Contacts");
		YFS_ContactReader.readAndStoreContacts(getApplicationContext());
		
	}
	/**
	 * fetching message only if the  message is not read and stored
	 */
	if(messageCount==null || messageCount==0){
		Log.e(YFS_SourceUtil.TAG, "Reading and Storing Message");
		YFS_MessageReader.readAndStoreMessages(getApplicationContext());
		
	}	 
	
	
	stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}
}
