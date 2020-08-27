/**
 * 
 */
package com.tongdun.data.queue;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 生产者消费者操作队列类 FIFO
 * @author yxw
 *
 */
@Component
public class DataBlockingQueue {

	private Logger logger = LoggerFactory.getLogger(DataBlockingQueue.class);
	
	 // 最大队列数
	 public static final Integer MAX_QUEUE_COUNT = 10000 * 50;
	 // 声明队列
	 private BlockingQueue<Map<String,Object>> queue = new LinkedBlockingQueue<Map<String,Object>>(MAX_QUEUE_COUNT);
	 // 统计已经消费数据数目
	 private AtomicInteger takeCount = new AtomicInteger(0); 
	 // 统计发送失败数据数目
	 private AtomicInteger faildCount = new AtomicInteger(0);

	public DataBlockingQueue() {
		logger.info("创建");
	}

	/**
	  * 添加数据进队列
	  * @param blackListDO
	 * @throws InterruptedException 
	  */
	 public void putQueue(Map<String,Object> blackListDO) throws InterruptedException{
		 queue.put(blackListDO);
	 }
	 
	 /**
	  * 消费队列数据
	  * @return
	  * @throws InterruptedException
	  */
	 public Map<String,Object> takeQueue() throws InterruptedException{
		 return  queue.take();
	 }

	 public Integer getSize(){
	 	return this.queue.size();
	 }
	 
	 /**
	  * 获取队列
	 * @return 
	  */
	 public BlockingQueue<Map<String,Object>> getQueue(){
		 return queue;
	 }
	 
	 /**
	  * 获取已经取出队列的记录数
	  * @return
	  */
	 public Integer getTakeCount(){
		 return takeCount.intValue();
	 }
	 
	 /**
	  * 消费失败数增加1
	  * @return
	  */
	 public void incrementFaildCount(){
		 this.faildCount.incrementAndGet(); 
	 }
	 
	 /**
	  * 获取失败数
	  * @return
	  */
	 public Integer getFaildCount(){
		 return faildCount.intValue();
	 }
	 
}
