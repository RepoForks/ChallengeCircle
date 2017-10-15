package com.jemshit.challenge.data.datasource.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.jemshit.challenge.data.BuildConfig;
import com.jemshit.challenge.data.datasource.Serializer;
import com.jemshit.challenge.domain.ContentValidator;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class SharedPrefCache implements Cache {

    private SharedPreferences sharedPreferences;
    private Serializer serializer;
    private ContentValidator contentValidator;

    @Inject
    public SharedPrefCache(@Named("AppContext") Context context, Serializer serializer, ContentValidator contentValidator) {
        this.sharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        this.serializer = serializer;
        this.contentValidator = contentValidator;
    }

    @Override public <ITEM_TYPE> ITEM_TYPE get(String key, Class<ITEM_TYPE> type) {
        String value = sharedPreferences.getString(key, "");
        return serializer.deserialize(value, type);
    }

    @Override public <ITEM_TYPE> ITEM_TYPE getInfinite(String key, Class<ITEM_TYPE> type) {
        String value = sharedPreferences.getString(key, "");
        return serializer.deserialize(value, type);
    }

    @Override public <ITEM_TYPE> List<ITEM_TYPE> getList(String key, Type type) {
        String value = sharedPreferences.getString(key, "");
        return serializer.deserializeList(value, type);
    }

    @Override
    public <ITEM_TYPE> void put(String key, ITEM_TYPE value, Class<ITEM_TYPE> type, Long cacheDuration) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, serializer.serialize(value, type));
        editor.putLong(key + "-duration", System.currentTimeMillis() + cacheDuration);
        editor.commit();
    }

    @Override
    public <ITEM_TYPE> void putList(String key, List<ITEM_TYPE> value, Type type, Long cacheDuration) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, serializer.serializeList(value, type));
        editor.putLong(key + "-duration", System.currentTimeMillis() + cacheDuration);
        editor.commit();
    }

    @Override
    public <ITEM_TYPE> void putInfinite(String key, ITEM_TYPE value, Class<ITEM_TYPE> type) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, serializer.serialize(value, type));
        editor.commit();
    }

    @Override public boolean isCachedAndValid(String key) {
        String cachedValue = sharedPreferences.getString(key, "");
        Long cachedValueDuration = sharedPreferences.getLong(key + "-duration", 0L);
        final long currentTime = System.currentTimeMillis();

        // Delete if expired
        if (!contentValidator.isEmptyString(cachedValue) && cachedValueDuration < currentTime) {
            sharedPreferences.edit().remove(key).commit();
            sharedPreferences.edit().remove(key + "-duration").commit();
        }

        return !contentValidator.isEmptyString(cachedValue) && cachedValueDuration >= currentTime;
    }
}
