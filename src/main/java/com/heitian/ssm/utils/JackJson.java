package com.heitian.ssm.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heitian.ssm.model.User;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;
import java.util.List;

public class JackJson { public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象序列号json字符串
     *
     * @param obj
     * @return
     */
    public  String writeValue(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串反序列号成对象
     *
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public  <T> T readValue(String json, Class<User> clazz) {
        try {
            return (T) mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
