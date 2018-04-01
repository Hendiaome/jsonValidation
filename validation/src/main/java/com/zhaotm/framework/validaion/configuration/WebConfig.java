package com.zhaotm.framework.validaion.configuration;

import com.zhaotm.framework.validaion.auguemtresolver.JsonResovler;
import com.zhaotm.framework.validaion.messageconvert.JsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author zhaotianming1
 * @date 2018/3/30
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonResovler());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new JsonHttpMessageConverter());
    }
}
