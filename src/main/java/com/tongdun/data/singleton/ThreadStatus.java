package com.tongdun.data.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程状态
 * @author yxw
 *
 */
public class ThreadStatus {

    private String tableStartTime ; //表最小开始时间
    private String tableEndTime; //表最大时间
    private AtomicInteger tableSize = new AtomicInteger(0); //表大小
    private AtomicInteger readLine = new AtomicInteger(0);//已读历史取条数
    private AtomicInteger readLineOnTime = new AtomicInteger(0);//已读实时取条数
    private AtomicInteger writeLine =new AtomicInteger(0);//已写入条数
    private AtomicInteger failedLine =new AtomicInteger(0);//失败条数
    private Map<String,Integer> readerStatus;// 0:结束,1:运行
    private Map<String,Integer> writerStatus;// 0:结束,1:运行
    private AtomicLong readUseTime = new AtomicLong(0); //读取耗时
    private AtomicLong writeUseTime = new AtomicLong(0); //写耗时
    private Map<String,Object> threadMap = new HashMap<>();
    private AtomicInteger totalTime=new AtomicInteger(0);

    public AtomicInteger getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(AtomicInteger totalTime) {
        this.totalTime = totalTime;
    }

    public static ThreadStatus getThreadStatus() {
        return threadStatus;
    }

    public static void setThreadStatus(ThreadStatus threadStatus) {
        ThreadStatus.threadStatus = threadStatus;
    }

    private ThreadStatus() {
    }

    private volatile static ThreadStatus threadStatus;

    public AtomicInteger getReadLineOnTime() {
        return readLineOnTime;
    }

    public void setReadLineOnTime(AtomicInteger readLineOnTime) {
        this.readLineOnTime = readLineOnTime;
    }

    public Map<String, Object> getThreadMap() {
        return threadMap;
    }

    public void setThreadMap(Map<String, Object> threadMap) {
        this.threadMap = threadMap;
    }

    public static ThreadStatus getSingleton(){
        if(threadStatus==null){
            synchronized (ThreadStatus.class){
                if (threadStatus == null) {
                    threadStatus = new ThreadStatus();
                }
            }
        }
        return threadStatus;
    }

    public AtomicLong getReadUseTime() {
        return readUseTime;
    }

    public void setReadUseTime(AtomicLong readUseTime) {
        this.readUseTime = readUseTime;
    }

    public AtomicLong getWriteUseTime() {
        return writeUseTime;
    }

    public void setWriteUseTime(AtomicLong writeUseTime) {
        this.writeUseTime = writeUseTime;
    }

    public String getTableStartTime() {
        return tableStartTime;
    }

    public void setTableStartTime(String tableStartTime) {
        this.tableStartTime = tableStartTime;
    }

    public String getTableEndTime() {
        return tableEndTime;
    }

    public void setTableEndTime(String tableEndTime) {
        this.tableEndTime = tableEndTime;
    }

    public AtomicInteger getTableSize() {
        return tableSize;
    }

    public void setTableSize(AtomicInteger tableSize) {
        this.tableSize = tableSize;
    }

    public AtomicInteger getReadLine() {
        return readLine;
    }

    public void setReadLine(AtomicInteger readLine) {
        this.readLine = readLine;
    }

    public AtomicInteger getWriteLine() {
        return writeLine;
    }

    public void setWriteLine(AtomicInteger writeLine) {
        this.writeLine = writeLine;
    }

    public AtomicInteger getFailedLine() {
        return failedLine;
    }

    public void setFailedLine(AtomicInteger failedLine) {
        this.failedLine = failedLine;
    }

    public Map<String, Integer> getReaderStatus() {
        return readerStatus;
    }

    public void setReaderStatus(Map<String, Integer> readerStatus) {
        this.readerStatus = readerStatus;
    }

    public Map<String, Integer> getWriterStatus() {
        return writerStatus;
    }

    public void setWriterStatus(Map<String, Integer> writerStatus) {
        this.writerStatus = writerStatus;
    }
}
