package com.tongdun.data.worker;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.singleton.ThreadStatus;

/**
 * 
 * @author yxw
 *
 */
public class MyThread extends Thread {

    private Object baseDao;

    private DataBlockingQueue dataBlockingQueue;

    private String threadName;

    private Map<String,Object> param;

    public MyThread() {
    }

    public MyThread(Object baseDao,DataBlockingQueue dataBlockingQueue,String threadName,Map<String,Object> param) {
        this.dataBlockingQueue = dataBlockingQueue;
        this.baseDao = baseDao;
        this.threadName = threadName;
        this.param = param;
    }

    ThreadStatus threadStatus = ThreadStatus.getSingleton();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
