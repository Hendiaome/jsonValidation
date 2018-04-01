package com.zhaotm.framework.validaion.messageconvert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.zhaotm.framework.validaion.annotation.RequestBody;
import com.zhaotm.framework.validaion.cache.ThreadHold;
import com.zhaotm.framework.validaion.exception.ValidationException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhao on 2018/4/1.
 */
public class JsonHttpMessageConverter implements HttpMessageConverter {
    private ObjectMapper objectMapper = new ObjectMapper ();

    // 该转换器的支持类型：application/json
    private List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

    public JsonHttpMessageConverter() {
        this.supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    }

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        if (mediaType == null) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        if (mediaType == null || MediaType.ALL.equals(mediaType)) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return this.supportedMediaTypes;
    }


    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return objectMapper.readValue(inputMessage.getBody(), clazz);
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

        try {
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) o;
            String name = (String) map.get("exception");
            if (name.equals(InvalidFormatException.class.getName())
                    || name.equals(ValidationException.class.getName())) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String msg = (String) map.get("message");
                RequestBody requestBody = ThreadHold.threadLocal.get();
                resultMap.put("code", requestBody.code());

                if (StringUtils.isEmpty(msg)) {
                    msg = requestBody.msg();
                }
                resultMap.put("msg", msg);
                objectMapper.writeValue(outputMessage.getBody(), resultMap);
            } else {
                objectMapper.writeValue(outputMessage.getBody(), o);
            }
        } catch (Exception e) {
            objectMapper.writeValue(outputMessage.getBody(), o);
        }
    }
}
