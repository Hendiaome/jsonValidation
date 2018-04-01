package com.zhaotm.framework.validaion.cache;

import com.zhaotm.framework.validaion.annotation.RequestBody;
import org.springframework.core.MethodParameter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取反射后实例或方法
 * @author zhaotianming1
 * @date 2018/3/30
 */
public class ReflectCache {
    private static Object instanceLock = new Object();
    private static Object methodLock = new Object();
    private static Object requestBodyLock = new Object();

    private volatile static Map<String, Object> instanceMap = new HashMap<String, Object>();
    private volatile static Map<String, Method> methodMapMap = new HashMap<String, Method>();
    private volatile static Map<String, RequestBody> requestBodyMapMap = new HashMap<String, RequestBody>();

    /**
     * 获取实例
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object getInstance(Class<?> clazz) throws Exception {
        Object instance = instanceMap.get(clazz.getName());

        if (null == instance) {
            synchronized (instanceLock) {
                if (null == instance)
                instance = clazz.newInstance();
                instanceMap.put(clazz.getName(), instance);
            }
        }

        return instance;
    }

    /**
     * 获取方法
     * @param clazz
     * @param methodName
     * @return
     * @throws Exception
     */
    public static Method getMethod(Class<?> clazz, String methodName) throws Exception {
        Method method = methodMapMap.get(clazz.getName() + "_" + methodName);

        if (null == method) {
            synchronized (methodLock) {
                if (null == method) {
                    method = ReflectionUtils.findMethod(clazz, methodName, String.class);
                    methodMapMap.put(clazz.getName() + "_" + methodName, method);
                }
            }
        }

        return method;
    }

    public static RequestBody getRequestBody(MethodParameter methodParameter) {
        RequestBody requestBody = requestBodyMapMap.get(methodParameter.getMethod().toString());

        if (null == requestBody) {
            synchronized (requestBodyLock) {
                if (null == requestBody) {
                    requestBody = methodParameter.getParameterAnnotation(RequestBody.class);
                    requestBodyMapMap.put(methodParameter.getMethod().toString(), requestBody);
                }
            }
        }

        return requestBody;
    }
}
