package com.jemshit.challenge.data.datasource.cache;

import java.lang.reflect.Type;
import java.util.List;

public interface Cache {

    <ITEM_TYPE> ITEM_TYPE get(final String key, Class<ITEM_TYPE> type);

    <ITEM_TYPE> ITEM_TYPE getInfinite(final String key, Class<ITEM_TYPE> type);

    <ITEM_TYPE> List<ITEM_TYPE> getList(final String key, Type type);

    <ITEM_TYPE> void put(String key, ITEM_TYPE value, Class<ITEM_TYPE> type, Long cacheDuration);

    <ITEM_TYPE> void putInfinite(String key, ITEM_TYPE value, Class<ITEM_TYPE> type);

    <ITEM_TYPE> void putList(String key, List<ITEM_TYPE> value, Type type, Long cacheDuration);

    boolean isCachedAndValid(final String key);

}
