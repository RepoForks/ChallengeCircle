package com.jemshit.challenge.domain.interactor;

import com.jemshit.challenge.domain.ContentValidator;
import com.jemshit.challenge.domain.exception.ParameterEmptyException;
import com.jemshit.challenge.domain.model.ProfileModel;
import com.jemshit.challenge.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GetProfileById {

    private Repository repository;
    private ContentValidator contentValidator;

    @Inject
    public GetProfileById(Repository repository, ContentValidator contentValidator) {
        this.repository = repository;
        this.contentValidator = contentValidator;
    }

    public Single<ProfileModel> execute(String profileId) {
        if (contentValidator.isEmptyString(profileId))
            return Single.error(new ParameterEmptyException());

        return repository.getProfile(profileId);
    }
}
