package com.jemshit.challenge.data.datasource;

import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    Single<LoginResponseEntity> login(String username, String password);

    Single<List<UserEntity>> getProfileList(String token);

    Single<ProfileEntity> getProfile(String profileId);

    String getToken();
}
