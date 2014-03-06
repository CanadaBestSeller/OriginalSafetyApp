package com.example.safetyalert;

import android.content.Context;
import android.widget.Toast;

public class Utils {

	public static void toast(Context c, String message, int duration) {
		CharSequence text = message;
		Toast toast = Toast.makeText(c, text, duration);
		toast.show();
	}
}