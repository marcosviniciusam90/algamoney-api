package com.algamoney.api.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class BeanUtils {
    public static <T> T clone(T obj) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(obj), (Type) obj.getClass());
    }
}
