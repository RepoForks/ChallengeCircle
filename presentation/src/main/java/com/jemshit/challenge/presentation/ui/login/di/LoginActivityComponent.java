package com.jemshit.challenge.presentation.ui.login.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.login.LoginActivity;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginContract;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginPresenter;
import com.trevjonez.inject.PlainComponent;
import com.trevjonez.inject.activity.ActivityComponentBuilder;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        LoginActivityComponent.BindsModule.class
})
public interface LoginActivityComponent extends PlainComponent<LoginActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<LoginActivity, LoginActivityComponent> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract LoginContract.Presenter providePresenter(LoginPresenter p);
    }
}