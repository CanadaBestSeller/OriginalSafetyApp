package com.example.safetyalert;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.example.safetyalert.MESSAGE";
	public static final int APP_NOTIFICATION_ID = 1;

	public boolean activation;
	private boolean guardianMode;
	public NotificationManager nm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		activation = false;
		guardianMode = false;
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void activationClicked(View view) {
		boolean on = ((ToggleButton) view).isChecked();
		if (on) {
			activateSafetyApp();
		} else {
			deactivateSafetyApp();
		}
	}

	public void startGuardianFor1Minute(View view) {
		guardianModeOn(1);
	}

	public void trigger(View view) {
		if (guardianMode && activation) {
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						displayTriggerActivity();

					case DialogInterface.BUTTON_NEGATIVE:
						break;
					}
				}
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(TriggerMessage.getTriggerMessage())
					.setPositiveButton(R.string.respond, dialogClickListener)
					.setNegativeButton(R.string.cancel, dialogClickListener)
					.show();
		}
	}

	public void displayTriggerActivity() {
		Intent intent = new Intent(this, DisplayTriggerDetailsActivity.class);
		startActivity(intent);
	}

	public void toast(String message, int duration) {
		Context context = getApplicationContext();
		CharSequence text = message;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	public void activateSafetyApp() {
		nm.notify(MainActivity.APP_NOTIFICATION_ID, defaultNotification());
		this.activation = true;
		toast("Safety app activated!", Toast.LENGTH_SHORT);
	}

	public void deactivateSafetyApp() {
		nm.cancel(MainActivity.APP_NOTIFICATION_ID);
		this.activation = false;
		toast("Safety app deactivated.", Toast.LENGTH_SHORT);
	}

	public Notification defaultNotification() {
		NotificationCompat.Builder ncb = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Safety Alert is ON.")
				.setContentText("Running in the background.");

		// User goes back to the screen when they click the notification
		Intent toMainActivity = new Intent(this, MainActivity.class);
		PendingIntent p = PendingIntent.getActivity(this, 0, toMainActivity, 0);
		ncb.setContentIntent(p);

		Notification notification = ncb.build();
		notification.flags |= Notification.FLAG_ONGOING_EVENT;

		return notification;
	}

	public void guardianModeOn(int minutes) {
		if (this.activation && !this.guardianMode) {
			// User goes back to the screen when they click the notification
			Intent toMainActivity = new Intent(this, MainActivity.class);
			PendingIntent p = PendingIntent.getActivity(this, 0,
					toMainActivity, 0);

			// TODO Get a different icon for guardian mode
			NotificationCompat.Builder ncb = new NotificationCompat.Builder(
					this).setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Guardian Mode")
					.setContentText("Your friend might send you an alert!")
					.setContentIntent(p);

			UpdateNotificationRunnable r = new UpdateNotificationRunnable(this,
					ncb, nm, minutes);
			toast("Guardian Mode is ON. For the next " + minutes
					+ " minutes, your friend might send you distress signals!",
					Toast.LENGTH_LONG);
			this.guardianMode = true;
			new Thread(r).start();
		}
	}

	public void guardianModeOff() {
		if (this.activation) {
			nm.notify(MainActivity.APP_NOTIFICATION_ID, defaultNotification());
			toast("Guardian Mode OFF. Thanks for helping out your friend!",
					Toast.LENGTH_SHORT);
			this.guardianMode = false;
		}
	}
}