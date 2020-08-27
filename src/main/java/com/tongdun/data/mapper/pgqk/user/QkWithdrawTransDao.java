package com.tongdun.data.mapper.pgqk.user;

import java.util.List;
import java.util.Map;

public interface QkWithdrawTransDao {
	
	String getCountByMap(Map<String, Object> map);
	
	List<Map<String, Object>> queryByParamMaps(Map<String, Object> map);
}