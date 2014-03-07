package com.example.safetyalert;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationFactory {

	public static Notification safetyAppOnNotification(Activity activity) {
		NotificationCompat.Builder ncb = new NotificationCompat.Builder(activity)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Safety Alert is ON.")
				.setContentText("Running in the background.");

		// User goes back to the screen when they click the notification
		Intent toMainActivity = new Intent(activity, MainActivity.class);
		PendingIntent p = PendingIntent.getActivity(activity, 0, toMainActivity, 0);
		ncb.setContentIntent(p);

		Notification notification = ncb.build();
		notification.flags |= Notification.FLAG_ONGOING_EVENT;

		return notification;
	}
}