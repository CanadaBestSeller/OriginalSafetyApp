package com.example.safetyalert;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class ContextToIntentListener implements DialogInterface.OnClickListener {

	Context context;
	Intent intent;
	
	public ContextToIntentListener(Context context, Intent intent) {
		this.context = context;
		this.intent = intent;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		context.startService(intent);
	}
}