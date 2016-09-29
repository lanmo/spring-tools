package com.yn.spring.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;

/**
 * Created by yangnan on 16/9/28.
 */
public class ConfigBean implements InitializingBean,FactoryBean<Object> {

    private Configuration configuration;

    private CompositeConfiguration compositeConfiguration;

    @Override
    public Object getObject() throws Exception {
        return compositeConfiguration != null ? ConfigurationConverter
                .getProperties(configuration) : null;
    }

    @Override
    public Class<?> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (compositeConfiguration == null) {
            compositeConfiguration = new CompositeConfiguration();
        }
        compositeConfiguration.addConfiguration(configuration);
    }

    public Object getProperty(String key) {
        return compositeConfiguration.getString(key);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
