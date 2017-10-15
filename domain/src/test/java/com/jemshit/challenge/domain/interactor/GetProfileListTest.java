package com.jemshit.challenge.domain.interactor;

import com.jemshit.challenge.domain.repository.Repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import rx.Single;

public class GetProfileListTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private GetProfileList getProfileList;
    @Mock Repository repository;

    @Before
    public void setUp() throws Exception {
        getProfileList = new GetProfileList(repository);
    }

    @Test
    public void execute_shouldInvokeRepository() throws Exception {
        // Assign
        Mockito.when(repository.getToken())
                .thenReturn("");
        Mockito.when(repository.getProfileList(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(Collections.emptyList()));

        // Act
        getProfileList.execute()
                .subscribe();

        // Assert
        Mockito.verify(repository).getToken();
        Mockito.verify(repository).getProfileList(ArgumentMatchers.anyString());
    }
}