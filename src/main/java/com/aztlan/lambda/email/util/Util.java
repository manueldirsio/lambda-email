package com.aztlan.lambda.email.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getTodayDate(){
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss ");
		String stringDate= DateFor.format(date);
		return stringDate;
	}
}
