package com.jemshit.challenge.presentation.ui.login.mvp;

import com.jemshit.challenge.domain.interactor.Login;
import com.jemshit.challenge.presentation.R;
import com.jemshit.challenge.presentation.provider.ResourceProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private Login loginUseCase;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;
    private Disposable disposable;
    private ResourceProvider resourceProvider;

    @Inject
    public LoginPresenter(Login loginUseCase, @Named("IoWorkScheduler") Scheduler subscribeOnScheduler,
                          @Named("MainScheduler") Scheduler observeOnScheduler,
                          @Named("AndroidResourceProvider") ResourceProvider resourceProvider) {
        this.loginUseCase = loginUseCase;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        this.resourceProvider = resourceProvider;
    }

    @Override
    public void login(String username, String password) {
        disposable = loginUseCase.execute(username, password)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnSubscribe(disposable1 -> {
                    if (isViewAttached()) view.showLoading();
                })
                .subscribe(
                        isSuccess -> {
                            if (isViewAttached()) {
                                if (isSuccess)
                                    view.onLoginSuccess();
                                else
                                    view.onLoginError("");
                            }
                        },
                        throwable -> {
                            if (isViewAttached()) view.onLoginError(resourceProvider.getString(R.string.error_global));
                        }
                );
    }


    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    private boolean isViewAttached() {
        return this.view != null;
    }

    @Override
    public void destroy() {
        detachView();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
