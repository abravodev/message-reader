package com.geevisoft.messagereader.domain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelper {

	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static String datetimeFormatted(Date date){
		return DATETIME_FORMAT.format(date);
	}

}
