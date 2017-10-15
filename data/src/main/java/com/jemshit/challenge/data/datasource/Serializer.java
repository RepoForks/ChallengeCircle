package com.jemshit.challenge.data.datasource;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class Serializer {
    private Gson gson;

    @Inject
    public Serializer(@Named("Other") Gson gson) {
        this.gson = gson;
    }

    public <T> String serialize(Object object, Class<T> type) {
        return gson.toJson(object, type);
    }

    public <T> T deserialize(String object, Class<T> type) {
        return gson.fromJson(object, type);
    }

    public <T> String serializeList(List<T> objectList, Type type) {
        return gson.toJson(objectList, type);
    }

    public <T> List<T> deserializeList(String objectList, Type type) {
        return gson.fromJson(objectList, type);
    }
}
