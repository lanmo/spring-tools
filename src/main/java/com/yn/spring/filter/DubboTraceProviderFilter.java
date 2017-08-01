package com.yn.spring.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.yn.constants.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

/**
 * Created by yangnan on 17/8/1.
 */
@Activate(
        group = {"provider"}
)
public class DubboTraceProviderFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(DubboTraceProviderFilter.class);

    public DubboTraceProviderFilter() {
        System.out.println("DubboTraceProviderFilter 初始化了");
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();
        if (context.isProviderSide()) {
            putMdcValue(context.getAttachments());
        }

        long startTime = System.currentTimeMillis();
        try {
            Result result = invoker.invoke(invocation);
            return result;
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("接口:[{}] 耗时:[{}]", invocation.getMethodName(), endTime - startTime);
            clear();
        }
    }

    private void clear() {
        MDC.remove(Constant.TRACE_ID);
    }

    /**
     * MDC设置值
     *
     * @param attachments
     */
    private void putMdcValue(Map<String, String> attachments) {
        if (attachments == null || attachments.isEmpty()) {
            return;
        }

        String traceInfo = attachments.get(Constant.TRACE_ID);
        if (StringUtils.isNotBlank(traceInfo)) {
            MDC.put(Constant.TRACE_ID, traceInfo);
        }
    }
}
