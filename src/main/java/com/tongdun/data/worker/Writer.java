package com.tongdun.data.worker;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.queue.FailedDataBlockingQueue;
import com.tongdun.data.utils.ProperitesUtil;
import com.tongdun.data.utils.WorkerUtil;

/**
 * 
 * @author yxw
 *
 */
public class Writer extends MyThread {

	private Logger logger = LoggerFactory.getLogger(Writer.class);

	private DataBlockingQueue dataBlockingQueue;

	private FailedDataBlockingQueue failDataBlockingQueue;

	private String threadName;

	public Writer(DataBlockingQueue dataBlockingQueue, FailedDataBlockingQueue failDataBlockingQueue,
			String threadName) {
		this.dataBlockingQueue = dataBlockingQueue;
		this.failDataBlockingQueue = failDataBlockingQueue;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		while (true) {
			// 如果队列为空，会阻塞当前线程
			Map<String, Object> dataDO = null;
			try {
				dataDO = dataBlockingQueue.takeQueue();
//				logger.info(dataDO.toString());
				boolean flag = WorkerUtil.doSomething(dataDO, threadStatus);
				if (!flag && "true".equals(ProperitesUtil.getPropertyValue("failed.checked"))) {
					threadStatus.getFailedLine().addAndGet(1);
					// 记录失败请求
					logger.info("线程{}数据{}发送失败，加入失败队列", threadName, dataDO.toString());
					failDataBlockingQueue.putQueue(dataDO);
				} else {
					threadStatus.getWriteLine().addAndGet(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getThreadName() {
		return threadName;
	}

}
