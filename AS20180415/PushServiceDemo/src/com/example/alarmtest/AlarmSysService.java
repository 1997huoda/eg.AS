package com.example.alarmtest;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmSysService extends Service {
	private final static String TAG="AlarmSysService";
	private Date startDate = null;
	public final static String ACTION=PushService.ACTION;
	private AlarmManager alarm;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Logger.i(TAG, "------------start");
		//startTimer();
		doAlarmTask();
	}
	
	public void doAlarmTask(){
		if(null==alarm){
			alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
			Intent intent =new Intent(AlarmSysService.this, AlarmReceiver.class);
			intent.setAction(ACTION);
			PendingIntent sender=PendingIntent.getBroadcast(AlarmSysService.this, 0, intent, 0);
			//早上9点
			startDate = Util.getTaskStartTime(this);
			//当天早上9点开始执行任务,重复执行是从第二天开始执行早上9点
			alarm.setRepeating(AlarmManager.RTC_WAKEUP, startDate.getTime(),1*24*60*60*1000+startDate.getTime(), sender);
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
