package com.tongdun.data.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.tongdun.data.mapper.pgqk.account.RemitFlowMapper;
import com.tongdun.data.mapper.pgqk.user.QkWithdrawTransDao;
import com.tongdun.data.mapper.pgqk.user.TStanStifDao;
import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.queue.FailedDataBlockingQueue;
import com.tongdun.data.singleton.ThreadStatus;
import com.tongdun.data.utils.DateUtils;
import com.tongdun.data.utils.ProperitesUtil;
import com.tongdun.data.utils.PropertiesCommon;
import com.tongdun.data.utils.WorkerUtil;
import com.tongdun.data.worker.FailChecked;
import com.tongdun.data.worker.Listener;
import com.tongdun.data.worker.ReaderHistory;
import com.tongdun.data.worker.ReaderOnTime;
import com.tongdun.data.worker.Writer;

/**
 * 容器启动监听器
 * 
 * @author yxw
 *
 */
public class ApplicationStartupListener implements ApplicationListener<ApplicationEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

	private TStanStifDao tStanStifDao;
	
	private QkWithdrawTransDao qkWithdrawTransDao;
	
	private RemitFlowMapper remitFlowMapper;

	private DataBlockingQueue dataBlockingQueue;

	private FailedDataBlockingQueue failDataBlockingQueue;

	private ThreadStatus threadStatus;

	private Map<String, Integer> readerStatus;

	private Map<String, Integer> writerStatus;

	private final long TICKTIME = 5 * 60 * 1000l;// 每5分钟扫描一次

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			this.doManyThing((ContextRefreshedEvent) event);
		}

	}

	/**
	 * 实时扫表的timer
	 * 
	 * @param
	 */
	private void doTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Map<String, Object> readOnTimeMap = new HashMap<>();
				readOnTimeMap.put("database", PropertiesCommon.DATABASE);
				logger.info("实时数据Map信息--钱客/店小友：" + readOnTimeMap.toString());
				try {
					doManageThingOnTime(readOnTimeMap);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}, 0, TICKTIME);
	}

	/**
	 * 根据时间窗口来扫表
	 * 
	 * @param startAndEndTime1
	 * @throws Exception
	 */
	private void doManageThingOnTime(Map<String, Object> startAndEndTime1) throws Exception {
		Map<String, Object> startAndEndTime = DateUtils.getStartAndEndTime(TICKTIME, TICKTIME);
		startAndEndTime.putAll(startAndEndTime1);
		logger.info("定时任务开始扫表,开始时间:{},结束时间:{}", startAndEndTime.get("start"), startAndEndTime.get("end"));

		// 读线程 读取准实时数据
        Object baseDao=null;
        String[] split = startAndEndTime.get("database").toString().split(",");
        for (String string : split) {
        	switch (string){
        	case "pay":
        		baseDao=this.tStanStifDao;
        		break;
        	case "withdraw":
        		baseDao=this.qkWithdrawTransDao;
        		break;
        	case "remit":
        		baseDao=this.remitFlowMapper;
        		break;
        	}
        	
        	String threadName = PropertiesCommon.READER_ONTIME_THREAD_NAME_PR + 1;
        	dosomething(baseDao, threadName, startAndEndTime);
		}
	}

	private void doManyThing(ContextRefreshedEvent event) {

		this.tStanStifDao = event.getApplicationContext().getBean(TStanStifDao.class);
		
		this.qkWithdrawTransDao = event.getApplicationContext().getBean(QkWithdrawTransDao.class);
		
		this.remitFlowMapper = event.getApplicationContext().getBean(RemitFlowMapper.class);

		// 原始队列
		this.dataBlockingQueue = event.getApplicationContext().getBean(DataBlockingQueue.class);
		// 失败队列
		this.failDataBlockingQueue = event.getApplicationContext().getBean(FailedDataBlockingQueue.class);

//		FieldsMapper fieldsMapper = FieldsMapper.getSingleton();
//		fieldsMapper.init();

		this.threadStatus = ThreadStatus.getSingleton();
		this.readerStatus = threadStatus.getReaderStatus();
		this.writerStatus = threadStatus.getWriterStatus();

		String thisdatabase = PropertiesCommon.DATABASE.toString();

		// 读线程 读取历史数据
		Map<String, Object> readHistoryMap = new HashMap<>();
		readHistoryMap.put("start", PropertiesCommon.HISTORY_START);
		readHistoryMap.put("end", ProperitesUtil.getPropertyValue("history.end"));
		readHistoryMap.put("database",thisdatabase);
		logger.info("历史数据Map信息：" + readHistoryMap.toString());
		if ("true".equals(PropertiesCommon.HISTORY_WORK)) {
			this.startReadThread(threadStatus, readerStatus, ReaderHistory.class, PropertiesCommon.readThreadNum,
					readHistoryMap, thisdatabase);
		}
//        }
		// 读线程 读取实时数据
		if ("true".equals(ProperitesUtil.getPropertyValue("ontime"))) {
			this.doTimer();
		}
		// 写线程 发送数据到API
		this.doEnd();

	}

	public void doEnd() {
		// 写线程
		for (int i = 0; i < PropertiesCommon.writeThreadNum; i++) {
			String threadName = PropertiesCommon.WRITER_THREAD_NAME_PR + i;
			if (writerStatus == null) {
				writerStatus = new HashMap<>();
			}
			writerStatus.put(threadName, 1);
			threadStatus.setWriterStatus(writerStatus);
			Writer writer = new Writer(dataBlockingQueue, failDataBlockingQueue, threadName);
			writer.start();
			threadStatus.getThreadMap().put(threadName, writer);
		}
		// 校验失败并重发
		if ("true".equals(ProperitesUtil.getPropertyValue("failed.checked"))) {
			int propertyValue = Integer.parseInt(ProperitesUtil.getPropertyValue("failed.num"));
			for (int i = 0; i < propertyValue; i++) {
				String threadName = PropertiesCommon.FAILCHECKED_THREAD_NAME_PR + i;
				FailChecked failChecked = new FailChecked(failDataBlockingQueue, threadName);
				failChecked.start();
				threadStatus.getThreadMap().put(threadName, failChecked);
			}
		}
		long start = System.currentTimeMillis();
		// 监听
		Listener listener = new Listener(dataBlockingQueue, failDataBlockingQueue, start);
		listener.start();
	}

	public void startReadThread(ThreadStatus threadStatus, Map<String, Integer> readerStatus, Class<?> clazz,
			int threadNum, Map<String, Object> params, String database) {
		// 读线程
		String threadName = PropertiesCommon.READER_ONTIME_THREAD_NAME_PR + database;
		if (clazz.getName().equals(ReaderHistory.class.getName())) {
			threadName = PropertiesCommon.READER_THREAD_NAME_PR + database;
		}

		if (readerStatus == null) {
			readerStatus = new HashMap<>();
		}
		// 读线程 读取准实时数据
        Object baseDao=null;
        String[] split = params.get("database").toString().split(",");
        for (String string : split) {
        	switch (string){
        	case "pay":
        		baseDao=this.tStanStifDao;
        		break;
        	case "withdraw":
        		baseDao=this.qkWithdrawTransDao;
        		break;
        	case "remit":
        		baseDao=this.remitFlowMapper;
        		break;
        	}
        	
        	readerStatus.put(threadName, 1);
        	threadStatus.setReaderStatus(readerStatus);
        	Constructor<?> constructor = null;
        	try {
        		constructor = clazz.getConstructor(Object.class, DataBlockingQueue.class, String.class, Map.class);
        		Object o = constructor.newInstance(baseDao, dataBlockingQueue, threadName, params);
        		if (o instanceof ReaderHistory) {
        			((ReaderHistory) o).start();
        		} else {
        			((ReaderOnTime) o).start();
        		}
        		threadStatus.getThreadMap().put(threadName, o);
        	} catch (NoSuchMethodException e) {
        		e.printStackTrace();
        	} catch (IllegalAccessException e) {
        		e.printStackTrace();
        	} catch (InstantiationException e) {
        		e.printStackTrace();
        	} catch (InvocationTargetException e) {
        		e.printStackTrace();
        	}
        }
	}

	public void dosomething(Object baseDao, String threadName, Map<String, Object> param) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String max = param.get("end").toString();
		String min = param.get("start").toString();
		logger.info("实时数据参数信息：threadName=" + threadName + ",param" + param.toString());
		String count = "";
		if(baseDao instanceof TStanStifDao){
			count = ((TStanStifDao) baseDao).getCountByMap(param);
		} else if(baseDao instanceof QkWithdrawTransDao) {
			count = ((QkWithdrawTransDao) baseDao).getCountByMap(param);
		} else if(baseDao instanceof RemitFlowMapper) {
			count = ((RemitFlowMapper) baseDao).getCountByMap(param);
		}

		logger.info("开始读取准实时数据,查询开始时间{},结束时间{},dataBase:{},查询总数{}", min, max, param.get("database").toString(), count);
		long maxTime = 0;
		long minTime = 0;
		try {
			maxTime = simpleDateFormat.parse(max).getTime();
			minTime = simpleDateFormat.parse(min).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		threadStatus.getTableSize().getAndAdd(count == null ? 0 : Integer.parseInt(count));
		long useStart = System.currentTimeMillis();
		WorkerUtil.doingReader(baseDao, dataBlockingQueue, minTime, maxTime, param);
		long useTime = System.currentTimeMillis() - useStart;
		logger.info("准实时数据读取完成,共消耗{}ms", useTime);
	}
}