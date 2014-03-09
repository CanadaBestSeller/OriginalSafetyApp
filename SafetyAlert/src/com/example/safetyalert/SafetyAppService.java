package com.example.safetyalert;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class SafetyAppService extends IntentService {

	public static final int SAFETY_APP_SERVICE_ID = 1;

	private NotificationManager nm;
	private Intent guardianshipSessionIntent;
	private Handler handler;

	public SafetyAppService() {
		super("SafetyApp Intent Service.");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		this.nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		guardianshipSessionIntent = new Intent(this, GuardianshipSessionService.class);
		
		startSafetyApp();
	}

	// These Methods should not be overwritten, as they spawn a background worker thread!
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		handler = new Handler();
//		this.nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//		guardianshipSessionIntent = new Intent(this, GuardianshipSessionService.class);
//	}
//	
//	@Override
//	public int onStartCommand(Intent intent, int flags, int starttId) {
//		startSafetyApp();
//		return START_REDELIVER_INTENT;
//	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		toast(R.string.alert_off, Toast.LENGTH_SHORT);
		nm.cancel(SAFETY_APP_SERVICE_ID);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	private void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	private void toast(int id, int duration) {
		Toast.makeText(this, this.getResources().getString(id), duration).show();
	}

	private void startSafetyApp() {
		Notification safetyAppOnNotification = NotificationFactory.safetyAppOnNotification(this);
		nm.notify(SAFETY_APP_SERVICE_ID, safetyAppOnNotification);

//		handler.post(new Runnable() { public void run() { toast("STARTED!"); } });
//
//		// This thread is causing the toast above to NOT EXECUTE
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		
//		handler.post(new Runnable() { public void run() { toast("DURING!"); } });
//
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		handler.post(new Runnable() { public void run() { toast("END!"); } });

		//this.stopSelf();

//		DialogManager dm = new DialogManager(this);
//		dm.spawnRequest(guardianshipSessionIntent);
	}

}