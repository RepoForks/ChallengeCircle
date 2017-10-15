package com.jemshit.challenge.data.datasource;

import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;

import java.util.List;

import rx.Single;

public interface DataSource {

    public Single<LoginResponseEntity> login(String username, String password);

    public Single<List<UserEntity>> getProfileList(String token);

    public Single<ProfileEntity> getProfile(String profileId);

    public String getToken();
}
