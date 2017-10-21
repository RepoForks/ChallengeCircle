package com.jemshit.challenge.presentation.ui.login.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.login.LoginActivity;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginContract;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = LoginActivitySubComponent.BindsModule.class)
public interface LoginActivitySubComponent extends AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract LoginContract.Presenter providePresenter(LoginPresenter p);
    }
}