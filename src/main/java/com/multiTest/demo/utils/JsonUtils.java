package com.multiTest.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.boot.json.JsonParseException;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author yangxiaoxu04
 * @date 2022/4/17 10:19 下午
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = defaultObjectMapper(new ObjectMapper());

    private JsonUtils() {
    }

    public static <T> String toJson(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // 使用spring的异常类
            throw new JsonParseException(e);
        }
    }

    public static <T> String toFormatJson(T object) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // 使用spring的异常类
            throw new JsonParseException(e);
        }
    }

    public static <T> List<T> toList(String json) {
        return fromJson(json, new TypeReference<List<T>>() {
        });
    }


    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return (T) MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            // 使用spring的异常类
            throw new JsonParseException(e);
        }
    }

    /**
     * 处理泛型
     *
     * @param jsonString
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(jsonString, typeReference);
        } catch (JsonProcessingException e) {
            // 使用spring的异常类
            throw new JsonParseException(e);
        }
    }

    public static ObjectMapper defaultObjectMapper(ObjectMapper objectMapper) {
        objectMapper.findAndRegisterModules();
        // 日期序列化为数字
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 序列化不支持的类型不报错
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 仅序列化非空字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.enable(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS);

        // 时间设置系统默认时区
        objectMapper.setTimeZone(TimeZone.getDefault());

        // 默认的filter
        objectMapper.setFilterProvider(new SimpleFilterProvider().setDefaultFilter(SimpleBeanPropertyFilter.serializeAll()));

        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

        return objectMapper;
    }
}
