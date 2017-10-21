package com.jemshit.challenge.presentation.ui.login.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginContract;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginActivityBindsModule {
    @Binds @ActivityScope
    abstract LoginContract.Presenter providePresenter(LoginPresenter p);
}