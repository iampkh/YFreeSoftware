package com.freedom.yfreesoftware;

import java.util.List;

import com.freedom.yfreesoftware.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class YFS_BasicInfoActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yfs_basic_info_page);
	StringBuilder infoString=new StringBuilder();
	/**
	 * preparing string list for all the user accounts
	 */
	String accountListString="";
	List<String> accountList=YFS_OtherInforReader.getUsername(getApplicationContext());
	int accountSize=accountList.size();
	for(int i=0;i<accountSize;i++){
		accountListString+="\t\t"+accountList.get(i)+" \n";
	}
	/**
	 * preparing string for IMEI
	 */
	String imeiString=YFS_OtherInforReader.getImeiNumber(getApplicationContext());
	/**
	 *preparing string for ServiceProvider string
	 */
	String serviceProvider=YFS_OtherInforReader.getNetworkOperatorName(getApplicationContext());
	/**
	 *preparing Device Name String 
	 */
	String deviceName=YFS_OtherInforReader.getDeviceName();
	/**
	 * preparing mobile number string
	 */
	String mobileNumber=YFS_OtherInforReader.getMobileNumber(getApplicationContext());
	
	/**
	 * Concating all the string
	 */
	
	infoString.append("\t"+getResources().getString(R.string.otherInfopage_info1)+"\n\n");
	infoString.append("Accounts you are using is :\n"+accountListString);
	infoString.append("\n-----------\n");
	infoString.append("Imei of Your device :\n\t\t"+imeiString+"\n");
	infoString.append("\n-----------\n");
	infoString.append("Service Provider: \n\t\t"+serviceProvider+"\n");
	infoString.append("\n-----------\n");
	infoString.append("Device you are using is : \n\t\t"+deviceName+"\n");
	infoString.append("\n-----------\n");
	infoString.append("PH is : \n\t\t"+mobileNumber+"\n");
	
	Log.e(YFS_SourceUtil.TAG, "infostring="+infoString.toString());
	
	/**
	 * setting the prepared text to textview in html format
	 */
	TextView basicInfoText=(TextView)findViewById(R.id.basicInfoTxt);
	basicInfoText.setTypeface(basicInfoText.getTypeface(), Typeface.BOLD);
	basicInfoText.setText(infoString.toString());
	
	}

}
