package com.example.safetyalert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.example.safetyalert.MESSAGE";
	public static final int APP_NOTIFICATION_ID = 1;

	public SafetyApp safetyApp = null;
	public Thread safetyAppThread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		if (on) { activateSafetyApp(); } 
		else { deactivateSafetyApp(); }
	}

	public void activateSafetyApp() {
		safetyApp = new SafetyApp(this);
		safetyAppThread = new Thread(safetyApp);
		safetyAppThread.start();
	}

	public void deactivateSafetyApp() {
		if (safetyAppThread != null) {
			safetyApp.terminate();
			try {
				safetyAppThread.join();
			} catch (InterruptedException e) {
				SafetyApp.debug("Exception encountered trying to join safetyAppThread: " + e);
			}
		}
		cleanup();
		Utils.toast(this, "Safety app deactivated.", Toast.LENGTH_SHORT);
	}

	private void cleanup() {
		// TODO Write method to terminate all running threads
		
	}
}