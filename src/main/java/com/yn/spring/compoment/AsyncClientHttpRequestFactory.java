package com.yn.spring.compoment;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


/**
 * Created by yangnan on 16/9/1.
 *
 * Spring restTemplate
 */
public class AsyncClientHttpRequestFactory extends HttpComponentsAsyncClientHttpRequestFactory {

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 300;

    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 3 * 1000;

    public AsyncClientHttpRequestFactory(int maxConnectionsPerRoute) {
        super();
        try {
            IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                    .setIoThreadCount( Runtime.getRuntime().availableProcessors() )
                    .setConnectTimeout( DEFAULT_READ_TIMEOUT_MILLISECONDS )
                    .setSoTimeout( DEFAULT_READ_TIMEOUT_MILLISECONDS )
                    .setTcpNoDelay(true)
                    .build();

            ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
            PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor);
            connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
            connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);
            CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(connectionManager).build();
            setHttpAsyncClient(httpclient);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }

    }

}
