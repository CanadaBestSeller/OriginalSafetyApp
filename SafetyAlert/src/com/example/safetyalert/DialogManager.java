package com.example.safetyalert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class DialogManager {

	private Context context;
	private AlertDialog.Builder builder;
	
	public DialogManager(Context context) {
		this.context = context;
	}
	
	public void spawnRequest(Intent startServceIntent) {

		builder = new AlertDialog.Builder(context);
		builder.setTitle("Test dialog");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("Content");

		ContextToIntentListener a = new ContextToIntentListener(context, startServceIntent);
		builder.setPositiveButton("OK", a);

		builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int whichButton) {
		        dialog.dismiss();
		    }
		});

		AlertDialog alert = builder.create();
		alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alert.show();	
	}
}