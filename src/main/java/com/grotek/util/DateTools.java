package com.grotek.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	
	public static String FormateDate(Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
	}
	
	public static String FormateDateOnlyMonth(Date date){
		DateFormat format = new SimpleDateFormat("MM/dd");
		return format.format(date);
	}
	
	public static String FormateDateShort(Date date){
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(date);
	}
	
	public static String FormateDateYearMonth(Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(date);
	}
	
	public static Date ParseString(String date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static Date ParseShortString(String date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static boolean afterTue(Date d){
		Calendar day=Calendar.getInstance();
	    day.setTime(d);
	    int dayOfWeek=day.get(Calendar.DAY_OF_WEEK); 
	    if(dayOfWeek<4&&dayOfWeek>1){
	    	return false;
	    }
	    else{
	    	return true;
	    }
	}

	/**
	 * 获取本周的周一
	 * @return
	 */
	public static Date getMonday(Date d){ 
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(1);
	    Calendar day=Calendar.getInstance();
	    day.setTime(d);
	    int dayOfWeek=day.get(Calendar.DAY_OF_WEEK);  
	    if(dayOfWeek==1){ //一周中第一天（周日）
	    	return new Date(d.getTime() - 6 * 24 * 60 * 60 * 1000);
	    }else{  
	    	int k = 2-dayOfWeek;
	        return new Date(d.getTime() + k * 24 * 60 * 60 * 1000);  
	    }  
	} 
	
	/**
	 * 获取本周的周日
	 * @return
	 */
	public static Date getSunday(Date d){ 
		d.setHours(23);
		d.setMinutes(59);
		d.setSeconds(59);
	    Calendar day=Calendar.getInstance(); 
	    day.setTime(d);
	    int dayOfWeek=day.get(Calendar.DAY_OF_WEEK);  
	    if(dayOfWeek==1){ //一周中第一天（周日）
	    	return d;
	    }else{  
	    	int k = 8-dayOfWeek;
	        return new Date(d.getTime() + k * 24 * 60 * 60 * 1000);  
	    }  
	}
	
	public static Date getAfterWeek(Date d){
		return new Date(d.getTime() + 7 * 24 * 60 * 60 * 1000);  
	}
	
	/**
	 * 获取前一天
	 * @return
	 */
	public static Date getPreday(){ 
		Date d=new Date();
	    return new Date(d.getTime() -1 * 24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取前两天
	 * @return
	 */
	public static Date getPreTwoday(){ 
		Date d=new Date();
	    return new Date(d.getTime() -2 * 24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取前一周
	 * @return
	 */
	public static Date getPreWeek(){ 
		Date d=new Date();
	    return new Date(d.getTime() -7 * 24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取某日的前一天
	 * @return
	 */
	public static Date getPreday(Date d){ 
	    return new Date(d.getTime() -1 * 24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取后n天
	 * @return
	 */
	public static Date getNextday(Date d,int n){ 
	    return new Date(d.getTime() +n * 24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取当前月的第一天
	 * @param d
	 * @return
	 */
	public static Date getFirstOfMonth(Date d){
		Calendar day=Calendar.getInstance(); 
	    day.setTime(d);
	    day.add(Calendar.MONTH, 0);
        day.set(Calendar.DAY_OF_MONTH,1);
        return day.getTime();
	}
	
	/**
	 * 获取当前月的最后一天
	 * @param d
	 * @return
	 */
	public static Date getLastOfMonth(Date d){
		Calendar day=Calendar.getInstance(); 
	    day.setTime(d);
	    day.set(Calendar.DAY_OF_MONTH, day.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	    return day.getTime();
	}
	
	/**
	 * 获取下个月的第一天
	 * @param d
	 * @return
	 */
	public static Date getFirstOfNextMonth(Date d){
		Calendar day=Calendar.getInstance(); 
	    day.setTime(d);
	    day.add(Calendar.MONTH, 1);
	    day.set(Calendar.DAY_OF_MONTH, 1);
        return day.getTime();
	}
	
	/** 
     * 获取当年的第一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearFirst(Date d){  
        Calendar currCal=Calendar.getInstance(); 
        currCal.setTime(d);
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearFirst(currentYear);  
    }  
      
    /** 
     * 获取当年的最后一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearLast(Date d){  
        Calendar currCal=Calendar.getInstance(); 
        currCal.setTime(d);
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearLast(currentYear);  
    }  
    
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
}
