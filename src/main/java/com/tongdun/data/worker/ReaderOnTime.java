package com.tongdun.data.worker;

import java.text.ParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.mapper.pgqk.account.RemitFlowMapper;
import com.tongdun.data.mapper.pgqk.user.QkWithdrawTransDao;
import com.tongdun.data.mapper.pgqk.user.TStanStifDao;
import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.utils.WorkerUtil;

/**
 * 
 * @author yxw
 *
 */
public class ReaderOnTime extends MyThread {

	private Logger logger = LoggerFactory.getLogger(ReaderOnTime.class);

	private Object baseDao;

	private DataBlockingQueue dataBlockingQueue;

	private String threadName;

	private Map<String, Object> param;

	public ReaderOnTime() {
	}

	public ReaderOnTime(Object baseDao, DataBlockingQueue dataBlockingQueue, String threadName,
			Map<String, Object> param) {
		this.dataBlockingQueue = dataBlockingQueue;
		this.baseDao = baseDao;
		this.threadName = threadName;
		this.param = param;
	}

	@Override
	public void run() {
		logger.info("开始读取准实时数据");
		String max = param.get("end").toString();
		String min = param.get("start").toString();
		String count = "";
		if(baseDao instanceof TStanStifDao){
			count = ((TStanStifDao) baseDao).getCountByMap(param);
		} else if(baseDao instanceof QkWithdrawTransDao) {
			count = ((QkWithdrawTransDao) baseDao).getCountByMap(param);
		} else if(baseDao instanceof RemitFlowMapper) {
			count = ((RemitFlowMapper) baseDao).getCountByMap(param);
		}

		logger.info("数据查询开始时间{},结束时间{}", min, max);
		long maxTime = 0;
		long minTime = 0;
		try {
			maxTime = simpleDateFormat.parse(max).getTime();
			minTime = simpleDateFormat.parse(min).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		threadStatus.getTableSize().getAndAdd(count == null ? 0 : Integer.parseInt(count));

		long useStart = System.nanoTime();
		try {
			WorkerUtil.doingReader(baseDao, dataBlockingQueue, minTime, maxTime, param);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		long useTime = System.nanoTime() - useStart;
		threadStatus.getReadUseTime().addAndGet(useTime);
		threadStatus.getReaderStatus().put(threadName, 0);
		logger.info("数据读取完成,共消耗{}ms", useTime);
	}
}
