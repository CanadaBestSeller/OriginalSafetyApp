package com.example.safetyalert;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationFactory {

	public static Notification safetyAppOnNotification(Context context) {
		NotificationCompat.Builder ncb = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Safety Alert is ON.")
				.setContentText("Running in the background.");

		// User goes back to the screen when they click the notification
//		Intent toMainActivity = new Intent(context, MainActivity.class);
//		PendingIntent p = PendingIntent.getActivity(context, 0, toMainActivity, 0);
//		ncb.setContentIntent(p);

		Notification notification = ncb.build();
		notification.flags |= Notification.FLAG_ONGOING_EVENT;

		return notification;
	}
}