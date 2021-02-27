package org.example.core.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 20:59
 */
@Slf4j
public class BeanUtils {

    public  static Map<String, Object> beanToMap(Object bean){
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try{
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(bean));
            }
        }catch (Exception e){
            log.error("BeanUtils beanToMap error"+e.getMessage());
        }

        return map;
    }
}
