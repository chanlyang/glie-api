package com.gaililie.glieapi.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by lixiaogang on 2018/10/23.
 */
public class UUIDUtil {

    public static String gen() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
}
