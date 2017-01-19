package com.muy.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanglikai on 2017/1/17.
 */
public final class JsonUtil {
    private static final JsonFactory factory = new JsonFactory();
    private static final ObjectMapper writeMapper = new ObjectMapper(factory);
    private static final ObjectMapper readMapper = new ObjectMapper(factory);

    public static String createJSONString(Object params) {
        return writeValueAsString(params);
    }

    public static Map<String, Object> createJSONMap(String inTarget) {
        Map<String, Object> value = readValue(inTarget, Map.class);
        return value == null ? new HashMap<String, Object>() : value;
    }

    public static <T> T createJSONClass(String inTarget, Class<T> valueType) {
        return readValue(inTarget, valueType);
    }

    public static <T> List<T> createJSONList(String inTarget, Class<T> valueType) {
        JavaType javaType = readMapper.getTypeFactory().constructCollectionType(ArrayList.class, valueType);
        return readValue(inTarget, javaType);
    }

    private static String writeValueAsString(Object params) {
        try {
            return writeMapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static <T> T readValue(String inTarget, Class<T> valueType) {
        try {
            return readMapper.readValue(inTarget, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <T> T readValue(String inTarget, JavaType javaType) {
        try {
            return readMapper.readValue(inTarget, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
