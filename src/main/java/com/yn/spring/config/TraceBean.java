package com.yn.spring.config;

import java.io.Serializable;

/**
 * Created by yangnan on 17/8/1.
 */
public class TraceBean implements Serializable {

    private String traceId;
    private String spanId;
    private String parentId;

    public static TraceBean builder(String traceId) {
        return new TraceBean(traceId);
    }

    public TraceBean(String traceId) {
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
