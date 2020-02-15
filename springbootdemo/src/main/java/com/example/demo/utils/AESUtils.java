package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.example.demo.vo.DeptVO;
import com.example.demo.vo.UserVO;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: AESUtils
 * @description: AES工具类
 * @author: chenhx
 * @create: 2019-12-05 13:53
 **/
public class AESUtils {
    private static final String KEY_AES = "AES";
    private static final String KEY = "1101101101101111";

    public AESUtils() throws Exception {
    }

    public static String encrypt(String src) throws Exception {

        byte[] raw = KEY.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes());
        return byte2hex(encrypted);
    }

    public static String decrypt(String src) throws Exception {

        byte[] raw = KEY.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = hex2byte(src);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;
    }

    private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public static void main(String[] args) throws Exception {
//        DeptVO deptVO=new DeptVO();
//        deptVO.setId(1);
//        deptVO.setDeptName("开发部");
//        String value = JSON.toJSONString(deptVO);
//        String encrypt = AESUtils.encrypt(value);
//        System.out.println(value);
//        System.out.println(encrypt);

        UserVO userVO=new UserVO(1,"赵六");
        String value = JSON.toJSONString(userVO);
        String encrypt = AESUtils.encrypt(value);
        System.out.println(value);
        System.out.println(encrypt);

    }

}
