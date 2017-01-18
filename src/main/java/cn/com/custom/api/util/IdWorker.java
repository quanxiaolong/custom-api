/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

/**
 * <p>标题：IdWorker </p>
 * <p>
 *    功能描述：Twitter的分布式自增ID算法snowflake (Java版)
 *    {@link http://www.cnblogs.com/relucent/p/4955340.html}
 * </p>
 * <p>创建日期：2017年1月18日下午2:20:08</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class IdWorker {
	
	private final long twepoch = 1288834974657L;
	private final long workerIdBits = 5L;
	private final long datacenterIdBits = 5L;
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private final long sequenceBits = 12L;
	private final long workerIdShift = sequenceBits;
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);
 
	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;
 
	public IdWorker(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}
 
	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format("Clock moved backwards.	Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}
 
		lastTimestamp = timestamp;
 
		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		IdWorker idWorker = new IdWorker(0, 0);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(id);
		}
	}

}
