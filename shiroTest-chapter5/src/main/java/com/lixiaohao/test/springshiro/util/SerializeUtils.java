package com.lixiaohao.test.springshiro.util;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.Serializable;



/**
 * @program: shiroTest
 * @description:
 * @author: xiaohao.li
 * @create: 2018-07-30 14:55
 **/

public class SerializeUtils extends SerializationUtils {

    public static String serializeToString(Serializable obj) {
        try {
            byte[] value = serialize(obj);
            return Base64.encodeToString(value);
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Session deserializeFromString(String base64) {
        try {
            byte[] objectData = Base64.decode(base64);
            return deserialize(objectData);
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }

}
