package com.jeeplus.modules.xhreception.pay;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

@SuppressWarnings("unchecked")
public class MySimpleDateFormat {

	private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private ThreadLocal threadLocal = new ThreadLocal();
	
	public MySimpleDateFormat(String format_str){
		DATE_FORMAT = format_str ;
	}

	public DateFormat getDateFormat()
	{
		DateFormat df = (DateFormat) threadLocal.get();
		if(df==null){
			df = new SimpleDateFormat(DATE_FORMAT);
			threadLocal.set(df);
		}
		return df;
	}

	public Date parse(String textDate) throws ParseException {
		return getDateFormat().parse(textDate);
	}
	
	public String format(Date d){
		return getDateFormat().format(d);
	}
	
	public String format(Object d){
		return getDateFormat().format(d);
	}
	
	public StringBuffer format(Date d,StringBuffer toAppendTo,FieldPosition fieldPosition){			
		return getDateFormat().format(d, toAppendTo, fieldPosition);
	}
	
	public StringBuffer format(Object d,StringBuffer toAppendTo,FieldPosition fieldPosition){			
		return getDateFormat().format(d, toAppendTo, fieldPosition);
	}
	public static void main(String[] args) throws ParseException {
		
		Date rtDate = null;
        String tmpString ="2018-02-06 14:34:01";
        rtDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(tmpString);
        
        System.out.println(rtDate.toString());
	}
}

