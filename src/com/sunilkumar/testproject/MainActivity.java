package com.sunilkumar.testproject;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Context myContext = this;
    public static final String SERVICE_LOG = "SERVICE_LOG";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(SERVICE_LOG, "Clicked on Start Button");
       
        Button startButton = (Button) findViewById(R.id.startButton);
    	Button stopButton = (Button) findViewById(R.id.stopButton);
    	
    	// Start Music Player service.
    			startButton.setOnClickListener(new OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					Log.d(SERVICE_LOG, "Clicked on Start Button");
    					// startService(..) method is from Context class, which is
    					// a superclass of Activity class
    					startService(new Intent(myContext, MyFirstService.class));
    				}
    			});
    			
    			
    			stopButton.setOnClickListener(new OnClickListener(){
    				@Override
    				public void onClick(View v) {
    					Log.d(SERVICE_LOG, "Clicked on Stop Button");
    					// startService(..) method is from Context class, which is
    					// a superclass of Activity class
    					stopService(new Intent(myContext, MyFirstService.class));
    				}
    			});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
