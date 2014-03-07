package com.example.safetyalert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	public SafetyApp safetyApp = null;
	public Thread safetyAppThread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		if (safetyApp != null) {
//			ToggleButton t = (ToggleButton) findViewById(R.id.activation_toggle);
//			t.setChecked(safetyApp.isOn);
//		}

		setContentView(R.layout.activity_main);
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
		deactivateSafetyApp();
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
			safetyAppThread.interrupt();
		}
		cleanup();
	}

	private void cleanup() {
		safetyApp = null;
		safetyAppThread = null;
	}
}