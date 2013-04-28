package com.sunilkumar.testproject;




import java.util.Date;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.os.ConditionVariable;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyFirstService extends Service {

	private static final int ID_HELLO_WORLD = 0;
	private ConditionVariable mCondition;
	private NotificationManager mNM = null;
	private Runnable mTask = new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i < 4; ++i) {
				
				showNotification(new Date());
					
			}
			// Done with our work... stop the service!
			MyFirstService.this.stopSelf();//will do the work of stopService Button
		}
	};
	
	private void showNotification(Date date) {
		Notification notification = new Notification(
			    android.R.drawable.stat_sys_warning,  //Icon to use
			    date.toString(), //Text
			    System.currentTimeMillis() //When to display - i.e. now
			);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
		        new Intent(this, MyFirstService.class), 0);
		
		notification.setLatestEventInfo(
			    getApplicationContext(),
			    "Stack Overflow", //Title of detail view
			    "This will launch Stack Overflow",  //Text on detail view
			    contentIntent
			);
		//Display the Notification
		//NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		mNM.notify(ID_HELLO_WORLD, notification);  //ID_HELLO_WORLD is a int ID
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void onCreate() {
		Toast.makeText(this,"Working on my first service",Toast.LENGTH_LONG).show();
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Thread notifyingThread = new Thread(null,mTask,"NotifyingService");
		notifyingThread.start();
		
		super.onCreate();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Service has been started", Toast.LENGTH_LONG).show();
		 return START_STICKY;
	}
	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service has been Stopped", Toast.LENGTH_SHORT).show();
		
	}

}
