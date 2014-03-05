package com.example.safetyalert;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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

	public void guardianModeNotification(int minutes) {
		// This part and the next NEED to communicate with thread below
		NotificationCompat.Builder ncb = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("Guardian Mode")
			.setContentText("Your friend might send you an alert!");
		
		// User goes back to the screen when they click the notification
		Intent toMainActivity = new Intent(this, MainActivity.class);
		PendingIntent p = PendingIntent.getActivity(this, 0, toMainActivity, 0);
		ncb.setContentIntent(p);
		
		
		// Start a lengthy operation in a background thread
		new Thread(
		    new Runnable() {

		        @Override
		        public void run() {
		            for (int i = 0; i <= 100; i+=5) {

		                ncb.setProgress(100, i, false);
		                Notification notification = ncb.build();
		                notification.flags |= Notification.FLAG_ONGOING_EVENT;
		                nm.notify(MainActivity.APP_NOTIFICATION_ID, notification);

                        try {
                            Thread.sleep(5*1000);
                        } catch (InterruptedException e) {
                            Log.d(TAG, "sleep failure");
                        }

		            }
		            guardianModeOff();
		        }
		    }
		).start();
		
		return notification;
	}
	
	public void guardianModeOn(int minutes) {
		if (this.activation) {
			nm.notify(MainActivity.APP_NOTIFICATION_ID, guardianModeNotification(minutes));
			toast("Guardian Mode is ON. For the next " + minutes + 
					" minutes, your friend might send you distress signals!", Toast.LENGTH_LONG);
			this.guardianMode = true;
		}
	}
	
	public void guardianModeOff() {
		if (this.activation) {
			nm.notify(MainActivity.APP_NOTIFICATION_ID, defaultNotification());
			toast("Guardian Mode OFF. Thanks for helping out your friend!", Toast.LENGTH_SHORT);
			this.guardianMode = false;
		}
	}
}