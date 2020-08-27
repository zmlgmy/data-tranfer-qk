package com.tongdun.data.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tongdun.data.entity.PgQkUser;
import com.tongdun.data.mapper.kratos.TrMerchantQkMapper;
import com.tongdun.data.mapper.pgqk.user.PgQkUserMapper;
import com.tongdun.data.utils.ProperitesUtil;

@RestController
public class ImportMemberService {

	private static Logger logger = LoggerFactory.getLogger(ImportMemberService.class);
	private String qkMemberFlag = ProperitesUtil.getPropertyValue("switch.trmember.qk");
	private String qkHistoryMemberFlag = ProperitesUtil.getPropertyValue("switch.history.trmember.qk");
	private int batchSize = Integer.parseInt(ProperitesUtil.getPropertyValue("import.trmember.batch.size"));
	private static int day = Integer.parseInt(ProperitesUtil.getPropertyValue("switch.day"));

	@Autowired
	private PgQkUserMapper pgQkUserMapper;

	@Autowired
	private TrMerchantQkMapper trMerchantQkMapper;

	@Scheduled(cron = "${schedule.member.import.task}")
	public void exportMember() {

		// qk_member
		if (qkMemberFlag.equals("true")) {
			logger.info("拉取实时商户定时任务开始，开始时间为：" + ProperitesUtil.getPropertyValue("schedule.member.import.task") + ", day:" + day);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
//			int trCount = trMerchantQkMapper.selectCountByParams(null);
//			if (trCount > 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, day);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				map.put("createTime", sdf.format(calendar.getTime()));
//			}
			Integer qkCount = pgQkUserMapper.selectCountByParams(map);
			logger.info("qk_member count-----------" + qkCount);
			int offset = 0;
//			int size = 100;
			if (qkCount > batchSize) {
		        while (true) {
		        	map.put("offset", offset);
		        	map.put("size", batchSize);
		        	logger.info("history qk_member 剩余待拉取----------->>" + qkCount);
		        	List<PgQkUser> qkUsers = pgQkUserMapper.selectAllByParams(map);
		            if (CollectionUtils.isEmpty(qkUsers)) {
		            	break;
		            } else {
		                offset += batchSize;
		                try {
		                	trMerchantQkMapper.batchInsert(qkUsers);
						} catch (Exception e) {
							logger.error("insert error ========>>"+JSON.toJSONString(qkUsers));
						}
		            }
		            qkCount -= batchSize;
		        }
			} else {
				map.put("offset", offset);
	        	map.put("size", batchSize);
	        	logger.info("history qk_member 剩余待拉取----------->>" + qkCount);
				List<PgQkUser> qkUsers = pgQkUserMapper.selectAllByParams(map);
				if (!CollectionUtils.isEmpty(qkUsers)) {					
					trMerchantQkMapper.batchInsert(qkUsers);
				}
				qkCount -= qkCount;
			}

			logger.info("qk_member增量数据处理完毕~");
		}
	}
	
	@Scheduled(cron = "${schedule.history.member.import.task}")
	public void exportHistoryMember() {

		// qk_member
		if (qkHistoryMemberFlag.equals("true")) {
			logger.info("拉取历史商户定时任务开始，开始时间为：" + ProperitesUtil.getPropertyValue("schedule.history.member.import.task"));
			Map<String, Object> map = new HashMap<String, Object>();
			Integer qkCount = pgQkUserMapper.selectCountByParams(map);
			logger.info("history qk_member count-----------" + qkCount);
			int offset = 0;
//			int size = 1;
			if (qkCount > batchSize) {
		        while (true) {
		        	map.put("offset", offset);
		        	map.put("size", batchSize);
		        	logger.info("history qk_member 剩余待拉取----------->>" + qkCount);
		        	List<PgQkUser> qkUsers = pgQkUserMapper.selectAllByParams(map);
		        	logger.info("history qk_member qkUsers-----------" + JSON.toJSONString(qkUsers));
		            if (CollectionUtils.isEmpty(qkUsers)) {
		            	break;
		            } else {
		                offset += batchSize;
		                try {
		                	trMerchantQkMapper.batchInsert(qkUsers);
						} catch (Exception e) {
							if(batchSize > 1) {
								for (PgQkUser pgQkUser : qkUsers) {
									try {
										trMerchantQkMapper.insert(pgQkUser);
									} catch (Exception e2) {
										logger.error("insert error ========>>"+JSON.toJSONString(pgQkUser));
									}
								}
							} else {								
								logger.error("batchInsert error ========>>"+JSON.toJSONString(qkUsers));
							}
						}
		            }
		            qkCount -= batchSize;
		        }
			} else {
				map.put("offset", offset);
	        	map.put("size", batchSize);
	        	logger.info("history qk_member 剩余待拉取----------->>" + qkCount);
				List<PgQkUser> qkUsers = pgQkUserMapper.selectAllByParams(map);
				if (!CollectionUtils.isEmpty(qkUsers)) {					
					trMerchantQkMapper.batchInsert(qkUsers);
				}
				qkCount -= qkCount;
			}

			logger.info("qk_member增量数据处理完毕~");
		}
	}

}
