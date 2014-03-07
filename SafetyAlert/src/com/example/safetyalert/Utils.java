package com.example.safetyalert;

import android.app.Activity;

public class Utils {

	public static void toast(Activity activity, String message, int length) {
		UiToast u = new UiToast(activity, message, length);
		activity.runOnUiThread(u);
	}

	public static void toast(Activity activity, int message_id, int length) {
		String message = activity.getResources().getString(message_id);
		UiToast u = new UiToast(activity, message, length);
		activity.runOnUiThread(u);
	}
}