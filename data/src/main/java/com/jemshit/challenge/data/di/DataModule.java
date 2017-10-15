package com.jemshit.challenge.data.di;

import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.datasource.cache.SharedPrefCache;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {
    @Binds @Named("SharedPrefCache")
    public abstract Cache provideItemCache(SharedPrefCache itemCache);
}
