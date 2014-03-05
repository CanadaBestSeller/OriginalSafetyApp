package com.example.safetyalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    public static final String EXTRA_MESSAGE = "com.example.safetyalert.MESSAGE";

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
    	if (on) {
    		activateSafetyApp();
    	} else {
    		deactivateSafetyApp();
    	}
    }

	public void deactivateSafetyApp() {
		// TODO Auto-generated method stub
		
	}

	public void activateSafetyApp() {
		// TODO Auto-generated method stub
	}
}