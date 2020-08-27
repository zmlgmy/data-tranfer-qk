package com.tongdun.data.worker;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.queue.FailedDataBlockingQueue;
import com.tongdun.data.utils.WorkerUtil;

/**
 * 读线程，写线程，结束监听
 * @author yxw
 *
 */
public class Listener extends MyThread {

    private Logger logger = LoggerFactory.getLogger(Listener.class);

    //原始队列
    DataBlockingQueue dataBlockingQueue ;
    //失败队列
    FailedDataBlockingQueue failDataBlockingQueue ;

    long start;

    public Listener(DataBlockingQueue dataBlockingQueue, FailedDataBlockingQueue failDataBlockingQueue,long start) {
        this.dataBlockingQueue = dataBlockingQueue;
        this.failDataBlockingQueue = failDataBlockingQueue;
        this.start=start;
    }

    @Override
    public void run() {
        while(true){
            long now = System.currentTimeMillis();
            logger.info("应用启动时间：{}ms,总共{}条数据，已读取{}条,已消费{}条,队列中包含:{}条,失败{}条，读取消耗{}s,写消耗{}s",(now-start),threadStatus.getTableSize(),threadStatus.getReadLine(),threadStatus.getWriteLine(),dataBlockingQueue.getSize(),failDataBlockingQueue.getSize(),threadStatus.getReadUseTime().get()/1000l/1000l,threadStatus.getWriteUseTime().get()/1000l/1000l);
            Map<String, Object> threadMap = threadStatus.getThreadMap();
            WorkerUtil.printThreadStatus(threadMap);
            Map<String, Integer> readerStatus = threadStatus.getReaderStatus();
            if(readerStatus!=null){
                boolean isReadOver = true;
                Set<String> keySet = readerStatus.keySet();
                for(String key:keySet){
                    if(readerStatus.get(key)==0){
                        logger.info("线程{},已读取完成",key);
                    }else{
                        isReadOver=false;
                    }
                }
                int size = dataBlockingQueue.getSize();
                int failedSize = failDataBlockingQueue.getSize();
                if(isReadOver&&failedSize==0&&size==0){
                    logger.info("已读取完成,开始停止应用");
                    WorkerUtil.stop(threadMap);
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
