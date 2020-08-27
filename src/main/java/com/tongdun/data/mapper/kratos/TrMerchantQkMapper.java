package com.tongdun.data.mapper.kratos;

import java.util.List;
import java.util.Map;

import com.tongdun.data.entity.PgQkUser;

public interface TrMerchantQkMapper {
    
    int batchInsert(List<PgQkUser> list);
    
    int insert(PgQkUser pgUser);

    int selectCountByParams(Map<String,Object> params);
}