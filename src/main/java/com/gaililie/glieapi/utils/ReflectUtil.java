package com.gaililie.glieapi.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 反射工具类
 */
public final class ReflectUtil {

    private ReflectUtil(){}

    /**
     * 反射获取对象属性和值，目前只筛选String类型且不为空的
     * @param source
     * @return
     */
    public static Map<String, Object> readNotNullFiledAndValMap(Object source) {
        Map<String, Object> map = new HashMap<>();
        if (source == null){
            return map;
        }
        Field[] fields = source.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            try {
                Field field = fields[i];
                // 对于每个属性，获取属性名
                String varName = field.getName();
                if ("serialVersionUID".equals(varName)){
                    continue;
                }
                // 获取原来的访问控制权限
                boolean accessFlag = field.isAccessible();
                // 修改访问控制权限
                field.setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = field.get(source);
                if (o != null) {
                    if (o instanceof String){
                        String value = o.toString();
                        if (StringUtils.isNotEmpty(value)){
                            map.put(varName, o);
                        }
                    }
                }
                // 恢复访问控制权限
                field.setAccessible(accessFlag);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}
