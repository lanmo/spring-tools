package com.yn.spring.service;

import java.util.Map;

/**
 * Created by yangnan on 16/9/27.
 */
public interface AsyncRestTemplateService {
    /**
     * get请求
     *
     * @param url
     * @param param
     * @param type
     * @return
     * @throws Exception
     */
    <T> void getForObject(String url, Map<String, ?> param, Class<T> type) throws Exception;

    /**xz
     * post请求
     *
     * @param url
     * @param param
     * @param type
     * @return
     * @throws Exception
     */
    <T> void postForObject(String url, Map<String, ?> param, Class<T> type) throws Exception;

    /**
     * post请求
     *
     * @param url
     * @param args
     * @param type
     * @return
     * @throws Exception
     */
    <T> void postForObject(String url, Class<T> type, String... args) throws Exception;

    /**
     * get请求
     *
     * @param url
     * @param args
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> void getForObject(String url, Class<T> type, String... args) throws Exception;

}
