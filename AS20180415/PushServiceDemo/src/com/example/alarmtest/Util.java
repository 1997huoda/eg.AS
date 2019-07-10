package com.example.alarmtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class Util {
	public static boolean logger=true;
	
	public static int getDayOfWeek(){
		  int weekday=0;
		  Calendar c = Calendar.getInstance(); 
		  c.setTime(new Date(System.currentTimeMillis())); 
		  int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); 
		  switch (dayOfWeek) { 
			  case 1: 
				  weekday=dayOfWeek;
				  System.out.println("������"); 
			   break; 
			  case 2: 
				  weekday=dayOfWeek;
				  //System.out.println("����һ"); 
			   break; 
			  case 3: 
				   weekday=dayOfWeek;				  
				   System.out.println("���ڶ�"); 
			   break; 
			  case 4:
				   weekday=dayOfWeek;					  
				   System.out.println("������"); 
			   break; 
			  case 5:
				   weekday=dayOfWeek;				  
				   System.out.println("������"); 
			   break; 
			  case 6:
				   weekday=dayOfWeek;					  
				   System.out.println("������"); 
			   break; 
			  case 7:
				   weekday=dayOfWeek;					  
				   System.out.println("������"); 
			   break; 
		  }
		  return weekday;
	}
	
	/**
	 * �趨ÿ��ĳ�����Ժ�ʼ  
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static Date  getTaskStartTime(Context ctx){
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		    Date date = new Date();
		    //���忪ʼʱ���ַ���
		    String timeStr = ctx.getString(R.string.push_start_time); 
		    timeStr =sdf.format(date)+timeStr;
		    //��õ����ָ��ʱ���date����
		    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    try {
		        date = sdf.parse(timeStr);
	        } catch (ParseException e) {
	        	e.printStackTrace();
	        }
		    //System.out.println("startTime= "+date.getTime());
		return date;
	}
	
	/**
	 * �趨ÿ��ĳ�����Ժ���� 
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static Date getTaskEndTime(Context ctx){
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		    Date date = new Date();
		    //���忪ʼʱ���ַ���
		    String timeStr = ctx.getString(R.string.push_end_time); ; 
		    timeStr =sdf.format(date)+timeStr;
		    //��õ����ָ��ʱ���date����
		    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    try {
		        date = sdf.parse(timeStr);
	        } catch (ParseException e) {
	        	e.printStackTrace();
	        }
		    //System.out.println("endTime= "+date.getTime());
		return date;
	}
	
	
	public static void getDaytime(){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
	    Date date = new Date();
	    //���忪ʼʱ���ַ���
	    String timeStr = "09:00:00"; 
	    timeStr =sdf.format(date)+timeStr;
	    //��õ����ָ��ʱ���date����
	    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    //System.out.println("�趨ʱ��Ϊ�� "+timeStr);
	    try {
	        date = sdf.parse(timeStr);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
	    //�жϽ����ִ��ʱ���Ƿ��Ѿ���ȥ�������ȥ���Ϊ����
	    if(date.getTime()<System.currentTimeMillis()){
	    	date = new Date(date.getTime()+24*60*60*1000);
	    }
	    
	    TimerTask task = new TimerTask(){
	        @Override
	        public void run() {
	         //your task
	         System.out.println("task ���� ");
	        }
	     };
	     Timer timer = new Timer();
	     timer.schedule(task, date, 24*60*60*1000); 
	}
	
	/**
	 * ��ȡ��ǰʱ�� yyyy-MM-dd HH:mm:ss
	 */
	public static Long getCurrentTime(){
		try{
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//System.out.println("current time---"+df.format(date));
			return df.parse(df.format(date)).getTime();
		}catch(Exception e){
		}
		return null;
	}
	
	public static String getLongTimeToDateTime(Long l){
		Date date=new Date(l);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t=df.format(date);
		System.out.println("���ڵ�ʱ����:"+t);
		return t;
	}
	
	public static boolean isServiceRunning(Context ctx,String filePath) {
	    ActivityManager manager = (ActivityManager) ctx.getSystemService(ctx.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (filePath.equalsIgnoreCase(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
}
