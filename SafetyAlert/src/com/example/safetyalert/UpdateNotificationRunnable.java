//package com.example.safetyalert;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.support.v4.app.NotificationCompat;
//import android.util.Log;
//
//public class UpdateNotificationRunnable implements Runnable {
//
//	// we're going to update progress 1/20th at a time.
//	public static final int GRANULARITY = 20;
//
//	public int minutes;
//	public int seconds;
//	public int timeslice;
//
//	public NotificationCompat.Builder ncb;
//	public NotificationManager nm;
//	public MainActivity m;
//
//	public UpdateNotificationRunnable(MainActivity m,
//			NotificationCompat.Builder ncb, NotificationManager nm, int minutes) {
//		this.ncb = ncb;
//		this.nm = nm;
//		this.m = m;
//
//		this.minutes = minutes;
//		// this.seconds = minutes*60;
//		this.seconds = 20;
//		this.timeslice = seconds / UpdateNotificationRunnable.GRANULARITY;
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//
//		for (int i = 0; i <= seconds; i += timeslice) {
//
//			ncb.setProgress(seconds, i, false);
//			Notification notification = ncb.build();
//			notification.flags |= Notification.FLAG_ONGOING_EVENT;
//			nm.notify(MainActivity.APP_NOTIFICATION_ID, notification);
//
//			try {
//				Thread.sleep(timeslice * 1000);
//			} catch (InterruptedException e) {
//				SafetyApp.log("sleep failure on progress notification");
//			}
//		}
//
//		// guardianModeOff() executes toast, which must be done in a UI thread.
//		m.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				m.guardianModeOff();
//			}
//		});
//	}
//}
