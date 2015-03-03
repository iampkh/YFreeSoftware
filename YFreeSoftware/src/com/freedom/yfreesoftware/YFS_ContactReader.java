package com.freedom.yfreesoftware;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.GetChars;
import android.util.Log;

public class YFS_ContactReader {
	
	
	
	/**
	 * Reading the contact details and storing to database
	 */
	public static void readAndStoreContacts(Context context) {
		Cursor cursor=getContactCursor(context);
		if(cursor.getCount()>0){  						//contact count greater than zero loop operation should perform
			cursor.moveToFirst();  						//moving the pointer of cursor to the first position
			
			while(cursor.moveToNext()){					//loop operation to read all the contacts
				
				/**
				 * id   :reading the contact unique id ,of current cursor position
				 * name :reading the contact name of the current cursor position
				 * HAS_PHONE_NUMBER : returns 1 if contact is greater than zero
				 *                    returns 0 if no contact is presents
				 * 
				 */
				 String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				 String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				 String phone="";
				 if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					 ContentResolver contentResolver =context.getContentResolver();
					 phone=YFS_ContactReader.getPhoneNumbers(contentResolver,id);
					
				}
				 
				 Log.e("pkhtag_Cursor", "Entering name="+name+" = phone="+phone);
				 
				 YFS_ContactReader.insertContactInDB(id, name, phone);
				 
			}
		}
 	
	}
	
	/**
	 * @param id
	 * @param name
	 * @param phone
	 * 
	 * inserting the contact to the database 
	 */
	private static void insertContactInDB(String id,String name,String phone) {
		ContentValues contentValues=new ContentValues();
		contentValues.put(YFS_DataBase.CONTACT_KEY_ID, id);
		contentValues.put(YFS_DataBase.CONTACT_NAME,name);
		contentValues.put(YFS_DataBase.CONTACT_NUMBER, phone);
		if(!phone.replace(" ", "").replace(",", "").equalsIgnoreCase("")){
			YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB.addDataToDatabase(YFS_DataBase.CONTACT_READER_TABLE_NAME, contentValues); 
			//Log.e("pkhtag", "contact id="+id+" name="+name+" \n number="+phone+"\n");
		}
		

	}
	
	/**
	 * return contact numbers
	 * @param contentResolver
	 * @param id
	 * @return contact numbers seperating with comma (,)
	 */
	private static String getPhoneNumbers(ContentResolver contentResolver,String id) {
		// TODO Auto-generated method stub
		Cursor pCur = contentResolver.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ " = ?", new String[] { id }, null);
		String ph="";
		pCur.moveToFirst();
		while(pCur.moveToNext()){
//			ph+=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			ph+=pCur.getString(pCur.getColumnIndex(Phone.NUMBER));
			
			ph+=",";
			
		}
		
		pCur.close();
		return ph;
	}
	
	/**
	 * contact reader,return the cursor for the contacts
	 * @return Cursor
	 */
	private static Cursor getContactCursor(Context context) {
		// TODO Auto-generated method stub
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
		
		return cursor;

	}
	
	
}
