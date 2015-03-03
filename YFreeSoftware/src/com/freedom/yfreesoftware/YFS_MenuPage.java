package com.freedom.yfreesoftware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class YFS_MenuPage extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.yfs_menu_page);
	
	findViewById(R.id.basicInfoBtn).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(getApplicationContext(), YFS_BasicInfoActivity.class));
			
		}
	});
	
	/**
	 * click listener for  application list 
	 */
findViewById(R.id.appBtn).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(getApplicationContext(), YFS_AllApplication.class));
			
		}
	});

/**
 * click listener for Contacts 
 */
findViewById(R.id.contactBtn).setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), YFS_ContactListActivity.class));
	}
});
/**
 * click listener for message 
 */
findViewById(R.id.messageBtn).setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		startActivity(new Intent(getApplicationContext(), YFS_MessageListActivity.class));
		
	}
});
/**
 * click listener for otherInfo btn 
 */
findViewById(R.id.otherInfoBtn).setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
});
	
	
	
	}

}
