package top.ourck.utils;

import java.util.Date;
import java.sql.Timestamp;

public class TimeUtils {

	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"; 
	
	public static Timestamp d2t(Date date) {
		if(date == null) return null;
		else return new Timestamp(date.getTime());
	}
	
	public static Date t2d(Timestamp ts) {
		if(ts == null) return null;
		else return new Date(ts.getTime());
	}
	
}
