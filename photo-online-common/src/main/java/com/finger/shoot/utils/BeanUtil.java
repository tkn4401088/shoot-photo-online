package com.finger.shoot.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by zb 2017/7/7.  实体Bean拷贝
 */
public class BeanUtil {

    /**
     * 获取对象中空属性
     * @param source  对象
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * copy对象属性  忽略空属性
     * @param src    原对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 拷贝对象属性
     * @param src     原对象
     * @param target  目标对象
     */
    public static void copyProperties(Object src, Object target){
        BeanUtils.copyProperties(src, target);
    }


    /**
     * 对象数组转Map
     * @param obs
     * @param properties
     * @return
     */
    public static List getListProperties(List<?> obs, String[] properties, boolean isToJson) {
        List list = new ArrayList();
        if(obs!=null && obs.size()>0){
            for(Object obj : obs){
                list.add(getProperties(obj, properties, isToJson));
            }
        }
        return list;
    }

    /**
     * 获取对象属性
     *
     * @param obj
     * @param properties
     * @return
     */
    public static Map getProperties(Object obj, String[] properties, Boolean isToJson) {
        if (obj == null) {
            throw new IllegalArgumentException("no bean specified");
        }
        if (properties == null) {
            throw new IllegalArgumentException("no priperties specified");
        }
        Map result = new LinkedHashMap();
        for (int i = 0; i < properties.length; i++) {
            Object value = getProperty(obj, properties[i]);

            if(isToJson) value = null==value ? "" : value;

            result.put(properties[i], value);
        }
        return result;
    }

    /**
     *
     * @param obj
     * @param property
     * @return
     */
    public static Object getProperty(Object obj, String property) {
        if (obj == null) {
            throw new IllegalArgumentException("no bean specified");
        }
        if (property == null) {
            throw new IllegalArgumentException("no property specified");
        }

        if (obj instanceof Map) {
            return ((Map) obj).get(property);
        }

        StringTokenizer st = new StringTokenizer(property, ".");
        if (st.countTokens() == 0) {
            return null;
        }

        Object result = obj;

        try {
            while (st.hasMoreTokens()) {
                String currentPropertyName = st.nextToken();
                if (result != null) {
                    result = invokeProperty(result, currentPropertyName);
                }
            }
            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object invokeProperty(Object obj, String property) {
        if ((property == null) || (property.length() == 0)) {
            return null; // just in case something silly happens.
        }
        Class cls = obj.getClass();
        Object[] oParams = {};
        Class[] cParams = {};
        try {
            // First try object.getProperty()
            Method method = cls.getMethod(createMethodName("get", property), cParams);

            return method.invoke(obj, oParams);
        } catch (Exception e1) {
            try {
                // First try object.isProperty()
                Method method = cls.getMethod(createMethodName("is", property), cParams);
                return method.invoke(obj, oParams);
            } catch (Exception e2) {
                try {
                    // Now try object.property()
                    Method method = cls.getMethod(property, cParams);

                    return method.invoke(obj, oParams);
                } catch (Exception e3) {
                    try {
                        // Now try object.property()
                        Field field = cls.getField(property);

                        return field.get(obj);
                    } catch (Exception e4) {
                        // oh well
                        return null;
                    }
                }
            }
        }
    }

    private static String createMethodName(String prefix, String propertyName) {
        return prefix + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
    }
}
