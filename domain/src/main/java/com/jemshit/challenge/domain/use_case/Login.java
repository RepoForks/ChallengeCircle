package com.jemshit.challenge.domain.use_case;

import com.jemshit.challenge.domain.ContentValidator;
import com.jemshit.challenge.domain.exception.ParameterEmptyException;
import com.jemshit.challenge.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class Login {

    private final Repository repository;
    private final ContentValidator contentValidator;

    @Inject
    public Login(Repository repository, ContentValidator contentValidator) {
        this.repository = repository;
        this.contentValidator = contentValidator;
    }

    public Single<Boolean> execute(final String username, final String password) {
        if (contentValidator.isEmptyString(username) || contentValidator.isEmptyString(password))
            return Single.error(new ParameterEmptyException());

        return repository.login(username, password);
    }
}
