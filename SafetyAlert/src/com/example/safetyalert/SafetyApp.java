package com.example.safetyalert;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;

public class SafetyApp implements Runnable {

	public static final int APP_NOTIFICATION_ID = 1;

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
	public void run() {
		nm.notify(APP_NOTIFICATION_ID, NotificationFactory.safetyAppOnNotification(mainActivity));
		Utils.toast(mainActivity, R.string.safety_app_on, Toast.LENGTH_SHORT);

		// Alert mode loop logic
		while (isOn) {
			try {
				Thread.sleep(10000);

				// GuardianMode
                Utils.toast(mainActivity, "Sent guardian request!", Toast.LENGTH_SHORT);

			} catch (InterruptedException e) {
				SafetyApp.debug("Alert mode loop thread interrupted" + e);
				isOn = false;
                Utils.toast(mainActivity, R.string.safety_app_off, Toast.LENGTH_SHORT);
			}
		}
	}

	public static void debug(String message) {
		// TODO Auto-generated method stub
		// Log.d(TAG, "sleep failure");
	}

	public void terminate() {
		isOn = false;
		nm.cancel(APP_NOTIFICATION_ID);
	}
}