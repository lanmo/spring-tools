package com.yn.spring.service.impl;

import com.yn.spring.compoment.FutureCallback;
import com.yn.spring.service.AsyncRestTemplateService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 16/9/27.
 */
@Service
public class AsyncRestTemplateServiceImpl implements AsyncRestTemplateService {

    @Resource
    private AsyncRestTemplate asyncRestTemplate;

    @Override
    public <T> void getForObject(String url, Map<String, ?> param, Class<T> type) throws Exception {

        Assert.notNull(url, "\'url\' must be null");

        String uri = getUrl(url, param);

        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.getForEntity(uri, String.class);

        future.addCallback(new FutureCallback<T>(uri, param, type));

    }

    @Override
    public <T> void postForObject(String url, Map<String, ?> param, Class<T> type) throws Exception {
        Assert.notNull(url, "\'url\' must be null");

        MultiValueMap map = getPostMap(param);
        HttpEntity<String> entity = new HttpEntity<String>(map);
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.postForEntity(url, entity, String.class);

        future.addCallback(new FutureCallback<T>(url, param, type));

    }

    @Override
    public <T> void postForObject(String url, Class<T> type, String... args) throws Exception {
        Assert.notNull(url, "\'url\' must be null");

        if (args == null) {
            postForObject(url, null, type);
        }

        if ((args.length & 1) != 0) {
            throw new IllegalArgumentException("params (params.length & 1) != 0");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        for (int i=0; i<args.length; i += 2) {
            param.put(args[i], args[i+1]);
        }

        postForObject(url, param, type);
    }

    @Override
    public <T> void getForObject(String url, Class<T> type, String... args) throws Exception {
        Assert.notNull(url, "\'url\' must be null");
        if (args == null) {
            getForObject(url, null, type);
        }

        if ((args.length & 1) != 0) {
            throw new IllegalArgumentException("params (params.length & 1) != 0");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        for (int i=0; i<args.length; i += 2) {
            param.put(args[i], args[i+1]);
        }

        getForObject(url, param, type);
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

    private MultiValueMap getPostMap(Map<String, ?> param) {
        MultiValueMap map = new LinkedMultiValueMap();
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, ?> entry : param.entrySet()) {
                map.add(entry.getKey(), entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString());
            }
        }
        return map;
    }
}
