package com.example.alarmtest;


import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PushService extends Service {
	private final static String TAG="PushService";
	private Date startDate = null;
	private Date endDate = null;
	private int doTaskDay=1;//��һִ��
	public final static String ACTION="cn.com.action.MyAction";
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
		mMessageNotification=MessageNotification.getInstance(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		doTask();
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	private void doTask() {
		// ÿ����һ����9��
		startDate = Util.getTaskStartTime(this);
		endDate= Util.getTaskEndTime(this);
		new Thread(){
			@Override
			public void run() {
				try{
					Logger.i(TAG, Util.getLongTimeToDateTime(startDate.getTime())+"------------����ʱ���ӡ���ʱ����ʼִ����Ϣ��ʾ!");
						//Log.i("FyPullService", "���� ���� ");
						int weekDay = Util.getDayOfWeek();
						int d = weekDay - 1;
						//Log.i("FyPullService", "weekDay=" + weekDay + "��������= " + d);
						/*Log.i("FyPullService", "�趨ʱ��=" + startDate.getTime() + "--ϵͳʱ��="
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
		}.start();
	}

	
	private void doTaskGetMessage(){
		Logger.i(TAG, "------------���ݡ���ʱʱ�ӡ���ʱ���������Ϣ��ʾ!");
		//�ӷ�������ȡ����bookId �ӷ������ϸ��µ�С˵֪ͨid�������ʾ��
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
					String filePath = "com.example.alarmtest.FyPushService";
					boolean flag = Util.isServiceRunning(PushService.this,filePath);
					if (flag == false) {
						PushService.this.startService(new Intent(PushService.this,MyPushService.class));
					}
					String _filePath = "com.example.alarmtest.PushService";
					boolean _flag= Util.isServiceRunning(PushService.this,_filePath);
					if (_flag == false) {
						PushService.this.startService(new Intent(PushService.this,PushService.class));
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
