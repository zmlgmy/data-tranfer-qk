package com.tongdun.data.mapper.kratos;

import com.tongdun.data.entity.Rule;

public interface RuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKeyWithBLOBs(Rule record);

    int updateByPrimaryKey(Rule record);
}