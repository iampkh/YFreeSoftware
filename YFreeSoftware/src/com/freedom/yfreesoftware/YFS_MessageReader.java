package com.freedom.yfreesoftware;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class YFS_MessageReader {
	public static String Msg_ID="_id";
	public static String Msg_Address="address";
	public static String Msg_body="body";
	public static String Msg_subject="subject";
	public static String Msg_person="person";
	public static Uri URI_Inbox = Uri.parse("content://sms/inbox");
 	
	public static Uri URI_Sentbox = Uri.parse("content://sms/sent");
     
     public static void readAndStoreMessages(Context context){
    	
    	 
    	 /**
    	  * toknow about the colums read the following link
    	  * http://pulse7.net/android/read-sms-message-inbox-sent-draft-android/
    	  */
        
	/**
	 * defining columns requried from the content resolver for reading message
	 */
         String[] requiredColum = new String[] { Msg_ID,Msg_Address,Msg_body,Msg_subject,Msg_person};

     /**
      * get the content resolver of the application
      */
         ContentResolver contentResolver =context.getContentResolver();

      /**
       * Cursor to read the data from the content resolver of inbox and sentbox
       */
         Cursor inboxCursor = contentResolver.query(URI_Inbox, requiredColum, null, null, null);
         Cursor sentBoxCrsor = contentResolver.query(URI_Sentbox, requiredColum, null, null, null);
         
         while(inboxCursor.moveToNext()){
        	 String id=inboxCursor.getString(inboxCursor.getColumnIndexOrThrow(Msg_ID));
        	 String name=inboxCursor.getString(inboxCursor.getColumnIndexOrThrow(Msg_person));
        	 String sub=inboxCursor.getString(inboxCursor.getColumnIndexOrThrow(Msg_subject));
        	 String address=inboxCursor.getString(inboxCursor.getColumnIndexOrThrow(Msg_Address));
        	 String body=inboxCursor.getString(inboxCursor.getColumnIndexOrThrow(Msg_body)); 
        	 
        	 insertMessageInDB(id, name, sub, address, body, "inbox");
         }
        while(sentBoxCrsor.moveToNext()){
        	 String id=sentBoxCrsor.getString(inboxCursor.getColumnIndexOrThrow(Msg_ID));
        	 String name=sentBoxCrsor.getString(inboxCursor.getColumnIndexOrThrow(Msg_person));
        	 String sub=sentBoxCrsor.getString(inboxCursor.getColumnIndexOrThrow(Msg_subject));
        	 String address=sentBoxCrsor.getString(inboxCursor.getColumnIndexOrThrow(Msg_Address));
        	 String body=sentBoxCrsor.getString(inboxCursor.getColumnIndexOrThrow(Msg_body));
        	 
        	 insertMessageInDB(id, name, sub, address, body, "sent");
         }
     	Log.e("pkhtag", "Message Reader function call");
         
     }
     /**
 	 * @param id
 	 * @param name
 	 * @param phone  
 	 * 
 	 * inserting the contact to the database 
 	 */
 	private static void insertMessageInDB(String id,String name,String subject,String address,String body,String type) {
 		ContentValues contentValues=new ContentValues();
 		contentValues.put(YFS_DataBase.MESSAGE_KEY_ID, id);
 		contentValues.put(YFS_DataBase.MESSAGE_NAME,name);
 		contentValues.put(YFS_DataBase.MESSAGE_NUMBER, address);
 		contentValues.put(YFS_DataBase.MESSAGE_SUBJECT, subject);
 		contentValues.put(YFS_DataBase.MESSAGE_TYPE, type);
 		contentValues.put(YFS_DataBase.MESSAGE_BODY, body);
 		
 			YFS_SourceUtil.Y_FREE_SOFTWARE_SQLITE_DB.addDataToDatabase(YFS_DataBase.MESSAGE_READER_TABLE_NAME, contentValues);
 			
 			Log.e("pkhtag", "Message  id="+id+"_name="+name+"_addrss="+address+"_subj="+subject+"_type="+type+"_body="+body);
 		
 		

 	}
}
