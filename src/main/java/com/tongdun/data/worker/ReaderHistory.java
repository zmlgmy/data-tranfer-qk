package com.tongdun.data.worker;

import java.text.ParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.mapper.pgqk.account.RemitFlowMapper;
import com.tongdun.data.mapper.pgqk.user.QkWithdrawTransDao;
import com.tongdun.data.mapper.pgqk.user.TStanStifDao;
import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.utils.ProperitesUtil;
import com.tongdun.data.utils.WorkerUtil;

/**
 * 
 * @author yxw
 *
 */
public class ReaderHistory extends MyThread {

	private Logger logger = LoggerFactory.getLogger(ReaderHistory.class);

	private Object baseDao;

	private DataBlockingQueue dataBlockingQueue;

	private String threadName;

	private Map<String, Object> param;

	public ReaderHistory() {
	}

	public ReaderHistory(Object baseDao, DataBlockingQueue dataBlockingQueue, String threadName,
			Map<String, Object> param) {
		this.dataBlockingQueue = dataBlockingQueue;
		this.baseDao = baseDao;
		this.threadName = threadName;
		this.param = param;
	}

	private long TIME_REP = Long.parseLong(ProperitesUtil.getPropertyValue("time.split"));

	@Override
	public void run() {
		logger.info("开始读取数据");
		String max = param.get("end").toString();
		String min = param.get("start").toString();
		String count = "";
		if(baseDao instanceof TStanStifDao){
			count = ((TStanStifDao) baseDao).getCountByMap(param);
			logger.info("历史数据查询开始时间{},结束时间{},历史数据总数{},database:{},参数信息{}", min, max, count, "pay", param);
		} else if(baseDao instanceof QkWithdrawTransDao) {
			count = ((QkWithdrawTransDao) baseDao).getCountByMap(param);
			logger.info("历史数据查询开始时间{},结束时间{},历史数据总数{},database:{},参数信息{}", min, max, count, "withdraw", param);
		}  else if(baseDao instanceof RemitFlowMapper) {
			count = ((RemitFlowMapper) baseDao).getCountByMap(param);
			logger.info("历史数据查询开始时间{},结束时间{},历史数据总数{},database:{},参数信息{}", min, max, count, "withdraw", param);
		}

		long maxTime = 0;
		long minTime = 0;
		try {
			maxTime = simpleDateFormat.parse(max).getTime() + 10;
			minTime = simpleDateFormat.parse(min).getTime() - 10;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long now = minTime;
		long bigNow = minTime + TIME_REP;
		threadStatus.getTableSize().getAndAdd(count == null ? 0 : Integer.parseInt(count));

		long useStart = System.currentTimeMillis();
		while (now < maxTime) {
			if (bigNow > maxTime) {
				bigNow = maxTime;
			}
			try {
				WorkerUtil.doingReader(baseDao, dataBlockingQueue, now, bigNow, param);
			} catch (Exception e) {
				e.printStackTrace();
			}
			now += TIME_REP;
			bigNow += TIME_REP;
		}
		long useTime = System.currentTimeMillis() - useStart;
		threadStatus.getReadUseTime().addAndGet(useTime);
		threadStatus.getReaderStatus().put(threadName, 0);
		logger.info("历史数据读取完成,共消耗{}ms", useTime);
	}
}
