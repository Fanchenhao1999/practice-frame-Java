package com.fan.utils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringToUtils {
    public static Map<String, Object> convert(String str, String regex) {
        Map<String, Object> resultMap= new LinkedHashMap<>();
        if (StringUtils.isNotBlank(str)) {
            String[] stringArray = str.split(regex);
            for(int i=0;i<stringArray.length;i++) {
                resultMap.put(stringArray[i].split("=")[0], stringArray[i].split("=")[1]);
            }

        }
        return resultMap;
    }
}


