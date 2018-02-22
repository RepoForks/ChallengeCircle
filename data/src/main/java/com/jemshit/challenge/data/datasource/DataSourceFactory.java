package com.jemshit.challenge.data.datasource;

import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.datasource.local.LocalDataSource;
import com.jemshit.challenge.data.datasource.remote.RemoteDataSource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class DataSourceFactory {

    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;
    private final Cache cache;

    @Inject
    public DataSourceFactory(LocalDataSource localDataSource, RemoteDataSource remoteDataSource, @Named("SharedPrefCache") Cache cache) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.cache = cache;
    }

    public DataSource getDataSourceForProfileList() {
        return cache.isCachedAndValid("profileList") ? localDataSource : remoteDataSource;
    }

    public DataSource getDataSourceForProfile(String profileId) {
        return cache.isCachedAndValid(profileId) ? localDataSource : remoteDataSource;
    }

    public DataSource getRemoteDataSource() {
        return remoteDataSource;
    }

    public DataSource getLocalDataSource() {
        return localDataSource;
    }
}
