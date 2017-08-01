package com.yn.spring.service.impl;

import com.yn.spring.service.RestTemplateService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 16/9/1.
 */
@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger logger = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Resource
    private RestTemplate restTemplate;

    public <T> T getForObject(String url, Map<String,?> param, Class<T> type) throws Exception {

        Assert.notNull(url, "\'url\' must not be null");

        String uri = getUrl(url, param);
        String response = null;
        try {
            response = restTemplate.getForObject(uri, String.class);
//            T obj = JSONObject.parseObject(response, type);
            logger.info("RestTemplateServiceImpl.getForObject url:[{}], response:[{}]", uri, response);
            return null;
        } catch (Exception e) {
            logger.error("RestTemplateServiceImpl.getForObject Exception url:[{}], response:[{}]", uri, response, e);
            throw e;
        }
    }

    public <T> T postForObject(String url, Map<String,?> param, Class<T> type) throws Exception {

        Assert.notNull(url, "\'url\' must not be null");

        String response = null;
        try {
            MultiValueMap map = getPostMap(param);
            response = restTemplate.postForObject(url, map, String.class);
//            T obj = JSONObject.parseObject(response, type);
            logger.info("RestTemplateServiceImpl.postForObject url:[{}], response:[{}]", url, response);
            return null;
        } catch (Exception e) {
            logger.error("RestTemplateServiceImpl.postForObject Exception url:[{}], response:[{}]", url, response, e);
            throw e;
        }
    }

    private MultiValueMap getPostMap(Map<String, ?> param) {
        MultiValueMap map = new LinkedMultiValueMap();
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, ?> entry : param.entrySet()) {
                map.add(entry.getKey(), entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString());
            }
        }
        return map;
    }

    private String getUrl(String url, Map<String, ?> param) {
        if (MapUtils.isEmpty(param)) {
            return url;
        }

        StringBuilder uri =  new StringBuilder(url);
        uri.append("?");
        for (Map.Entry<String, ?> entry : param.entrySet()) {
            uri.append(entry.getKey()).append("=").append(entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString()).append("&");
        }

        return uri.deleteCharAt(uri.length() - 1).toString();
    }

    @Override
    public <T> T postForObject(String url, Class<T> type, String... args) throws Exception {

        Assert.notNull(url, "\'url\' must not be null");

        if (args == null) {
            return postForObject(url, null, type);
        }

        if ((args.length & 1) != 0) {
            throw new IllegalArgumentException("params (params.length & 1) != 0");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        for (int i=0; i<args.length; i += 2) {
            param.put(args[i], args[i+1]);
        }

        return postForObject(url, param, type);

    }

    @Override
    public <T> T getForObject(String url, Class<T> type, String... args) throws Exception {
        Assert.notNull(url, "\'url\' must not be null");

        if (args == null) {
            return getForObject(url, null, type);
        }

        if ((args.length & 1) != 0) {
            throw new IllegalArgumentException("params (params.length & 1) != 0");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        for (int i=0; i<args.length; i += 2) {
            param.put(args[i], args[i+1]);
        }

        return getForObject(url, param, type);
    }


}
