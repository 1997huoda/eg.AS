package com.example.alarmtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button test1;
	Button test2;

	AlarmManager alarmManager = null;
	PendingIntent pendingActivityIntent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(new Intent(this,MyPushService.class));
		startService(new Intent(this,PushService.class));
		startService(new Intent(this,AlarmSysService.class));
		finish();
	}
	
	

}
