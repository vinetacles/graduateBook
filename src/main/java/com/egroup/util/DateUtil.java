package com.egroup.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {
	
	public String getYesterdayYMD_(String date){
		//init func
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar todayCalendar = Calendar.getInstance();
		if(date==null){
			todayCalendar = Calendar.getInstance();
		}else{
			todayCalendar = new GregorianCalendar(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)) - 1,
					Integer.valueOf(date.substring(8, 10)));
		}
		todayCalendar.add(Calendar.DAY_OF_MONTH, -1);	
		return format.format(todayCalendar.getTime());	 
	}
	
	public String ConversionYMDHMSS_ (Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		String DateString = formatter.format(date);
		return DateString ;
	}
	
	public String ConversionYMDHMS (Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String DateString = formatter.format(date);
		return DateString ;
	}
		
	public String ConversionYMDHMS_ (Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		String DateString = formatter.format(date);
		return DateString ;
	}
		
	public String ConversionHMSbyGMT(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		String DateString = formatter.format(date);
		return DateString ;
	}
	
	public String ConversionYMD_join(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String DateString = formatter.format(date);
		return DateString ;
	}
	
	public String ConversionYMD_(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String DateString = formatter.format(date);
		return DateString ;
	}
	
	public String ConversionYM_(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String DateString = formatter.format(date);
		return DateString ;
	}
		
	public Date StringtoYMD_(String dateString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);
		return date;
	}
	
	public String MDYtoYMD(String dateString) throws ParseException{
		SimpleDateFormat MDYformatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat YMDformatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = MDYformatter.parse(dateString);
		String resultDateString = YMDformatter.format(date);
		return resultDateString;
	}
	
	public Date StringtoYMDHMS_(String dateString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse(dateString);
		return date;
	}
	
	public Date StringtoYMDHMSS_(String dateString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date date = formatter.parse(dateString);
		return date;
	}
}
