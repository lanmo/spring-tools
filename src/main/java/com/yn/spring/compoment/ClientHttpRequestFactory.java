package com.yn.spring.compoment;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


/**
 * Created by yangnan on 16/9/1.
 *
 * Spring restTemplate
 */
public class ClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 300;

    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 3 * 1000;

    public ClientHttpRequestFactory(int maxConnectionsPerRoute) {
        super();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();
        super.setHttpClient(httpclient);
        setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);
        setConnectTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);

    }

}
