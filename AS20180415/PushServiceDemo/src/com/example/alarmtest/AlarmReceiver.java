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
			Logger.i(TAG, "------------���յ�����ʱʱ�ӡ������Ĺ㲥����������Ϣ��ѭ��ѯ��ʾ!");
			context.startService(mintent);
		} 
		if(intent.getAction().equals(SYSTEM_BROADCAST_ACTION)){
			Logger.i(TAG, "���յ���ϵͳ�������󷢳��Ĺ㲥����ʼ��������!");
			context.startService(new Intent(context,AlarmSysService.class));
			context.startService(new Intent(context,MyPushService.class));
		}
	}
}
