package com.jemshit.challenge.domain.interactor;

import com.jemshit.challenge.domain.ContentValidator;
import com.jemshit.challenge.domain.exception.ParameterEmptyException;
import com.jemshit.challenge.domain.model.ProfileModel;
import com.jemshit.challenge.domain.repository.Repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

public class GetProfileByIdTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private GetProfileById getProfileById;
    @Mock Repository repository;

    @Before
    public void setUp() throws Exception {
        getProfileById = new GetProfileById(repository, new ContentValidator());
    }

    @Test
    public void execute_shouldReturnErrorOnEmptyProfileId() throws Exception {
        // Assign
        TestObserver<ProfileModel> testObserver = new TestObserver<>();

        // Act
        getProfileById.execute("")
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoValues();
        testObserver.assertError(ParameterEmptyException.class);
    }

    @Test
    public void execute_shouldInvokeRepository() throws Exception {
        // Assign
        Mockito.when(repository.getProfile(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(new ProfileModel()));

        // Act
        getProfileById.execute("notEmpty")
                .subscribe();

        // Assert
        Mockito.verify(repository).getProfile(ArgumentMatchers.anyString());
    }
}