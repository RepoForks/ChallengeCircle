package com.jemshit.challenge.domain.use_case;

import com.jemshit.challenge.domain.model.UserModel;
import com.jemshit.challenge.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GetProfileList {

    private final Repository repository;

    @Inject
    public GetProfileList(Repository repository) {
        this.repository = repository;
    }

    public Single<List<UserModel>> execute() {
        return repository.getProfileList(repository.getToken());
    }
}
