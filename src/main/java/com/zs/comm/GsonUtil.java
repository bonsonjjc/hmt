package com.zs.comm;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class GsonUtil {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    private GsonUtil() {

    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Map<String, Object> toMap(Object object) {
        String json = toJson(object);
        return custom(json, new TypeToken<HashMap<String, Object>>() {
        });
    }

    public static <T> T fromJson(String json, Class<T> t) {
        try {
            return gson.fromJson(json, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> fromJson(String json) {
        return custom(json, new TypeToken<HashMap<String, Object>>() {
        });
    }

    public static <T> T custom(String json, TypeToken<T> typeToken) {
        try {
            return gson.fromJson(json, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> fromJsonList(String json, Class<T> t) {
        List<T> list = new ArrayList<>();
        try {
            JsonElement fromJson = gson.fromJson(json, JsonElement.class);
            if (fromJson.isJsonArray()) {
                JsonArray jsonArray = fromJson.getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonElement element = jsonArray.get(i);
                    T obj = gson.fromJson(element, t);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
