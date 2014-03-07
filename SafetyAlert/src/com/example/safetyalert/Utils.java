package com.example.safetyalert;

import android.app.Activity;

public class Utils {

	public static void toast(Activity activity, String message, int length) {
		UiToast u = new UiToast(activity, message, length);
		activity.runOnUiThread(u);
	}
}