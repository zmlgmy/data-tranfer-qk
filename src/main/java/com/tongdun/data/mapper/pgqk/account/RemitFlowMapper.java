package com.tongdun.data.mapper.pgqk.account;

import java.util.List;
import java.util.Map;

public interface RemitFlowMapper {
	
	String getCountByMap(Map<String, Object> map);

	List<Map<String, Object>> queryByParamMaps(Map<String, Object> map);
}