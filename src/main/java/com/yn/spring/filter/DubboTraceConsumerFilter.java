package com.yn.spring.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.yn.constants.Constant;
import com.yn.keygen.DefaultKeyGenerator;
import com.yn.keygen.KeyGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * Created by yangnan on 17/8/1.
 * Dubbo过滤器：向MDC注入客户端传过来的日志相关参数
 * <p>
 *    CR:客户端接收时间
 *    CS:客户端发送时间
 *    SR:服务端接收时间
 *    SS:服务端发送时间
 * </p>
 */
@Activate(
        group = {"consumer"}
)
public class DubboTraceConsumerFilter implements Filter {

    private static final KeyGenerator keyGenerator = new DefaultKeyGenerator();

    public DubboTraceConsumerFilter() {
        System.out.println("DubboTraceConsumerFilter 初始化了");
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        RpcContext rpcContext = RpcContext.getContext();

        if (rpcContext.isConsumerSide()) {
            String traceId = MDC.get(Constant.TRACE_ID);
            if (StringUtils.isBlank(traceId)) {
                traceId = String.valueOf(keyGenerator.generateKey());
                MDC.put(Constant.TRACE_ID, traceId);
                System.out.println("TRACE_ID is null");
            }
            rpcContext.setAttachment(Constant.TRACE_ID, traceId);
            System.out.println(traceId);
        }

        try {
            long startTime = System.currentTimeMillis();
            Result result = invoker.invoke(invocation);
            System.out.println("服务提供者耗时" + (System.currentTimeMillis() - startTime));
            return result;
        } finally {
            if (rpcContext.isProviderSide()) {
                MDC.clear();
            }
        }

    }

}
