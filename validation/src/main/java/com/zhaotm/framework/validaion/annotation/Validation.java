package com.zhaotm.framework.validaion.annotation;

import com.zhaotm.framework.validaion.validatesuport.ValidateEmpty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validation {
    /**默认提示信息**/
    String msg() default "";

    /**次属性是否为对象**/
    boolean isObject() default false;

    /**默认校验类**/
    Class validateClass() default ValidateEmpty.class;
}
