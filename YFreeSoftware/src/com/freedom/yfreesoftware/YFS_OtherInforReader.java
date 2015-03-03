package com.freedom.yfreesoftware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Browser;
import android.telephony.TelephonyManager;
import android.util.Log;


public class YFS_OtherInforReader  {
	
	/**
	 * readBrowserHistory : to read the browser title and url
	 * 
	 * reference :http://stackoverflow.com/questions/2577084/android-read-browser-history
	 * @param context
	 */
	public static ArrayList<String> readBrowserHistory(Context context) {
		String[] proj = new String[] { Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };
		String sel = Browser.BookmarkColumns.BOOKMARK + " = 0"; // 0 = history, 1 = bookmark
		Cursor mCur = context.getContentResolver().query(Browser.BOOKMARKS_URI, proj, sel, null, null);
		mCur.moveToFirst();
		@SuppressWarnings("unused")
		String title = "";
		@SuppressWarnings("unused")
		String url = "";
		ArrayList<String> urlList=new ArrayList<String>();
		if (mCur.moveToFirst() && mCur.getCount() > 0) {
		    boolean cont = true;
		    while (mCur.isAfterLast() == false && cont) {
		        title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
		        url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));
		        // Do something with title and url
		        Log.e("BrowserHistory", "Url :"+url);
		        urlList.add(url);
		        mCur.moveToNext();
		    }
		}
		return urlList;

	}
	/**
	 * readBrowserBookMarks : to read broswed bookmarks title and url
	 * 
	 * reference :http://stackoverflow.com/questions/2577084/android-read-browser-history
	 * @param context
	 */
	public static ArrayList<String> readBrowserBookMarks(Context context) {
		

		String[] proj = new String[] { Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };
		String sel = Browser.BookmarkColumns.BOOKMARK + " = 1"; // 0 = history, 1 = bookmark
		Cursor mCur = context.getContentResolver().query(Browser.BOOKMARKS_URI, proj, sel, null, null);
		mCur.moveToFirst();
		@SuppressWarnings("unused")
		String title = "";
		@SuppressWarnings("unused")
		String url = "";
		ArrayList<String> urlList=new ArrayList<String>();
		if (mCur.moveToFirst() && mCur.getCount() > 0) {
		    boolean cont = true;
		    while (mCur.isAfterLast() == false && cont) {
		        title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
		        url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));
		        // Do something with title and url
		        urlList.add(url);
		        mCur.moveToNext();
		    }
		}
		return urlList;
	}
	
	/**
	 *returns list of installed browsers or browser based application
	 * @param context
	 * @return
	 */
	public static List<String> getInstalledBrowsers(Context context) {
		  PackageManager packageManager = context.getPackageManager();
		    Intent intent = new Intent(Intent.ACTION_VIEW);
		    intent.setData(Uri.parse("http://www.google.com"));
		    List<String> browserLIst=new ArrayList<String>();
		    List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
		            PackageManager.MATCH_DEFAULT_ONLY);
		            for (ResolveInfo info : list) {
		                String name = info.activityInfo.name;
		                Log.e("BrowserName", "Name: "+name);
		                browserLIst.add(name);
		            }

		    return browserLIst;
	}

	/**
	 * Read mobile model
	 */
	public static String getDeviceName() {
	    final String manufacturer = Build.MANUFACTURER;
	    final String model = Build.MODEL;
	    if (model.startsWith(manufacturer)) {
	        return model;
	    }
	    if (manufacturer.equalsIgnoreCase("HTC")) {
	        // make sure "HTC" is fully capitalized.
	        return "HTC " + model;
	    }
	    return manufacturer + " " + model;
	}
	/**
	 * returns list of app installed in the device
	 */
	public static List<String> getInstalledApps(Context context) {
	    // TODO Auto-generated method stub
		List<String> installedApp=new ArrayList<String>();
	     PackageManager packageManager=context.getPackageManager();
	        List<ApplicationInfo> applist=packageManager.getInstalledApplications(0);
	        String appnames="";

	        Iterator<ApplicationInfo> it=applist.iterator();
	        while(it.hasNext()){
	            ApplicationInfo pk=(ApplicationInfo)it.next();

	            String appname = packageManager.getApplicationLabel(pk).toString();
	            installedApp.add(appname);
	            //appnames+=appname+"\n";
	            
	        }
	        return installedApp;

	}
	
	/**
	 * @return String: Imedi number of mobile
	 * 
	 */
	public static String getImeiNumber(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}
	
	/**
	 * @return String: Network Operator name  of mobile
	 * 
	 */
	public static String getNetworkOperatorName(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		return telephonyManager.getNetworkOperatorName();
	}
	

	/**
	 * @return String: Mobile number 
	 * 
	 */
	public static String getMobileNumber(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String mob=telephonyManager.getLine1Number();;
		if(mob!=null){
			return mob;
		}else{
			return "no sim card found";
		}
		
	}
	public static int PH_NUMBER=0;
	/**
	 * @return String : name of the mobile user. ( registered mobile phone user name)
	 */
	public static List<String> getUsername(Context context){
	    AccountManager manager = AccountManager.get(context); 
	    //Account[] accounts = manager.getAccountsByType("com.google");
	    Account[] accounts = manager.getAccounts();
	    List<String> possibleEmails = new LinkedList<String>();

	    for (Account account : accounts) {
	      // TODO: Check possibleEmail against an email regex or treat
	      // account.name as an email address only for certain account.type values.
	      possibleEmails.add(account.name);
	     
	    
	      
	    }

	    return possibleEmails;
	  /*  if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
	        String email = possibleEmails.get(0);
	        String[] parts = email.split("@");
	        if(parts.length > 0 && parts[0] != null)
	            return parts[0];
	        else
	            return null;
	    }else*/
	     //   return null;
	}

}
