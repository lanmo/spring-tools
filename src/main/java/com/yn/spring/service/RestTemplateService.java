package com.yn.spring.service;

import java.util.Map;

/**
 * Created by yangnan on 16/9/1.
 *
 * 同步
 */
public interface RestTemplateService {

    /**
     * get请求
     *
     * @param url
     * @param param
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getForObject(String url, Map<String, ?> param, Class<T> type) throws Exception;

    /**
     * post请求
     *
     * @param url
     * @param param
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T postForObject(String url, Map<String, ?> param, Class<T> type) throws Exception;

    /**
     * post请求
     *
     * @param url
     * @param args
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T postForObject(String url, Class<T> type, String... args) throws Exception;

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
    <T> T getForObject(String url, Class<T> type, String... args) throws Exception;

}
