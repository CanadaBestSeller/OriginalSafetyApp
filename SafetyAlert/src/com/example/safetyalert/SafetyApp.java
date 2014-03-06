package com.example.safetyalert;

import android.app.Activity;
import android.widget.Toast;

public class SafetyApp implements Runnable {

	public Activity mainActivity;
	public volatile boolean isOn = true;

	public SafetyApp(Activity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	public void run() {
		while (isOn) {
			try {
				Thread.sleep(5000);

				// GuardianMode
				mainActivity.runOnUiThread(new Runnable() {
					public void run() {
						Utils.toast(mainActivity.getApplicationContext(),
								"Sent guardian request!", Toast.LENGTH_SHORT);
					}
				});

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
	}
}