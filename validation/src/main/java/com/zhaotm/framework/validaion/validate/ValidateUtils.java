package com.zhaotm.framework.validaion.validate;

import com.zhaotm.framework.validaion.annotation.Validation;
import com.zhaotm.framework.validaion.cache.ReflectCache;
import com.zhaotm.framework.validaion.exception.ValidationException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 校验工具类
 * @author zhaotianming1
 * @date 2018/3/30
 */
public class ValidateUtils {
    /**
     * 校验方法
     * @param targetInstance
     * @throws Exception
     */
    public static void validate(Object targetInstance) throws Exception {
        if (null == targetInstance) {
            throw new ValidationException("参数错误");
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(targetInstance);

        for (Field field : targetInstance.getClass().getDeclaredFields()) {
            //目标类的各个属性值
            Object property = beanWrapper.getPropertyValue(field.getName());
            //每个属性包含的注解
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();

            for (Annotation annotation : declaredAnnotations) {
                if (annotation instanceof Validation) {
                    //目标属性包含validation注解
                    String msg = ((Validation) annotation).msg();
                    Class validateClass = ((Validation) annotation).validateClass();
                    Method validateMethod = ReflectCache.getMethod(validateClass, "validate");
                    Object validateInstance = ReflectCache.getInstance(validateClass);

                    if (((Validation) annotation).isObject()) {
                        //递归校验
                        validate(property);
                    } else {
                        //检验类去校验
                        Boolean pass = (Boolean) ReflectionUtils.invokeMethod(validateMethod, validateInstance, String.valueOf(property));
                        if (!pass) {
                            throw new ValidationException(msg);
                        }
                    }
                }
            }
        }
    }
}
