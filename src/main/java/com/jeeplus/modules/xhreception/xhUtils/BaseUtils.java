package com.jeeplus.modules.xhreception.xhUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BaseUtils {
	
	public static List removeNull(String[] arrayString){
		List<String> list1 = new ArrayList<String>();
		for(int i=0;i<arrayString.length;i++){
			if(arrayString[i]!=null && arrayString[i].length()!=0){
				list1.add(arrayString[i]);
			}
		}
		return list1;
	}
	
	public static void main(String[] str) throws ParseException{
		String content = "0001";
		Date date = new Date();
		String no = DateUtils.dateFormat(date, DateUtils.DATE_TIME_PATTERNS)+content;
		String last = no.substring(no.length()-4, no.length());
		System.out.println(last);
		String first = no.substring(0,8);
		System.out.println(first);
		System.out.println(no);
	}
	
	public static String creatOrder() throws ParseException{
		Date date = new Date();
		return DateUtils.dateFormat(date, DateUtils.DATE_TIME_PATTERNS);
	}
}
