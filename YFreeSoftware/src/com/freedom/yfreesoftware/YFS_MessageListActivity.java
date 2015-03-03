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

public class YFS_MessageListActivity extends Activity {
	/**
	 * declaration of YFS_Database to fetch the stored contact list 
	 */
	 YFS_DataBase sdb;
	 ArrayList<MessageInfo> arraylistMessage=new ArrayList<YFS_MessageListActivity.MessageInfo>();
	
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
	    
	    Cursor messageCursor=sdb.queryDataBase(YFS_DataBase.MESSAGE_READER_TABLE_NAME);
	   /**
	    * checking the message read from the database is null ,
	    * if not null then adding the data to arraylist
	    */
	    if(messageCursor!=null){
	    	messageCursor.moveToFirst();
	    	while(messageCursor.moveToNext()){
	    		MessageInfo messageinfo=new MessageInfo();
	    		String messageName=messageCursor.getString(YFS_DataBase.MESSAGE_NAME_INDEX);
	    		String messageNumber=messageCursor.getString(YFS_DataBase.MESSAGE_NUMBER_INDEX);
	    		String messageSubject=messageCursor.getString(YFS_DataBase.MESSAGE_SUBJECT_INDEX);
	    		String messageType=messageCursor.getString(YFS_DataBase.MESSAGE_TYPE_INDEX);
	    		String messageBody=messageCursor.getString(YFS_DataBase.MESSAGE_BODY_INDEX);
	    		
	    		messageinfo.setName(messageName);
	    		messageinfo.setNumber(messageNumber);
	    		messageinfo.setSubject(messageSubject);
	    		messageinfo.setType(messageType);
	    		messageinfo.setBody(messageBody);
	    		
	    		arraylistMessage.add(messageinfo);
	    	}
	    }
	    
	    ListView messageLIstview=(ListView) findViewById(R.id.itemListView);
	    MessageAdapter contactAdapter=new MessageAdapter(getApplicationContext(), arraylistMessage);
	   messageLIstview.setAdapter(contactAdapter);
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
	class MessageAdapter extends BaseAdapter{
		ArrayList<MessageInfo> mMessageInfo;
		Context context;
		public MessageAdapter(Context context,ArrayList<MessageInfo> messageInfo) {
			mMessageInfo=messageInfo;
			this.context=context;
		}
		/**
		 * getCount api to return the size of list displayed to be mentioned,
		 *  In which we should return size of the array so that many data will be displayed
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMessageInfo.size();
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
				convertView=inflater.inflate(R.layout.custom_message_list, null);
		
			/**
			 * initializing the textview name and number to a textview
			 */
			TextView nameView=(TextView) convertView.findViewById(R.id.message_name);
			TextView contactView=(TextView) convertView.findViewById(R.id.message_number);
			TextView typeView=(TextView) convertView.findViewById(R.id.message_Type);
			TextView subjectView=(TextView) convertView.findViewById(R.id.message_subject);
			TextView bodyView=(TextView) convertView.findViewById(R.id.message_body);
			/**
			 * setting name and number to textview from contact info list
			 */
			nameView.setText(mMessageInfo.get(position).getName());
			contactView.setText(mMessageInfo.get(position).getNumber());
			typeView.setText(mMessageInfo.get(position).getType());
			subjectView.setText(mMessageInfo.get(position).getSubject());
			bodyView.setText(mMessageInfo.get(position).getBody());
			/**
			 * returning the modified view to set to list, if not returning, view will be not displayed
			 */
			return convertView;
		}
		
	}
	
	/**
	 * getter setter class to hold, Name and Number
	 */
	class MessageInfo{
		String name;
		String number;
		String body;
		String type;
		String subject;
		
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
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
