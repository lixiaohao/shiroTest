package com.lixiaohao.test.springshiro.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by xiaohao.li on 2017/7/25.
 */
public class MD5HashUtil {

    //默认盐值
    private static final String default_salt        = "ChinaRedStar";
//    private static final String default_salt        = "1234567890";
    //默认循环次数
    private static final Integer default_frequency  = 5;

    /***
     *
     * 默认加密
     *
     * @param str
     * @return
     */
    public static String encrypt (String str) {
        Md5Hash md5Hash = new Md5Hash(str,default_salt,default_frequency);
       return md5Hash.toString();
    }


    /***
     *
     * 加密
     *
     * @param str
     * @param salt
     * @param frequency
     * @return
     */
    public static String encrypt ( String str,String salt, Integer frequency ) {
        Md5Hash md5Hash = new Md5Hash(str,salt,frequency);
        return md5Hash.toString();
    }

}
