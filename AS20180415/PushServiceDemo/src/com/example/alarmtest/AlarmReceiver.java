package com.example.alarmtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver  extends BroadcastReceiver{
	private final static String TAG="AlarmReceiver";
	private final static String SYSTEM_BROADCAST_ACTION="android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(PushService.ACTION)) {
			Toast.makeText(context, "short alarm", Toast.LENGTH_LONG)
					.show();
			Intent mintent = new Intent(context, PushService.class);
			Logger.i(TAG, "------------接收到【定时时钟】发出的广播并将进行消息轮循查询提示!");
			context.startService(mintent);
		} 
		if(intent.getAction().equals(SYSTEM_BROADCAST_ACTION)){
			Logger.i(TAG, "接收到【系统启动】后发出的广播，开始启动服务!");
			context.startService(new Intent(context,AlarmSysService.class));
			context.startService(new Intent(context,MyPushService.class));
		}
	}
}
