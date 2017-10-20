package com.jemshit.challenge.presentation.ui.profile_list.mvp;

import com.jemshit.challenge.domain.interactor.GetProfileList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class ProfileListPresenterTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private ProfileListPresenter presenter;
    @Mock private ProfileListContract.View view;
    @Mock private GetProfileList getProfileList;

    @Before
    public void setUp() throws Exception {
        presenter = new ProfileListPresenter(getProfileList, Schedulers.trampoline(), Schedulers.trampoline());
        presenter.attachView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.destroy();
    }

    @Test
    public void getProfileDetail_successShouldInvokeView() {
        // Assign
        Mockito.when(getProfileList.execute())
                .thenReturn(Single.just(Collections.emptyList()));

        // Act
        presenter.getProfileList();

        // Assert
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).showProfileList(ArgumentMatchers.anyList());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getProfileDetail_errorShouldInvokeView() {
        // Assign
        Mockito.when(getProfileList.execute())
                .thenReturn(Single.error(new Throwable()));

        // Act
        presenter.getProfileList();

        // Assert
        Mockito.verify(view).showLoading();
        Mockito.verify(view, Mockito.times(0)).showProfileList(ArgumentMatchers.anyList());
        Mockito.verify(view).showError(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void getProfileDetail_shouldNotInvokeViewAfterDetach() {
        // Assign
        presenter.detachView();
        Mockito.when(getProfileList.execute())
                .thenReturn(Single.just(Collections.emptyList()));

        // Act
        presenter.getProfileList();

        // Assert
        Mockito.verifyZeroInteractions(view);
    }

}