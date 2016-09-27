package com.yn.spring.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by yangnan on 16/9/27.
 */
public class L {

    private static final SerializerFeature[] SERIALIZERFEATURES = {
                                SerializerFeature.WriteNullListAsEmpty,
                                SerializerFeature.DisableCircularReferenceDetect,
                                SerializerFeature.PrettyFormat};

    public static void trace(Object obj) {
        System.out.println(JSONObject.toJSONString(obj, SERIALIZERFEATURES));
    }

    public static void trace(String key, Object obj) {
        System.out.println(key + ":" + JSONObject.toJSONString(obj, SERIALIZERFEATURES));
    }

    public static void error(Object obj) {
        System.err.println(JSONObject.toJSONString(obj, SERIALIZERFEATURES));
    }

    public static void error(String key, Object obj) {
        System.err.println(key + ":" + JSONObject.toJSONString(obj, SERIALIZERFEATURES));
    }
}
