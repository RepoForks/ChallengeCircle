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

import io.reactivex.Single;


@Singleton
public class LocalDataSource implements DataSource {

    private final Cache cache;

    @Inject
    public LocalDataSource(@Named("SharedPrefCache") Cache cache) {
        this.cache = cache;
    }

    @Override public Single<LoginResponseEntity> login(String username, String password) {
        // No Op on Disk
        return Single.just(new LoginResponseEntity());
    }

    @Override public Single<List<UserEntity>> getProfileList(String token) {
        Type listType = new TypeToken<ArrayList<UserEntity>>() {
        }.getType();
        List<UserEntity> userEntityList = cache.getList("profileList", listType);
        if (userEntityList == null)
            userEntityList = new ArrayList<>(0);
        return Single.just(userEntityList);
    }

    @Override public Single<ProfileEntity> getProfile(String profileId) {
        ProfileEntity profileEntity = cache.get(profileId, ProfileEntity.class);
        if (profileEntity == null)
            profileEntity = new ProfileEntity();
        return Single.just(profileEntity);
    }

    @Override public String getToken() {
        return cache.getInfinite("token", String.class);
    }
}
