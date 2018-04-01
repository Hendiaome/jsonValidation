package com.zhaotm.framework.validaion.auguemtresolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaotm.framework.validaion.annotation.RequestBody;
import com.zhaotm.framework.validaion.cache.ThreadHold;
import com.zhaotm.framework.validaion.validate.ValidateUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaotianming1
 * @date 2018/3/30
 */
public class JsonResovler implements HandlerMethodArgumentResolver {
    private ObjectMapper objectMapper = new ObjectMapper ();

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(RequestBody.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class<?> targetClass = methodParameter.getParameterType();
        RequestBody requestBody = methodParameter.getParameterAnnotation(RequestBody.class);
        ThreadHold.threadLocal.set(requestBody);

        Object targetInstance = objectMapper.readValue(nativeWebRequest.getNativeRequest(HttpServletRequest.class).getInputStream(), targetClass);
        ValidateUtils.validate(targetInstance);

        return targetInstance;
    }

}
