package com.jemshit.challenge.domain.repository;

import com.jemshit.challenge.domain.model.ProfileModel;
import com.jemshit.challenge.domain.model.UserModel;

import java.util.List;

import rx.Single;

public interface Repository {

    Single<Boolean> login(String username, String password);

    Single<List<UserModel>> getProfileList(String token);

    Single<ProfileModel> getProfile(String profileId);

    String getToken();

}
