package com.ysa.common.filter;


import brave.internal.HexCodec;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.google.common.collect.Lists;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.List;
import java.util.Random;

/**
 * @author ysa
 * @date 2020/6/5 19:10
 * @description dubbo 追踪
 */
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public final class YsaTraceFilter implements Filter {

    private static final String MDC_TRACE_ID = "traceId";
    private static final String MDC_SPAN_ID = "spanId";
    private static final String MDC_SPAN_EXPORTABLE = "spanExportable";
    private static final String MDC_SPAN_EXPORT = "X-Span-Export";
    private static final String MDC_X_B3_SPANID = "X-B3-SpanId";
    private static final String MDC_X_B3_TRACEID = "X-B3-TraceId";


    private List<String> mdcListField = Lists.newArrayList(
            MDC_TRACE_ID,
            MDC_SPAN_ID,
            MDC_SPAN_EXPORTABLE,
            MDC_SPAN_EXPORT,
            MDC_X_B3_SPANID,
            MDC_X_B3_TRACEID
    );

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();
        //服务提供端
        if (rpcContext.isProviderSide()) {
            //放入日志的mdc里面
            if (MDC.getCopyOfContextMap() == null) {
                /**
                 * 如果当前没有上下文
                 */
                if (invocation.getAttachments().containsKey(MDC_TRACE_ID)) {
                    String newSpanId = HexCodec.toLowerHex(new Random().nextLong());
                    mdcListField.forEach(field -> {
                        String parentValue = invocation.getAttachments().get(field);
                        if (field.equals(MDC_SPAN_ID) || field.equals(MDC_X_B3_SPANID)) {
                            parentValue = newSpanId;
                        }
                        MDC.put(field, parentValue);
                    });
                } else {
                    String traceId = HexCodec.toLowerHex(new Random().nextLong());
                    MDC.put(MDC_TRACE_ID, traceId);
                    MDC.put(MDC_X_B3_TRACEID, traceId);
                }
            }
        } else {
            //消费端调用前，放入rpc的context
            if (null != MDC.getCopyOfContextMap() && !MDC.getCopyOfContextMap().isEmpty()) {
                invocation.getAttachments().putAll(MDC.getCopyOfContextMap());
            } else {
                String traceId = HexCodec.toLowerHex(new Random().nextLong());
                MDC.put(MDC_TRACE_ID, traceId);
                MDC.put(MDC_X_B3_TRACEID, traceId);
            }
        }
        Result rpcResult = invoker.invoke(invocation);
        //如果是调用方，返回结果后 就应该结束了本条链路
        if (rpcContext.isProviderSide()) {
            MDC.clear();
        }
        return rpcResult;
    }
}
