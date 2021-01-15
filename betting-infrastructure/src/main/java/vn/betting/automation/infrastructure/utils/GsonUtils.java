package vn.betting.automation.infrastructure.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonUtils {

    private static final Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        gson = gsonBuilder.disableHtmlEscaping().create();
    }

    public static String toJsonString(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> List<T> fromJonStringToList(String sJson, Type t) {
        return gson.fromJson(sJson, t);
    }

    public static String toJsonString(Map<String, String> map) {
        return gson.toJson(map);
    }

    public static <T> T fromJsonString(String sJson, Class<T> t) {
        return gson.fromJson(sJson, t);
    }

    public static Map<String, String> fromJonString(String sJson) {
        return new Gson().fromJson(sJson, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public static <T> T json2Collection(String sJson, Type t) {
        return gson.fromJson(sJson, t);
    }

}
