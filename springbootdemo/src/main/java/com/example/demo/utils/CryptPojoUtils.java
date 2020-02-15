package com.example.demo.utils;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.example.demo.annotation.Secure;
import com.example.demo.annotation.SecureFlag;

import java.lang.reflect.Field;
import java.util.List;

public class CryptPojoUtils {

    private final static String KEY = "abcdef";


    /**
     * 对含注解字段解密
     */
    public static <T> void decryptFieldOrList(T t) {
        if (null == t) {
            return;
        }
        if (t instanceof List) {
            List values = (List) t;
            for (Object object : values) {
                CryptPojoUtils.decryptField(object);
            }
        } else {
            CryptPojoUtils.decryptField(t);
        }
    }

    /**
     * 对含注解字段解密
     */
    private static <T> void decryptField(T t) {
        SecureFlag annotation = t.getClass().getAnnotation(SecureFlag.class);
        if (null == annotation) {
            return;
        }
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Secure.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, SecurityUtil.decrypt(fieldValue, KEY));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对含注解字段加密
     */
    private static <T> void encryptField(T t) {
        if (null == t) {
            return;
        }
        SecureFlag annotation = t.getClass().getAnnotation(SecureFlag.class);

        if (null == annotation) {
            return;
        }

        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Secure.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, SecurityUtil.encrypt(fieldValue, KEY));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对含注解字段加密
     */
    public static <T> void encryptFields(T[] objects) {
        for (Object obj : objects) {
            encryptField(obj);
        }
    }

    public static String encryptObject(Object object) throws Exception {
        if (null == object) {
            return null;
        }
        return SecurityUtil.encrypt(String.valueOf(object), KEY);
    }
}