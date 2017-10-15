package com.jemshit.challenge.data.repository;

import com.jemshit.challenge.data.datasource.DataSourceFactory;
import com.jemshit.challenge.data.entity.mapper.ProfileEntityMapper;
import com.jemshit.challenge.data.entity.mapper.UserEntityMapper;
import com.jemshit.challenge.domain.model.ProfileModel;
import com.jemshit.challenge.domain.model.UserModel;
import com.jemshit.challenge.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

@Singleton
public class DataRepository implements Repository {

    private DataSourceFactory dataSourceFactory;
    private UserEntityMapper userEntityMapper;
    private ProfileEntityMapper profileEntityMapper;

    @Inject
    public DataRepository(DataSourceFactory dataSourceFactory, UserEntityMapper userEntityMapper, ProfileEntityMapper profileEntityMapper) {
        this.dataSourceFactory = dataSourceFactory;
        this.userEntityMapper = userEntityMapper;
        this.profileEntityMapper = profileEntityMapper;
    }

    @Override public Single<Boolean> login(String username, String password) {
        return dataSourceFactory.getRemoteDataSource().login(username, password)
                .map(loginResponseEntity -> loginResponseEntity != null);
    }

    @Override public Single<List<UserModel>> getProfileList(String token) {
        return dataSourceFactory.getDataSourceForProfileList().getProfileList(token)
                .map(userEntities -> userEntityMapper.transform(userEntities));
    }

    @Override public Single<ProfileModel> getProfile(String profileId) {
        return dataSourceFactory.getDataSourceForProfile(profileId).getProfile(profileId)
                .map(profileEntity -> profileEntityMapper.transform(profileEntity));
    }

    @Override public String getToken() {
        return dataSourceFactory.getLocalDataSource().getToken();
    }
}
