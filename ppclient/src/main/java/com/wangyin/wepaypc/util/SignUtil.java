package com.wangyin.wepaypc.util;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by lijunfu on 14-4-29.
 */
public final class SignUtil {

    @SuppressWarnings("unused")
	private final static String charSet = "UTF-8";

    /**
     * 生成签名原串
     *
     * @param object        需要签名的dto
     * @param unSignKeyList 不需要签名的dto中的字段
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("rawtypes")
    public static String signString(Object object, List<String> unSignKeyList) throws IllegalArgumentException, IllegalAccessException {
        TreeMap<String, Object> map = SignUtil.objectToMap(object);

        // 拼原String
        StringBuilder sb = new StringBuilder();
        // 删除不需要参与签名的属性
        for (String str : unSignKeyList) {
            map.remove(str);
        }
        // 连接
       
		Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            sb.append(entry.getKey() + "=" + (entry.getValue() == null ? "" : entry.getValue()) + "&");
        }
        // 去掉最后一个&
        String result = sb.toString();
        if (result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }
        System.out.println(result);
        return result;
    }

    /**
     * 对象转map
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static TreeMap<String, Object> objectToMap(Object object) throws IllegalArgumentException, IllegalAccessException {
        TreeMap<String, Object> map = new TreeMap<String, Object>();

        // 父类属性
        for (Class<?> cls = object.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            // 添加属性key到list
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                map.put(f.getName(), f.get(object));
            }
        }
        return map;
    }
}

