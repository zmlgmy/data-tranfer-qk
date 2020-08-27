package com.tongdun.data.mapper.pgqk.user;

import java.util.List;
import java.util.Map;

import com.tongdun.data.entity.PgQkUser;

public interface PgQkUserMapper {

    PgQkUser selectByPrimaryKey(Long id);
    
    List<PgQkUser> selectAllByParams(Map<String, Object> map);
    
    Integer selectCountByParams(Map<String, Object> map);

}