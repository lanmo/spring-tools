package com.yn.spring.config;

import java.lang.reflect.Field;
import java.util.List;

import com.yn.spring.annotation.PropertyConfig;
import com.yn.spring.constant.AppConstants;
import com.yn.spring.utils.L;
import org.springframework.beans.BeansException;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;

/**
 * Created by yangnan on 16/9/28.
 *
 */
public class PropertyInjectBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    @Autowired
    private ConfigBean configBean;

    private List<String> classNames;

    private SimpleTypeConverter converter = new SimpleTypeConverter();

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(classNames.contains(beanName)) {
            L.trace("beanName", beanName);
            findPropertyAutowiringMetadata(bean);
        }
//        L.trace("beanName==", beanName);
        return true;
    }

    private void findPropertyAutowiringMetadata(final Object bean) {

        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException,
                    IllegalAccessException {
                PropertyConfig propertyConfig = field.getAnnotation(PropertyConfig.class);
                if(propertyConfig != null) {
                    Object value = configBean.getProperty(propertyConfig.value());

                    if(value != null) {
                        value = converter.convertIfNecessary(value, field.getType());
                        ReflectionUtils.makeAccessible(field);
                        ReflectionUtils.setField(field, null, value);
                    }
                }
            }
        });

        L.trace("ddddd", AppConstants.url);
    }

    public void setClassNames(List<String> classNames) {
        this.classNames = classNames;
    }
}
