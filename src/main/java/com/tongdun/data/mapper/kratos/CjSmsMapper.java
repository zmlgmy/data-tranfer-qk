package com.tongdun.data.mapper.kratos;

import java.util.Map;

import com.tongdun.data.entity.CjSms;

public interface CjSmsMapper {
	
	Integer selectCountByParams(Map<String, Object> map);
	
    int deleteByPrimaryKey(Integer id);

    int insert(CjSms record);

    int insertSelective(CjSms record);

    CjSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CjSms record);

    int updateByPrimaryKey(CjSms record);
}