package com.wbh.emall.common.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author WBH
 * @since 2022/2/20
 */
public class Objects {
    
    /**
     * 判断一个对象是否拥有非空的字段
     *
     * @param object 待判断的对象
     * @return 一个对象是否拥有非空的字段
     */
    @SneakyThrows
    public static boolean hasFieldFilled(Object object) {
        Class<?> curClass = object.getClass();
        while (curClass != null) {
            for (Field f : curClass.getDeclaredFields()) {
                f.setAccessible(true);
                // 不判断常量字段
                if (Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                if (!isEmpty(f.get(object))) {
                    return true;
                }
                f.setAccessible(false);
            }
            curClass = curClass.getSuperclass();
            if (curClass == Object.class) {
                break;
            }
        }
        return false;
    }
    
    
    /**
     * 判断一个对象指定对象是否有属性
     *
     * @param object 待判断的对象
     * @param fields 希望非空的字段
     * @return 判断结果
     */
    public static boolean assertFieldsFilled(Object object, String... fields) throws IllegalAccessException {
        List<String> fieldsList = Arrays.asList(fields);
        Class<?> curClass = object.getClass();
        while (curClass != null) {
            for (Field f : curClass.getDeclaredFields()) {
                f.setAccessible(true);
                // 不判断常量字段
                if (Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                // 如果带判断的字段为空, 返回false
                String fieldName = f.getName();
                if (fieldsList.contains(fieldName) && isEmpty(f.get(object))) {
                    throw new IllegalArgumentException(String.format("字段%s不能为空", fieldName));
                }
                f.setAccessible(false);
            }
            curClass = curClass.getSuperclass();
            if (curClass == Object.class) {
                break;
            }
        }
        return true;
    }
    
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String && ("".equals(object.toString()))) {
            return true;
        }
        if (object instanceof Collection && ((Collection<?>) object).isEmpty()) {
            return true;
        }
        if (object instanceof Map && ((Map<?, ?>) object).isEmpty()) {
            return true;
        }
        return object instanceof Object[] && ((Object[]) object).length == 0;
    }
    
}
