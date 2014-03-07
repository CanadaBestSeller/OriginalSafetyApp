package com.example.safetyalert;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private Intent safetyAppIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		safetyAppIntent = new Intent(this, SafetyAppService.class);

		setContentView(R.layout.activity_main);

		ToggleButton t = (ToggleButton) findViewById(R.id.activation_toggle);
		// Need to change both the visual as well as the actual value
		t.setChecked(safetyAppIsRunning());
		t.setSelected(safetyAppIsRunning());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	// ORIGINAL METHODS
	// public void activationClicked(View view) {
	// boolean on = ((ToggleButton) view).isChecked();
	// if (on) { activateSafetyApp(); }
	// else { deactivateSafetyApp(); }
	// }
	//
	// public void activateSafetyApp() {
	// safetyApp = new SafetyApp(this);
	// safetyAppThread = new Thread(safetyApp);
	// safetyAppThread.start();
	// }
	//
	// public void deactivateSafetyApp() {
	// if (safetyAppThread != null) {
	// safetyApp.terminate();
	// safetyAppThread.interrupt();
	// }
	// cleanup();
	// }

	public void activationClicked(View view) {
		// TODO This is not working properly, when the button is uncheced, nothing happens.
		if (((ToggleButton) view).isChecked()) {
			activateSafetyApp();
		} else {
			deactivateSafetyApp();
		}
	}

	private void activateSafetyApp() {
		startService(safetyAppIntent);
	}

	private void deactivateSafetyApp() {
		stopService(safetyAppIntent);
	}

	private boolean safetyAppIsRunning() {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager
				.getRunningServices(Integer.MAX_VALUE)) {
			if (SafetyAppService.class.getName().equals(
					service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}