package com.jeeplus.modules.xhreception.pay;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;



public class StringUtil {
	private static int index = 0;
	private static Map<String, Integer> seeds = new HashMap<String, Integer>();

	

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String number) {
		Pattern pattern = Pattern.compile("[\\d\\.]+");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(isNumber("234"));
	}
	
	public static String isNull(String str){
		if(str==null){
			return "";
		}
		return str;
	}
	
	public static String isNull(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = sdf.format(date);
		return result;
	}
	
	public static String isNull(Double d){
		if(d==null){
			return "0.00";
		}
		return new BigDecimal(d.toString()).toString();
	}
	
	public static String isNull(BigDecimal d){
		if(d==null){
			return "0.00";
		}
		return d.toString();
	}
	
	public static String listToString(List list) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append("'"+ list.get(i) + "',");  
	            } else {  
	                sb.append("'"+list.get(i)+"'");  
	            }  
	        }  
	    }  
	    return sb.toString();  
	}  
	
	public static String noncrStr(int length){
		StringBuilder builder = new StringBuilder(length);  
		for (int i = 0; i < length; i++) {
			builder.append(RandomStringUtils.randomNumeric(1));
		}
		return builder.toString();
	}
}
