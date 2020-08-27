package com.tongdun.data.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 * 
 * @author yxw
 *
 */
public class DateUtils {

	private static final SimpleDateFormat HHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static final SimpleDateFormat HHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 根据当前时间获取开始时间结束事件 endTime = now - toEnd startTime =now - toEnd - startToEnd
	 * 
	 * @param toEnd
	 * @param startToEnd
	 */
	public static Map<String, Object> getStartAndEndTime(long toEnd, long startToEnd) {
		Map<String, Object> startAndEndTime = getStartAndEndTime(null, toEnd, startToEnd);
		return startAndEndTime;
	}

	/**
	 * 根据date获取开始时间结束事件 endTime = date - toEnd startTime =date - toEnd - startToEnd
	 * 
	 * @param date
	 * @param toEnd
	 * @param startToEnd
	 */
	public static Map<String, Object> getStartAndEndTime(Date date, long toEnd, long startToEnd) {
		SimpleDateFormat HHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		SimpleDateFormat HHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (date != null) {
			now = date;
		}

		String parse = HHMM.format(now);
		parse = parse + ":00";
		String startStr = "";
		String endStr = "";
		try {
			Date parse1 = HHMMSS.parse(parse);
			long start = parse1.getTime() - toEnd - startToEnd;
			long end = parse1.getTime() - toEnd;
			startStr = HHMMSS.format(new Date(start));
			endStr = HHMMSS.format(new Date(end));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, Object> results = new HashMap<>();
		results.put("start", startStr);
		results.put("end", endStr);
		return results;
	}

//    public static void main(String[] args) {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                getStartAndEndTime(1000l*60,1000l*60*2);
//            }
//        },0,2000l*60);
//
//    }
}
