package com.lixiaohao.test.springshiro.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * description  加密
 *
 * Created by xiaohao.li on 2017/7/25.
 */
public class BASE65Util {
    public static String encrypt ( String str) {
        return new BASE64Encoder().encode(str.getBytes());
    }

    /***
     *
     * 解密
     *
     * @param str
     * @return
     * @throws IOException
     */
    public static String decrypt ( String str ) throws IOException {
        return new String(new BASE64Decoder().decodeBuffer(str),"utf-8");
    }

}
