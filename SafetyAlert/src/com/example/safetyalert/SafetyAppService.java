package com.example.safetyalert;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.view.WindowManager;
import android.widget.Toast;

public class SafetyAppService extends Service {

	public static final int SAFETY_APP_SERVICE_ID = 1;

	private NotificationManager nm;

	@Override
	public void onCreate() {
		super.onCreate();
		this.nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int starttId) {
		startSafetyApp();
		return START_REDELIVER_INTENT;
	}
	
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

//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		//Toast.makeText(this, "Guardian session finished! Thanks for helping out your friend. :)", Toast.LENGTH_SHORT).show();

//		DialogManager dm = new DialogManager(this)
//		dm.spawnRequest();
	}
}