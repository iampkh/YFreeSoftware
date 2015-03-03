package com.freedom.yfreesoftware;

import java.util.Collections;
import java.util.List;

import com.freedom.yfreesoftware.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class YFS_AllApplication extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.yfs_all_application);
	    TextView allApplicationTextView=(TextView) findViewById(R.id.allApplicationTxt);
	    /**
	     * String which uses to crate all application in a list
	     */
	    String appliccationString="Following are the Installed Applications \n";
	    
	    List<String> apps=YFS_OtherInforReader.getInstalledApps(getApplicationContext());
	    /**
	     * code to sort application list in alphabetic order
	     */
	    Collections.sort(apps);
	    int appListSize=apps.size();
	    
	    for(int i=0;i<appListSize;i++){
	    	appliccationString+="\t "+i+") "+apps.get(i)+"\n";
	    }
	allApplicationTextView.setText(appliccationString);
	}

}
