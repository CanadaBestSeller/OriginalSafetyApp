package com.example.safetyalert;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;

public class SafetyApp implements Runnable {

	public volatile boolean isOn = true;

	public Activity mainActivity;
	public NotificationManager nm;

	public SafetyApp(Activity mainActivity) {
		this.mainActivity = mainActivity;
		
		// If this doesn't work, make MainActivity pass it instead
		this.nm = (NotificationManager) mainActivity.getSystemService(
				Context.NOTIFICATION_SERVICE);

	}

	@Override
	// TODO clean up messy Utils/Toast logic
	public void run() {
		nm.notify(MainActivity.APP_NOTIFICATION_ID,
				NotificationFactory.safetyAppOnNotification(mainActivity));
//		Utils.toast(mainActivity.getApplicationContext(),
//				"Safety app activated!", Toast.LENGTH_SHORT);
		Utils.toast(mainActivity, "Safety App activated!", Toast.LENGTH_SHORT);

//				mainActivity.runOnUiThread(new Runnable() {
//					public void run() {
//						Utils.toast(mainActivity.getApplicationContext(),
//								"Safety app activated!", Toast.LENGTH_SHORT);
//					}
//				});
		while (isOn) {
			try {
				Thread.sleep(5000);

				// GuardianMode
                Utils.toast(mainActivity, "Sent guardian request!", Toast.LENGTH_SHORT);

			} catch (InterruptedException e) {
				SafetyApp.debug("Exception in SafetyApp thread: " + e);
				isOn = false;
			}
		}
	}

	public static void debug(String message) {
		// TODO Auto-generated method stub
		// Log.d(TAG, "sleep failure");
	}

	public void terminate() {
		isOn = false;
		nm.cancel(MainActivity.APP_NOTIFICATION_ID);
	}
}