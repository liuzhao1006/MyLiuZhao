package com.sx.trans.supervision.utils;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class JSONUtils {

    private static final String TAG = "JSONUtil";

    public static final String EMPTY = "";
    public static final String EMPTY_JSON = "{}";
    public static final String EMPTY_JSON_ARRAY = "[]";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    public static String toJson(Object target, Type targetType, boolean isSerializeNulls, Double version, String datePattern, boolean excludesFieldsWithoutExpose) {
        if (target == null)
            return EMPTY_JSON;
        GsonBuilder builder = new GsonBuilder();
        if (isSerializeNulls)
            builder.serializeNulls();
        if (version != null)
            builder.setVersion(version.doubleValue());
        if (TextUtils.isEmpty(datePattern))
            datePattern = DEFAULT_DATE_PATTERN;
        builder.setDateFormat(datePattern);
        if (excludesFieldsWithoutExpose)
            builder.excludeFieldsWithoutExposeAnnotation();
        String result = EMPTY;
        Gson gson = builder.create();
        try {
            if (targetType != null) {
                result = gson.toJson(target, targetType);
            } else {
                result = gson.toJson(target);
            }
        } catch (Exception ex) {
            Log.e(TAG, "目标对象 " + target.getClass().getName() + " 转换 JSON 字符串时，发生异常！", ex);
            if (target instanceof Collection || target instanceof Iterator || target instanceof Enumeration || target.getClass().isArray()) {
                result = EMPTY_JSON_ARRAY;
            } else
                result = EMPTY_JSON;
        }
        return result;
    }

    public static String toJson(Object target) {
        return toJson(target, null, true, null, null, false);
    }

    public static String toJson(Object target, String datePattern) {
        return toJson(target, null, false, null, datePattern, true);
    }

    public static String toJson(Object target, Double version) {
        return toJson(target, null, false, version, null, true);
    }

    public static String toJson(Object target, boolean excludesFieldsWithoutExpose) {
        return toJson(target, null, false, null, null, excludesFieldsWithoutExpose);
    }

    public static String toJson(Object target, Double version, boolean excludesFieldsWithoutExpose) {
        return toJson(target, null, false, version, null, excludesFieldsWithoutExpose);
    }

    public static String toJson(Object target, Type targetType) {
        return toJson(target, targetType, false, null, null, true);
    }

    public static String toJson(Object target, Type targetType, Double version) {
        return toJson(target, targetType, false, version, null, true);
    }

    public static String toJson(Object target, Type targetType, boolean excludesFieldsWithoutExpose) {
        return toJson(target, targetType, false, null, null, excludesFieldsWithoutExpose);
    }

    public static String toJson(Object target, Type targetType, Double version, boolean excludesFieldsWithoutExpose) {
        return toJson(target, targetType, false, version, null, excludesFieldsWithoutExpose);
    }

    public static <T> T fromJson(String json, TypeToken<T> token, String datePattern) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (TextUtils.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, token.getType());
        } catch (Exception ex) {
            Log.e(TAG, json + " 无法转换为 " + token.getRawType().getName() + " 对象!", ex);
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        return fromJson(json, token, null);
    }

    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (TextUtils.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        Gson gson = builder.create();

        try {
            return gson.fromJson(json, clazz);
        } catch (Exception ex) {
            Log.e(TAG, json + " 无法转换为 " + clazz.getName() + " 对象!", ex);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, null);
    }


    public static String getJsonParam(Map<String, Object> paramsMap) {
        int uid = -1;
        String userType="62";
//        int uid = 1;
        String auth = "0A557766FAE15B79771CC49A61B22C4B170E9932403B08F2D742566D447982D3F1167477";
        HashMap<String, Object> limitParam = new HashMap<String, Object>();
            if (uid>0){
                limitParam.put("uid", uid);
            }else{
                limitParam.put("uid", "");
            }
            limitParam.put("uid", "208871");
            limitParam.put("auth", auth);
            limitParam.put("vehicle_brand_code","CE109");
            limitParam.put("app_name","A001");
        limitParam.put("userType", userType);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("limit", limitParam);
        params.put("param", paramsMap);

        return JSONUtils.toJson(paramsMap);
    }


    public static String getRes(Object o, boolean isArray) {
        String data = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(o.toString());
            if (isArray) return (jsonObject.getJSONArray("data").toString());
            else return (jsonObject.getJSONObject("data").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
