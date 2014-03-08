package com.example.safetyalert;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class GuardianshipSessionService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int starttId) {
		startGuardianshipSession();
		return START_REDELIVER_INTENT;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
		
	private void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
//	private void toast(int id, int duration) {
//		Toast.makeText(this, this.getResources().getString(id), duration).show();
//	}

	private void startGuardianshipSession() {
		toast("STARTED GUARDIANSHIP!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toast("FINISHED GUARDIANSHIP!");
	}
}
