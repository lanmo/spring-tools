package com.yn.spring.compoment;

import com.yn.spring.utils.L;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

/**
 * Created by yangnan on 16/9/27.
 */
public class FutureCallback<T> implements ListenableFutureCallback<ResponseEntity<String>> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String url;
    private Map<String, ?> param;

    private Class<T> t;

    public FutureCallback(String url, Map<String, ?> param, Class<T> t) {
        this.url = url;
        this.param = param;
        this.t = t;
    }

    @Override
    public void onSuccess(ResponseEntity<String> tResponseEntity) {
        logger.info("FutureCallback url:[{}] param:[{}] res:[{}]", url, param, tResponseEntity.getBody());
    }

    @Override
    public void onFailure(Throwable throwable) {
        L.trace(url, param);
//        throwable.printStackTrace();
    }
}
