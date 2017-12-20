package common;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Create by psh
 * Date: 2017/11/5
 * json转换
 */
public class Object2Conversion {

    public static <T>T map2Object (Map map,Class<T> clazz) {
        Objects.requireNonNull(map);
        JSONObject jsonObject = new JSONObject(map);
        T o1 = jsonObject.toJavaObject(clazz);
        return o1;
    }

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec("");

    }

}
