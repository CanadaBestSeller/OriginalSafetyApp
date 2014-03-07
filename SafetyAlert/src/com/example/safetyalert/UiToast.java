package com.example.safetyalert;

import android.app.Activity;
import android.widget.Toast;

public class UiToast implements Runnable {
	
	public Activity activity;
	public String message;
	public int length;

	public UiToast(Activity activity, String message, int length) {
		this.activity = activity;
		this.message = message;
		this.length = length;
	}

	@Override
	public void run() {
		CharSequence text = message;
		Toast toast = Toast.makeText(activity, text, length);
		toast.show();
	}

}
