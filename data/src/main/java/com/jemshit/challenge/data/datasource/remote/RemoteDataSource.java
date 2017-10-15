package com.jemshit.challenge.data.datasource.remote;

import com.google.gson.reflect.TypeToken;
import com.jemshit.challenge.data.datasource.DataSource;
import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;
import com.jemshit.challenge.data.exception.FetchDataException;
import com.jemshit.challenge.data.other.ConstantsCache;
import com.jemshit.challenge.data.web_service.ApiService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Single;

@Singleton
public class RemoteDataSource implements DataSource {

    private ApiService apiService;
    private Cache cache;

    @Inject
    public RemoteDataSource(ApiService apiService, @Named("SharedPrefCache") Cache cache) {
        this.apiService = apiService;
        this.cache = cache;
    }

    @Override public Single<LoginResponseEntity> login(String username, String password) {
        return apiService.login(username, password)
                .map(loginResponseEntity -> {
                    if (loginResponseEntity != null && loginResponseEntity.size() > 0)
                        return loginResponseEntity.get(0);
                    else
                        return null;
                })
                // Save to Cache
                .doOnSuccess(loginResponseEntity -> {
                    if (loginResponseEntity != null)
                        cache.putInfinite("token", loginResponseEntity.getToken(), String.class);
                });
    }

    @Override public Single<List<UserEntity>> getProfileList(String token) {
        return apiService.getProfileList(token)
                .map(getProfileListResponseEntity -> {
                    if (getProfileListResponseEntity != null && getProfileListResponseEntity.size() > 0) {
                        return getProfileListResponseEntity.get(0).getData();
                    } else
                        throw new FetchDataException("");
                })
                // Save to Cache
                .doOnSuccess(userEntities -> {
                    Type type = new TypeToken<ArrayList<UserEntity>>() {
                    }.getType();
                    cache.putList("profileList", userEntities, type, ConstantsCache.PROFILE_LIST_CACHE);
                });
    }

    @Override public Single<ProfileEntity> getProfile(String profileId) {
        return apiService.getProfile(profileId)
                .map(getProfileResponseEntity -> {
                    if (getProfileResponseEntity != null && getProfileResponseEntity.size() > 0) {
                        return getProfileResponseEntity.get(0);
                    } else
                        throw new FetchDataException("");
                })
                // Save to Cache
                .doOnSuccess(profileEntity -> {
                    cache.put(profileEntity.getId(), profileEntity, ProfileEntity.class, ConstantsCache.PROFILE_CACHE);
                });
    }

    @Override public String getToken() {
        // No Op on Remote
        return "";
    }
}
