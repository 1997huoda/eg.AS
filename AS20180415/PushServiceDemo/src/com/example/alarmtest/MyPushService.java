package com.example.alarmtest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyPushService extends Service {
	private final static String TAG="FyPushService";
	private TimerTask task = null;
	private Timer timer = null;
	private Date startDate = null;
	private Date endDate = null;
	private int doTaskDay=1;//周一执行
	private boolean isRunning = true;
	
	private MessageNotification mMessageNotification;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		//周一执行
		doTaskDay=Integer.parseInt(this.getString(R.string.doTaskDate));
		checkServiceStatus();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mMessageNotification=MessageNotification.getInstance(this);
		doTask();
		return super.onStartCommand(intent, flags, startId);
	}

	private void doTask() {
		timer = new Timer();
		// 每周周一早上9点
		startDate = Util.getTaskStartTime(this);
		endDate= Util.getTaskEndTime(this);
		task = new TimerTask() {
			@Override
			public void run() {
				try{
					Logger.i(TAG, Util.getLongTimeToDateTime(startDate.getTime())+"------------【定时器】定时任务开始执行消息提示!");
						//Log.i("FyPullService", "任务 来了 ");
						int weekDay = Util.getDayOfWeek();
						int d = weekDay - 1;
						//Log.i("FyPullService", "weekDay=" + weekDay + "今天是周= " + d);
						/*	Log.i("FyPullService", "设定时间=" + startDate.getTime() + "--系统时间="
								+ System.currentTimeMillis()+"结束时间="+endDate.getTime());*/
						//周一  注意：每一周的第一天从周日开始
						//Log.i(TAG,"weekDay=="+(doTaskDay+1));
						if (weekDay ==(doTaskDay+1)){//System.currentTimeMillis()
							Long n=System.currentTimeMillis();//Util.getCurrentTime();
							Long s=startDate.getTime();
							Long e=endDate.getTime();
							if(n-s>=0&&e-n>=0){
								doTaskGetMessage();	
							}
						} 
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		};
		timer.schedule(task, startDate, 1*24*60*60*1000); // 7*24*60*60*1000
	}

	
	
	
	private void doTaskGetMessage(){
		Logger.i(TAG, "------------根据定时器定时任务进行消息提示!");
		//从服务器获取到的bookId 从服务器上更新的通知id保存和提示的
		int bookId=123456;
		//默认是1000 提醒状态 改变条件
		int status=mMessageNotification.getNotificationStatus();
		int nbookId=mMessageNotification.getNotificationBookId();
		if(nbookId!=bookId||status==mMessageNotification.getNotificationID()){
			Log.i(TAG, TAG+"开始执行任务了 ");
			/**
			 * 从服务器上获取到消息
			 */
			String tickerText="您好，有新消息!";
			String title="有新消息提示";
			String content="有最新........消息已经发布,请随时关注";
			String mbookId=""+bookId;
			String perChapterId="1233";
			String chapterId="1234";
			mMessageNotification.getNotification(tickerText,title,content,mbookId,perChapterId,chapterId);
			int nid=mMessageNotification.getNotificationID();
			//Log.i(TAG, "nid=="+nid);
			mMessageNotification.resetNotificationStatus();
			//bookId++;
		}
	}
	
	
	
	
	
	private void checkServiceStatus() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					String filePath = "com.example.alarmtest.MyPushService";
					boolean flag = Util.isServiceRunning(MyPushService.this,filePath);
					if (flag == false) {
						MyPushService.this.startService(new Intent(MyPushService.this,MyPushService.class));
					}
					String _filePath = "com.example.alarmtest.PushService";
					boolean _flag= Util.isServiceRunning(MyPushService.this,_filePath);
					if (_flag == false) {
						MyPushService.this.startService(new Intent(MyPushService.this,PushService.class));
					}
				} catch (Exception e) {

				}
			}
		}.start();
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
