package com.example.safetyalert;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SafetyAppService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int starttId) {

		return starttId;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
