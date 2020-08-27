package com.tongdun.data.utils;

/**
 * 
 * @author yxw
 *
 */
public class PropertiesCommon {

	public static final int readThreadNum = Integer.parseInt(ProperitesUtil.getPropertyValue("read.thread.num"));

	public static final int writeThreadNum = Integer.parseInt(ProperitesUtil.getPropertyValue("write.thread.num"));

//	public static final String querySql = ProperitesUtil.getPropertyValue("forseti.querySql");
//
	public static final String HISTORY_START = ProperitesUtil.getPropertyValue("history.start");
//
//	public static final String appName = ProperitesUtil.getPropertyValue("forseti.querySql.appName");
//
//	public static final String secretKey = ProperitesUtil.getPropertyValue("forseti.querySql.secretKey");
//
//	public static final String eventId = ProperitesUtil.getPropertyValue("forseti.querySql.eventId");

	public static final String READER_THREAD_NAME_PR = "Reader-History-Thread-";

	public static final String READER_ONTIME_THREAD_NAME_PR = "Reader-Ontime-Thread-";

	public static final String WRITER_THREAD_NAME_PR = "Writer-Thread-";

	public static final String FAILCHECKED_THREAD_NAME_PR = "Writer-Failed-Thread-";

	public static final String EVENT_OCCUR_TIME = ProperitesUtil.getPropertyValue("eventOccurTime");

	public static final String DATABASE = ProperitesUtil.getPropertyValue("forseti.querySql.database");

	public static final String DEFAULT_EVENT_ID = ProperitesUtil.getPropertyValue("default.eventId");

	public static final String QK_DXY_EVENT_ID = ProperitesUtil.getPropertyValue("qk.dxy.eventId");

	public static final String QK_SECRET_KEY = ProperitesUtil.getPropertyValue("qk.secretKey");

	public static final String DXY_SECRET_KEY = ProperitesUtil.getPropertyValue("dxy.secretKey");

	public static final String DEFAULT_APPNAME = ProperitesUtil.getPropertyValue("default.appName");

	public static final String HISTORY_WORK = ProperitesUtil.getPropertyValue("history.worker");

	
}
