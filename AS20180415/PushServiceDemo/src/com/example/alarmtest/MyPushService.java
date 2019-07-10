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
	private int doTaskDay=1;//��һִ��
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
		//��һִ��
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
		// ÿ����һ����9��
		startDate = Util.getTaskStartTime(this);
		endDate= Util.getTaskEndTime(this);
		task = new TimerTask() {
			@Override
			public void run() {
				try{
					Logger.i(TAG, Util.getLongTimeToDateTime(startDate.getTime())+"------------����ʱ������ʱ����ʼִ����Ϣ��ʾ!");
						//Log.i("FyPullService", "���� ���� ");
						int weekDay = Util.getDayOfWeek();
						int d = weekDay - 1;
						//Log.i("FyPullService", "weekDay=" + weekDay + "��������= " + d);
						/*	Log.i("FyPullService", "�趨ʱ��=" + startDate.getTime() + "--ϵͳʱ��="
								+ System.currentTimeMillis()+"����ʱ��="+endDate.getTime());*/
						//��һ  ע�⣺ÿһ�ܵĵ�һ������տ�ʼ
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
		Logger.i(TAG, "------------���ݶ�ʱ����ʱ���������Ϣ��ʾ!");
		//�ӷ�������ȡ����bookId �ӷ������ϸ��µ�֪ͨid�������ʾ��
		int bookId=123456;
		//Ĭ����1000 ����״̬ �ı�����
		int status=mMessageNotification.getNotificationStatus();
		int nbookId=mMessageNotification.getNotificationBookId();
		if(nbookId!=bookId||status==mMessageNotification.getNotificationID()){
			Log.i(TAG, TAG+"��ʼִ�������� ");
			/**
			 * �ӷ������ϻ�ȡ����Ϣ
			 */
			String tickerText="���ã�������Ϣ!";
			String title="������Ϣ��ʾ";
			String content="������........��Ϣ�Ѿ�����,����ʱ��ע";
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
