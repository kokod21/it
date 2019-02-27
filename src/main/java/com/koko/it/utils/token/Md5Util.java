package com.koko.it.utils.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: MD5加密工具
 * Auth: Frank
 * Date: 2017-10-26
 * Time: 上午 10:08
 */
public class Md5Util {
    /**
     * 获取字节数组形式的MD5摘要
     * @param data 需要获取摘要的字节数组
     * @return 获取到的MD5摘要数组
     * @throws Exception
     */
    public static byte[] md5Byte(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     * @param src 需要获得MD5摘要的字符串
     * @return  获取到的十六进制的MD5摘要
     */
    public static String md5Hex(String src) throws NoSuchAlgorithmException {
        byte[] bs = md5Byte(src.getBytes());
//        return new String(new Hex().encode(bs));
        return  encode(bs);
    }

    /**
     * 字节流转成十六进制表示
     */
    public static String encode(byte[] src) {
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < src.length; n++) {
            strHex = Integer.toHexString(src[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }
}
