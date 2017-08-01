package com.yn.spring.resolver;

import com.yn.spring.annotation.Form;
import com.yn.spring.annotation.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 17/7/28.
 */
public class BaseFormArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Form.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class<?> type = methodParameter.getParameterType();
        Object args = BeanUtils.instantiate(type);
        Map<String, String> paramtemers = getRequestMap(nativeWebRequest.getParameterMap());
//        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(args.getClass());
        Field[] fs = args.getClass().getFields();
        for (Field descriptor : fs) {

//            descriptor.getWriteMethod().invoke(args, paramtemers.get(descriptor.getDisplayName()));

            NotBlank notBlank = descriptor.getAnnotation(NotBlank.class);
            if (notBlank == null) {
                continue;
            }

            String value = paramtemers.get(descriptor.getName());
            if (!StringUtils.hasLength(value)) {
                throw new RuntimeException(descriptor.getName() + " " + notBlank.message());
            }
        }

        return args;
    }

    private Map<String,String> getRequestMap(Map<String, String[]> parameterMap) {

        if (parameterMap == null || parameterMap.size() == 0) {
            return null;
        }

        Map<String,String> map = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }

        return map;
    }
}
