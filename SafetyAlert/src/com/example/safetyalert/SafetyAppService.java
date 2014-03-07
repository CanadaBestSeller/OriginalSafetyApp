package com.example.safetyalert;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SafetyAppService extends Service {

	public static final int SAFETY_APP_SERVICE_ID = 1;

	private NotificationManager nm;

	@Override
	public void onCreate() {
		super.onCreate();
		this.nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int starttId) {
		showNotification();
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		return START_REDELIVER_INTENT;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "My Service Destroyed", Toast.LENGTH_LONG).show();
		nm.cancel(SAFETY_APP_SERVICE_ID);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void showNotification() {
		// Service has not been initialized yet, can't pass this into safetyAppOnNotification
		Notification safetyAppOnNotification = NotificationFactory.safetyAppOnNotification(this);
		nm.notify(SAFETY_APP_SERVICE_ID, safetyAppOnNotification);
	}
}
