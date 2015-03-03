package com.freedom.yfreesoftware;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class YFS_ContactListActivity extends Activity {
	/**
	 * declaration of YFS_Database to fetch the stored contact list 
	 */
	 YFS_DataBase sdb;
	 ArrayList<ContactInfo> arraylistContacts=new ArrayList<YFS_ContactListActivity.ContactInfo>();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    setContentView(R.layout.yfs_list_activity);
	     sdb=new YFS_DataBase(getApplicationContext());
	     /**
	      * opening database (ie) making the database to read and write
	      */
	    sdb.openDataBase();
	    
	    Cursor contactCursor=sdb.queryDataBase(YFS_DataBase.CONTACT_READER_TABLE_NAME);
	   /**
	    * checking the contact read from the database is null ,
	    * if not null then adding the data to arraylist
	    */
	    if(contactCursor!=null){
	    	contactCursor.moveToFirst();
	    	while(contactCursor.moveToNext()){
	    		ContactInfo contactinfo=new ContactInfo();
	    		String contactName=contactCursor.getString(YFS_DataBase.CONTACT_NAME_INDEX);
	    		String contactNumber=contactCursor.getString(YFS_DataBase.CONTACT_NUMBER_INDEX);
	    		contactinfo.setName(contactName);
	    		contactinfo.setNumber(contactNumber);
	    		arraylistContacts.add(contactinfo);
	    	}
	    }
	    
	    ListView contactLIstview=(ListView) findViewById(R.id.itemListView);
	    ContactAdapter contactAdapter=new ContactAdapter(getApplicationContext(), arraylistContacts);
	    contactLIstview.setAdapter(contactAdapter);
	    contactAdapter.notifyDataSetChanged();
	    
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/**
		 * closing the database once the activity is destroyed
		 */
		sdb.closeDataBase();
		
		
	}
	/**
	 * A Base Adaptor to display two textview 
	 * 
	 * @author pkh
	 *
	 */
	class ContactAdapter extends BaseAdapter{
		ArrayList<ContactInfo> mContactInfo;
		Context context;
		public ContactAdapter(Context context,ArrayList<ContactInfo> contactInfo) {
			mContactInfo=contactInfo;
			this.context=context;
		}
		/**
		 * getCount api to return the size of list displayed to be mentioned,
		 *  In which we should return size of the array so that many data will be displayed
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mContactInfo.size();
		}

		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
/**
 * custom view will be rendered in this function, this api is the key to set our value to the custom list
 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			/**
			 * convertView is inflated to the custom contact list .xml 
			 */
	
				LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=inflater.inflate(R.layout.custom_contact_list, null);
		
			/**
			 * initializing the textview name and number to a textview
			 */
			TextView nameView=(TextView) convertView.findViewById(R.id.contact_name);
			TextView contactView=(TextView) convertView.findViewById(R.id.contact_number);
			/**
			 * setting name and number to textview from contact info list
			 */
			nameView.setText(mContactInfo.get(position).getName());
			contactView.setText(mContactInfo.get(position).getNumber());
			/**
			 * returning the modified view to set to list, if not returning, view will be not displayed
			 */
			return convertView;
		}
		
	}
	
	/**
	 * getter setter class to hold, Name and Number
	 */
	class ContactInfo{
		String name;
		String number;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
	}

}
