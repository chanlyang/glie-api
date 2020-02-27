package com.gaililie.glieapi.model.enums;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 枚举类接口与值映射
 *
 */
public interface EnumTypeIdentity {

    /**
     *
     * 枚举值与底层实际值 int 的转化关系
     *
     * @return
     */
    int getValue();

    Map<Class<? extends EnumTypeIdentity>, ImmutableMap<Integer, ? extends EnumTypeIdentity>> cachedMapping = Maps.newConcurrentMap();

    static <T extends EnumTypeIdentity> T ofType(Class<T> type, int value){
        if(type == null || !type.isEnum() || !EnumTypeIdentity.class.isAssignableFrom(type)){
            throw new RuntimeException("enum type assignableFrom EnumTypeIdentity.class is required");
        }
        ImmutableMap<Integer, ? extends EnumTypeIdentity> cachedItemGroup = cachedMapping.get(type);

        if(cachedItemGroup == null){
            try {
                Method values = type.getDeclaredMethod("values", null);
                T[] valuesObject = (T[]) values.invoke(null);
                cachedItemGroup = Maps.uniqueIndex(Arrays.asList(valuesObject), it -> it.getValue());
                cachedMapping.put(type, cachedItemGroup);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return (T) cachedItemGroup.get(value);
    }
}
