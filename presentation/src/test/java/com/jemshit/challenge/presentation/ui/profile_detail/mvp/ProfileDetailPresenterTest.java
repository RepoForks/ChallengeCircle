package com.jemshit.challenge.presentation.ui.profile_detail.mvp;

import com.jemshit.challenge.domain.interactor.GetProfileById;
import com.jemshit.challenge.domain.model.ProfileModel;

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

import rx.Single;
import rx.schedulers.Schedulers;

public class ProfileDetailPresenterTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private ProfileDetailPresenter presenter;
    @Mock private ProfileDetailContract.View view;
    @Mock private GetProfileById getProfileById;

    @Before
    public void setUp() throws Exception {
        presenter = new ProfileDetailPresenter(getProfileById, Schedulers.immediate(), Schedulers.immediate());
        presenter.attachView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.destroy();
    }

    @Test
    public void getProfileDetail_successShouldInvokeView() {
        // Assign
        Mockito.when(getProfileById.execute(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(new ProfileModel()));

        // Act
        presenter.getProfileDetail("");

        // Assert
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).showProfile(ArgumentMatchers.any(ProfileModel.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getProfileDetail_errorShouldInvokeView() {
        // Assign
        Mockito.when(getProfileById.execute(ArgumentMatchers.anyString()))
                .thenReturn(Single.error(new Throwable()));

        // Act
        presenter.getProfileDetail("");

        // Assert
        Mockito.verify(view).showLoading();
        Mockito.verify(view, Mockito.times(0)).showProfile(ArgumentMatchers.any(ProfileModel.class));
        Mockito.verify(view).showError(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void getProfileDetail_shouldNotInvokeViewAfterDetach() {
        // Assign
        presenter.detachView();
        Mockito.when(getProfileById.execute(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(new ProfileModel()));

        // Act
        presenter.getProfileDetail("");

        // Assert
        Mockito.verifyZeroInteractions(view);
    }

}