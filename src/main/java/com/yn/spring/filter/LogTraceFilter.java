package com.yn.spring.filter;

import com.yn.constants.Constant;
import com.yn.keygen.DefaultKeyGenerator;
import com.yn.keygen.KeyGenerator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yangnan on 17/8/1.
 */
public class LogTraceFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LogTraceFilter.class);

    public static final KeyGenerator generator = new DefaultKeyGenerator();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ClassPathResource resource = new ClassPathResource("log4j.xml");
        try {
            System.out.println(resource.getFile().getAbsolutePath());
            DOMConfigurator.configure(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            MDC.put(Constant.TRACE_ID, String.valueOf(generator.generateKey()));
            logger.info("before");
            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("after");
        } finally {
            logger.info("end");
            System.out.println(MDC.getCopyOfContextMap());
            clear();
        }
    }

    /**
     * 清理工作
     *
     */
    private void clear() {
        MDC.remove(Constant.TRACE_ID);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
