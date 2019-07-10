package com.example.alarmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MessageNotification {
	private static MessageNotification mInstance=null;
	private final static int notificationID = 1000; 
	private int messageNotificationID = 1000; 
	private NotificationManager mNotificationManager=null;
	private Context mContext;
	
	private String bookId="1000";
	public final static  String SHARE_PRE_FILE = "my_preferences";
	public final static  String DOTASK = "task";
	public final static  String BOOKID = "bookId";	
	private SharedPreferences sharedPrefsFile;
	
	private MessageNotification(Context context){
		mContext=context;
		sharedPrefsFile =context.getSharedPreferences(SHARE_PRE_FILE, 0);
		mNotificationManager=(NotificationManager)mContext.getSystemService(mContext.NOTIFICATION_SERVICE); 
	}
	
	public synchronized static MessageNotification getInstance(Context context){
		if(mInstance==null){
			mInstance=new MessageNotification(context);
		}
		return mInstance;
	}
	
	public void getNotification(String tickerText,String title,String content,String bookId,String perChapterId,String chapterId){
		Notification mNotification=new Notification();
		mNotification.icon=R.drawable.ic_launcher;
		//状态栏初次提示消息标题
		mNotification.tickerText=tickerText;
		mNotification.defaults = Notification.DEFAULT_SOUND;  
		Intent intent=new Intent(mContext,MessageActivity.class);
		Bundle bundle=new Bundle();
		bundle.putString("bookId", bookId);
		bundle.putString("perChapterId", perChapterId);
		bundle.putString("chapterId", chapterId);
		intent.putExtras(bundle);
		PendingIntent mPendingIntent=PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		//状态栏详细提示消息标题和内容
		mNotification.setLatestEventInfo(mContext, title, content, mPendingIntent);
		//发布消息
		mNotificationManager.notify(messageNotificationID, mNotification);
		//避免覆盖消息，采取ID自增
		messageNotificationID++;
		//执行完以后更新状态
		changeNotificationStatus(messageNotificationID,Integer.parseInt(bookId!=null?bookId:"1000"));
	}
	
	/**
	 * 发送状态栏通知消息
	 * @param ctx
	 * @param icon
	 * @param text
	 * @param contentTitle
	 * @param contentText
	 * @param intent
	 */
	public void setNotification(int icon,int text,String contentTitle,String contentText,Intent intent){
		Notification mNotification=new Notification();
		
		mNotification.icon=icon;
		mNotification.tickerText=mContext.getString(text);
		mNotification.defaults = Notification.DEFAULT_SOUND; 
		PendingIntent mPendingIntent=PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		mNotification.setLatestEventInfo(mContext,contentTitle,contentText, mPendingIntent);
		//发布消息
		mNotificationManager.notify(messageNotificationID, mNotification);
		//避免覆盖消息，采取ID自增
		messageNotificationID++;
		//执行完以后更新状态
		changeNotificationStatus(messageNotificationID);

	}
	
	/****
	 * 自定义View
	 * @param ctx
	 * @param icon
	 * @param text
	 * @param view
	 * @param contentTitle
	 * @param contentText
	 * @param intent
	 */
	public void setNotification(int icon,int text,View view,String contentTitle,String contentText,Intent intent){
		Notification mNotification=new Notification();
		mNotification.icon=icon;
		mNotification.tickerText=mContext.getString(text);
		//mNotification.contentView.addView(0,view);
		PendingIntent mPendingIntent=PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		mNotification.setLatestEventInfo(mContext,contentTitle,contentText, mPendingIntent);
		//发布消息
		mNotificationManager.notify(messageNotificationID, mNotification);
		//避免覆盖消息，采取ID自增
		messageNotificationID++;
		//执行完以后更新状态
		changeNotificationStatus(messageNotificationID);
	}
	
	public int getNotificationID(){
		return notificationID;
	}

	private void changeNotificationStatus(int status){
		sharedPrefsFile.edit().putInt(DOTASK, status).commit();
	}
	
	private void changeNotificationStatus(int status,int bookId){
		sharedPrefsFile.edit().putInt(DOTASK, status).commit();
		sharedPrefsFile.edit().putInt(BOOKID, bookId).commit();
	}
	
	public int getNotificationStatus(){
		return sharedPrefsFile.getInt(DOTASK, messageNotificationID);
	}
	
	public int getNotificationBookId(){
		return sharedPrefsFile.getInt(BOOKID, 1000);
	}
	
	public void resetNotificationStatus(){
		sharedPrefsFile.edit().putInt(DOTASK, notificationID).commit();
	}
}
