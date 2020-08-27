package com.tongdun.data.controler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tongdun.data.mapper.pgqk.user.TStanStifDao;
import com.tongdun.data.utils.ProperitesUtil;
import com.tongdun.data.utils.PropertiesCommon;


@RestController
public class UserController {
	
	@Autowired
	private TStanStifDao tStanStifDao;
	
	@RequestMapping("/getUsersList")
	public void getUsersList() {
	}
	
	@RequestMapping("/getStifList")
	public List<Map<String,Object>> getStifList() {
		Map<String,Object> readHistoryMap = new HashMap<>();
		readHistoryMap.put("start",PropertiesCommon.HISTORY_START);
		readHistoryMap.put("end",ProperitesUtil.getPropertyValue("history.end"));
		return tStanStifDao.queryByParamMaps(readHistoryMap);
	}
	
	@RequestMapping("/getCount")
	public String getCountByStif() {
		Map<String,Object> readHistoryMap = new HashMap<>();
		readHistoryMap.put("start",PropertiesCommon.HISTORY_START);
		readHistoryMap.put("end",ProperitesUtil.getPropertyValue("history.end"));
		String countByMap = tStanStifDao.getCountByMap(readHistoryMap);
		return countByMap;
	}
}