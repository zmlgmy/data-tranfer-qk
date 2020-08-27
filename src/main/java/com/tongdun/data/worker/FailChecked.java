package com.tongdun.data.worker;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.queue.FailedDataBlockingQueue;
import com.tongdun.data.utils.WorkerUtil;

/**
 * 
 * @author yxw
 *
 */
public class FailChecked extends MyThread{

    private Logger logger = LoggerFactory.getLogger(FailChecked.class);

    private FailedDataBlockingQueue failDataBlockingQueue;

    private String threadName;

    public FailChecked() {
    }


    public FailChecked(FailedDataBlockingQueue failDataBlockingQueue , String threadName){
        this.failDataBlockingQueue=failDataBlockingQueue;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while(true){
            //拉取失败数据
            Map<String, Object> data=null;
            try {
                data = failDataBlockingQueue.takeQueue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long hitSize=0;
            if(hitSize==0){
                boolean b = WorkerUtil.doSomething(data, threadStatus);
                if(!b){
                    try {
                        failDataBlockingQueue.putQueue(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    threadStatus.getFailedLine().getAndDecrement();
                    threadStatus.getWriteLine().getAndAdd(1);
                }
            }else{
                threadStatus.getFailedLine().getAndDecrement();
                threadStatus.getWriteLine().getAndAdd(1);
            }
            //已经写入则不做任何写入
        }
    }
}
