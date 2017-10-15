package com.jemshit.challenge.data.datasource.local;

import com.google.gson.reflect.TypeToken;
import com.jemshit.challenge.data.datasource.DataSource;
import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Single;

@Singleton
public class LocalDataSource implements DataSource {

    private Cache cache;

    @Inject
    public LocalDataSource(@Named("SharedPrefCache") Cache cache) {
        this.cache = cache;
    }

    @Override public Single<LoginResponseEntity> login(String username, String password) {
        // No Op on Disk
        return Single.just(null);
    }

    @Override public Single<List<UserEntity>> getProfileList(String token) {
        Type listType = new TypeToken<ArrayList<UserEntity>>() {
        }.getType();
        return Single.just(cache.getList("profileList", listType));
    }

    @Override public Single<ProfileEntity> getProfile(String profileId) {
        return Single.just(cache.get(profileId, ProfileEntity.class));
    }

    @Override public String getToken() {
        return cache.getInfinite("token", String.class);
    }
}
