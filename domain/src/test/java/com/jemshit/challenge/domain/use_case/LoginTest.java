package com.jemshit.challenge.domain.use_case;

import com.jemshit.challenge.domain.ContentValidator;
import com.jemshit.challenge.domain.exception.ParameterEmptyException;
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

public class LoginTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private Login login;
    @Mock Repository repository;

    @Before
    public void setUp() throws Exception {
        login = new Login(repository, new ContentValidator());
    }

    @Test
    public void execute_shouldReturnErrorOnEmptyUsername() throws Exception {
        // Assign
        TestObserver<Boolean> testObserver = new TestObserver<>();

        // Act
        login.execute("", "notEmpty")
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoValues();
        testObserver.assertError(ParameterEmptyException.class);
    }

    @Test
    public void execute_shouldReturnErrorOnEmptyPassword() throws Exception {
        // Assign
        TestObserver<Boolean> testObserver = new TestObserver<>();

        // Act
        login.execute("notEmpty", "")
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoValues();
        testObserver.assertError(ParameterEmptyException.class);
    }

    @Test
    public void execute_shouldInvokeRepository() throws Exception {
        // Assign
        Mockito.when(repository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(true));

        // Act
        login.execute("notEmpty", "notEmpty")
                .subscribe();

        // Assert
        Mockito.verify(repository).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }

}