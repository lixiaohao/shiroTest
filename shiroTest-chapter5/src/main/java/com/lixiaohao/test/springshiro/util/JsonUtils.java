package com.lixiaohao.test.springshiro.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: shiroTest
 * @description:
 * @author: xiaohao.li
 * @create: 2018-07-27 16:21
 **/

public class JsonUtils {

    private  static ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 对象转换成json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T>String objectToJson(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to Json error",e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转换成格式化的json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T>String objectToJsonPretty(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to Json error",e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象Class
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T jsonToObject(String src,Class<T> clazz){
        if(StringUtils.isEmpty(src) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) src : objectMapper.readValue(src,clazz);
        } catch (Exception e) {
            log.warn("Parse Json to Object error",e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象TypeReference
     * @param src
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T>T jsonToObject(String src, TypeReference<T> typeReference){
        if(StringUtils.isEmpty(src) || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception e) {
            log.warn("Parse Json to Object error",e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象
     * @param src
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T>T jsonToObject(String src, Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
        try {
            return objectMapper.readValue(src,javaType);
        } catch (Exception e) {
            log.warn("Parse Json to Object error",e);
            e.printStackTrace();
            return null;
        }
    }


}
