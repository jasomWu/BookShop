package com.jasomWu.utils;

import com.jasomWu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/** 把map中的值注入到JavaBean中去
 * @author sunwu
 * @create 2021-02-03-20:14
 */
public class WebUtils {

    public static <T> T  copyParamToBean(Map value,T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            if (null!=strInt){
                int anStrInt = new Integer(strInt);
                return anStrInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
