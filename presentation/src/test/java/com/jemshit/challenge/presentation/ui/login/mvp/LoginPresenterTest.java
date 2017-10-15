package com.jemshit.challenge.presentation.ui.login.mvp;

import com.jemshit.challenge.domain.interactor.Login;

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

public class LoginPresenterTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private LoginPresenter presenter;
    @Mock private LoginContract.View view;
    @Mock private Login loginUseCase;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(loginUseCase, Schedulers.immediate(), Schedulers.immediate());
        presenter.attachView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.destroy();
    }

    @Test
    public void login_successShouldInvokeView() {
        // Assign
        Mockito.when(loginUseCase.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(true));

        // Act
        presenter.login("", "");

        // Assert
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).onLoginSuccess();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void login_failShouldInvokeView() {
        // Assign
        Mockito.when(loginUseCase.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(false));

        // Act
        presenter.login("", "");

        // Assert
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).onLoginError(ArgumentMatchers.anyString());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void login_errorShouldInvokeView() {
        // Assign
        Mockito.when(loginUseCase.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.error(new Throwable()));

        // Act
        presenter.login("", "");

        // Assert
        Mockito.verify(view).showLoading();
        Mockito.verify(view, Mockito.times(0)).onLoginSuccess();
        Mockito.verify(view).onLoginError(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void login_shouldNotInvokeViewAfterDetach() {
        // Assign
        presenter.detachView();
        Mockito.when(loginUseCase.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(true));

        // Act
        presenter.login("", "");

        // Assert
        Mockito.verifyZeroInteractions(view);
    }

}