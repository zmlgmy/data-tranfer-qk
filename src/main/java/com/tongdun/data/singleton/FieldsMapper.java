package com.tongdun.data.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.utils.ProperitesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yxw
 *
 */
public class FieldsMapper {

    private volatile static FieldsMapper fieldsMapper;

    private FieldsMapper() {
    }

    private Logger logger = LoggerFactory.getLogger(FieldsMapper.class);

    private Map<String, String> fieldsMappers = new HashMap<>();

    public void init() {
        String queryFields = ProperitesUtil.getPropertyValue("query.fields");
        String systemFields = ProperitesUtil.getPropertyValue("system.fields");
        if (queryFields != null && systemFields != null) {
            String[] querys = queryFields.split(",");
            String[] systems = systemFields.split(",");
            if (querys.length != systems.length) {
                logger.error("query.fields and system.fields lenght is diff");
            } else {
                for (int i = 0; i < querys.length; i++) {
                    fieldsMappers.put(querys[i], systems[i]);
                }
            }
        } else {
            logger.error("query.fields and system.fields is null");
        }
        logger.info("init end");
    }

    public Map<String, String> getFieldsMapper() {
        return fieldsMappers;
    }

    public static FieldsMapper getSingleton() {
        if (fieldsMapper == null) {
            synchronized (FieldsMapper.class) {
                if (fieldsMapper == null) {
                    fieldsMapper = new FieldsMapper();
                }
            }
        }
        return fieldsMapper;
    }

}